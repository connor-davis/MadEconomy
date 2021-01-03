package tech.connordavis.madeconomy.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import tech.connordavis.madeconomy.registry.ModRegistry;

public class ModItems {
    /**
     * "Default" Items
     */
    public static final RegistryObject<Item> SILVER_INGOT = ModRegistry.ITEMS.register("silver_ingot", () ->
            new Item(new Item.Properties().group(ItemGroup.MATERIALS)));
    public static final RegistryObject<Item> SILVER_NUGGET = ModRegistry.ITEMS.register("silver_nugget", () ->
            new Item(new Item.Properties().group(ItemGroup.MATERIALS)));

    /**
     * "Special" Items
     */
    public static final RegistryObject<SilverCoin> SILVER_COIN = ModRegistry.ITEMS.register("silver_coin", SilverCoin::new);

    public static void register() {}
}
