package net.pyrix25633.wild_update.registries;

import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.pyrix25633.wild_update.block.ModBlocks;

public class ModFlammableBlocks {
    public static void registerFlammables(){
        final int Encouragement = 5, Flammability = 20;
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.MANGROVE_LOG, Encouragement, Flammability);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.MANGROVE_WOOD, Encouragement, Flammability);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.MANGROVE_PLANKS, Encouragement, Flammability);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STRIPPED_MANGROVE_LOG, Encouragement, Flammability);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STRIPPED_MANGROVE_WOOD, Encouragement, Flammability);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.MANGROVE_STAIRS, Encouragement, Flammability);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.MANGROVE_SLAB, Encouragement, Flammability);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.MANGROVE_FENCE, Encouragement, Flammability);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.MANGROVE_FENCE_GATE, Encouragement, Flammability);
    }
}