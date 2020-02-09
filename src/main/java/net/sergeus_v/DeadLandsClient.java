package net.sergeus_v;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandler;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.screen.ScreenProviderRegistry;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.BlockRenderView;
import net.sergeus_v.block.DeadChestBlock;
import net.sergeus_v.gui.DeadChestBlockController;
import net.sergeus_v.gui.DeadChestBlockScreen;
import net.minecraft.container.BlockContext;

import java.awt.*;
import java.util.function.Function;

public class DeadLandsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient(){
        DeadLands mod = DeadLands.getInstance();
        initBloodRender(mod.bloodFluidStill, mod.bloodFluidFlowing, new Identifier(DeadLands.NAMESPACE, "blood"), new Color(194, 149, 8).getRGB());
        BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(), mod.bloodFluidStill, mod.bloodFluidFlowing);
        
        
        //регистрируем Сундук     
        ScreenProviderRegistry.INSTANCE.registerFactory(DeadChestBlock.ID, (syncId, identifier, player, buf) -> new DeadChestBlockScreen(new DeadChestBlockController(syncId, player.inventory, BlockContext.create(player.world, buf.readBlockPos())), player));
        //Конец регистрации сундука
    }

    private void initBloodRender(final Fluid still, final Fluid flowing, final Identifier textureFluidId, final int color) {
        final Identifier stillSpriteId = new Identifier(textureFluidId.getNamespace(), "block/" + textureFluidId.getPath() + "_still");
        final Identifier flowingSpriteId = new Identifier(textureFluidId.getNamespace(), "block/" + textureFluidId.getPath() + "_flow");

        ClientSpriteRegistryCallback.event(SpriteAtlasTexture.BLOCK_ATLAS_TEX).register((atlasTexture, registry) -> {
            registry.register(stillSpriteId);
            registry.register(flowingSpriteId);
        });

        final Identifier fluidId = Registry.FLUID.getId(still);
        final Identifier listenerId = new Identifier(fluidId.getNamespace(), fluidId.getPath() + "_reload_listener");

        final Sprite[] fluidSprites = new Sprite[] { null, null };

        ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES).registerReloadListener(new SimpleSynchronousResourceReloadListener() {
            @Override
            public Identifier getFabricId() {
                return listenerId;
            }

            @Override
            public void apply(ResourceManager resourceManager) {
                final Function<Identifier, Sprite> atlas = MinecraftClient.getInstance().getSpriteAtlas(SpriteAtlasTexture.BLOCK_ATLAS_TEX);
                fluidSprites[0] = atlas.apply(stillSpriteId);
                fluidSprites[1] = atlas.apply(flowingSpriteId);
            }
        });

        final FluidRenderHandler renderHandler = new FluidRenderHandler() {
            @Override
            public Sprite[] getFluidSprites(BlockRenderView view, BlockPos pos, FluidState state) {
                return fluidSprites;
            }

            @Override
            public int getFluidColor(BlockRenderView view, BlockPos pos, FluidState state) {
                return color;
            }
        };

        FluidRenderHandlerRegistry.INSTANCE.register(still, renderHandler);
        FluidRenderHandlerRegistry.INSTANCE.register(flowing, renderHandler);
    }
}
