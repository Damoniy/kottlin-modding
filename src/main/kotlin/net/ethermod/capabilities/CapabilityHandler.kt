package net.ethermod.capabilities

import net.ethermod.Ether
import net.ethermod.capabilities.interfaces.IAccessorySlot
import net.ethermod.capabilities.player.SlotStorage
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.CapabilityInject
import net.minecraftforge.common.capabilities.CapabilityManager
import net.minecraftforge.fml.common.Mod.EventBusSubscriber
import java.util.concurrent.Callable


object CapabilityHandler {
    @CapabilityInject(IAccessorySlot::class)
    lateinit var ACCESSORY_SLOT_CAPABILITY: Capability<IAccessorySlot>

    fun register(){
        CapabilityManager.INSTANCE.register(
            IAccessorySlot::class.java, SlotStorage(),
            Callable { null })
    }

    @EventBusSubscriber(modid = Ether.MODID)
    object Registration {
        /*@SubscribeEvent
        fun attachEntityCapabilities(event: AttachCapabilitiesEvent<Entity?>) {
            if (event.getObject() is PlayerEntity) {
                event.addCapability(
                    ResourceLocation(Ether.MODID, "accessory_slot"),
                    SlotProvider(AccessorySlot()))
            }
        }*/
    }
}