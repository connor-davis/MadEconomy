package tech.connordavis.madeconomy.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import tech.connordavis.madeconomy.groups.ModItemGroup;
import tech.connordavis.madeconomy.registry.ModRegistry;

import java.util.function.Supplier;

public class ModBlocks {
    /**
     * "Default" Blocks
     */
    public static final RegistryObject<Block> SILVER_ORE = register("silver_ore", SilverOre::new);
    public static final RegistryObject<Block> SILVER_BLOCK = register("silver_block", SilverBlock::new);

    /**
     * "Special" Blocks
     */
    public static final RegistryObject<MagicalSafe> MAGICAL_SAFE = register("magical_safe", MagicalSafe::new);

    public static void register() {
    }

    private static <T extends Block> RegistryObject<T> registerNoItem(String name, Supplier<T> block) {
        return ModRegistry.BLOCKS.register(name, block);
    }

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> block) {
        RegistryObject<T> ret = registerNoItem(name, block);
        ModRegistry.ITEMS.register(name, () -> new BlockItem(ret.get(), new Item.Properties().group(ModItemGroup.MAIN_GROUP)));
        return ret;
    }
}
