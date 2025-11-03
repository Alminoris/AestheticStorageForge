package net.alminoris.aestheticstorage.menu;

import net.alminoris.aestheticstorage.block.custom.HalfcupboardBlock;
import net.alminoris.aestheticstorage.block.entity.HalfcupboardBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HalfcupboardMenu extends AbstractContainerMenu {

    private final HalfcupboardBlockEntity blockEntity;
    private final ContainerLevelAccess access;

    public HalfcupboardMenu(int containerId, Inventory playerInventory, HalfcupboardBlockEntity blockEntity)
    {
        super(ModMenus.HALFCUPBOARD_MENU.get(), containerId);
        this.blockEntity = blockEntity;
        this.access = ContainerLevelAccess.create(playerInventory.player.level(), blockEntity.getBlockPos());

        int l = 0;
        for (int i = 1; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                this.addSlot(new Slot(blockEntity, l++, 53 + i * 18, 27 + j * 18));
            }
        }

        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);
    }

    // Client-side constructor (Forge syncs BlockPos automatically)
    public HalfcupboardMenu(int containerId, Inventory playerInventory, FriendlyByteBuf friendlyByteBuf)
    {
        this(containerId, playerInventory, (HalfcupboardBlockEntity) playerInventory.player.level().getBlockEntity(friendlyByteBuf.readBlockPos()));
    }

    @Override
    public boolean stillValid(Player player) {
        return blockEntity != null && blockEntity.stillValid(player);
    }

    @Override
    public void removed(Player player)
    {
        super.removed(player);

        if (player.level().isClientSide) return;

        Level world = player.level();
        BlockPos startPos = blockEntity.getBlockPos();
        BlockState startState = world.getBlockState(startPos);

        if (!(startState.getBlock() instanceof HalfcupboardBlock)) return;

        Direction facing = startState.getValue(HalfcupboardBlock.FACING);

        List<BlockPos> stack = new ArrayList<>();
        Set<BlockPos> visited = new HashSet<>();
        stack.add(startPos);

        while (!stack.isEmpty())
        {
            BlockPos currentPos = stack.removeLast();
            if (!visited.add(currentPos)) continue;

            BlockState currentState = world.getBlockState(currentPos);

            if (currentState.getBlock() instanceof HalfcupboardBlock &&
                    currentState.getValue(HalfcupboardBlock.FACING) == facing)
            {

                HalfcupboardBlock.Variant currentVariant = currentState.getValue(HalfcupboardBlock.VARIANT);

                if (currentVariant == HalfcupboardBlock.Variant.UP || currentVariant == HalfcupboardBlock.Variant.DOWN || currentVariant == HalfcupboardBlock.Variant.CENTER)
                {
                    if (currentState.getValue(HalfcupboardBlock.OPEN))
                    {
                        world.setBlock(currentPos, currentState.setValue(HalfcupboardBlock.OPEN, false), 3);
                    }
                    stack.add(currentPos.above());
                    stack.add(currentPos.below());
                }
                else if (currentVariant == HalfcupboardBlock.Variant.NORMAL)
                {
                    if (currentState.getValue(HalfcupboardBlock.OPEN))
                    {
                        world.setBlock(currentPos, currentState.setValue(HalfcupboardBlock.OPEN, false), 3);
                    }
                }
            }
        }
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);

        if (slot != null && slot.hasItem()) {
            ItemStack original = slot.getItem();
            newStack = original.copy();

            int containerSlots = this.blockEntity.getContainerSize();
            int totalSlots = this.slots.size();

            if (index < containerSlots) {
                if (!this.moveItemStackTo(original, containerSlots, totalSlots, true)) {
                    return ItemStack.EMPTY;
                }
            } else {
                if (!this.moveItemStackTo(original, 0, containerSlots, false)) {
                    return ItemStack.EMPTY;
                }
            }

            if (original.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }

        return newStack;
    }

    private void addPlayerInventory(Inventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }
}
