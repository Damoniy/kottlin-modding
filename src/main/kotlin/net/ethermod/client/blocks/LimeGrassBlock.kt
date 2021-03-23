package net.ethermod.client.blocks

import net.minecraft.block.Block

class LimeGrassBlock (p: Properties, blockName: String): Block(p) {
    init{
        this.setRegistryName("ether", blockName)
    }
}