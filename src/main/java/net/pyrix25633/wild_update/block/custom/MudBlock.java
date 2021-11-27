package net.pyrix25633.wild_update.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.PointedDripstoneBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.pyrix25633.wild_update.block.ModBlocks;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class MudBlock extends Block {
    public MudBlock(Settings settings) {
        super(settings.ticksRandomly());
    }

    @Override
    @SuppressWarnings("deprecation")
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if(world.getBlockState(pos.down()).getBlock() == Blocks.DRIPSTONE_BLOCK &&
                world.getBlockState(pos.down(2)).getBlock() == Blocks.POINTED_DRIPSTONE &&
                world.getBlockState(pos.down(2)) == Blocks.POINTED_DRIPSTONE.getDefaultState()
                        .with(PointedDripstoneBlock.VERTICAL_DIRECTION, Direction.DOWN)) {
            super.randomTick(state, world, pos, random);
            world.setBlockState(pos, Blocks.CLAY.getDefaultState());
            world.addBlockBreakParticles(pos.up(), Blocks.CLAY.getDefaultState());
            if(!world.isClient) {
                world.playSound(null, pos, SoundEvents.BLOCK_GRAVEL_PLACE, SoundCategory.BLOCKS,
                        1.0f, 1.0f);
                world.spawnParticles(new BlockStateParticleEffect(ParticleTypes.BLOCK, Blocks.CLAY.getDefaultState()),
                        pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5,
                        100, 0.2, 0.2, 0.2, 10
                );
            }
        }
    }
}