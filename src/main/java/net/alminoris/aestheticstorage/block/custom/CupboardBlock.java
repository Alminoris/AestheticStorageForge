package net.alminoris.aestheticstorage.block.custom;

import com.mojang.serialization.MapCodec;
import net.alminoris.aestheticstorage.block.entity.CabinetBlockEntity;
import net.alminoris.aestheticstorage.block.entity.CupboardBlockEntity;
import net.alminoris.aestheticstorage.block.entity.ModBlockEntities;
import net.alminoris.aestheticstorage.menu.CabinetMenu;
import net.alminoris.aestheticstorage.menu.CupboardMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.TagKey;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.*;

public class CupboardBlock extends BaseEntityBlock implements SimpleWaterloggedBlock
{
    public static final int MAX_STACK_HEIGHT = 4;

    private static final VoxelShape SHAPE = Block.box(0, 0, 0, 16, 16, 16);

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty OPEN = BooleanProperty.create("open");
    public static final BooleanProperty FLIPPED = BooleanProperty.create("flipped");
    public static final BooleanProperty MANUAL_FLIPPED = BooleanProperty.create("manual_flipped");
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public enum Variant implements StringRepresentable
    {
        NORMAL("normal"),
        CENTER("center"),
        UP("up"),
        DOWN("down");

        private final String name;
        Variant(String name) { this.name = name; }

        @Override
        public String getSerializedName() { return this.name; }
    }

    public static final EnumProperty<Variant> VARIANT = EnumProperty.create("variant", Variant.class);

    public CupboardBlock(Properties properties)
    {
        super(properties.noOcclusion());
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(VARIANT, Variant.NORMAL)
                .setValue(OPEN, false)
                .setValue(FLIPPED, false)
                .setValue(MANUAL_FLIPPED, false)
                .setValue(WATERLOGGED, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        builder.add(FACING, WATERLOGGED, VARIANT, OPEN, FLIPPED, MANUAL_FLIPPED);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx)
    {
        return SHAPE;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx)
    {
        return SHAPE;
    }

    @Override
    public VoxelShape getVisualShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx)
    {
        return SHAPE;
    }

    @Override
    public RenderShape getRenderShape(BlockState state)
    {
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state)
    {
        return new CupboardBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx)
    {
        Level level = ctx.getLevel();
        BlockPos pos = ctx.getClickedPos();
        Direction facing = ctx.getHorizontalDirection();
        boolean waterlogged = level.getFluidState(pos).getType() == Fluids.WATER;

        int countUp = countConnectedCupboards(level, pos.above(), facing, Direction.UP);
        int countDown = countConnectedCupboards(level, pos.below(), facing, Direction.DOWN);
        int totalHeight = countUp + countDown + 1;

        if (totalHeight > MAX_STACK_HEIGHT)
            return null;

        return this.defaultBlockState()
                .setValue(FACING, facing)
                .setValue(WATERLOGGED, waterlogged);
    }

    private int countConnectedCupboards(Level level, BlockPos pos, Direction facing, Direction dir)
    {
        int count = 0;
        while (count < MAX_STACK_HEIGHT)
        {
            BlockState state = level.getBlockState(pos);
            if (state.getBlock() == this && state.getValue(FACING) == facing)
            {
                count++;
                pos = pos.relative(dir);
            }
            else break;
        }
        return count;
    }

