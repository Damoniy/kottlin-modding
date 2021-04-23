package net.ethermod.client.blocks

import net.ethermod.client.blocks.enums.EnumBlockType.*
import net.ethermod.client.items.ItemHandler
import net.ethermod.utils.annotations.Item
import net.ethermod.utils.annotations.Translucent
import net.minecraft.block.Block
import net.minecraft.client.renderer.RenderType
import net.minecraft.client.renderer.RenderTypeLookup
import net.minecraftforge.registries.IForgeRegistry
import kotlin.reflect.full.hasAnnotation

object BlockHandler {
    val blueDirt_block = BlueDirtBlock(GROUND, "blue_dirt")
    val limeGrass_block = LimeGrassBlock(GRASS, "lime_grass")
    val limeTallGrass_block = LimeTallGrassBlock(DAMONIY_GENERIC_PLANTS, "lime_tall_grass")

    val whiteLog_block = DamoniyLogBlock(WOOD, "white_log")
    val limePlanks_block = GenericBlock(PLANKS, "white_planks")

    val etherOakLog_block = DamoniyLogBlock(WOOD, "ether_oak_log")
    val purplePlanks_block = GenericBlock(PLANKS, "purple_planks")

    val purpleGlowstone_block = LampBlock(LAMP, "purple_glowstone")

    private val BLOCKS: ArrayList<Block> = arrayListOf(blueDirt_block, limeGrass_block, whiteLog_block,
    etherOakLog_block, purpleGlowstone_block, limeTallGrass_block, limePlanks_block, purplePlanks_block

    )
    @JvmStatic fun processBlocks(r: IForgeRegistry<Block>){ BLOCKS.forEach{r.register(it); if(it::class.hasAnnotation<Item>()) createItemBlock(it);
        if(it::class.hasAnnotation<Translucent>()) setTranslucent(it)}}

    private fun createItemBlock(b: Block){
        ItemHandler.generateItemFromBlock(b)
    }

    private fun setTranslucent(b: Block){
        RenderTypeLookup.setRenderLayer(b, RenderType.getTranslucent())
    }
}