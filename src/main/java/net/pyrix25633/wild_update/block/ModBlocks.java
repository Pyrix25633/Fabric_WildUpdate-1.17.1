package net.pyrix25633.wild_update.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.pyrix25633.wild_update.WildUpdate;
import net.pyrix25633.wild_update.block.custom.*;

public class ModBlocks {

    static final float mudBrickHardness = 1.5f, mudBrickResistance = 2.2f,
            mangroveHardness = 2f, mangroveResistance = 1.8f,
            hollowHardness = 2f, hollowResistance = 1.8f;

    // Mud Blocks
    public static final Block MUD_BLOCK = registerBlock("mud_block",
            new MudBlock(FabricBlockSettings.of(Material.SOLID_ORGANIC)
                    .strength(0.85f, 1f)
                    .jumpVelocityMultiplier(0.9f)
                    .breakByTool(FabricToolTags.SHOVELS)
                    .sounds(BlockSoundGroup.ROOTED_DIRT)));
    public static final Block MUD_BRICKS = registerBlock("mud_bricks",
            new Block(FabricBlockSettings.of(Material.SOLID_ORGANIC)
                    .strength(mudBrickHardness, mudBrickResistance)
                    .breakByTool(FabricToolTags.PICKAXES).requiresTool()
                    .sounds(BlockSoundGroup.STONE)));
    public static final Block MUD_BRICK_STAIRS = registerBlock("mud_brick_stairs",
            new ModStairsBlock(ModBlocks.MUD_BRICKS.getDefaultState(),
                    FabricBlockSettings.of(Material.SOLID_ORGANIC)
                    .strength(mudBrickHardness, mudBrickResistance)
                    .breakByTool(FabricToolTags.PICKAXES).requiresTool()
                    .sounds(BlockSoundGroup.STONE)));
    public static final Block MUD_BRICK_SLAB = registerBlock("mud_brick_slab",
            new SlabBlock(FabricBlockSettings.of(Material.SOLID_ORGANIC)
                    .strength(mudBrickHardness, mudBrickResistance)
                    .breakByTool(FabricToolTags.PICKAXES).requiresTool()
                    .sounds(BlockSoundGroup.STONE)));
    public static final Block MUD_BRICK_WALL = registerBlock("mud_brick_wall",
            new WallBlock(FabricBlockSettings.of(Material.SOLID_ORGANIC)
                    .strength(mudBrickHardness, mudBrickResistance)
                    .breakByTool(FabricToolTags.PICKAXES).requiresTool()
                    .sounds(BlockSoundGroup.STONE)));
    //Mangrove Blocks
    public static final Block MANGROVE_LOG = registerBlock("mangrove_log",
            new ModLogBlock(FabricBlockSettings.of(Material.WOOD)
                    .strength(1f, 1f)
                    .breakByTool(FabricToolTags.AXES)
                    .sounds(BlockSoundGroup.WOOD)));
    public static final Block STRIPPED_MANGROVE_LOG = registerBlock("stripped_mangrove_log",
            new ModLogBlock(FabricBlockSettings.of(Material.WOOD)
                    .strength(1f, 1f)
                    .breakByTool(FabricToolTags.AXES)
                    .sounds(BlockSoundGroup.WOOD)));
    public static final Block MANGROVE_WOOD = registerBlock("mangrove_wood",
            new ModLogBlock(FabricBlockSettings.of(Material.WOOD)
                    .strength(1f, 1f)
                    .breakByTool(FabricToolTags.AXES)
                    .sounds(BlockSoundGroup.WOOD)));
    public static final Block STRIPPED_MANGROVE_WOOD = registerBlock("stripped_mangrove_wood",
            new ModLogBlock(FabricBlockSettings.of(Material.WOOD)
                    .strength(1f, 1f)
                    .breakByTool(FabricToolTags.AXES)
                    .sounds(BlockSoundGroup.WOOD)));
    public static final Block MANGROVE_PLANKS = registerBlock("mangrove_planks",
            new Block(FabricBlockSettings.of(Material.WOOD)
                    .strength(mangroveHardness, mangroveResistance)
                    .breakByTool(FabricToolTags.AXES)
                    .sounds(BlockSoundGroup.WOOD)));
    public static final Block MANGROVE_STAIRS = registerBlock("mangrove_stairs",
            new ModStairsBlock(ModBlocks.MUD_BRICKS.getDefaultState(),
                    FabricBlockSettings.of(Material.WOOD)
                            .strength(mangroveHardness, mangroveResistance)
                            .breakByTool(FabricToolTags.AXES)
                            .sounds(BlockSoundGroup.WOOD)));
    public static final Block MANGROVE_SLAB = registerBlock("mangrove_slab",
            new SlabBlock(FabricBlockSettings.of(Material.WOOD)
                    .strength(mangroveHardness, mangroveResistance)
                    .breakByTool(FabricToolTags.AXES)
                    .sounds(BlockSoundGroup.WOOD)));
    public static final Block MANGROVE_FENCE = registerBlock("mangrove_fence",
            new FenceBlock(FabricBlockSettings.of(Material.WOOD)
                    .strength(mangroveHardness, mangroveResistance)
                    .breakByTool(FabricToolTags.AXES)
                    .sounds(BlockSoundGroup.WOOD)));
    public static final Block MANGROVE_FENCE_GATE = registerBlock("mangrove_fence_gate",
            new FenceGateBlock(FabricBlockSettings.of(Material.WOOD)
                    .strength(mangroveHardness, mangroveResistance)
                    .breakByTool(FabricToolTags.AXES)
                    .sounds(BlockSoundGroup.WOOD)));
    public static final Block MANGROVE_BUTTON = registerBlock("mangrove_button",
            new ModWoodenButtonBlock(FabricBlockSettings.of(Material.WOOD)
                    .strength(mangroveHardness, mangroveResistance)
                    .breakByTool(FabricToolTags.AXES)
                    .sounds(BlockSoundGroup.WOOD)));
    public static final Block MANGROVE_PRESSURE_PLATE = registerBlock("mangrove_pressure_plate",
            new ModPressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING,
                    FabricBlockSettings.of(Material.WOOD)
                    .strength(mangroveHardness, mangroveResistance)
                    .breakByTool(FabricToolTags.AXES)
                    .sounds(BlockSoundGroup.WOOD)));
    public static final Block MANGROVE_DOOR = registerBlock("mangrove_door",
            new ModDoorBlock(FabricBlockSettings.of(Material.WOOD)
                            .strength(mangroveHardness, mangroveResistance)
                            .breakByTool(FabricToolTags.AXES)
                            .sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block MANGROVE_TRAPDOOR = registerBlock("mangrove_trapdoor",
            new ModTrapdoorBlock(FabricBlockSettings.of(Material.WOOD)
                            .strength(mangroveHardness, mangroveResistance)
                            .breakByTool(FabricToolTags.AXES)
                            .sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block MANGROVE_LEAVES = registerBlock("mangrove_leaves",
            new LeavesBlock(FabricBlockSettings.of(Material.LEAVES)
                    .strength(0.2f, 0.2f)
                    .breakByTool(FabricToolTags.HOES)
                    .breakByTool(FabricToolTags.SHEARS, 0)
                    .sounds(BlockSoundGroup.AZALEA_LEAVES).nonOpaque()));
    public static final Block MANGROVE_ROOTS = registerBlock("mangrove_roots",
            new MangroveRoots(FabricBlockSettings.of(Material.SOLID_ORGANIC)
                    .strength(0.4f, 0.4f)
                    .breakByTool(FabricToolTags.HOES)
                    .sounds(BlockSoundGroup.ROOTS).nonOpaque()));
    //Sculk blocks
    public static final Block SCULK_BLOCK = registerBlock("sculk_block",
            new SculkBlock(FabricBlockSettings.of(Material.SCULK)
                    .strength(0.5f, 1.2f)
                    .breakByTool(FabricToolTags.HOES)
                    .sounds(BlockSoundGroup.SCULK_SENSOR)));
    public static final Block SCULK_CATALIST = registerBlock("sculk_catalist",
            new CatalistBlock(FabricBlockSettings.of(Material.SCULK)
                    .strength(1f, 1.2f)
                    .breakByTool(FabricToolTags.HOES)
                    .sounds(BlockSoundGroup.SCULK_SENSOR)));
    public static final Block SCULK_SHRIEKER = registerBlock("sculk_shrieker",
            new ShriekerBlock(FabricBlockSettings.of(Material.SCULK)
                    .strength(1f, 1.2f)
                    .breakByTool(FabricToolTags.HOES)
                    .sounds(BlockSoundGroup.SCULK_SENSOR).nonOpaque()));
    public static final Block SCULK_VEIN = registerBlock("sculk_vein",
            new VeinBlock(FabricBlockSettings.of(Material.SCULK)
                    .strength(0.5f, 1.2f)
                    .breakByTool(FabricToolTags.HOES)
                    .sounds(BlockSoundGroup.SCULK_SENSOR).nonOpaque()));
    //Hollow Birch Log
    public static final Block HOLLOW_BIRCH_LOG = registerBlock("hollow_birch_log",
            new HollowLog(FabricBlockSettings.of(Material.WOOD)
                    .strength(hollowHardness, hollowResistance)
                    .breakByTool(FabricToolTags.AXES)
                    .sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block STRIPPED_HOLLOW_BIRCH_LOG = registerBlock("stripped_hollow_birch_log",
            new HollowLog(FabricBlockSettings.of(Material.WOOD)
                    .strength(hollowHardness, hollowResistance)
                    .breakByTool(FabricToolTags.AXES)
                    .sounds(BlockSoundGroup.WOOD).nonOpaque()));

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
