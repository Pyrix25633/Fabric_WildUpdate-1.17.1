package net.pyrix25633.wild_update.block.custom;

import net.minecraft.block.BlockState;
import net.minecraft.block.GlowLichenBlock;
import net.minecraft.block.Waterloggable;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class VeinBlock extends GlowLichenBlock implements Waterloggable {

    public VeinBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected boolean canSpread(BlockState state, BlockView world, BlockPos pos, Direction from) {
        return false;
    }

    @Override
    public boolean trySpreadRandomly(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        return false;
    }

    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, BlockEntity blockEntity, ItemStack stack) {
        super.afterBreak(world, player, pos, state, blockEntity, stack);
        if (EnchantmentHelper.getLevel(Enchantments.SILK_TOUCH, stack) == 0) {
            int exp = ThreadLocalRandom.current().nextInt(1, 5);
            this.dropExperience((ServerWorld) world, pos, exp);
        }
    }
}
