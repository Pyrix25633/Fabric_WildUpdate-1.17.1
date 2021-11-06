package net.pyrix25633.wild_update.block.custom;

import net.minecraft.block.Block;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

public class ModSculkBlock extends Block {
    public ModSculkBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected void dropExperience(ServerWorld world, BlockPos pos, int size) {
        super.dropExperience(world, pos, 4);
    }
}
