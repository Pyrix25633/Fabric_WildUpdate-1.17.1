package net.pyrix25633.wild_update;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;
import net.pyrix25633.wild_update.block.ModBlocks;
import net.pyrix25633.wild_update.item.ModItems;
import net.pyrix25633.wild_update.registries.ModFlammableBlocks;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WildUpdate implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LogManager.getLogger("wild_update");

	public static final String MOD_ID = "wild_update";

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		ModItems.registerModItems();
		ModBlocks.registerModBlocks();

		ModFlammableBlocks.registerFlammables();

		LOGGER.info("Hello Fabric world!");
	}
}
