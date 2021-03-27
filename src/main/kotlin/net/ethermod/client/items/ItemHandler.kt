package net.ethermod.client.items

import net.ethermod.client.blocks.enums.EnumItemType.*
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraftforge.registries.IForgeRegistry
import net.minecraft.item.Items
object ItemHandler {

    val purpleGlowstoneDust = AbstractDamoniyItem(MISCELLANEOUS, "purple_glowstone_dust")

    @JvmStatic val ITEMS: ArrayList<Item> = arrayListOf(purpleGlowstoneDust)
    @JvmStatic fun processItems(r: IForgeRegistry<Item>) { println("FORGE REGISTRY IS IN ITEMS NOW"); ITEMS.forEach { r.register(it)}}

    fun generateItemForBlock(b: Block): Item {
        val itemBlock = ItemBlock(b, BLOCKS);
            ITEMS.add(itemBlock)
        return itemBlock;
    }
}