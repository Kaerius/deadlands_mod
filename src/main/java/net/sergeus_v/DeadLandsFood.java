package net.sergeus_v;


	import net.minecraft.entity.player.PlayerEntity;
	import net.minecraft.item.Item;
	import net.minecraft.item.ItemStack;
	import net.minecraft.sound.SoundEvents;
	import net.minecraft.util.ActionResult;
	import net.minecraft.util.Hand;
	import net.minecraft.world.World;
	import net.minecraft.util.TypedActionResult;

	public class DeadLandsFood extends Item {

		public DeadLandsFood(Settings settings) {
			super(settings);
		}

		@Override
		public TypedActionResult<ItemStack> use(World world, PlayerEntity PlayerEntity, Hand hand){
			PlayerEntity.playSound(SoundEvents.ENTITY_PANDA_EAT, 1.0F, 1.0F);
				return new TypedActionResult<ItemStack>(ActionResult.CONSUME, PlayerEntity.getStackInHand(hand));
		}
	}