package net.ethermod.client.inventory

import net.minecraft.nbt.INBT
import net.minecraft.util.Direction
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.CapabilityInject
import net.minecraftforge.common.capabilities.ICapabilitySerializable
import net.minecraftforge.common.util.LazyOptional
import net.minecraftforge.items.IItemHandler

object SlotProvider: ICapabilitySerializable<INBT> {

    @CapabilityInject(IItemHandler::class)
    var ITEM_HANDLER_CAPABILITY: Capability<IItemHandler>? = null
    var inventoryHandlerLazyOptional: LazyOptional<IItemHandler>? = null

    override fun <T : Any?> getCapability(cap: Capability<T>, side: Direction?): LazyOptional<T> {
        return if (cap == ITEM_HANDLER_CAPABILITY) inventoryHandlerLazyOptional!!.cast() else this.getCapability(cap, side)
    }

    override fun serializeNBT(): INBT {
        TODO("Not yet implemented")
    }

    override fun deserializeNBT(nbt: INBT?) {
        TODO("Not yet implemented")
    }
}