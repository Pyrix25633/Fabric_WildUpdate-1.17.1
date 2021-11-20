package net.pyrix25633.wild_update.block.custom;

import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.pyrix25633.wild_update.block.ModBlocks;

public class HollowLog extends Block {
    public static final EnumProperty<Direction.Axis> AXIS;
    public static final BooleanProperty MOSSY;

    public HollowLog(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState((BlockState) this.getDefaultState().with(AXIS, Direction.Axis.Y).with(MOSSY, false));
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(AXIS, ctx.getSide().getAxis()).with(MOSSY, false);
    }

    private static final VoxelShape X_1 = Block.createCuboidShape(0,0,0,16,2,16);
    private static final VoxelShape X_2 = Block.createCuboidShape(0,14,0,16,16,16);
    private static final VoxelShape X_3 = Block.createCuboidShape(0,2,0,16,14,2);
    private static final VoxelShape X_4 = Block.createCuboidShape(0,2,14,16,14,16);
    private static final VoxelShape X_MOSS = Block.createCuboidShape(0,2,2,16,3,14);
    private static final VoxelShape X = VoxelShapes.union(X_1, X_2, X_3, X_4);
    private static final VoxelShape X_MOSSY = VoxelShapes.union(X_1, X_2, X_3, X_4, X_MOSS);
    private static final VoxelShape Y_1 = Block.createCuboidShape(0,0,0,16,16,2);
    private static final VoxelShape Y_2 = Block.createCuboidShape(0,0,2,2,16,14);
    private static final VoxelShape Y_3 = Block.createCuboidShape(0,0,14,16,16,16);
    private static final VoxelShape Y_4 = Block.createCuboidShape(14,0,2,16,16,14);
    private static final VoxelShape Y_MOSS = Block.createCuboidShape(2,0,2,14,1,14);
    private static final VoxelShape Y = VoxelShapes.union(Y_1, Y_2, Y_3, Y_4);
    private static final VoxelShape Y_MOSSY = VoxelShapes.union(Y_1, Y_2, Y_3, Y_4, Y_MOSS);
    private static final VoxelShape Z_1 = Block.createCuboidShape(0,0,0,16,2,16);
    private static final VoxelShape Z_2 = Block.createCuboidShape(0,14,0,16,16,16);
    private static final VoxelShape Z_3 = Block.createCuboidShape(0,2,0,2,14,16);
    private static final VoxelShape Z_4 = Block.createCuboidShape(14,2,0,16,14,16);
    private static final VoxelShape Z_MOSS = Block.createCuboidShape(2,2,0,14,3,16);
    private static final VoxelShape Z = VoxelShapes.union(Z_1, Z_2, Z_3, Z_4);
    private static final VoxelShape Z_MOSSY = VoxelShapes.union(Z_1, Z_2, Z_3, Z_4, Z_MOSS);

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        VoxelShape toReturn;
        if(state.get(AXIS) == Direction.Axis.X) {
            if(state.get(MOSSY)) {
                toReturn = X_MOSSY;
            }
            else {
                toReturn = X;
            }
        }
        else if(state.get(AXIS) == Direction.Axis.Z) {
            if(state.get(MOSSY)) {
                toReturn = Z_MOSSY;
            }
            else {
                toReturn = Z;
            }
        }
        else {
            if(state.get(MOSSY)) {
                toReturn = Y_MOSSY;
            }
            else {
                toReturn = Y;
            }
        }
        return toReturn;
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getCameraCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        VoxelShape toReturn;
        if(state.get(AXIS) == Direction.Axis.X) {
            if(state.get(MOSSY)) {
                toReturn = X_MOSSY;
            }
            else {
                toReturn = X;
            }
        }
        else if(state.get(AXIS) == Direction.Axis.Z) {
            if(state.get(MOSSY)) {
                toReturn = Z_MOSSY;
            }
            else {
                toReturn = Z;
            }
        }
        else {
            if(state.get(MOSSY)) {
                toReturn = Y_MOSSY;
            }
            else {
                toReturn = Y;
            }
        }
        return toReturn;
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        VoxelShape toReturn;
        if(state.get(AXIS) == Direction.Axis.X) {
            if(state.get(MOSSY)) {
                toReturn = X_MOSSY;
            }
            else {
                toReturn = X;
            }
        }
        else if(state.get(AXIS) == Direction.Axis.Z) {
            if(state.get(MOSSY)) {
                toReturn = Z_MOSSY;
            }
            else {
                toReturn = Z;
            }
        }
        else {
            if(state.get(MOSSY)) {
                toReturn = Y_MOSSY;
            }
            else {
                toReturn = Y;
            }
        }
        return toReturn;
    }

    @Override
    @SuppressWarnings("deprecation")
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack playerItem = player.getStackInHand(hand);
        boolean finished = false;
        if(state.isOf(this)) {
            if(playerItem.getItem() == Items.MOSS_CARPET) {
                if(!state.get(MOSSY)) {
                    if(state.isOf(ModBlocks.HOLLOW_BIRCH_LOG)) {
                        world.setBlockState(pos, ModBlocks.HOLLOW_BIRCH_LOG.getDefaultState()
                                .with(AXIS, state.get(AXIS)).with(MOSSY, true));
                        world.playSound(player, player.getX(), player.getY(), player.getZ(),
                                SoundEvents.BLOCK_MOSS_CARPET_PLACE, SoundCategory.NEUTRAL, 1.0f, 1.0f);
                        if(!player.isCreative()) {
                            playerItem.decrement(1);
                        }
                        finished = true;
                    }
                    else if(state.isOf(ModBlocks.STRIPPED_HOLLOW_BIRCH_LOG)) {
                        world.setBlockState(pos, ModBlocks.STRIPPED_HOLLOW_BIRCH_LOG.getDefaultState()
                                .with(AXIS, state.get(AXIS)).with(MOSSY, true));
                        world.playSound(player, player.getX(), player.getY(), player.getZ(),
                                SoundEvents.BLOCK_MOSS_CARPET_PLACE, SoundCategory.NEUTRAL, 1.0f, 1.0f);
                        if(!player.isCreative()) {
                            playerItem.decrement(1);
                        }
                        finished = true;
                    }
                }
            }
            else if(playerItem.getItem() == Items.SHEARS) {
                if(state.get(MOSSY)) {
                    if(state.isOf(ModBlocks.HOLLOW_BIRCH_LOG)) {
                        world.setBlockState(pos, ModBlocks.HOLLOW_BIRCH_LOG.getDefaultState()
                                .with(AXIS, state.get(AXIS)).with(MOSSY, false));
                        world.playSound(player, player.getX(), player.getY(), player.getZ(),
                                SoundEvents.BLOCK_MOSS_CARPET_BREAK, SoundCategory.NEUTRAL, 1.0f, 1.0f);
                        world.addBlockBreakParticles(pos, Blocks.MOSS_CARPET.getDefaultState());
                        dropStack(world, pos, new ItemStack(Items.MOSS_CARPET));
                        playerItem.<PlayerEntity>damage(1, player, (p) -> p.sendToolBreakStatus(hand));
                        finished = true;
                    }
                    else if(state.isOf(ModBlocks.STRIPPED_HOLLOW_BIRCH_LOG)) {
                        world.setBlockState(pos, ModBlocks.STRIPPED_HOLLOW_BIRCH_LOG.getDefaultState()
                                .with(AXIS, state.get(AXIS)).with(MOSSY, false));
                        world.playSound(player, player.getX(), player.getY(), player.getZ(),
                                SoundEvents.BLOCK_MOSS_CARPET_BREAK, SoundCategory.NEUTRAL, 1.0f, 1.0f);
                        world.addBlockBreakParticles(pos, Blocks.MOSS_CARPET.getDefaultState());
                        dropStack(world, pos, new ItemStack(Items.MOSS_CARPET));
                        playerItem.<PlayerEntity>damage(1, player, (p) -> p.sendToolBreakStatus(hand));
                        finished = true;
                    }
                }
            }
            else if(playerItem.getItem() == Items.WOODEN_AXE || playerItem.getItem() == Items.STONE_AXE ||
                    playerItem.getItem() == Items.IRON_AXE || playerItem.getItem() == Items.GOLDEN_AXE ||
                    playerItem.getItem() == Items.DIAMOND_AXE || playerItem.getItem() == Items.NETHERITE_AXE){
                if(state.isOf(ModBlocks.HOLLOW_BIRCH_LOG)) {
                    world.setBlockState(pos, ModBlocks.STRIPPED_HOLLOW_BIRCH_LOG.getDefaultState()
                            .with(AXIS, state.get(AXIS)).with(MOSSY, state.get(MOSSY)));
                    playerItem.<PlayerEntity>damage(1, player, (p) -> p.sendToolBreakStatus(hand));
                    world.playSound(player, player.getX(), player.getY(), player.getZ(),
                            SoundEvents.ITEM_AXE_STRIP, SoundCategory.NEUTRAL, 1.0f, 1.0f);
                    finished = true;
                }
            }
        }

        if(finished){
            return ActionResult.success(true);
        }

        return super.onUse(state, world, pos, player, hand, hit);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(HollowLog.AXIS, MOSSY);
    }

    static {
        AXIS = Properties.AXIS;
        MOSSY = BooleanProperty.of("mossy");
    }
}
