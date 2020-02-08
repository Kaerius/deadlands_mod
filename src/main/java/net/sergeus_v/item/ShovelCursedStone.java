package net.sergeus_v.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.ToolMaterial;

public class ShovelCursedStone extends ShovelItem {

	public ShovelCursedStone(ToolMaterial toolMaterial_1) {
		super(toolMaterial_1, -2, -3.0f, new Item.Settings().group(ItemGroup.TOOLS));
	}
}