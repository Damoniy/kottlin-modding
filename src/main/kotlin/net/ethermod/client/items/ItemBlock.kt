package net.ethermod.client.items

import net.ethermod.Ether
import net.ethermod.client.blocks.AbstractDamoniyBlock
import net.ethermod.client.blocks.enums.EnumItemType
import net.minecraft.block.Block
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup

class ItemBlock(block:Block, itemType: EnumItemType): BlockItem(block, itemType.p){
    init{
        super.setRegistryName(Ether.MODID, "item_${(block as AbstractDamoniyBlock).blockName}")
    }
}
