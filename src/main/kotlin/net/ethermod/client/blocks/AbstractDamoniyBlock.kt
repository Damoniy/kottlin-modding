package net.ethermod.client.blocks

import net.ethermod.Ether
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.fluid.Fluid
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.shapes.ISelectionContext
import net.minecraft.util.math.shapes.VoxelShape
import net.minecraft.world.IBlockReader


abstract class AbstractDamoniyBlock (p: Properties, val blockName: String): Block(p) {

    init{
        this.setRegistryName(Ether.MODID, blockName)
    }
}