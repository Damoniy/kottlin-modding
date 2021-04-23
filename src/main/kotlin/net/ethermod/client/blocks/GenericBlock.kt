package net.ethermod.client.blocks

import net.ethermod.client.blocks.enums.EnumBlockType
import net.ethermod.utils.annotations.Item

@Item
class GenericBlock(ebt: EnumBlockType, blockName: String): AbstractDamoniyBlock(ebt.props, blockName) {
}