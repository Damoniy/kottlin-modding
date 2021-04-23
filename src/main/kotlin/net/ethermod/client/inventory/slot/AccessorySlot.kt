package net.ethermod.client.inventory.slot

import net.ethermod.capabilities.interfaces.IAccessorySlot
import net.minecraft.item.ItemStack
import net.minecraft.nbt.CompoundNBT
import net.minecraft.util.NonNullList

class AccessorySlot(): IAccessorySlot{
    private val accessoryInventory: NonNullList<ItemStack> = NonNullList.withSize(3, ItemStack.EMPTY)

    override fun update() {
    }

    override fun serializeNBT(): CompoundNBT {
        val nbt = CompoundNBT()
        for(i in 41..43){
            if(this.accessoryInventory.get(i).isEmpty){
                nbt.putByte("Slot", (i + 200).toByte())
                (this.accessoryInventory.get(i)).write(nbt)
            }
        }
        return nbt
    }

    override fun deserializeNBT(nbt: CompoundNBT?) {
        val i: Int = nbt!!.getInt("Slot") and 255
        val itemstack = ItemStack.read(nbt)
        if (!itemstack.isEmpty) {
            if(i in 41..43){
                this.accessoryInventory.set(i, itemstack)
            }
        }
    }
}