package net.ethermod.client.blocks

import net.ethermod.client.blocks.enums.EnumBlockType
import net.ethermod.utils.annotations.Item
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.block.IGrowable
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockReader
import net.minecraft.world.World
import net.minecraft.world.server.ServerWorld
import java.util.*

@Item
class LimeGrassBlock(ebt: EnumBlockType, blockName: String): SnowyLimeBlock(ebt.props, blockName), IGrowable {

    override fun canGrow(worldIn: IBlockReader, pos: BlockPos, state: BlockState, isClient: Boolean): Boolean {
        return worldIn.getBlockState(pos.up()).isAir()
    }

    override fun canUseBonemeal(worldIn: World, rand: Random, pos: BlockPos, state: BlockState): Boolean {
        return true
    }

    override fun grow(worldIn: ServerWorld, rand: Random, pos: BlockPos, state: BlockState?) {
        val blockpos = pos.up()
        val blockstate = BlockHandler.limegrass_block.defaultState
        label48@ for (i in 0..127) {
            var blockpos1 = blockpos
            for (j in 0 until i / 16) {
                blockpos1 =
                    blockpos1.add(rand.nextInt(3) - 1, (rand.nextInt(3) - 1) * rand.nextInt(3) / 2, rand.nextInt(3) - 1)
                if (!worldIn.getBlockState(blockpos1.down()).isIn(this) || worldIn.getBlockState(blockpos1)
                        .hasOpaqueCollisionShape(worldIn, blockpos1)
                ) {
                    continue@label48
                }
            }
            val blockstate2 = worldIn.getBlockState(blockpos1)
            if (blockstate2.isIn(blockstate.block) && rand.nextInt(10) == 0) {
                (blockstate.block as IGrowable).grow(worldIn, rand, blockpos1, blockstate2)
            }
            if (blockstate2.isAir) {
                var blockstate1: BlockState
                blockstate1 =  blockstate
                }
                if (blockstate.isValidPosition(worldIn, blockpos1)) {
                    worldIn.setBlockState(blockpos1, blockstate, 3)
                }
        }
    }
}