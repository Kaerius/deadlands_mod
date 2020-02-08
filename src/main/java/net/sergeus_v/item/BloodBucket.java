package net.sergeus_v.item;

import net.minecraft.item.BucketItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import net.minecraft.util.Rarity;
import net.sergeus_v.fluid.BloodFluid;

public class BloodBucket extends BucketItem {
    public BloodBucket(BloodFluid fluid) {
        super(fluid, new Settings().group(ItemGroup.MISC).recipeRemainder(Items.BUCKET).maxCount(1).rarity(Rarity.COMMON));
    }
}
