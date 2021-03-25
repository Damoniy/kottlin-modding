package net.ethermod.client.items

import net.ethermod.client.blocks.enums.EnumItemType
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraftforge.registries.IForgeRegistry

object ItemHandler {

    @JvmStatic val ITEMS: ArrayList<Item> = arrayListOf()
    @JvmStatic fun processItems(r: IForgeRegistry<Item>) { println("FORGE REGISTRY IS IN ITEMS NOW"); ITEMS.forEach { r.register(it)}}

    fun generateItemForBlock(b: Block): Item {
        val itemBlock = ItemBlock(b, EnumItemType.BLOCKS);
            ITEMS.add(itemBlock)
        return itemBlock;
    }
}