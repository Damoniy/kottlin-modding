package net.ethermod.client.blocks

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.RotatedPillarBlock
import net.minecraft.item.BlockItemUseContext
import net.minecraft.state.StateContainer
import net.minecraft.state.properties.BlockStateProperties
import net.minecraft.util.Direction
import net.minecraft.util.Rotation

abstract class PillarBlock(properties: Properties, name: String) : AbstractDamoniyBlock(properties, name) {
    override fun rotate(state: BlockState, rot: Rotation): BlockState {
        return when (rot) {
            Rotation.COUNTERCLOCKWISE_90, Rotation.CLOCKWISE_90 -> when (state.get<Direction.Axis>(RotatedPillarBlock.AXIS) as Direction.Axis) {
                Direction.Axis.X -> state.with(RotatedPillarBlock.AXIS, Direction.Axis.Z)
                Direction.Axis.Z -> state.with(RotatedPillarBlock.AXIS, Direction.Axis.X)
                else -> state
            }
            else -> state
        }
    }

    override fun fillStateContainer(builder: StateContainer.Builder<Block, BlockState>) {
        builder.add(RotatedPillarBlock.AXIS)
    }

    override fun getStateForPlacement(context: BlockItemUseContext): BlockState? {
        return defaultState.with(RotatedPillarBlock.AXIS, context.face.axis)
    }

    companion object {
        val AXIS = BlockStateProperties.AXIS
    }

    init {
        defaultState = defaultState.with(RotatedPillarBlock.AXIS, Direction.Axis.Y)
    }
}