package net.ethermod.client.blocks

import net.minecraft.block.*
import net.minecraft.block.material.Material
import net.minecraft.block.material.MaterialColor
import net.minecraft.block.AbstractBlock.Properties

enum class EnumBlockType(val props: Properties){
    GROUND(Properties.create(Material.EARTH, MaterialColor.DIRT).sound(SoundType.GROUND).hardnessAndResistance(0.5F)),
    GRASS(Properties.create(Material.ORGANIC, MaterialColor.GRASS).sound(SoundType.PLANT).hardnessAndResistance(0.6F).tickRandomly()),
    ROCK(Properties.create(Material.ROCK, MaterialColor.STONE).sound(SoundType.STONE).hardnessAndResistance(1.5F, 6F)),
    HARDER_ROCK(Properties.create(Material.ROCK, MaterialColor.STONE).sound(SoundType.STONE).hardnessAndResistance(2.5F, 8F)),
    PLANKS(Properties.create(Material.WOOD, MaterialColor.WOOD).sound(SoundType.WOOD).hardnessAndResistance(3.0F, 3.0F)),
    WOOD(Properties.create(Material.WOOD, MaterialColor.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2.5F, 8F)),
    PORTAL(Properties.create(Material.PORTAL, MaterialColor.AIR).doesNotBlockMovement().tickRandomly().sound(SoundType.GLASS).hardnessAndResistance(-1.0F)),
    FOLIAGE(Properties.create(Material.LEAVES, MaterialColor.FOLIAGE).sound(SoundType.PLANT).hardnessAndResistance(0.2F).tickRandomly()),
    LAMP(Properties.create(Material.GLASS, MaterialColor.AIR).sound(SoundType.GLASS).hardnessAndResistance(0.7F).setLightLevel{state: BlockState -> 14}),
    GLASS(Properties.create(Material.GLASS).hardnessAndResistance(0.3F).sound(SoundType.GLASS).notSolid())
}