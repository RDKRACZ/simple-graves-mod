package com.speechrezz.simple_gravestones.registry;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.StateManager;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class GravestoneBlock extends Block implements BlockEntityProvider {

    public static final DirectionProperty FACING; // = DirectionProperty.of("gravestone_block", Direction.SOUTH);

    //public static final BooleanProperty WATERLOGGED;
    protected static final VoxelShape GRAVE_SHAPE_NORTH;
    protected static final VoxelShape GRAVE_SHAPE_EAST;
    protected static final VoxelShape GRAVE_SHAPE_SOUTH;
    protected static final VoxelShape GRAVE_SHAPE_WEST;

    protected GravestoneBlock(Settings settings) {
        super(settings);
        this.setDefaultState((BlockState)((BlockState)this.stateManager.getDefaultState()).with(FACING, Direction.SOUTH));
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
        switch((Direction)state.get(FACING)) {
            case NORTH:
                return GRAVE_SHAPE_NORTH;
            case EAST:
                return GRAVE_SHAPE_EAST;
            case WEST:
                return GRAVE_SHAPE_WEST;
            default:
                return GRAVE_SHAPE_SOUTH;
        }
    }

    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return true;
    }

    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return (BlockState)this.getDefaultState().with(FACING, ctx.getPlayerFacing().getOpposite());
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        player.addExperience(((GravestoneBlockEntity) world.getBlockEntity(pos)).getExperience());
        System.out.println("DEBUG - onBreak exp: " + ((GravestoneBlockEntity)world.getBlockEntity(pos)).getExperience() + ", blockPos: " + pos.toShortString());
        super.onBreak(world, pos, state, player);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        //System.out.println("DEBUG - onUse exp: " + ((GravestoneBlockEntity) world.getBlockEntity(pos)).getExperience() + ", blockPos: " + pos.toShortString());
        return super.onUse(state, world, pos, player, hand, hit);
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    static {
        //WATERLOGGED = Properties.WATERLOGGED;
        GRAVE_SHAPE_SOUTH = Block.createCuboidShape(1.0D, 0.0D, 2.0D, 15.0D, 16.0D, 4.0D);
        GRAVE_SHAPE_NORTH = Block.createCuboidShape(1.0D, 0.0D, 12.0D, 15.0D, 16.0D, 14.0D);

        GRAVE_SHAPE_EAST = Block.createCuboidShape(2.0D, 0.0D, 1.0D, 4.0D, 16.0D, 15.0D);
        GRAVE_SHAPE_WEST = Block.createCuboidShape(12.0D, 0.0D, 1.0D, 14.0D, 16.0D, 15.0D);

        FACING = HorizontalFacingBlock.FACING;

    }


    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof GravestoneBlockEntity) {
                ItemScatterer.spawn(world, pos, (GravestoneBlockEntity) blockEntity);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new GravestoneBlockEntity(pos, state);
    }
}
