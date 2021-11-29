package net.pyrix25633.wild_update;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.color.world.FoliageColors;
import net.minecraft.client.render.RenderLayer;
import net.pyrix25633.wild_update.block.ModBlocks;
import net.pyrix25633.wild_update.item.ModItems;
import net.pyrix25633.wild_update.registries.ModFlammableBlocks;
import net.pyrix25633.wild_update.registry.RegisterDispenser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WildUpdateClient implements ClientModInitializer {
    // This logger is used to write text to the console and the log file.
    // It is considered best practice to use your mod id as the logger's name.
    // That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LogManager.getLogger("wild_update");

    public static final String MOD_ID = "wild_update";

    @Override
    public void onInitializeClient() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        ModItems.registerModItems();
        ModBlocks.registerModBlocks();
        RegisterDispenser.RegisterDispenser();

        ModFlammableBlocks.registerFlammables();

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.MANGROVE_TRAPDOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.MANGROVE_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SCULK_SHRIEKER, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SCULK_VEIN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.MANGROVE_LEAVES, RenderLayer.getCutout());

        ColorProviderRegistry.BLOCK.register(((state, world, pos, tintIndex) -> {
            assert world != null;
            return BiomeColors.getFoliageColor(world, pos);
        }), ModBlocks.MANGROVE_LEAVES);

        ColorProviderRegistry.ITEM.register(((stack, tintIndex) -> FoliageColors.getDefaultColor()), ModBlocks.MANGROVE_LEAVES);

        LOGGER.info("Hello Fabric world!");
    }
}
