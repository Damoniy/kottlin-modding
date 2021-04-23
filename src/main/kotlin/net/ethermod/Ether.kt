package net.ethermod

import com.google.common.collect.ImmutableList
import com.mojang.datafixers.util.Pair
import net.ethermod.capabilities.CapabilityHandler
import net.ethermod.client.blocks.BlockHandler
import net.ethermod.client.items.ItemHandler
import net.ethermod.utils.annotations.Logger
import net.minecraft.block.Block
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.inventory.EquipmentSlotType
import net.minecraft.inventory.container.PlayerContainer
import net.minecraft.inventory.container.Slot
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.NonNullList
import net.minecraft.util.ResourceLocation
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.event.entity.EntityJoinWorldEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent

@Mod(Ether.MODID)
object Ether {
    const val MODID = "ether"

    @Mod.EventBusSubscriber(modid = Ether.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
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
                inventory.allInventories = ImmutableList.of(inventory.armorInventory, inventory.mainInventory, inventory.offHandInventory, accessoryInventory)

                container.addSlot(object : Slot(inventory, 41, 77, 44) {
                    override fun getSlotStackLimit(): Int {
                        return 1
                    }

                    override fun isItemValid(p_75214_1_: ItemStack): Boolean {
                        return p_75214_1_.canEquip(EquipmentSlotType.CHEST, player)
                    }

                    override fun canTakeStack(p_82869_1_: PlayerEntity): Boolean {
                        val itemstack = this.stack
                        return if (!itemstack.isEmpty && !p_82869_1_.isCreative && EnchantmentHelper.hasBindingCurse(
                                itemstack
                            )
                        ) false else super.canTakeStack(p_82869_1_)
                    }

                    @OnlyIn(Dist.CLIENT)
                    override fun getBackground(): Pair<ResourceLocation, ResourceLocation>? {
                        return Pair.of(PlayerContainer.LOCATION_BLOCKS_TEXTURE, PlayerContainer.EMPTY_ARMOR_SLOT_SHIELD)
                    }
                })
            }
        }
    }

    fun resourceLocation(msg: String): ResourceLocation {
        return ResourceLocation(MODID, msg)
    }
}

