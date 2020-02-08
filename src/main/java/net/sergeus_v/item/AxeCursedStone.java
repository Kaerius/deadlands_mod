package net.sergeus_v.item;

import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ToolMaterial;

public class AxeCursedStone extends AxeItem {

	public AxeCursedStone(ToolMaterial toolMaterial_3) {
		super(toolMaterial_3, 2, 5, new Item.Settings().group(ItemGroup.TOOLS));
	}


}
