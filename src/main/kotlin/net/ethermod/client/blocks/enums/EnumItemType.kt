package net.ethermod.client.blocks.enums

import net.minecraft.item.Item.Properties
import net.minecraft.item.ItemGroup

enum class EnumItemType(val p: Properties) {
    MISCELLANEOUS(Properties().group(ItemGroup.MISC)),
    BLOCKS(Properties().group(ItemGroup.BUILDING_BLOCKS))
}