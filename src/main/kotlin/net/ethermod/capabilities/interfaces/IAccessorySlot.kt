package net.ethermod.capabilities.interfaces

import net.ethermod.capabilities.CapabilityHandler
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.nbt.CompoundNBT
import net.minecraftforge.common.util.INBTSerializable
import net.minecraftforge.common.util.LazyOptional

interface IAccessorySlot: INBTSerializable<CompoundNBT> {

    operator fun get(player: PlayerEntity): LazyOptional<IAccessorySlot?>? {
        return player.getCapability(CapabilityHandler.ACCESSORY_SLOT_CAPABILITY)
    }

    fun update()
}