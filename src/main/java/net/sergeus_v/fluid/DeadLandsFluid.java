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
		
		 // ���������� �� ������ �������� ��������� ���� ��������?
		
		@Override
		public boolean matchesType(Fluid fluid)
		{
			return fluid == getStill() || fluid == getFlowing();
		}
	 	
		//������� �������� ����������, ��� ����?

	@Override
		protected boolean isInfinite()
		{
			return false;
		}
	 
		 // ��������� ��������, ����� �������� �������� � ������� ����. ����� ����
		 // ������� ���� �����. ���� ������������� ���� "block.lava.extinguish"
		
		
		@Override
		protected void beforeBreakingBlock(IWorld world, BlockPos pos, BlockState state)
		{
			final BlockEntity blockEntity = state.getBlock().hasBlockEntity() ? world.getBlockEntity(pos) : null;
			Block.dropStacks(state, world.getWorld(), pos, blockEntity);
		}
	 
		// ���� ���������� ������, ���� �� FluidState ���� ������������ ������ �
		// �������� ��� ����.
		
		//createBlockState
		//You must override Block#fillStateContainer if your block has properties. Look at vanilla for examples.
		//net.minecraft.fluid.EmptyFluid
		
		
		@Override
		protected boolean isEmpty()
		{
			return true;
		}
		
		
		// �������, ���� ������ �������� ����� ���� � ��� FluidState?
		
		@Override
		protected boolean method_15777(FluidState fluidState, BlockView blockView, BlockPos blockPos, Fluid fluid, Direction direction)
		{
			return false;
		}
	 
		// ��������, ������� � ��������� ���������� ��� ������ � �������� ���������? ���� ������������ 4. 
		// ���� ���������� 2 � ��������� � 4 � �������.
		
		@Override
		protected int method_15733(WorldView worldView)
		{
			return 4;
		}
	 
		// ���� ������������ 1. ���� ���������� 1 � ��������� � 2 � �������.
		 
		@Override
		protected int getLevelDecreasePerBlock(WorldView worldView)
		{
			return 1;
		}
	 
		 // ���� ������������ 5. ���� ���������� 30 � ��������� � 10 � �������.
		
		@Override
		public int getTickRate(WorldView worldView)
		{
			return 5;
		}
		
		// ���� � ���� ��� ���������� 100.0F
		
		@Override
		protected float getBlastResistance()
		{
			return 100.0F;
		}
	}