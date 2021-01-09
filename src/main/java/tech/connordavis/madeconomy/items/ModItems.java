package tech.connordavis.madeconomy.items;

import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import tech.connordavis.madeconomy.groups.ModItemGroup;
import tech.connordavis.madeconomy.registry.ModRegistry;

public class ModItems {
    /**
     * "Default" Items
     */
    public static final RegistryObject<Item> SILVER_INGOT = ModRegistry.ITEMS.register("silver_ingot", () ->
            new Item(new Item.Properties().group(ModItemGroup.MAIN_GROUP)));
    public static final RegistryObject<Item> SILVER_NUGGET = ModRegistry.ITEMS.register("silver_nugget", () ->
            new Item(new Item.Properties().group(ModItemGroup.MAIN_GROUP)));
    public static final RegistryObject<Item> MAGIC_SHARD = ModRegistry.ITEMS.register("magic_shard", () ->
            new Item(new Item.Properties().fireproof().group(ModItemGroup.MAIN_GROUP)));

    /**
     * "Special" Items
     */
    public static final RegistryObject<SilverCoin> SILVER_COIN = ModRegistry.ITEMS.register("silver_coin", SilverCoin::new);
    public static final RegistryObject<Wallet> WALLET = ModRegistry.ITEMS.register("wallet", Wallet::new);

    public static void register() {
    }
}
