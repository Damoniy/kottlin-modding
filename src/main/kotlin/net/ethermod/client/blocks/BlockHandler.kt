package net.ethermod.client.blocks

import net.ethermod.client.blocks.enums.EnumBlockType.GROUND
import net.ethermod.client.blocks.enums.EnumBlockType.GRASS
import net.ethermod.client.blocks.enums.EnumBlockType.WOOD
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

    @JvmStatic val BLOCKS: ArrayList<Block> = arrayListOf(bluedirt_block, limegrass_block)
    @JvmStatic fun processBlocks(r: IForgeRegistry<Block>){ println("FORGE REGISTRY IS IN BLOCKS NOW"); BLOCKS.forEach{r.register(it); if(it::class.hasAnnotation<Item>()) createItemBlock(it) } }

    private fun createItemBlock(b: Block){
        ItemHandler.generateItemForBlock(b)
    }
}