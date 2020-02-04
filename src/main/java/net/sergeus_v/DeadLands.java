package net.sergeus_v;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.Identifier;


public class DeadLands implements ModInitializer {


	/*public static final Item DEAD_ITEM = new DeadLandsItem(new Item.Settings().group(ItemGroup.MISC).maxCount(16)); */
	
	public static final Block DEAD_BLOCK = new Block(FabricBlockSettings.of(Material.METAL).build());
	
	public static final DeadLandsItem DEAD_ITEM = new DeadLandsItem(new Item.Settings().group(ItemGroup.MISC));
	
	@Override
	public void onInitialize() {

		Registry.register(Registry.ITEM, new Identifier("deadlands", "dead_item"), DEAD_ITEM);
		

	    		
	    		
	    		Registry.register(Registry.BLOCK, new Identifier("deadlands", "dead_block"), DEAD_BLOCK);
		Registry.register(Registry.ITEM, new Identifier("deadlands", "dead_block"), new BlockItem(DEAD_BLOCK, new Item.Settings().group(ItemGroup.MISC)));
	}
}
