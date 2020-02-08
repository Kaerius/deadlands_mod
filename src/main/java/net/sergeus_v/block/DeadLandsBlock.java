package net.sergeus_v.block;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tools.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Material;

public class DeadLandsBlock extends Block
{

	public DeadLandsBlock()
    
        {
        super(FabricBlockSettings.of(Material.METAL).breakByHand(false).breakByTool(FabricToolTags.PICKAXES).strength(2,0.2f).build());

    }
}