package net.pyrix25633.wild_update.mixin;

import net.pyrix25633.wild_update.block.ModBlocks;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractBlock.class)
public class AbstractBlockMixin {
    @Inject(at = @At("HEAD"), method = "onUse")
    private void onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit,
                       CallbackInfoReturnable<ActionResult> cir) {
        if (world.getBlockState(hit.getBlockPos()).getBlock() == Blocks.DIRT) {

            ItemStack itemStack = player.getStackInHand(hand);

            if (itemStack.isItemEqual(Items.POTION.getDefaultStack())) {
                if (PotionUtil.getPotion(itemStack) == Potions.WATER) {
                    world.setBlockState(pos, ModBlocks.MUD_BLOCK.getDefaultState());
                    if (!world.isClient) {
                        world.playSound(
                                null,
                                pos,
                                SoundEvents.BLOCK_GRAVEL_BREAK,
                                SoundCategory.BLOCKS,
                                1f,
                                1f
                        );
                        ((ServerWorld) world).spawnParticles(new BlockStateParticleEffect(ParticleTypes.BLOCK,
                                        ModBlocks.MUD_BLOCK.getDefaultState()), hit.getBlockPos().getX() + 0.5,
                                hit.getBlockPos().getY() + 0.5, hit.getBlockPos().getZ() + 0.5,
                                100, 0.2, 0.2, 0.2, 10
                        );
                    }
                    if (player.canModifyBlocks()) {
                        if (!player.isCreative()) {
                            ItemStack removeItem = new ItemStack(Items.GLASS_BOTTLE);
                            player.setStackInHand(hand, removeItem);
                        }
                    }
                }
            }
        }
    }
}