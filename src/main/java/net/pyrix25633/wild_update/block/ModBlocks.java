package net.pyrix25633.wild_update.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.pyrix25633.wild_update.WildUpdate;

public class ModBlocks {

    public static final Block MUD_BLOCK = registerBlock("mud_block",
            new Block(FabricBlockSettings.of(Material.SOLID_ORGANIC)
                    .strength(0.85f, 1f)
                    .jumpVelocityMultiplier(0.9f)
                    .breakByTool(FabricToolTags.SHOVELS)
                    .sounds(BlockSoundGroup.ROOTED_DIRT)));

    public static final Block MUD_BRICKS = registerBlock("mud_bricks",
            new Block(FabricBlockSettings.of(Material.SOLID_ORGANIC)
                    .strength(1.5f, 2f)
                    .breakByTool(FabricToolTags.PICKAXES).requiresTool()
                    .sounds(BlockSoundGroup.STONE)));

    public static final Block MANGROVE_LOG = registerBlock("mangrove_log",
            new Block(FabricBlockSettings.of(Material.WOOD)
                    .strength(1f, 1f)
                    .breakByTool(FabricToolTags.AXES)
                    .sounds(BlockSoundGroup.WOOD))); //Model and texture!

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registry.BLOCK, new Identifier(WildUpdate.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registry.ITEM, new Identifier(WildUpdate.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));
    }

    public static void registerModBlocks() {
        System.out.println("Registering ModBlocks for " + WildUpdate.MOD_ID);
    }
}
