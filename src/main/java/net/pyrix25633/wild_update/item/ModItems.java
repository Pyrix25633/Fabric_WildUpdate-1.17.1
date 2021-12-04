package net.pyrix25633.wild_update.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.pyrix25633.wild_update.WildUpdate;

public class ModItems {

    //* public static final Item RUBY = registerItem("ruby", new Item(new FabricItemSettings()
    //*        .group(ItemGroup.BUILDING_BLOCKS)));

    private static Item registerItem(String name, Item item ) {
        return Registry.register(Registry.ITEM, new Identifier(WildUpdate.MOD_ID, name), item);
    }

    public static void registerModItems() {
        System.out.println("Registering Mod Items for " + WildUpdate.MOD_ID);
    }
}
