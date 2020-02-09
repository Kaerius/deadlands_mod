package net.sergeus_v.items;

import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Rarity;

public class BloodPie extends Item {
    public BloodPie() {
        super(new Item.Settings().group(ItemGroup.FOOD).rarity(Rarity.COMMON).food(new FoodComponent.Builder().hunger(5).saturationModifier(0.4f).build()));
    }
}
