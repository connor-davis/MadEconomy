package tech.connordavis.madeconomy.tileentitites;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import tech.connordavis.madeconomy.utils.ModProperties;

public class ModTileEntityTypes {
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, ModProperties.MOD_ID);
}
