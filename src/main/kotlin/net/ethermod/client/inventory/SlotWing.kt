package net.ethermod.client.inventory

import net.ethermod.Ether
import net.minecraft.inventory.IInventory
import net.minecraft.inventory.container.PlayerContainer
import net.minecraft.inventory.container.Slot

class SlotWing(inventory: IInventory, index: Int, posX: Int, posY: Int): Slot(inventory, index, posX, posY) {
    private val SLOT_WING = Ether.resourceLocation("items/empty_armor_slot_boots")

    init{
        this.setBackground(PlayerContainer.LOCATION_BLOCKS_TEXTURE, PlayerContainer.EMPTY_ARMOR_SLOT_CHESTPLATE)
    }
}