package tech.connordavis.madeconomy.shapes;

import net.minecraft.block.Block;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;

import java.util.stream.Stream;

public class MagicalSafeShapes {
    public static final VoxelShape NORTH = Stream.of(
            Block.makeCuboidShape(1, 0, 1, 15, 1, 15),
            Block.makeCuboidShape(1, 10, 1, 15, 11, 15),
            Block.makeCuboidShape(1, 1, 1, 2, 10, 15),
            Block.makeCuboidShape(14, 1, 1, 15, 10, 15),
            Block.makeCuboidShape(2, 1, 14, 14, 10, 15),
            Block.makeCuboidShape(2, 1, 2, 14, 10, 3)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();

    public static final VoxelShape EAST = Stream.of(
            Block.makeCuboidShape(1, 0, 1, 15, 1, 15),
            Block.makeCuboidShape(1, 10, 1, 15, 11, 15),
            Block.makeCuboidShape(1, 1, 1, 15, 10, 2),
            Block.makeCuboidShape(1, 1, 14, 15, 10, 15),
            Block.makeCuboidShape(1, 1, 2, 2, 10, 14),
            Block.makeCuboidShape(13, 1, 2, 14, 10, 14)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();

    public static final VoxelShape SOUTH = Stream.of(
            Block.makeCuboidShape(1, 0, 1, 15, 1, 15),
            Block.makeCuboidShape(1, 10, 1, 15, 11, 15),
            Block.makeCuboidShape(14, 1, 1, 15, 10, 15),
            Block.makeCuboidShape(1, 1, 1, 2, 10, 15),
            Block.makeCuboidShape(2, 1, 1, 14, 10, 2),
            Block.makeCuboidShape(2, 1, 13, 14, 10, 14)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();

    public static final VoxelShape WEST = Stream.of(
            Block.makeCuboidShape(1, 0, 1, 15, 1, 15),
            Block.makeCuboidShape(1, 10, 1, 15, 11, 15),
            Block.makeCuboidShape(1, 1, 14, 15, 10, 15),
            Block.makeCuboidShape(1, 1, 1, 15, 10, 2),
            Block.makeCuboidShape(14, 1, 2, 15, 10, 14),
            Block.makeCuboidShape(2, 1, 2, 3, 10, 14)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();
}
