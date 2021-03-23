package net.ethermod.client.blocks

import net.ethermod.Ether
import net.ethermod.client.items.ItemBlock
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.item.Items

abstract class AbstractDamoniyBlock (p: Properties, blockName: String): Block(p) {

    init{
        this.setRegistryName(Ether.MODID, blockName)
    }

    fun generateItemForBlock(): Item{
        val ib = ItemBlock(this)
        return ib;
    }
}