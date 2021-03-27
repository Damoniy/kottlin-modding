package net.ethermod.client.blocks

import net.ethermod.client.blocks.enums.EnumBlockType.*
import net.ethermod.client.items.ItemHandler
import net.ethermod.utils.annotations.Item
import net.minecraft.block.Block
import net.minecraftforge.registries.IForgeRegistry
import kotlin.reflect.full.hasAnnotation
import net.minecraft.block.Blocks

object BlockHandler {

    val bluedirt_block = BlueDirtBlock(GROUND, "blue_dirt")
    val limegrass_block = LimeGrassBlock(GRASS, "lime_grass")
    val whitelog_block = DamoniyLogBlock(WOOD, "white_log")
    val etheroaklog_block = DamoniyLogBlock(WOOD, "ether_oak_log")
    val purpleglowstone_block = LampBlock(LAMP, "purple_glowstone")

    @JvmStatic val BLOCKS: ArrayList<Block> = arrayListOf(bluedirt_block, limegrass_block, whitelog_block,
    etheroaklog_block, purpleglowstone_block)
    @JvmStatic fun processBlocks(r: IForgeRegistry<Block>){ println("FORGE REGISTRY IS IN BLOCKS NOW"); BLOCKS.forEach{r.register(it); if(it::class.hasAnnotation<Item>()) createItemBlock(it) } }

    private fun createItemBlock(b: Block){
        ItemHandler.generateItemForBlock(b)
    }
}