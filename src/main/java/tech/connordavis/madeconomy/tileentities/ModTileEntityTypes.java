package tech.connordavis.madeconomy.tileentities;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import tech.connordavis.madeconomy.blocks.ModBlocks;
import tech.connordavis.madeconomy.registry.ModRegistry;

public class ModTileEntityTypes {
    public static final RegistryObject<TileEntityType<MagicalSafeTileEntity>> MAGICAL_SAFE = ModRegistry.TILE_ENTITIES.register("magical_safe", () ->
            TileEntityType.Builder.create(MagicalSafeTileEntity::new, ModBlocks.MAGICAL_SAFE.get()).build(null));

    public static void register() {
    }
}
