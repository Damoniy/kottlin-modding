package net.ethermod.client.inventory.enum

import net.ethermod.Ether
import net.ethermod.client.items.ItemHandler
import net.minecraft.item.Item
import net.minecraft.util.ResourceLocation

enum class SlotType(val validItem: Item, val texture: ResourceLocation, val number: Int) {
    WING(ItemHandler.purpleGlowstoneDust, Ether.resourceLocation("slot/wing"), 1),
    RING(ItemHandler.purpleGlowstoneDust, Ether.resourceLocation("slot/ring"), 2),
    PENDANT(ItemHandler.purpleGlowstoneDust, Ether.resourceLocation("slot/pendant"), 3);
}