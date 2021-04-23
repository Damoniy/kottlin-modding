package net.ethermod.client.blocks

import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.block.material.Material
import net.minecraft.item.BlockItemUseContext
import net.minecraft.pathfinding.PathType
import net.minecraft.util.Direction
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockReader
import net.minecraft.world.IWorld
import net.minecraft.world.IWorldReader
import net.minecraftforge.common.IPlantable

abstract class DamoniyBushBlock(props: Properties, blockName: String) : AbstractDamoniyBlock(props, blockName),
    IPlantable {
    protected open fun isValidGround(state: BlockState, worldIn: IBlockReader?, pos: BlockPos?): Boolean {
        return ((state.blockState.material === Material.ORGANIC ||
                state.blockState.material === Material.EARTH) &&
                state.isSolid)
                && !state.isIn(Blocks.HAY_BLOCK) && !state.isIn(Blocks.DRIED_KELP_BLOCK) &&
                !state.isIn(Blocks.TARGET)
    }

    override fun isTransparent(state: BlockState?): Boolean {
        return true
    }

    override fun updatePostPlacement(
        stateIn: BlockState,
        facing: Direction,
        facingState: BlockState,
        worldIn: IWorld,
        currentPos: BlockPos,
        facingPos: BlockPos
    ): BlockState {
        return if (!stateIn.isValidPosition(
                worldIn,
                currentPos
            )
        ) Blocks.AIR.defaultState else super.updatePostPlacement(
            stateIn,
            facing,
            facingState,
            worldIn,
            currentPos,
            facingPos
        )
    }

    override fun isValidPosition(state: BlockState, worldIn: IWorldReader, pos: BlockPos): Boolean {
        val blockpos = pos.down()
        return isValidGround(worldIn.getBlockState(blockpos), worldIn, blockpos)
    }

    override fun propagatesSkylightDown(state: BlockState, reader: IBlockReader, pos: BlockPos): Boolean {
        return state.fluidState.isEmpty
    }

    override fun allowsMovement(state: BlockState, worldIn: IBlockReader, pos: BlockPos, type: PathType): Boolean {
        return true;
    }

    override fun getPlant(world: IBlockReader, pos: BlockPos): BlockState {
        val state = world.getBlockState(pos)
        return if (state.block !== this) defaultState else state
    }

    override fun getStateForPlacement(context: BlockItemUseContext): BlockState? {
        val blockpos = context.pos
        return if (blockpos.y < 255 && context.world.getBlockState(blockpos.up())
                .isReplaceable(context)
        ) super.getStateForPlacement(context) else null
    }
}