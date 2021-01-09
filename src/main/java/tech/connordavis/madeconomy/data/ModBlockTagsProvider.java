package tech.connordavis.madeconomy.data;

import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import tech.connordavis.madeconomy.MadEconomy;
import tech.connordavis.madeconomy.blocks.ModBlocks;
import tech.connordavis.madeconomy.tags.ModTags;
import tech.connordavis.madeconomy.utils.ModProperties;

public class ModBlockTagsProvider extends BlockTagsProvider {
    public ModBlockTagsProvider(DataGenerator generatorIn, ExistingFileHelper existingFileHelper) {
        super(generatorIn, MadEconomy.ID, existingFileHelper);
    }

    @Override
    protected void registerTags() {
        getOrCreateTagBuilder(ModTags.Blocks.ORES_SILVER).add(ModBlocks.SILVER_ORE.get());
        getOrCreateTagBuilder(Tags.Blocks.ORES).addTag(ModTags.Blocks.ORES_SILVER);

        getOrCreateTagBuilder(ModTags.Blocks.STORAGE_BLOCKS_SILVER).add(ModBlocks.SILVER_BLOCK.get());
        getOrCreateTagBuilder(Tags.Blocks.STORAGE_BLOCKS).addTag(ModTags.Blocks.STORAGE_BLOCKS_SILVER);
    }
}
