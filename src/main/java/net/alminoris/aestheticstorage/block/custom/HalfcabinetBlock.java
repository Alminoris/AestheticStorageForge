package net.alminoris.aestheticstorage.block.custom;

import com.mojang.serialization.MapCodec;
import net.alminoris.aestheticstorage.block.entity.CabinetBlockEntity;
import net.alminoris.aestheticstorage.block.entity.CupboardBlockEntity;
import net.alminoris.aestheticstorage.block.entity.HalfcabinetBlockEntity;
import net.alminoris.aestheticstorage.block.entity.ModBlockEntities;
import net.alminoris.aestheticstorage.menu.CupboardMenu;
import net.alminoris.aestheticstorage.menu.HalfcabinetMenu;
import net.alminoris.aestheticstorage.util.helper.VoxelShapeHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.TagKey;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class HalfcabinetBlock extends BaseEntityBlock implements SimpleWaterloggedBlock
{
    private static final VoxelShape SHAPE = HalfcabinetBlock.box(0D, 0D, 0D, 16D, 16D, 7D);

    public final boolean HAS_FLIP;

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty OPEN = BooleanProperty.create("open");
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final EnumProperty<Variant> VARIANT = EnumProperty.create("variant", Variant.class);

    public enum Variant implements StringRepresentable
    {
        NORMAL("normal"),
        LEFT("left"),
        RIGHT("right");

        private final String name;
        Variant(String name) { this.name = name; }
        @Override
        public String getSerializedName() { return this.name; }
    }

    public HalfcabinetBlock(Properties properties, boolean hasFlip)
    {
        super(properties.noOcclusion());
        this.HAS_FLIP = hasFlip;

        this.registerDefaultState(
                this.stateDefinition.any()
                        .setValue(FACING, Direction.NORTH)
                        .setValue(VARIANT, Variant.NORMAL)
                        .setValue(OPEN, false)
                        .setValue(WATERLOGGED, false)
        );
    }

    private VoxelShape getRotatedShape(BlockState state)
    {
        Direction direction = state.getValue(FACING);

        List<AABB> boxes = new ArrayList<>();
        boxes.add(SHAPE.bounds());

        return VoxelShapeHelper.rotateShape(boxes, direction);
    }

    @Override
    public VoxelShape getShape(BlockState p_60555_, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_)
    {
        return getRotatedShape(p_60555_);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        builder.add(FACING, VARIANT, OPEN, WATERLOGGED);
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
        return new HalfcabinetBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type)
    {
        return createTickerHelper(type, ModBlockEntities.HALFCABINET_BLOCK_ENTITY.get(),
                (world, pos, blockState, entity) -> entity.tick(world, pos, blockState));
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx)
    {
        boolean waterlogged = ctx.getLevel().getFluidState(ctx.getClickedPos()).getType() == Fluids.WATER;
        return this.defaultBlockState()
                .setValue(FACING, ctx.getHorizontalDirection())
                .setValue(WATERLOGGED, waterlogged);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction dir, BlockState neighbor, LevelAccessor level, BlockPos pos, BlockPos neighborPos)
    {
        if (state.getValue(WATERLOGGED))
        {
            level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }

        return updateCabinetVariant(state, level, pos);
    }

    @Override
    public FluidState getFluidState(BlockState state)
    {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean moved)
    {
        if (!state.is(newState.getBlock()))
        {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof HalfcabinetBlockEntity cabinet)
            {
                Containers.dropContents(level, pos, cabinet);
                level.updateNeighbourForOutputSignal(pos, this);
            }
            super.onRemove(state, level, pos, newState, moved);
        }
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos,
                                 Player player, InteractionHand hand, BlockHitResult hit)
    {
        if (!player.getItemInHand(InteractionHand.MAIN_HAND).isEmpty())
        {
            if (player.getItemInHand(InteractionHand.MAIN_HAND).is(TagKey.create(net.minecraft.core.registries.Registries.ITEM,
                    ResourceLocation.fromNamespaceAndPath("aestheticseating", "wrench"))))
            {
                Variant variant = state.getValue(VARIANT);

                if (HAS_FLIP)
                {
                    level.setBlock(pos, state.setValue(VARIANT, Variant.NORMAL), 3);
                }
                else
                {
                    if (variant == Variant.LEFT)
                        level.setBlock(pos, state.setValue(VARIANT, Variant.RIGHT), 3);
                    else
                        level.setBlock(pos, state.setValue(VARIANT, Variant.LEFT), 3);
                }

                return InteractionResult.SUCCESS;
            }

            return InteractionResult.PASS;
        }

        if (!level.isClientSide)
        {
            boolean open = state.getValue(OPEN);
            Variant variant = state.getValue(VARIANT);
            Direction facing = state.getValue(FACING);

            MenuProvider provider = (MenuProvider) level.getBlockEntity(pos);

            if (open) {
                if (!level.isClientSide && player instanceof ServerPlayer serverPlayer) {
                    BlockEntity blockEntity = level.getBlockEntity(pos);
                    if (blockEntity instanceof HalfcabinetBlockEntity cabinet) {
                        NetworkHooks.openScreen(serverPlayer, cabinet, pos);
                    }
                }
            }

            open = !open;

            if (HAS_FLIP)
            {
                boolean isRight = variant == Variant.RIGHT;
                boolean isLeft = variant == Variant.LEFT;

                BlockPos otherPos = isRight
                        ? pos.relative(facing.getCounterClockWise())
                        : isLeft
                        ? pos.relative(facing.getClockWise())
                        : pos;

                BlockState otherState = level.getBlockState(otherPos);

                if (otherState.getBlock() == this &&
                        otherState.getValue(FACING) == facing &&
                        otherState.getValue(VARIANT) != variant)
                {
                    level.setBlock(otherPos, otherState.setValue(OPEN, open), 3);
                }

                level.setBlock(pos, state.setValue(OPEN, open), 3);
            }
            else
            {
                level.setBlock(pos, state.setValue(OPEN, open), 3);
            }

            return InteractionResult.SUCCESS;
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean notify)
    {
        super.onPlace(state, level, pos, oldState, notify);
        updateSurroundingCabinets(level, pos);
    }

    private void updateSurroundingCabinets(Level level, BlockPos pos)
    {
        for (Direction dir : Direction.Plane.HORIZONTAL)
        {
            BlockPos neighborPos = pos.relative(dir);
            BlockState neighborState = level.getBlockState(neighborPos);

            if (neighborState.getBlock() == this)
                level.setBlock(neighborPos, updateCabinetVariant(neighborState, level, neighborPos), 3);
        }
    }

    private BlockState updateCabinetVariant(BlockState state, LevelAccessor world, BlockPos pos)
    {
        Direction facing = state.getValue(FACING);
        BlockPos leftPos = pos.relative(facing.getCounterClockWise());
        BlockPos rightPos = pos.relative(facing.getClockWise());

        boolean leftConnected = isCabinet(world, leftPos, facing);
        boolean rightConnected = isCabinet(world, rightPos, facing);

        if (leftConnected)
            return state.setValue(VARIANT, Variant.RIGHT);
        else if (rightConnected)
            return state.setValue(VARIANT, Variant.LEFT);
        else
            return state.setValue(VARIANT, Variant.NORMAL);
    }

    private boolean isCabinet(LevelAccessor world, BlockPos pos, Direction facing)
    {
        BlockState state = world.getBlockState(pos);
        return state.getBlock() == this && state.getValue(FACING) == facing;
    }
}
