package net.ethermod.client.items

import net.minecraft.block.Block
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup

class ItemBlock(block:Block): BlockItem(block, Item.Properties()){}
