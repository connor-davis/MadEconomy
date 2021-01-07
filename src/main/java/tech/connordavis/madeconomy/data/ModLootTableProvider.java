package tech.connordavis.madeconomy.data;

import net.minecraft.data.DataGenerator;
import tech.connordavis.madeconomy.blocks.ModBlocks;
import tech.connordavis.madeconomy.utils.BaseLootTableProvider;

public class ModLootTableProvider extends BaseLootTableProvider {
    public ModLootTableProvider(DataGenerator dataGeneratorIn) {
        super(dataGeneratorIn);
    }

    @Override
    protected void addTables() {
        lootTables.put(ModBlocks.SILVER_ORE.get(), createStandardTable("silver_ore", ModBlocks.SILVER_ORE.get()));
        lootTables.put(ModBlocks.SILVER_BLOCK.get(), createStandardTable("silver_block", ModBlocks.SILVER_BLOCK.get()));
        lootTables.put(ModBlocks.MAGICAL_SAFE.get(), createStandardTable("magical_safe", ModBlocks.MAGICAL_SAFE.get()));
    }
}
