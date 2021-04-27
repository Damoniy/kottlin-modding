package net.ethermod.client.items

import net.ethermod.Ether
import net.ethermod.client.blocks.AbstractDamoniyBlock
import net.ethermod.client.items.enums.EnumItemType
import net.minecraft.block.Block
import net.minecraft.item.BlockItem

class ItemBlock(block:Block, itemType: EnumItemType): BlockItem(block, itemType.p){
    init{
        super.setRegistryName(Ether.MODID, "item_${(block as AbstractDamoniyBlock).blockName}")
    }
}
