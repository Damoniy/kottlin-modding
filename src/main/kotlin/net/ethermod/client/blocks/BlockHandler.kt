package net.ethermod.client.blocks

import net.ethermod.client.blocks.EnumBlockType
import net.ethermod.utils.annotations.Item
import net.minecraft.block.Block
import net.minecraft.item.ItemGroup
import net.minecraftforge.registries.IForgeRegistry
import kotlin.reflect.full.hasAnnotation


object BlockHandler {

    @JvmStatic val blue_dirt = BlueDirtBlock(EnumBlockType.GROUND, "blue_dirt")
    @JvmStatic val BLOCKS: ArrayList<Block> = arrayListOf(blue_dirt)
    @JvmStatic fun processBlocks(r: IForgeRegistry<Block>){ BLOCKS.forEach{r.register(it); if(it::class.hasAnnotation<Item>()) createItemBlock(it) } }

    private fun createItemBlock(b: Block){
        val itemBlock = (b as AbstractDamoniyBlock).generateItemForBlock()
    }
}