package net.pyrix25633.wild_update.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

import java.util.Random;

public class MudBlock extends Block {
    public MudBlock(Settings settings) {
        super(settings.ticksRandomly());
    }

    @Override
    @SuppressWarnings("deprecation")
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if(world.getBlockState(pos.down()).getBlock() == Blocks.DRIPSTONE_BLOCK &&
                world.getBlockState(pos.down(2)).getBlock() == Blocks.POINTED_DRIPSTONE) {
            super.randomTick(state, world, pos, random);
            world.setBlockState(pos, Blocks.CLAY.getDefaultState());
            world.addBlockBreakParticles(pos.up(), Blocks.CLAY.getDefaultState());
        }
    }
}