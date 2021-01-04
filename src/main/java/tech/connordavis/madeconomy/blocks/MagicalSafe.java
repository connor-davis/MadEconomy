package tech.connordavis.madeconomy.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import tech.connordavis.madeconomy.containers.MagicalSafeContainer;
import tech.connordavis.madeconomy.shapes.MagicalSafeShapes;
import tech.connordavis.madeconomy.tileentities.MagicalSafeTileEntity;

import javax.annotation.Nullable;
import java.util.stream.Stream;

public class MagicalSafe extends Block {
    public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;

    public MagicalSafe(Properties properties) {
        super(properties);

        this.setDefaultState(this.getStateContainer().getBaseState().with(FACING, Direction.NORTH));
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        switch (state.get(FACING)) {
            case SOUTH:
                return MagicalSafeShapes.SOUTH;
            case EAST:
                return MagicalSafeShapes.EAST;
            case WEST:
                return MagicalSafeShapes.WEST;
            default:
                return MagicalSafeShapes.NORTH;
        }
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState state, IWorld world, BlockPos pos, Rotation direction) {
        return state.with(FACING, direction.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.toRotation(state.get(FACING)));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isRemote) {
            TileEntity tile = worldIn.getTileEntity(pos);

            if (tile instanceof MagicalSafeTileEntity) {
                NetworkHooks.openGui((ServerPlayerEntity) player, (MagicalSafeTileEntity) tile, pos);
                return ActionResultType.SUCCESS;
            }
        }
        return ActionResultType.FAIL;
    }

    @Override
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()) {
            TileEntity tile = worldIn.getTileEntity(pos);

            if (tile instanceof MagicalSafeTileEntity) {
                InventoryHelper.dropItems(worldIn, pos, ((MagicalSafeTileEntity) tile).getItems());
            }
        }
    }
}
