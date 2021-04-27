package net.ethermod.client.items.enums

import net.minecraft.item.Item.Properties
import net.minecraft.item.ItemGroup

enum class EnumItemType(val props: Properties) {
    MAGIC(Properties().group(ItemGroup.BREWING)),
    MISCELLANEOUS(Properties().group(ItemGroup.MISC)),
    BLOCKS(Properties().group(ItemGroup.BUILDING_BLOCKS))
}