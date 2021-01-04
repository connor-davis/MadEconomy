package tech.connordavis.madeconomy.registry;

import net.minecraft.block.Block;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import tech.connordavis.madeconomy.blocks.ModBlocks;
import tech.connordavis.madeconomy.containers.ModContainerTypes;
import tech.connordavis.madeconomy.items.ModItems;
import tech.connordavis.madeconomy.tileentities.ModTileEntityTypes;
import tech.connordavis.madeconomy.utils.ModProperties;

public class ModRegistry {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ModProperties.MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ModProperties.MOD_ID);
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, ModProperties.MOD_ID);
    public static final DeferredRegister<ContainerType<?>> CONTAINER_TYPES = DeferredRegister.create(ForgeRegistries.CONTAINERS, ModProperties.MOD_ID);

    public static void register() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        TILE_ENTITIES.register(modEventBus);
        CONTAINER_TYPES.register(modEventBus);

        ModBlocks.register();
        ModItems.register();
        ModTileEntityTypes.register();
        ModContainerTypes.register();
    }
}
