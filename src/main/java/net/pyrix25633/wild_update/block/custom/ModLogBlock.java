package net.pyrix25633.wild_update.block.custom;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.pyrix25633.wild_update.block.ModBlocks;

public class ModLogBlock extends Block {
    public static final EnumProperty<Direction.Axis> AXIS;

    public ModLogBlock (AbstractBlock.Settings settings) {
        super(settings);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockPos pos = ctx.getBlockPos();

        return (BlockState)this.getDefaultState().with(AXIS, ctx.getSide().getAxis());
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack playerItem = player.getStackInHand(hand);
        Boolean finished = false;

        if(state.isOf(this)){
            if ((playerItem.getItem() == Items.WOODEN_AXE || playerItem.getItem() == Items.STONE_AXE || playerItem.getItem() == Items.GOLDEN_AXE || playerItem.getItem() == Items.IRON_AXE || playerItem.getItem() == Items.DIAMOND_AXE || playerItem.getItem() == Items.NETHERITE_AXE)) {
                //Strip Log
                world.playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.ITEM_AXE_STRIP, SoundCategory.NEUTRAL, 1.0f, 1.0f);

                if(state.isOf(ModBlocks.MANGROVE_LOG)){
                    world.setBlockState(pos, ModBlocks.STRIPPED_MANGROVE_LOG.getDefaultState().with(ModLogBlock.AXIS, state.get(ModLogBlock.AXIS)));
                } else if(state.isOf(ModBlocks.MANGROVE_WOOD)) {
                    world.setBlockState(pos, ModBlocks.STRIPPED_MANGROVE_WOOD.getDefaultState().with(ModLogBlock.AXIS, state.get(ModLogBlock.AXIS)));
                }
                playerItem.<PlayerEntity>damage(1, player, (p) -> p.sendToolBreakStatus(hand));

                finished = true;
            }
        }

        if(finished){
            return ActionResult.success(true);
        }

        return super.onUse(state, world, pos, player, hand, hit);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(ModLogBlock.AXIS);
    }

    static {
        AXIS = Properties.AXIS;
    }

}