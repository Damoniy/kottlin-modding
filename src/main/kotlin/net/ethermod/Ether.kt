package net.ethermod

import com.google.common.collect.ImmutableList
import net.ethermod.capabilities.CapabilityHandler
import net.ethermod.client.blocks.BlockHandler
import net.ethermod.client.inventory.SlotAccessories
import net.ethermod.client.inventory.enum.SlotType
import net.ethermod.client.items.ItemHandler
import net.ethermod.utils.annotations.Logger
import net.minecraft.block.Block
import net.minecraft.client.gui.screen.inventory.ContainerScreen
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.inventory.container.PlayerContainer
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.NonNullList
import net.minecraft.util.ResourceLocation
import net.minecraftforge.client.event.TextureStitchEvent
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.event.entity.EntityJoinWorldEvent
import net.minecraftforge.event.entity.living.LivingEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent

@Mod(Ether.MODID)
object Ether {
    const val MODID = "ether"

    private val INVENTORY_BACKGROUND = resourceLocation("/textures/gui/container/inventory.png")
    private val accessoryInventory = NonNullList.withSize(3, ItemStack.EMPTY)


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

        @SubscribeEvent
        fun sendTexturesToMinecraftAtlas(event: TextureStitchEvent.Pre){
            if(event.map.textureLocation == PlayerContainer.LOCATION_BLOCKS_TEXTURE){
                for(slot in SlotType.values()){
                    event.addSprite(slot.texture)
                }
            }
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

                ContainerScreen.INVENTORY_BACKGROUND = INVENTORY_BACKGROUND

                inventory.allInventories = ImmutableList.of(inventory.armorInventory, inventory.mainInventory, inventory.offHandInventory, accessoryInventory)

                for(slot in SlotType.values()) {
                    container.addSlot(SlotAccessories(slot, inventory, 40 + slot.number, 77, 44  - (slot.number - 1) * 18))
                }
            }
        }

        @SubscribeEvent
        fun allowAbilities(event: LivingEvent.LivingUpdateEvent){
            if(event.entity is PlayerEntity){
                val player = event.entity as PlayerEntity
                val stack = accessoryInventory[1].stack

                if(stack != null && stack.item == ItemHandler.poisonRing){
                    player.abilities.allowFlying = true
                }
            }
        }
    }

    fun resourceLocation(path: String): ResourceLocation {
        return ResourceLocation(MODID, path)
    }
}

