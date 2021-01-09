package tech.connordavis.madeconomy.features;

import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.event.world.BiomeLoadingEvent;

import java.util.Optional;

public interface IFeature {
    public void setId(String id);

    public void addToConfig(ForgeConfigSpec.Builder builder);

    public Optional<ConfiguredFeature<?, ?>> createFeature(BiomeLoadingEvent biome);

    public GenerationStage.Decoration getGenerationStage();

}