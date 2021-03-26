package net.ethermod.client.blocks

import net.ethermod.client.blocks.enums.EnumBlockType
import net.ethermod.utils.annotations.Item

@Item
class DamoniyLogBlock(ebt: EnumBlockType, blockName: String): PillarBlock(ebt.props, blockName) {
}