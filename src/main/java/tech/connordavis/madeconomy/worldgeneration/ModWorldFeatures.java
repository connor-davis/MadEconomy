package tech.connordavis.madeconomy.worldgeneration;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import tech.connordavis.madeconomy.blocks.ModBlocks;
import tech.connordavis.madeconomy.utils.Lang;

import java.util.Arrays;
import java.util.Optional;

public enum ModWorldFeatures {
    /**
     * Counted Ores
     */
    SILVER_ORE(new CountedOreFeature(ModBlocks.SILVER_ORE, 18, 2).between(40, 86)),

    /**
     * Chance Ores
     */
    MAGIC_ORE(new ChanceOreFeature(ModBlocks.MAGIC_ORE, 4, 1 / 32f).between(0, 30)),


    ;

    /**
     * Increment this number if all world gen entries should be overwritten in this
     * update. Worlds from the previous version will overwrite potentially changed
     * values with the new defaults.
     */
    public static final int forcedUpdateVersion = 1;

    public IFeature feature;

    ModWorldFeatures(IFeature feature) {
        this.feature = feature;
        this.feature.setId(Lang.asId(name()));
    }

    public static void reload(BiomeLoadingEvent event) {
        for (ModWorldFeatures entry : ModWorldFeatures.values()) {
            if (event.getName() == Biomes.THE_VOID.getRegistryName())
                continue;
            if (event.getCategory() == Biome.Category.NETHER)
                continue;

            Optional<ConfiguredFeature<?, ?>> createFeature = entry.feature.createFeature(event);
            if (!createFeature.isPresent())
                continue;

            event.getGeneration().feature(entry.feature.getGenerationStage(), createFeature.get());
        }
    }

    public static void fillConfig(ForgeConfigSpec.Builder builder) {
        Arrays.stream(values()).forEach(entry -> {
            builder.push(Lang.asId(entry.name()));
            entry.feature.addToConfig(builder);
            builder.pop();
        });
    }
}
