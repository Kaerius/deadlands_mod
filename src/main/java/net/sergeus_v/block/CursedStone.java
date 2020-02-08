package net.sergeus_v.block;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tools.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Material;

	public class  CursedStone extends Block
	{

		public CursedStone()
	    
	        {
	        super(FabricBlockSettings.of(Material.STONE).breakByHand(false).breakByTool(FabricToolTags.PICKAXES).strength(3,0.3f).build());

	    }
	}