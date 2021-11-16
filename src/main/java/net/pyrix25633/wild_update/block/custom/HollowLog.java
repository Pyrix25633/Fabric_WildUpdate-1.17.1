package net.pyrix25633.wild_update.block.custom;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class HollowLog extends Block {
    public static final EnumProperty<Direction.Axis> AXIS;

    public HollowLog(AbstractBlock.Settings settings) {
        super(settings);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(AXIS, ctx.getSide().getAxis());
    }

    private static final VoxelShape X_1 = Block.createCuboidShape(0,0,0,16,2,16);
    private static final VoxelShape X_2 = Block.createCuboidShape(0,14,0,16,16,16);
    private static final VoxelShape X_3 = Block.createCuboidShape(0,2,0,16,14,2);
    private static final VoxelShape X_4 = Block.createCuboidShape(0,2,14,16,14,16);
    private static final VoxelShape X = VoxelShapes.union(X_1, X_2, X_3, X_4);
    private static final VoxelShape Y_1 = Block.createCuboidShape(0,0,0,16,16,2);
    private static final VoxelShape Y_2 = Block.createCuboidShape(0,0,2,2,16,14);
    private static final VoxelShape Y_3 = Block.createCuboidShape(0,0,14,16,16,16);
    private static final VoxelShape Y_4 = Block.createCuboidShape(14,0,2,16,16,14);
    private static final VoxelShape Y = VoxelShapes.union(Y_1, Y_2, Y_3, Y_4);
    private static final VoxelShape Z_1 = Block.createCuboidShape(0,0,0,16,2,16);
    private static final VoxelShape Z_2 = Block.createCuboidShape(0,14,0,16,16,16);
    private static final VoxelShape Z_3 = Block.createCuboidShape(0,2,0,2,14,16);
    private static final VoxelShape Z_4 = Block.createCuboidShape(14,2,0,16,14,16);
    private static final VoxelShape Z = VoxelShapes.union(Z_1, Z_2, Z_3, Z_4);

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        VoxelShape toReturn;
        if(state.get(AXIS) == Direction.Axis.X) {
            toReturn =  X;
        }
        else if(state.get(AXIS) == Direction.Axis.Z) {
            toReturn = Z;
        }
        else {
            toReturn = Y;
        }
        return toReturn;
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getCameraCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        VoxelShape toReturn;
        if(AXIS.getValues() == Direction.Axis.X) {
            toReturn =  X;
        }
        else if(AXIS.getValues() == Direction.Axis.Z) {
            toReturn = Z;
        }
        else {
            toReturn = Y;
        }
        return toReturn;
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        VoxelShape toReturn;
        if(AXIS.getValues() == Direction.Axis.X) {
            toReturn =  X;
        }
        else if(AXIS.getValues() == Direction.Axis.Z) {
            toReturn = Z;
        }
        else {
            toReturn = Y;
        }
        return toReturn;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(HollowLog.AXIS);
    }

    static {
        AXIS = Properties.AXIS;
    }
}
