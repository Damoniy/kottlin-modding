package net.ethermod.client.blocks

import net.ethermod.client.blocks.enums.EnumBlockType
import net.ethermod.utils.annotations.Item
import net.ethermod.utils.annotations.Translucent
import net.minecraft.block.*
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.shapes.ISelectionContext
import net.minecraft.util.math.shapes.VoxelShape
import net.minecraft.world.IBlockReader
import net.minecraft.world.World
import net.minecraft.world.server.ServerWorld
import net.minecraftforge.common.IForgeShearable
import java.util.*

@Item
@Translucent
class LimeTallGrassBlock(blockType: EnumBlockType, blockName: String): DamoniyBushBlock(blockType.props, blockName), IGrowable, IForgeShearable {
    private val SHAPE = makeCuboidShape(2.0, 0.0, 2.0, 14.0, 13.0, 14.0)

    override fun getShape(state: BlockState?, worldIn: IBlockReader?, pos: BlockPos?, context: ISelectionContext?): VoxelShape? {
        return SHAPE
    }

    override fun canGrow(p0: IBlockReader, p1: BlockPos, p2: BlockState, p3: Boolean): Boolean {
        return true;
    }

    override fun canUseBonemeal(p0: World, p1: Random, p2: BlockPos, p3: BlockState): Boolean {
        return true;
    }

    override fun grow(worldIn: ServerWorld, random: Random, pos: BlockPos, blockState: BlockState) {
        val doublePlantBlock = (if (this === Blocks.FERN) Blocks.LARGE_FERN else Blocks.TALL_GRASS) as DoublePlantBlock
        if (doublePlantBlock.defaultState.isValidPosition(worldIn, pos) && worldIn.isAirBlock(pos.up())) {
            doublePlantBlock.placeAt(worldIn, pos, 2)
        }

    }

    override fun getOffsetType(): OffsetType? {
        return OffsetType.XYZ
    }
}