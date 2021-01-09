package tech.connordavis.madeconomy.registry;

import net.minecraft.block.Block;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import tech.connordavis.madeconomy.MadEconomy;
import tech.connordavis.madeconomy.blocks.ModBlocks;
import tech.connordavis.madeconomy.containers.ModContainerTypes;
import tech.connordavis.madeconomy.items.ModItems;
import tech.connordavis.madeconomy.tileentities.ModTileEntityTypes;

public class ModRegistry {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MadEconomy.ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MadEconomy.ID);
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, MadEconomy.ID);
    public static final DeferredRegister<ContainerType<?>> CONTAINER_TYPES = DeferredRegister.create(ForgeRegistries.CONTAINERS, MadEconomy.ID);

    public static void register() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        BLOCKS.register(eventBus);
        ITEMS.register(eventBus);
        TILE_ENTITIES.register(eventBus);
        CONTAINER_TYPES.register(eventBus);

        ModBlocks.register();
        ModItems.register();
        ModTileEntityTypes.register();
        ModContainerTypes.register();
    }
}
