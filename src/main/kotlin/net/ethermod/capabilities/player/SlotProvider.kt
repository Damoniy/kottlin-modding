package net.ethermod.capabilities.player

import net.ethermod.capabilities.CapabilityHandler
import net.ethermod.capabilities.interfaces.IAccessorySlot
import net.minecraft.nbt.CompoundNBT
import net.minecraft.util.Direction
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.ICapabilityProvider
import net.minecraftforge.common.util.INBTSerializable
import net.minecraftforge.common.util.LazyOptional
import net.minecraftforge.common.util.NonNullSupplier

class SlotProvider(private val player: IAccessorySlot) : ICapabilityProvider, INBTSerializable<CompoundNBT> {

    override fun serializeNBT(): CompoundNBT {
        return player.serializeNBT()
    }

    override fun deserializeNBT(nbt: CompoundNBT?) {
        player.deserializeNBT(nbt)
    }

    override fun <T : Any?> getCapability(cap: Capability<T>, side: Direction?): LazyOptional<T> {
        return if (cap === CapabilityHandler.ACCESSORY_SLOT_CAPABILITY ) {
            LazyOptional.of(NonNullSupplier<T> { this.player as T})
        } else LazyOptional.empty<T>()
    }
}