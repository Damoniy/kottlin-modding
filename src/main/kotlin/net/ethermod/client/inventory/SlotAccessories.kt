package net.ethermod.client.inventory

import net.ethermod.Ether
import net.ethermod.client.inventory.enum.SlotType
import net.minecraft.inventory.IInventory
import net.minecraft.inventory.container.PlayerContainer
import net.minecraft.inventory.container.Slot
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation

class SlotAccessories(private val type: SlotType, inventory: IInventory, index: Int, posX: Int, posY: Int): Slot(inventory, index, posX, posY) {

    init {
        this.setBackground(PlayerContainer.LOCATION_BLOCKS_TEXTURE, type.texture)
    }

    override fun getSlotStackLimit(): Int {
        return 1
    }

    override fun isItemValid(stack: ItemStack): Boolean {
        return stack.item == type.validItem
    }
}