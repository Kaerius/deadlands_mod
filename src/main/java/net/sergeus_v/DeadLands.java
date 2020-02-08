package net.sergeus_v;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.BlockItem;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.decorator.ChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.feature.LakeFeature;
import net.minecraft.world.gen.feature.SingleStateFeatureConfig;
import net.sergeus_v.block.CursedStone;
import net.sergeus_v.block.DeadLandsBlock;
import net.sergeus_v.fluid.BloodFluid;
import net.sergeus_v.item.AxeCursedStone;
import net.sergeus_v.item.BloodBucket;
import net.sergeus_v.item.DeadLandsItem;
import net.sergeus_v.item.HoeCursedStone;
import net.sergeus_v.item.PickaxeCursedStone;
import net.sergeus_v.item.PieceOfCursedStone;
import net.sergeus_v.item.ShovelCursedStone;
import net.sergeus_v.item.SwordCursedStone;
import net.sergeus_v.item.ToolMaterialDeadLands;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.GenerationStep;


public class DeadLands implements ModInitializer {


	public static final CursedStone CURSED_STONE = new CursedStone();
	public static final DeadLandsBlock DEAD_BLOCK = new DeadLandsBlock();
	
	public static final DeadLandsItem DEAD_ITEM = new DeadLandsItem(new Item.Settings().group(ItemGroup.MISC));
	public static final Item BONE_MEAT = new DeadLandsFood(new Item.Settings().group(ItemGroup.FOOD).food(new FoodComponent.Builder().hunger(1).saturationModifier(6f).snack().meat().alwaysEdible().statusEffect(new StatusEffectInstance(StatusEffects.HEALTH_BOOST, 20*3), 0.5f).build()));
	public static final PieceOfCursedStone PIECE_OF_CURSED_STONE = new PieceOfCursedStone(new Item.Settings().group(ItemGroup.MISC));
	
	public BloodFluid.Still STILL_BLOOD;
	public BloodFluid.Flowing FLOWING_BLOOD;
	public BloodFluid.Block BLOOD;
	public BloodBucket BLOOD_BUCKET;
	
	public static LakeFeature BLOOD_LAKE;
	
	private static DeadLands instance;
	
    public static DeadLands getInstance()
    {
       return instance;
       }
	
	@Override
	public void onInitialize() {

		instance = this;
		
		Registry.register(Registry.ITEM, new Identifier("deadlands", "dead_item"), DEAD_ITEM);
		Registry.register(Registry.ITEM, new Identifier("deadlands", "bone_meat"), BONE_MEAT);
		Registry.register(Registry.ITEM, new Identifier("deadlands", "piece_of_cursed_stone"), PIECE_OF_CURSED_STONE);

	    Registry.register(Registry.BLOCK, new Identifier("deadlands", "dead_block"), DEAD_BLOCK);
		Registry.register(Registry.ITEM, new Identifier("deadlands", "dead_block"), new BlockItem(DEAD_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
		
		Registry.register(Registry.BLOCK, new Identifier("deadlands", "cursed_stone"), CURSED_STONE);
		Registry.register(Registry.ITEM, new Identifier("deadlands", "cursed_stone"), new BlockItem(CURSED_STONE, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
		
		Registry.register(Registry.ITEM, new Identifier("deadlands", "pickaxe_cursed_stone"), new PickaxeCursedStone(new ToolMaterialDeadLands()));	//Мотыга
		Registry.register(Registry.ITEM, new Identifier("deadlands", "shovel_cursed_stone"), new ShovelCursedStone(new ToolMaterialDeadLands()));	//Лопата
		Registry.register(Registry.ITEM, new Identifier("deadlands", "hoe_cursed_stone"), new HoeCursedStone(new ToolMaterialDeadLands()));			//Мотыга
		Registry.register(Registry.ITEM, new Identifier("deadlands", "sword_cursed_stone"), new SwordCursedStone(new ToolMaterialDeadLands()));		//Меч
		Registry.register(Registry.ITEM, new Identifier("deadlands", "axe_cursed_stone"), new AxeCursedStone(new ToolMaterialDeadLands()));			//Топор
	
		FuelRegistry.INSTANCE.add(DEAD_ITEM, 80);
		
		//STILL_BLOOD = Registry.FLUID.add(new Identifier("deadlands", "blood"), new BloodFluid.Still());
		//FLOWING_BLOOD = Registry.FLUID.add(new Identifier("deadlands", "flowing_blood"), new BloodFluid.Flowing());
		//BLOOD_BUCKET = Registry.ITEM.add(new Identifier("deadlands", "blood_bucket"), new BloodBucket(STILL_BLOOD, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1)));
		//BLOOD = Registry.BLOCK.add(new Identifier("deadlands", "blood"), new BloodFluidBlock(STILL_BLOOD, FabricBlockSettings.copy(Blocks.WATER).build()){});
		
		BLOOD_BUCKET = new BloodBucket(STILL_BLOOD);
		STILL_BLOOD = new BloodFluid.Still();
		FLOWING_BLOOD = new BloodFluid.Flowing();
		BLOOD = Registry.BLOCK.add(new Identifier("deadlands", "blood"), new BloodFluid.Block(STILL_BLOOD));
		
		//Registry.register(Registry.BLOCK, new Identifier("deadlands", "blood_block"), BLOOD);
		Registry.register(Registry.FLUID, new Identifier("deadlands", "blood_fluid"), STILL_BLOOD);
        Registry.register(Registry.FLUID, new Identifier("deadlands", "flowing_blood"), FLOWING_BLOOD);
        Registry.register(Registry.ITEM, new Identifier("deadlands", "blood_bucket"), BLOOD_BUCKET);
		
		BLOOD_LAKE = Registry.register(Registry.FEATURE, new Identifier("deadlands", "blood_lake"), new LakeFeature(SingleStateFeatureConfig::deserialize));
		 
		// generate in swamps, similar to water lakes, but with a chance of 40 (the higher the number, the lower the generation chance)
		Biomes.SWAMP.addFeature(GenerationStep.Feature.LOCAL_MODIFICATIONS,	BLOOD_LAKE.configure(new SingleStateFeatureConfig(BLOOD.getDefaultState()))
				.createDecoratedFeature(Decorator.WATER_LAKE.configure(new ChanceDecoratorConfig(40)))
		);

	
	}

	public static RenderLayer getTranslucent() {
		// TODO Auto-generated method stub
		return null;
	}


}
