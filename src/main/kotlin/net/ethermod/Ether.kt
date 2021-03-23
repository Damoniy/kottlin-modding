package net.ethermod

import net.ethermod.client.blocks.BlockHandler
import net.minecraft.block.Block
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger


@Mod(Ether.MODID)
object Ether {

    val LOGGER: Logger = LogManager.getLogger()
    const val MODID = "ether"

    @Mod.EventBusSubscriber(modid = Ether.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    object RegistrationHandler {

        @JvmStatic
        @SubscribeEvent
        fun setup(event: FMLCommonSetupEvent) {
            event.description()
        }

        @JvmStatic
        @SubscribeEvent fun registerBlocks(event: RegistryEvent.Register<Block>) {BlockHandler.processBlocks(event.registry)}

    }
}

