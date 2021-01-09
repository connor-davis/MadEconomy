package tech.connordavis.madeconomy.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import tech.connordavis.madeconomy.registry.ModRegistry;

import java.util.function.Supplier;

import static tech.connordavis.madeconomy.groups.ModItemGroup.MAIN_GROUP;

public class ModBlocks {
    /**
     * "Default" Blocks
     */
    public static final RegistryObject<Block> SILVER_ORE = register("silver_ore", () ->
            new Block(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(3, 10).harvestLevel(2).sound(SoundType.STONE)));
    public static final RegistryObject<Block> SILVER_BLOCK = register("silver_block", () ->
            new Block(AbstractBlock.Properties.create(Material.IRON).hardnessAndResistance(3, 10).sound(SoundType.METAL)));
    public static final RegistryObject<Block> MAGIC_ORE = register("magic_ore", () ->
            new Block(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(3, 10).harvestLevel(2).luminance(value -> value.getLightValue() + 10).sound(SoundType.STONE)));

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
        ModRegistry.ITEMS.register(name, () -> new BlockItem(ret.get(), new Item.Properties().group(MAIN_GROUP.get())));
        return ret;
    }
}
