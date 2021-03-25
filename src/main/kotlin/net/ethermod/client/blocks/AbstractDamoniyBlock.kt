package net.ethermod.client.blocks

import net.ethermod.Ether
import net.minecraft.block.Block


abstract class AbstractDamoniyBlock (p: Properties, val blockName: String): Block(p) {

    init{
        this.setRegistryName(Ether.MODID, blockName)
    }
}