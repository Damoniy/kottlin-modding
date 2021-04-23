package net.ethermod.common.world.dimension

import net.ethermod.Ether
import net.minecraft.util.RegistryKey
import net.minecraft.util.ResourceLocation
import net.minecraft.util.registry.Registry
import net.minecraft.world.DimensionType
import net.minecraft.world.World

object DimensionHandler {
    val ETHER_DIMENSION: RegistryKey<DimensionType> =
        RegistryKey.getOrCreateKey(Registry.DIMENSION_TYPE_KEY, ResourceLocation(Ether.MODID, "ether"))
}