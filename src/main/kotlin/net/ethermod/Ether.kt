package net.ethermod

import com.google.common.collect.ImmutableList
import net.ethermod.capabilities.CapabilityHandler
import net.ethermod.client.blocks.BlockHandler
import net.ethermod.client.inventory.SlotWing
import net.ethermod.client.items.ItemHandler
import net.ethermod.utils.annotations.Logger
import net.minecraft.block.Block
import net.minecraft.client.gui.screen.inventory.ContainerScreen
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.NonNullList
import net.minecraft.util.ResourceLocation
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.event.entity.EntityJoinWorldEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent

@Mod(Ether.MODID)
object Ether {
    const val MODID = "ether"

    private val INVENTORY_BACKGROUND = resourceLocation("/textures/gui/container/inventory.png")
    private val SLOT_WING = ResourceLocation(MODID,"/textures/gui/container/slot/wing")
    private val SLOT_RING = resourceLocation("/textures/gui/container/ring.png")
    private val SLOT_PENDANT = resourceLocation("item/pendant.png")
    private val SLOT_TEXTURE = arrayOf(SLOT_WING, SLOT_RING, SLOT_PENDANT)

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    object RegistrationHandler {

        init{
            MinecraftForge.EVENT_BUS.register(ForgeEvents)
            Logger.log("Forge Events has been entered the event bus.")
        }

        @SubscribeEvent
        fun init(event: FMLCommonSetupEvent) {

            event.enqueueWork {
                CapabilityHandler.register()
            }
            // DimensionHandler.init()
            event.description()
        }

        @SubscribeEvent fun registerBlocks(e: RegistryEvent.Register<Block>) {BlockHandler.processBlocks(e.registry)}
        @SubscribeEvent fun registerItems(e: RegistryEvent.Register<Item>) {ItemHandler.processItems(e.registry)}
    }

    object ForgeEvents {
        @SubscribeEvent
        fun attachSlotToContainer(event: EntityJoinWorldEvent) {
            if (event.entity is PlayerEntity) {
                val player = event.entity as PlayerEntity
                val container = player.container
                val inventory = player.inventory
                val accessoryInventory = NonNullList.withSize(3, ItemStack.EMPTY)

                ContainerScreen.INVENTORY_BACKGROUND = INVENTORY_BACKGROUND

                inventory.allInventories = ImmutableList.of(inventory.armorInventory, inventory.mainInventory, inventory.offHandInventory, accessoryInventory)

                for(slotNumber in 0..2) {
                    container.addSlot(SlotWing(inventory, 40 + slotNumber, 77, 44  - slotNumber * 18))
                }
            }
        }
    }

    fun resourceLocation(path: String): ResourceLocation {
        return ResourceLocation(MODID, path)
    }
}

