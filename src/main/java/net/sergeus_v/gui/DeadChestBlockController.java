package net.sergeus_v.gui;

import io.github.cottonmc.cotton.gui.CottonCraftingController;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WItemSlot;

import net.minecraft.container.BlockContext;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.recipe.RecipeType;

public class DeadChestBlockController extends CottonCraftingController{
	public DeadChestBlockController(int syncId, PlayerInventory inventory, BlockContext context) {
        super(RecipeType.SMELTING, syncId, inventory, getBlockInventory(context), getBlockPropertyDelegate(context));
        
        // рисуем само окно размером 300 на 200 точек
        WGridPanel root = new WGridPanel();
        setRootPanel(root);
        root.setSize(300, 200);

        WItemSlot itemSlot = WItemSlot.of(blockInventory, 0);
        root.add(itemSlot, 4, 1);

        //Рисуем инвентарь игрока
        root.add(this.createPlayerInventoryPanel(), 0, 3);

        root.validate(this);
    }
}
