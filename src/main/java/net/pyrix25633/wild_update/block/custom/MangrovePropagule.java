package net.pyrix25633.wild_update.block.custom;

import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.pyrix25633.wild_update.block.ModBlocks;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class MangrovePropagule extends PlantBlock implements Waterloggable, Fertilizable {
    public static final BooleanProperty HANGING;
    public static final BooleanProperty WATERLOGGED;
    public static final BooleanProperty MATURE;

    public MangrovePropagule(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(WATERLOGGED, false).with(HANGING, false)
                .with(MATURE, false));
    }

    protected static Direction attachedDirection(BlockState state) {
        return state.get(HANGING) ? Direction.DOWN : Direction.UP;
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        FluidState fluidstate = ctx.getWorld().getFluidState(ctx.getBlockPos());
        Direction[] placementDirection = ctx.getPlacementDirections();
        int directions = placementDirection.length;
        int i;

        for(i = 0; i < directions; ++i) {
            Direction direction = placementDirection[i];
            if(direction.getAxis() == Direction.Axis.Y) {
                BlockState blockState = this.getDefaultState().with(HANGING, direction == Direction.UP)
                        .with(MATURE, false);
                if(blockState.canPlaceAt(ctx.getWorld(), ctx.getBlockPos())) {
                    return blockState.with(WATERLOGGED, fluidstate.getFluid() == Fluids.WATER);
                }
            }
        }

        return null;
    }

    @Override
    public void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED, HANGING, MATURE);
    }

    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        Direction direction = attachedDirection(state).getOpposite();
        if (direction == Direction.UP) {
            return world.getBlockState(new BlockPos(pos.up())).getMaterial() == Material.LEAVES;
        } else {
            BlockPos blockPos = pos.down();
            return world.getBlockState(new BlockPos(blockPos)).isIn(BlockTags.DIRT);
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public PistonBehavior getPistonBehavior(BlockState state) {
        return PistonBehavior.DESTROY;
    }

    @Override
    @SuppressWarnings("deprecation")
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return false;
    }


    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Vec3d offset = state.getModelOffset(world, pos);
        if (!state.get(HANGING)) {
            return VoxelShapes.union(createCuboidShape(5, 0, 5, 11, 14, 11))
                    .offset(offset.x, offset.y, offset.z);
        } else {
            if(state.get(MATURE)) {
                return VoxelShapes.union(createCuboidShape(6, 3, 6, 10, 16, 10))
                        .offset(offset.x, offset.y, offset.z);
            }
            else {
                return VoxelShapes.union(createCuboidShape(6, 7, 6, 10, 16, 10))
                        .offset(offset.x, offset.y, offset.z);
            }

        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (world.getLightLevel(pos.up()) >= 9) {
            if(!state.get(HANGING)) {
                grow(world, random, pos, state);
            }
            else if(!state.get(MATURE)) {
                world.setBlockState(pos, state.with(MATURE, true));
            }
        }
    }

    @Override
    public boolean isFertilizable(BlockView world, BlockPos pos, BlockState state, boolean isClient) {
        return true;
    }

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return howMuchWater(pos, (ServerWorld) world) < 2;
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        if(random.nextInt(3) == 1) {
            this.generate(world, pos, random);
        }
    }

    /*
     * Function to generate the tree
     */
    public void generate(ServerWorld world, BlockPos pos, Random random) {
        int x = pos.getX(), minX = x - 4, maxX = x + 4;
        int y = pos.getY(), minY = pos.getY() - 2, maxY = y + 13;
        int z = pos.getZ(), minZ = z - 4, maxZ = z + 4;
        int i, j, k, relX, relY, relZ;
        int howMuchWater = howMuchWater(pos, world);
        BlockPos tempPos;
        BlockState toPlace, tempState;
        boolean waterlogged = false;
        int treeType = random.nextInt(4), randHeight = random.nextInt(3);
        if(howMuchWater < 2) {
            //first time: tree log
            for(i = minX; i <= maxX; i++) { //x
                for(j = minZ; j <= maxZ; j++) { //z
                    for(k = minY; k <= maxY; k++) { //y
                        relX = i - x;
                        relY = k - y;
                        relZ = j - z;
                        tempPos = pos.add(relX, relY, relZ);
                        tempState = world.getBlockState(tempPos);
                        if(matchReplaceable(tempState)) {
                            if(tempState.isOf(Blocks.WATER)) {
                                waterlogged = true;
                            }
                            else if(tempState.isOf(ModBlocks.MANGROVE_PROPAGULE)) {
                                if(tempState.get(WATERLOGGED)) {
                                    waterlogged = true;
                                }
                            }
                            else {
                                waterlogged = false;
                            }
                            toPlace = getBlockToPlace(i, j, k, pos, treeType, randHeight, howMuchWater,
                                    waterlogged, random);
                            if(toPlace != Blocks.AIR.getDefaultState()) {
                                world.setBlockState(tempPos, toPlace);
                            }
                        }
                    }
                }
            }
            //second time: decorations such as propagule and vines
            for(i = minX; i <= maxX; i++) { //x
                for(j = minZ; j <= maxZ; j++) { //z
                    for(k = minY; k <= maxY; k++) { //y
                        relX = i - x;
                        relY = k - y;
                        relZ = j - z;
                        tempPos = pos.add(relX, relY, relZ);
                        tempState = world.getBlockState(tempPos);
                        if(matchReplaceable(tempState)) {
                            waterlogged = tempState.isOf(Blocks.WATER);
                            toPlace = getDecorationToPlace(i, j, k, pos, tempPos, randHeight, treeType, waterlogged,
                            world, random);
                            if(toPlace != Blocks.AIR.getDefaultState()) {
                                world.setBlockState(tempPos, toPlace);
                            }
                        }
                    }
                }
            }
        }
    }

    /*
     * Function to know if the block is replaceable
     */
    public boolean matchReplaceable(BlockState state){
        Block[] blocks = {Blocks.VINE, Blocks.AIR, Blocks.ACACIA_LEAVES, Blocks.BIRCH_LEAVES, Blocks.AZALEA_LEAVES,
                Blocks.DARK_OAK_LEAVES, Blocks.OAK_LEAVES, Blocks.FLOWERING_AZALEA_LEAVES, Blocks.JUNGLE_LEAVES,
                Blocks.SPRUCE_LEAVES, Blocks.GRASS, Blocks.SNOW, Blocks.WATER,
                ModBlocks.MANGROVE_LEAVES, ModBlocks.MANGROVE_PROPAGULE, ModBlocks.MANGROVE_ROOTS};
        int i;
        int max = blocks.length;
        for(i = 0; i < max; i++) {
            if(blocks[i] == state.getBlock()) {
                return true;
            }
        }
        return false;
    }

    /*
     * Function to get the block to place
     */
    public BlockState getBlockToPlace(int i, int j, int k, BlockPos pos, int treeType, int randHeight, int howMuchWater,
                                      boolean waterlogged, Random random) {
        int logX = pos.getX();
        int maxLogY = pos.getY() + 6 + randHeight, minLogY = pos.getY() + 1 + randHeight;
        int logZ = pos.getZ();
        if(howMuchWater == 0) {
            if(i == logX && j == logZ && k <= maxLogY) { //log
                return ModBlocks.MANGROVE_LOG.getDefaultState();
            }
            else {
                if(ifPlaceFoliage(i, j, k, logX, maxLogY, logZ, treeType, random)) { //leaves
                    return ModBlocks.MANGROVE_LEAVES.getDefaultState();
                }
            }
        }
        else {
            if(i == logX && j == logZ && k <= maxLogY) {
                if(k > minLogY) { //log
                    return ModBlocks.MANGROVE_LOG.getDefaultState();
                }
                else { //root block under log
                    return ModBlocks.MANGROVE_ROOTS.getDefaultState().with(WATERLOGGED, waterlogged);
                }
            }
            else if(k < minLogY) { //roots
                if(k == minLogY - 1) {
                    if((((i == logX - 1) || (i == logX + 1)) && (j == logZ)) ||
                            (((j == logZ - 1) || (j == logZ + 1)) && (i == logX))) {
                        return ModBlocks.MANGROVE_ROOTS.getDefaultState().with(WATERLOGGED, waterlogged);
                    }
                }
                else {
                    if((((i == logX - 2) || (i == logX + 2)) && (j == logZ)) ||
                            (((j == logZ - 2) || (j == logZ + 2)) && (i == logX))) {
                        return ModBlocks.MANGROVE_ROOTS.getDefaultState().with(WATERLOGGED, waterlogged);
                    }
                }
            }
            else { //leaves
                if(ifPlaceFoliage(i, j, k, logX, maxLogY, logZ, treeType, random)) {
                    return ModBlocks.MANGROVE_LEAVES.getDefaultState();
                }
            }
        }
        return Blocks.AIR.getDefaultState();
    }

    /*
     * Function to get the decoration to place
     */
    public BlockState getDecorationToPlace(int i, int j , int k, BlockPos pos, BlockPos tempPos, int randHeight,
                                           int treeType, boolean waterlogged, ServerWorld world, Random random) {
        int logX = pos.getX();
        int maxLogY = pos.getY() + 6 + randHeight;
        int logZ = pos.getZ();
        if(world.getBlockState(tempPos.up()) == ModBlocks.MANGROVE_LEAVES.getDefaultState()) {
            if(ifPlacePropagule(i, j, k, logX, maxLogY, logZ, treeType, random)) { //propagule
                if(random.nextInt(4) == 1) {
                    return ModBlocks.MANGROVE_PROPAGULE.getDefaultState().with(HANGING, true)
                            .with(MATURE, true).with(WATERLOGGED, waterlogged);
                }
                return ModBlocks.MANGROVE_PROPAGULE.getDefaultState().with(HANGING, true)
                        .with(MATURE, false).with(WATERLOGGED, waterlogged);
            }
        }
        return Blocks.AIR.getDefaultState();
    }

    /*
     * Function to know if place foliage
     */
    public boolean ifPlaceFoliage(int i, int j, int k, int logX, int maxLogY, int logZ, int treeType, Random random) {
        switch(treeType) {
            case 0:
                if((k >= maxLogY - 2 && k < maxLogY + 3) && (i < logX + 3 && i > logX - 2) &&
                        (j < logZ + 2 && j > logZ - 3)) {
                    if((k < maxLogY || k > maxLogY + 1) || (i > logX + 1 || i < logX) || (j > logZ || j < logZ - 1)) {
                        if(random.nextInt(12) == 1) {
                            break;
                        }
                    }
                    return true;
                }
                break;
            case 1:
                if((k >= maxLogY - 1 && k < maxLogY + 2) && (i < logX + 2 && i > logX - 3) &&
                        (j < logZ + 3 && j > logZ - 2)) {
                    if((k < maxLogY + 1 || k > maxLogY) || (i > logX || i < logX - 1) || (j > logZ + 1 || j < logZ)) {
                        if(random.nextInt(12) == 1) {
                            break;
                        }
                    }
                    return true;
                }
                break;
            case 2:
                if((k >= maxLogY - 1 && k < maxLogY + 3) && (i < logX + 4 && i > logX - 2) &&
                        (j < logZ + 2 && j > logZ - 4)) {
                    if((k < maxLogY + 1 || k > maxLogY + 1) || (i > logX + 2 || i < logX) ||
                            (j > logZ || j < logZ - 2)) {
                        if(random.nextInt(12) == 1) {
                            break;
                        }
                    }
                    return true;
                }
                break;
            case 3:
                if((k >= maxLogY - 2 && k < maxLogY + 4) && (i < logX + 2 && i > logX - 4) &&
                        (j < logZ + 4 && j > logZ - 2)) {
                    if((k < maxLogY || k > maxLogY + 2) || (i > logX || i < logX - 2) || (j > logZ + 2 || j < logZ)) {
                        if(random.nextInt(12) == 1) {
                            break;
                        }
                    }
                    return true;
                }
        }
        return false;
    }

    /*
     * Function to know if place propagule
     */
    public boolean ifPlacePropagule(int i, int j, int k, int logX, int maxLogY, int logZ, int treeType, Random random) {
        switch(treeType) {
            case 0:
                if((k >= maxLogY - 3 && k < maxLogY + 2) && (i < logX + 3 && i > logX + 2) &&
                        (j < logZ + 2 && j > logZ - 3)) {
                    if(random.nextInt(10) == 1) {
                        return true;
                    }
                }
                break;
            case 1:
                if((k >= maxLogY - 2 && k < maxLogY + 1) && (i < logX + 2 && i > logX - 3) &&
                        (j < logZ + 3 && j > logZ - 2)) {
                    if(random.nextInt(10) == 1) {
                        return true;
                    }
                }
                break;
            case 2:
                if((k >= maxLogY - 2 && k < maxLogY + 2) && (i < logX + 4 && i > logX - 2) &&
                        (j < logZ + 2 && j > logZ - 4)) {
                    if(random.nextInt(10) == 1) {
                        return true;
                    }
                }
                break;
            case 3:
                if((k >= maxLogY - 3 && k < maxLogY + 3) && (i < logX + 2 && i > logX - 4) &&
                        (j < logZ + 4 && j > logZ - 2)) {
                    if(random.nextInt(10) == 1) {
                        return true;
                    }
                }
        }
        return false;
    }

    /*
     * Function to know how much water there is above it, or if there are some non-replaceable blocks
     */
    public int howMuchWater(BlockPos pos, ServerWorld world) {
        int i;
        int waterBlocks = 0, nonReplaceable = 0;
        for(i = 1; i <= 5; i++) {
            if(world.getBlockState(pos.up(i)).getBlock() == Blocks.WATER) {
                waterBlocks++;
            }
            else if(!matchReplaceable(world.getBlockState(pos.up(i)))) {
                nonReplaceable++;
            }
        }
        if(waterBlocks > 4 || nonReplaceable > 0) {
            return 2; //too much water or non-replaceable blocks above
        }
        else if(waterBlocks != 0) {
            return 1; //some water, it's ok
        }

        return 0; //no water, it's ok too
    }

    static {
        HANGING = BooleanProperty.of("hanging");
        WATERLOGGED = Properties.WATERLOGGED;
        MATURE = BooleanProperty.of("mature");
    }
}
