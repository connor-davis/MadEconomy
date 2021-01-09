package tech.connordavis.madeconomy.data;

import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import tech.connordavis.madeconomy.MadEconomy;
import tech.connordavis.madeconomy.items.ModItems;
import tech.connordavis.madeconomy.tags.ModTags;
import tech.connordavis.madeconomy.utils.ModProperties;

public class ModItemTagsProvider extends ItemTagsProvider {
    public ModItemTagsProvider(DataGenerator dataGenerator, BlockTagsProvider blockTagProvider, ExistingFileHelper existingFileHelper) {
        super(dataGenerator, blockTagProvider, MadEconomy.ID, existingFileHelper);
    }

    @Override
    protected void registerTags() {
        copy(ModTags.Blocks.ORES_SILVER, ModTags.Items.ORES_SILVER);
        copy(Tags.Blocks.ORES, Tags.Items.ORES);
        copy(ModTags.Blocks.ORES_MAGIC, ModTags.Items.ORES_MAGIC);
        copy(Tags.Blocks.ORES, Tags.Items.ORES);
        copy(ModTags.Blocks.STORAGE_BLOCKS_SILVER, ModTags.Items.STORAGE_BLOCKS_SILVER);
        copy(Tags.Blocks.STORAGE_BLOCKS, Tags.Items.STORAGE_BLOCKS);

        getOrCreateTagBuilder(ModTags.Items.NUGGETS_SILVER).add(ModItems.SILVER_NUGGET.get());
        getOrCreateTagBuilder(Tags.Items.NUGGETS).addTag(ModTags.Items.NUGGETS_SILVER);
        getOrCreateTagBuilder(ModTags.Items.INGOTS_SILVER).add(ModItems.SILVER_INGOT.get());
        getOrCreateTagBuilder(Tags.Items.INGOTS).addTag(ModTags.Items.INGOTS_SILVER);
    }
}
