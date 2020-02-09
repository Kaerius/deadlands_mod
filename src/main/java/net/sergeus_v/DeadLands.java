package net.sergeus_v;

import net.sergeus_v.fluid.BloodFluid;
import net.sergeus_v.gui.DeadChestBlockController;
import net.sergeus_v.items.BloodBucket;
import net.sergeus_v.items.BloodPie;
import net.fabricmc.api.ModInitializer;
import net.minecraft.block.Block;
import net.minecraft.block.FluidBlock;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.container.BlockContext;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.BlockItem;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.decorator.ChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.feature.LakeFeature;
import net.minecraft.world.gen.feature.SingleStateFeatureConfig;
import net.sergeus_v.block.CursedStone;
import net.sergeus_v.block.DeadChestBlock;
import net.sergeus_v.block.DeadChestBlockEntity;
import net.sergeus_v.block.DeadLandsBlock;
import net.sergeus_v.items.AxeCursedStone;
import net.sergeus_v.items.DeadLandsItem;
import net.sergeus_v.items.HoeCursedStone;
import net.sergeus_v.items.PickaxeCursedStone;
import net.sergeus_v.items.PieceOfCursedStone;
import net.sergeus_v.items.ShovelCursedStone;
import net.sergeus_v.items.SwordCursedStone;
import net.sergeus_v.items.ToolMaterialDeadLands;
import net.minecraft.world.gen.GenerationStep;


public class DeadLands implements ModInitializer {
    public static final String NAMESPACE = "deadlands";
    private static DeadLands instance;
    public BloodBucket bloodBucket;
    public FluidBlock bloodFluidBlock;
    public BloodFluid.Still bloodFluidStill;
    public BloodFluid.Flowing bloodFluidFlowing;
    
    //регистрируем СУЩНОСТЬ СундукА 
    public static BlockEntityType<DeadChestBlockEntity> DEAD_CHEST_BLOCK_ENTITY;
    public static final Block DEAD_CHEST_BLOCK = new DeadChestBlock(FabricBlockSettings.of(Material.METAL).build());
    //Конец регистрации СУЩНОСТЬ сундука
      
    public static final CursedStone CURSED_STONE = new CursedStone();
	public static final DeadLandsBlock DEAD_BLOCK = new DeadLandsBlock();
	
	public static final DeadLandsItem DEAD_ITEM = new DeadLandsItem(new Item.Settings().group(ItemGroup.MISC));
	public static final Item BONE_MEAT = new DeadLandsFood(new Item.Settings().group(ItemGroup.FOOD).food(new FoodComponent.Builder().hunger(1).saturationModifier(6f).snack().meat().alwaysEdible().statusEffect(new StatusEffectInstance(StatusEffects.HEALTH_BOOST, 20*3), 0.5f).build()));
	public static final PieceOfCursedStone PIECE_OF_CURSED_STONE = new PieceOfCursedStone(new Item.Settings().group(ItemGroup.MISC));
	
	public static LakeFeature BLOOD_LAKE;
	
    public static DeadLands getInstance(){
        return instance;
    }

    @Override
    public void onInitialize() {
        instance = this;
      
        //регистрируем Сундук    
        Registry.register(Registry.BLOCK, new Identifier(NAMESPACE, "deadchest"), DEAD_CHEST_BLOCK);
        Registry.register(Registry.ITEM, new Identifier(NAMESPACE, "deadchest"), new BlockItem(DEAD_CHEST_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));   
        //DEAD_CHEST_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY, id, entry)
        DEAD_CHEST_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, "deadlands:deadchest", BlockEntityType.Builder.create(DeadChestBlockEntity::new, DEAD_CHEST_BLOCK).build(null));

        // в примере Registry.BLOCK_ENTITY но такого нет, исправил на Registry.BLOCK_ENTITY_TYPE
        
        ContainerProviderRegistry.INSTANCE.registerFactory(DeadChestBlock.ID, (syncId, id, player, buf) -> new DeadChestBlockController(syncId, player.inventory, BlockContext.create(player.world, buf.readBlockPos())));
        
        //Конец регистрации сундука
        
        bloodFluidStill = new BloodFluid.Still();
        bloodFluidFlowing = new BloodFluid.Flowing();
        bloodBucket = new BloodBucket(bloodFluidStill);
        bloodFluidBlock = Registry.BLOCK.add(new Identifier(NAMESPACE, "blood"), new BloodFluid.Block(bloodFluidStill));

        Registry.register(Registry.FLUID, new Identifier(NAMESPACE, "blood_fluid"), bloodFluidStill);
        Registry.register(Registry.FLUID, new Identifier(NAMESPACE, "flowing_blood_fluid"), bloodFluidFlowing);
        Registry.register(Registry.ITEM, new Identifier(NAMESPACE, "blood_bucket"), bloodBucket);
        Registry.register(Registry.ITEM, new Identifier(NAMESPACE, "blood_pie"), new BloodPie());
        
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
        
		BLOOD_LAKE = Registry.register(Registry.FEATURE, new Identifier("deadlands", "blood_lake"), new LakeFeature(SingleStateFeatureConfig::deserialize));
		 
		// generate in swamps, similar to water lakes, but with a chance of 40 (the higher the number, the lower the generation chance)
		Biomes.SWAMP.addFeature(GenerationStep.Feature.LOCAL_MODIFICATIONS,	BLOOD_LAKE.configure(new SingleStateFeatureConfig(bloodFluidBlock.getDefaultState()))
				.createDecoratedFeature(Decorator.WATER_LAKE.configure(new ChanceDecoratorConfig(40)))
		);
    }
}
