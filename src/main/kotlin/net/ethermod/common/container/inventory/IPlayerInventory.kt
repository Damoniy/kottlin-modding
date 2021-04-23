package net.ethermod.common.container.inventory

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.inventory.IInventory
import net.minecraft.item.Item
import net.minecraft.nbt.CompoundNBT

interface IInventoryPlayer: IInventory {

    val player: PlayerEntity;

    fun hasAnAccessoryInSlot(item: Item?): Boolean

    fun writeToNBT(compound: CompoundNBT?)

    fun readFromNBT(compound: CompoundNBT?)

}