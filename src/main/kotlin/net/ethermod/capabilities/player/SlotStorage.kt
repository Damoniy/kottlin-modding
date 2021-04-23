package net.ethermod.capabilities.player

import net.ethermod.capabilities.interfaces.IAccessorySlot
import net.minecraft.nbt.CompoundNBT
import net.minecraft.nbt.INBT
import net.minecraft.util.Direction
import net.minecraftforge.common.capabilities.Capability

class SlotStorage(): Capability.IStorage<IAccessorySlot> {
    override fun writeNBT(capability: Capability<IAccessorySlot>, instance: IAccessorySlot, side: Direction): INBT {
        return instance.serializeNBT()
    }

    override fun readNBT(capability: Capability<IAccessorySlot>, instance: IAccessorySlot, side: Direction, nbt: INBT) {
        if (nbt is CompoundNBT) {
            instance.deserializeNBT(nbt as CompoundNBT?)
        }
    }
}