    @Override
    public BlockState updateShape(BlockState state, Direction dir, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos)
    {
        if (state.getValue(WATERLOGGED))
            level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));

        return updateCupboardVariant(state, level, pos);
    }

    @Override
    public FluidState getFluidState(BlockState state)
    {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean moved)
    {
        if (state.getBlock() != newState.getBlock())
        {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof CupboardBlockEntity cupboard)
            {
                Containers.dropContents(level, pos, cupboard);
                level.updateNeighbourForOutputSignal(pos, this);
            }
            super.onRemove(state, level, pos, newState, moved);
        }
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit)
    {
        ItemStack held = player.getItemInHand(InteractionHand.MAIN_HAND);

        if (!held.isEmpty())
        {
            if (held.is(TagKey.create(ForgeRegistries.ITEMS.getRegistryKey(),
                    ResourceLocation.fromNamespaceAndPath("aestheticseating", "wrench"))))
            {
                boolean newFlipped = !state.getValue(FLIPPED);
                Direction facing = state.getValue(FACING);

                List<BlockPos> stack = new ArrayList<>();
                Set<BlockPos> visited = new HashSet<>();
                stack.add(pos);

                while (!stack.isEmpty())
                {
                    BlockPos current = stack.remove(stack.size()-1);
                    if (!visited.add(current)) continue;

                    BlockState currentState = level.getBlockState(current);
                    if (currentState.getBlock() == this && currentState.getValue(FACING) == facing)
                    {
                        BlockState newState = currentState
                                .setValue(FLIPPED, newFlipped)
                                .setValue(MANUAL_FLIPPED, true);
                        level.setBlock(current, newState, 3);

                        stack.add(current.above());
                        stack.add(current.below());
                    }
                }
                return InteractionResult.SUCCESS;
            }

            return InteractionResult.PASS;
        }

        if (level.isClientSide)
            return InteractionResult.SUCCESS;

        boolean currentOpen = state.getValue(OPEN);

        if (currentOpen) {
            if (!level.isClientSide && player instanceof ServerPlayer serverPlayer) {
                BlockEntity blockEntity = level.getBlockEntity(pos);
                if (blockEntity instanceof CupboardBlockEntity cabinet) {
                    NetworkHooks.openScreen(serverPlayer, cabinet, pos);
                }
            }
        }

        currentOpen = true;
        Direction facing = state.getValue(FACING);

        List<BlockPos> stack = new ArrayList<>();
        Set<BlockPos> visited = new HashSet<>();
        stack.add(pos);

        while (!stack.isEmpty())
        {
            BlockPos current = stack.remove(stack.size()-1);
            if (!visited.add(current)) continue;

            BlockState currentState = level.getBlockState(current);
            if (currentState.getBlock() == this && currentState.getValue(FACING) == facing)
            {
                Variant variant = currentState.getValue(VARIANT);
                if (variant == Variant.UP || variant == Variant.DOWN || variant == Variant.CENTER)
                {
                    level.setBlock(current, currentState.setValue(OPEN, currentOpen), 3);
                    stack.add(current.above());
                    stack.add(current.below());
                }
                else if (variant == Variant.NORMAL)
                {
                    level.setBlock(current, currentState.setValue(OPEN, currentOpen), 3);
                }
            }
        }

        return InteractionResult.SUCCESS;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type)
    {
        return createTickerHelper(type, ModBlockEntities.CUPBOARD_BLOCK_ENTITY.get(),
                (lvl, pos, st, be) -> be.tick(lvl, pos, st));
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving)
    {
        super.onPlace(state, level, pos, oldState, isMoving);
        updateSurroundingVerticalCupboards(level, pos);
    }

    private void updateSurroundingVerticalCupboards(Level level, BlockPos pos)
    {
        for (Direction dir : new Direction[]{Direction.UP, Direction.DOWN})
        {
            BlockPos neighborPos = pos.relative(dir);
            BlockState neighborState = level.getBlockState(neighborPos);
            if (neighborState.getBlock() == this)
                level.setBlock(neighborPos, updateCupboardVariant(neighborState, level, neighborPos), 3);
        }
    }

    private BlockState updateCupboardVariant(BlockState state, LevelAccessor level, BlockPos pos)
    {
        Direction facing = state.getValue(FACING);
        BlockPos up = pos.above();
        BlockPos down = pos.below();

        boolean upConnected = isCupboard(level, up, facing);
        boolean downConnected = isCupboard(level, down, facing);

        Variant variant = Variant.NORMAL;
        if (upConnected && downConnected) variant = Variant.CENTER;
        else if (upConnected) variant = Variant.DOWN;
        else if (downConnected) variant = Variant.UP;

        BlockPos leftPos = pos.relative(facing.getClockWise());
        boolean autoFlipped = isCupboard(level, leftPos, facing);

        boolean manual = state.getValue(MANUAL_FLIPPED);
        boolean flipped = manual ? state.getValue(FLIPPED) : autoFlipped;

        return state.setValue(VARIANT, variant)
                .setValue(FLIPPED, flipped)
                .setValue(MANUAL_FLIPPED, manual);
    }

    private boolean isCupboard(LevelAccessor level, BlockPos pos, Direction facing)
    {
        BlockState state = level.getBlockState(pos);
        return state.getBlock() == this && state.getValue(FACING) == facing;
    }
}
