package tech.connordavis.madeconomy.data.client;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemModelProvider;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import tech.connordavis.madeconomy.blocks.ModBlocks;
import tech.connordavis.madeconomy.utils.ModProperties;

public class ModBlockStateProvider extends BlockStateProvider {
    private final ModItemModelProvider modelProvider;

    public ModBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, ModProperties.MOD_ID, exFileHelper);

        this.modelProvider = new ModItemModelProvider(gen, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(ModBlocks.SILVER_BLOCK.get());
        simpleBlock(ModBlocks.SILVER_ORE.get());
    }
}
