package net.sergeus_v.fluid;

import net.minecraft.fluid.BaseFluid;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.IWorld;
import net.minecraft.world.WorldView;

public abstract class DeadLandsFluid extends BaseFluid
	{
		
		 // Возвращает ли данная жидкость экземпляр этой жидкости?
		
		@Override
		public boolean matchesType(Fluid fluid)
		{
			return fluid == getStill() || fluid == getFlowing();
		}
	 	
		//Возврат жидкости бесконечен, как вода?

	@Override
		protected boolean isInfinite()
		{
			return false;
		}
	 
		 // Выполните действия, когда жидкость попадает в сменный блок. Капли воды
		 // Таблица лута блока. Лава воспроизводит звук "block.lava.extinguish"
		
		
		@Override
		protected void beforeBreakingBlock(IWorld world, BlockPos pos, BlockState state)
		{
			final BlockEntity blockEntity = state.getBlock().hasBlockEntity() ? world.getBlockEntity(pos) : null;
			Block.dropStacks(state, world.getWorld(), pos, blockEntity);
		}
	 
		// Лава возвращает истину, если ее FluidState выше определенной высоты и
		// Жидкость это вода.
		
		//createBlockState
		//You must override Block#fillStateContainer if your block has properties. Look at vanilla for examples.
		//net.minecraft.fluid.EmptyFluid
		
		
		@Override
		protected boolean isEmpty()
		{
			return true;
		}
		
		
		// Вернуть, если данная жидкость может течь в это FluidState?
		
		@Override
		protected boolean method_15777(FluidState fluidState, BlockView blockView, BlockPos blockPos, Fluid fluid, Direction direction)
		{
			return false;
		}
	 
		// Возможно, связано с проверкой расстояния для потока в соседние отверстия? Вода возвращается 4. 
		// Лава возвращает 2 в Сверхмире и 4 в Пустоте.
		
		@Override
		protected int method_15733(WorldView worldView)
		{
			return 4;
		}
	 
		// Вода возвращается 1. Лава возвращает 1 в Сверхмире и 2 в Пустоте.
		 
		@Override
		protected int getLevelDecreasePerBlock(WorldView worldView)
		{
			return 1;
		}
	 
		 // Вода возвращается 5. Лава возвращает 30 в Сверхмире и 10 в Пустоте.
		
		@Override
		public int getTickRate(WorldView worldView)
		{
			return 5;
		}
		
		// Вода и Лава оба возвращают 100.0F
		
		@Override
		protected float getBlastResistance()
		{
			return 100.0F;
		}
	}