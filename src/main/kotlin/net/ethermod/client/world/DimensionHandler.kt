package net.ethermod.client.world

import net.ethermod.Ether
import net.minecraft.util.RegistryKey
import net.minecraft.util.ResourceLocation
import net.minecraft.util.registry.Registry

object DimensionHandler {
    val the_ether = RegistryKey.getOrCreateKey(Registry.DIMENSION_KEY, Ether.resourceLocation("ether_world"))

    fun init(){
        Registry.register(Registry.BIOME_PROVIDER_CODEC, Ether.resourceLocation("nullable_prefix"), null)
        Registry.register(Registry.CHUNK_GENERATOR_CODEC, Ether.resourceLocation("nullable_prefix"), null)
    }
}