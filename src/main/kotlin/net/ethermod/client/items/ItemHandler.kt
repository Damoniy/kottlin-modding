package net.ethermod.client.items

import net.ethermod.client.items.enums.EnumItemType.*
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraftforge.registries.IForgeRegistry

object ItemHandler {

    val purpleGlowstoneDust = AbstractDamoniyItem(MISCELLANEOUS, "purple_glowstone_dust")
    val poisonRing = AbstractDamoniyItem(MAGIC, "poison_ring")

    @JvmStatic val ITEMS: ArrayList<Item> = arrayListOf(purpleGlowstoneDust, poisonRing)
    @JvmStatic fun processItems(r: IForgeRegistry<Item>) { println("FORGE REGISTRY IS IN ITEMS NOW"); ITEMS.forEach { r.register(it)}}

    fun generateItemFromBlock(b: Block): Item {
        val itemBlock = ItemBlock(b, BLOCKS);
            ITEMS.add(itemBlock)
        return itemBlock;
    }
}