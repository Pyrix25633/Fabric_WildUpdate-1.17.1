package net.pyrix25633.wild_update.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class MudBlock extends Block {
    public MudBlock(Settings settings) {
        super(settings);
    }
    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        int randPoss = ThreadLocalRandom.current().nextInt(0, 11);
        if(world.getBlockState(pos.down()).getBlock() == Blocks.DRIPSTONE_BLOCK &&
                world.getBlockState(pos.down().down()).getBlock() == Blocks.POINTED_DRIPSTONE) {
            if(randPoss == 0) {
                super.randomDisplayTick(state, world, pos, random);
                world.setBlockState(pos, Blocks.CLAY.getDefaultState());
            }
        }

    }
}