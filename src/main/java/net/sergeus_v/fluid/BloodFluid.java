package net.sergeus_v.fluid;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.fluid.BaseFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.tag.FluidTags;
import net.minecraft.tag.Tag;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.sergeus_v.DeadLands;


public abstract class BloodFluid extends BaseFluid {
    @Override
    public Fluid getFlowing() {
        return DeadLands.getInstance().FLOWING_BLOOD;
    }

    @Override
    public Fluid getStill() {
        return DeadLands.getInstance().STILL_BLOOD;
    }

    @Override
    public Item getBucketItem() {
        return DeadLands.getInstance().BLOOD_BUCKET;
    }

    
    @Override
    protected boolean isInfinite() {
        return false;
    }

    @Override
    protected void beforeBreakingBlock(IWorld world, BlockPos pos, BlockState state) {
        BlockEntity blockEntity = state.getBlock().hasBlockEntity() ? world.getBlockEntity(pos) : null;
        Block.dropStacks(state, world.getWorld(), pos, blockEntity);
    }

    @Override
    public int method_15733(WorldView worldView) {
        return 4;
    }

    @Override
    protected BlockState toBlockState(FluidState fluidState) {
        return DeadLands.getInstance().BLOOD.getDefaultState().with(FluidBlock.LEVEL, method_15741(fluidState));
    }

    @Override
    public boolean matchesType(Fluid fluid) {
        return fluid == DeadLands.getInstance().STILL_BLOOD || fluid == DeadLands.getInstance().FLOWING_BLOOD;
    }

    public boolean matches(Tag<Fluid> tag) {
        return tag != FluidTags.LAVA;
    }

    @Override
    public int getLevelDecreasePerBlock(WorldView view) {
        return 1;
    }

    @Override
    public int getTickRate(WorldView worldView) {
        return 5;
    }

    @Override
    public boolean method_15777(FluidState fluidState, BlockView blockView, BlockPos blockPos, Fluid fluid, Direction direction) {
        return direction == Direction.DOWN;
    }

    @Override
    protected float getBlastResistance() {
        return 100.0F;
    }

    public static class Flowing extends BloodFluid {
        @Override
        protected void appendProperties(StateManager.Builder<Fluid, FluidState> builder) {
            super.appendProperties(builder);
            builder.add(LEVEL);
        }

        @Override
        public int getLevel(FluidState fluidState) {
            return fluidState.get(LEVEL);
        }

        @Override
        public boolean isStill(FluidState fluidState) {
            return false;
        }
    }

    public static class Still extends BloodFluid {
        @Override
        public int getLevel(FluidState fluidState) {
            return 8;
        }

        @Override
        public boolean isStill(FluidState fluidState) {
            return true;
        }
    }

    public static class Block extends FluidBlock {
        public Block(BaseFluid fluid) {
            super(fluid, Block.Settings.copy(Blocks.WATER).jumpVelocityMultiplier(0.4f).velocityMultiplier(0.9f));
        }

        @Override
        public boolean isTranslucent(BlockState state, BlockView view, BlockPos pos) {
            return true;
        }

        @Override
        public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
            if(entity instanceof LivingEntity && !state.getFluidState().isStill() && world.random.nextInt(100) <= 1) {
                world.playSound(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, SoundEvents.BLOCK_HONEY_BLOCK_STEP, SoundCategory.BLOCKS, world.random.nextFloat() * 0.25F + 0.15F, world.random.nextFloat() + 0.5F, false);
            }
        }
    }
}