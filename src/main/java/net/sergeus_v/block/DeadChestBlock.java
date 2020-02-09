package net.sergeus_v.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.Identifier;
import net.minecraft.world.BlockView;
import net.sergeus_v.DeadLands;


public class DeadChestBlock extends Block implements BlockEntityProvider{
	//DeadChest - Сам предмет в майне
	//DeadChestBlock - его блок
	//DeadChestBlockEntity - сущность где хранятс данные
	//DeadChestBlockController
	//DeadChestBlockScreen
	//ImplementedInventory - интерфейс инвенторя
	
	public static final Identifier ID = new Identifier(DeadLands.NAMESPACE,"deadchest");

	public DeadChestBlock(Settings settings) {
		super(settings);
		// TODO Auto-generated constructor stub
	}

	@Override
	public BlockEntity createBlockEntity(BlockView view) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
