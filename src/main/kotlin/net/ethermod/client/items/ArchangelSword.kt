package net.ethermod.client.items

import com.google.common.collect.ImmutableMultimap
import com.google.common.collect.Multimap
import net.ethermod.client.items.enums.EnumItemType
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.block.material.Material
import net.minecraft.enchantment.IVanishable
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.ai.attributes.Attribute
import net.minecraft.entity.ai.attributes.AttributeModifier
import net.minecraft.entity.ai.attributes.Attributes
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.inventory.EquipmentSlotType
import net.minecraft.item.IItemTier
import net.minecraft.item.ItemStack
import net.minecraft.item.TieredItem
import net.minecraft.tags.BlockTags
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class ArchangelSword(tier: IItemTier, var damage: Float, attackSpeed: Float, enum: EnumItemType): TieredItem(tier, enum.props), IVanishable{

    private var attributeModifiers: Multimap<Attribute, AttributeModifier>? = null
    val playerClass = ""

    init{
        if(playerClass == "Archangel") damage *= tier.attackDamage
        val builder = ImmutableMultimap.builder<Attribute, AttributeModifier>()
        builder.put(Attributes.ATTACK_DAMAGE, AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", this.damage as Double, AttributeModifier.Operation.ADDITION))
        builder.put(Attributes.ATTACK_SPEED, AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", attackSpeed as Double, AttributeModifier.Operation.ADDITION))
        attributeModifiers = builder.build()
    }

    override fun canPlayerBreakBlockWhileHolding(state: BlockState?, worldIn: World?, pos: BlockPos?, player: PlayerEntity): Boolean {
        return !player.isCreative
    }

    override fun getDestroySpeed(stack: ItemStack?, state: BlockState): Float {
        return if (state.isIn(Blocks.COBWEB)) {
            15.0f
        } else {
            val material = state.material
            if (material != Material.PLANTS && material != Material.TALL_PLANTS && material != Material.CORAL && !state.isIn(BlockTags.LEAVES) && material != Material.GOURD) 1.0f else 1.5f
        }
    }

    override fun hitEntity(stack: ItemStack, target: LivingEntity?, attacker: LivingEntity): Boolean {
        stack.damageItem(0, attacker, { entity: LivingEntity -> entity.sendBreakAnimation(EquipmentSlotType.MAINHAND) })
        return true
    }

    override fun onBlockDestroyed(stack: ItemStack, worldIn: World?, state: BlockState, pos: BlockPos?, entityLiving: LivingEntity): Boolean {
        if (state.getBlockHardness(worldIn, pos) > 0.0f) {
            stack.damageItem(0, entityLiving, { entity: LivingEntity -> entity.sendBreakAnimation(EquipmentSlotType.MAINHAND) })
        }
        return true
    }

    override fun canHarvestBlock(blockIn: BlockState): Boolean {
        return blockIn.isIn(Blocks.COBWEB)
    }

    override fun getAttributeModifiers(equipmentSlot: EquipmentSlotType): Multimap<Attribute, AttributeModifier>? {
        return if (playerClass == "Archangel") attributeModifiers else super.getAttributeModifiers(equipmentSlot)
    }
}