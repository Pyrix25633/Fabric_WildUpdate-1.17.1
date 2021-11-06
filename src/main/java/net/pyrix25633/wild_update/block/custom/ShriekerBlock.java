package net.pyrix25633.wild_update.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class ShriekerBlock extends Block {
    public ShriekerBlock(Settings settings) {
        super(settings);
    }

    private static final VoxelShape SHRIEKER = Block.createCuboidShape(0,0,0,16,8,16);

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHRIEKER;
    }

    @Override
    public VoxelShape getCameraCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHRIEKER;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHRIEKER;
    }

    @Override
    protected void dropExperience(ServerWorld world, BlockPos pos, int size) {
        super.dropExperience(world, pos, 4);
    }
}
