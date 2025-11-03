package net.alminoris.aestheticstorage.menu;

import net.alminoris.aestheticstorage.block.custom.HalfcabinetBlock;
import net.alminoris.aestheticstorage.block.entity.HalfcabinetBlockEntity;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

public class HalfcabinetMenu extends AbstractContainerMenu {

    private final HalfcabinetBlockEntity blockEntity;
    private final ContainerLevelAccess access;

    public HalfcabinetMenu(int containerId, Inventory playerInventory, HalfcabinetBlockEntity blockEntity)
    {
        super(ModMenus.HALFCABINET_MENU.get(), containerId);
        this.blockEntity = blockEntity;
        this.access = ContainerLevelAccess.create(playerInventory.player.getLevel(), blockEntity.getBlockPos());

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
    public HalfcabinetMenu(int containerId, Inventory playerInventory, FriendlyByteBuf friendlyByteBuf)
    {
        this(containerId, playerInventory, (HalfcabinetBlockEntity) playerInventory.player.getLevel().getBlockEntity(friendlyByteBuf.readBlockPos()));
    }

    @Override
    public boolean stillValid(Player player) {
        return blockEntity != null && blockEntity.stillValid(player);
    }

    @Override
    public void removed(Player player) {
        super.removed(player);
        if (!player.getLevel().isClientSide) {
            BlockState state = player.getLevel().getBlockState(blockEntity.getBlockPos());
            if (state.hasProperty(HalfcabinetBlock.OPEN)) {
                player.getLevel().setBlock(blockEntity.getBlockPos(), state.setValue(HalfcabinetBlock.OPEN, false), 3);
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
