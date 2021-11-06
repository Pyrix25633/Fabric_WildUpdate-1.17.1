package net.pyrix25633.wild_update.block.custom;

import net.minecraft.block.BlockState;
import net.minecraft.block.GlowLichenBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;

import java.util.Random;

public class VeinBlock extends GlowLichenBlock {
    public VeinBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected boolean canSpread(BlockState state, BlockView world, BlockPos pos, Direction from) {
        return false;
    }

    @Override
    protected void dropExperience(ServerWorld world, BlockPos pos, int size) {
        super.dropExperience(world, pos, 4);
    }

    @Override
    public boolean trySpreadRandomly(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        return false;
    }
}
