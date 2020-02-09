package net.sergeus_v.block;

import net.sergeus_v.DeadLands;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.DefaultedList;

public class DeadChestBlockEntity extends BlockEntity implements ImplementedInventory{

	DefaultedList<ItemStack> items = DefaultedList.ofSize(2, ItemStack.EMPTY); //ofSize = кол-во €чеек инвентор€, ItemStack = размер стаков по умолчани€ю
	
	public DeadChestBlockEntity() {
		super(DeadLands.DEAD_CHEST_BLOCK_ENTITY);
		// TODO Auto-generated constructor stub
	}

	@Override
	public DefaultedList<ItemStack> getItems() {
		// TODO Auto-generated method stub
		return items;
	}
		
	@Override
	public boolean canPlayerUseInv(PlayerEntity player) {
		// TODO Auto-generated method stub
		//return ImplementedInventory.super.canPlayerUseInv(player); //расто€ние с которого можно взамидействовать с инвенторем (ѕо умолчанию метод)
		return pos.isWithinDistance(player.getBlockPos(), 5); //расто€ние с которого можно взамидействовать с инвенторем (”становили расто€ние 5 блока)
	}
	
	
	@Override
	public void fromTag(CompoundTag tag) { //вз€ти€ из инвентор€
		// TODO Auto-generated method stub
		super.fromTag(tag);
		Inventories.fromTag(tag, items);
	}
	
	@Override
	public CompoundTag toTag(CompoundTag tag) { //положить в инвенторь
		// TODO Auto-generated method stub
		Inventories.toTag(tag, items);
		return super.toTag(tag);
	}

	
}
