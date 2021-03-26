package net.ethermod.client.blocks

import net.minecraft.block.*
import net.minecraft.tags.FluidTags
import net.minecraft.util.Direction
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IWorldReader
import net.minecraft.world.lighting.LightEngine
import net.minecraft.world.server.ServerWorld
import java.util.*

abstract class SnowyLimeBlock(p: Properties, blockName: String): SnowyBlueDirt(p, blockName) {

    private fun isSnowyConditions(state: BlockState, worldReader: IWorldReader, pos: BlockPos): Boolean {
        val blockpos = pos.up()
        val blockstate = worldReader.getBlockState(blockpos)
        return if (blockstate.isIn(Blocks.SNOW) && blockstate.get(SnowBlock.LAYERS) == 1) {
            true
        } else if (blockstate.fluidState.level == 8) {
            false
        } else {
            val i = LightEngine.func_215613_a(
                worldReader,
                state,
                pos,
                blockstate,
                blockpos,
                Direction.UP,
                blockstate.getOpacity(worldReader, blockpos)
            )
            i < worldReader.maxLightLevel
        }
    }

    private fun isSnowyAndNotUnderwater(state: BlockState, worldReader: IWorldReader, pos: BlockPos): Boolean {
        val blockpos = pos.up()
        return isSnowyConditions(state, worldReader, pos) && !worldReader.getFluidState(blockpos)
            .isTagged(FluidTags.WATER)
    }

    override fun randomTick(state: BlockState, worldIn: ServerWorld, pos: BlockPos, random: Random) {
        if (!isSnowyConditions(state, worldIn, pos)) {
            if (!worldIn.isAreaLoaded(
                    pos,
                    3
                )
            ) return  // Forge: prevent loading unloaded chunks when checking neighbor's light and spreading
            worldIn.setBlockState(pos, Blocks.DIRT.defaultState)
        } else {
            if (worldIn.getLight(pos.up()) >= 9) {
                val blockstate = defaultState
                for (i in 0..3) {
                    val blockpos = pos.add(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1)
                    if (worldIn.getBlockState(blockpos).isIn(Blocks.DIRT) && isSnowyAndNotUnderwater(
                            blockstate,
                            worldIn,
                            blockpos
                        )
                    ) {
                        worldIn.setBlockState(
                            blockpos, blockstate.with(
                                SNOWY, java.lang.Boolean.valueOf(
                                    worldIn.getBlockState(blockpos.up()).isIn(
                                        Blocks.SNOW
                                    )
                                )
                            )
                        )
                    }
                }
            }
        }
    }
}