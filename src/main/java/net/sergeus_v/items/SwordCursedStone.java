package net.sergeus_v.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;

public class SwordCursedStone extends SwordItem {

	public SwordCursedStone(ToolMaterial toolMaterial_3) {
		super(toolMaterial_3, 2, 10, new Item.Settings().group(ItemGroup.COMBAT));

	}
}
