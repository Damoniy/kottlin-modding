package net.ethermod.client.items

import net.ethermod.client.items.enums.EnumItemType
import net.minecraft.item.Item

abstract class ItemBase(type: EnumItemType, itemName: String):  Item(type.props){
    init{
        this.setRegistryName(itemName)
    }
}