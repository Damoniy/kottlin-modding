package net.minecraft.block

import net.ethermod.client.blocks.AbstractDamoniyBlock
import net.minecraft.item.BlockItemUseContext
import net.minecraft.state.BooleanProperty
import net.minecraft.state.StateContainer
import net.minecraft.state.properties.BlockStateProperties
import net.minecraft.util.Direction
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IWorld
import java.lang.Boolean

open class SnowyBlueDirt(p: Properties, blockName: String) : AbstractDamoniyBlock(p, blockName) {
    override fun updatePostPlacement(
        stateIn: BlockState,
        facing: Direction,
        facingState: BlockState,
        worldIn: IWorld,
        currentPos: BlockPos,
        facingPos: BlockPos
    ): BlockState {
        return if (facing != Direction.UP) super.updatePostPlacement(
            stateIn,
            facing,
            facingState,
            worldIn,
            currentPos,
            facingPos
        ) else stateIn.with(
            SNOWY, Boolean.valueOf(facingState.isIn(Blocks.SNOW_BLOCK) || facingState.isIn(Blocks.SNOW))
        )
    }

    override fun getStateForPlacement(context: BlockItemUseContext): BlockState? {
        val blockstate = context.world.getBlockState(context.pos.up())
        return defaultState.with(
            SNOWY, Boolean.valueOf(
                blockstate.isIn(Blocks.SNOW_BLOCK) || blockstate.isIn(
                    Blocks.SNOW
                )
            )
        )
    }

    override fun fillStateContainer(builder: StateContainer.Builder<Block, BlockState>) {
        builder.add(SNOWY)
    }

    companion object {
        val SNOWY = BlockStateProperties.SNOWY
    }

    init {
        defaultState = stateContainer.baseState.with(SNOWY, Boolean.valueOf(false))
    }
}