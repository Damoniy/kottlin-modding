package net.ethermod.client.items

import net.ethermod.Ether
import net.ethermod.client.blocks.enums.EnumItemType
import net.minecraft.item.Item

class AbstractDamoniyItem(enum: EnumItemType, itemName: String): Item(enum.p){
    init{
        super.setRegistryName(Ether.MODID, "item_$itemName")
    }
}