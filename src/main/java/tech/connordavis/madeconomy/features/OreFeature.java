package tech.connordavis.madeconomy.features;

import net.minecraft.block.Block;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;
import net.minecraftforge.common.ForgeConfigSpec.Builder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.fml.RegistryObject;
import org.apache.commons.lang3.tuple.Pair;
import tech.connordavis.madeconomy.config.ConfigBase;
import tech.connordavis.madeconomy.config.ModConfigs;

import java.util.Optional;

public abstract class OreFeature<T extends IPlacementConfig> extends ConfigBase implements IFeature {
    public String id;

    protected ConfigBool enable;
    protected ConfigInt clusterSize;
    protected ConfigInt minHeight;
    protected ConfigInt maxHeight;

    private final RegistryObject<Block> block;
    private Biome.Category specificCategory;

    public OreFeature(RegistryObject<Block> block, int clusterSize) {
        this.block = block;
        this.enable = b(true, "enable", "Whether to spawn this in your World");
        this.clusterSize = i(clusterSize, 0, "clusterSize");
        this.minHeight = i(0, 0, "minHeight");
        this.maxHeight = i(256, 0, "maxHeight");
    }

    public OreFeature<T> between(int minHeight, int maxHeight) {
        allValues.remove(this.minHeight);
        allValues.remove(this.maxHeight);
        this.minHeight = i(minHeight, 0, "minHeight");
        this.maxHeight = i(maxHeight, 0, "maxHeight");
        return this;
    }

    public OreFeature<T> inBiomes(Biome.Category category) {
        specificCategory = category;
        return this;
    }

    @Override
    public void onReload() {

    }

    @Override
    public Optional<ConfiguredFeature<?, ?>> createFeature(BiomeLoadingEvent biome) {
        if (specificCategory != null && biome.getCategory() != specificCategory)
            return Optional.empty();
        if (canGenerate())
            return Optional.empty();

        Pair<Placement<T>, T> placement = getPlacement();
        ConfiguredFeature<?, ?> createdFeature = Feature.ORE
                .configure(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD, block.get()
                        .getDefaultState(), clusterSize.get()))
                .decorate(placement.getKey()
                        .configure(placement.getValue()))
                .decorate(Placement.RANGE
                        .configure(new TopSolidRangeConfig(minHeight.get(), 0, maxHeight.get() - minHeight.get())))
                .spreadHorizontally();

        return Optional.of(createdFeature);
    }

    @Override
    public GenerationStage.Decoration getGenerationStage() {
        return GenerationStage.Decoration.UNDERGROUND_ORES;
    }

    protected boolean canGenerate() {
        return minHeight.get() >= maxHeight.get() || clusterSize.get() <= 0 || !enable.get()
                || ModConfigs.COMMON.worldGen.disable.get();
    }

    protected abstract Pair<Placement<T>, T> getPlacement();

    @Override
    public void addToConfig(Builder builder) {
        registerAll(builder);
    }

    @Override
    public String getName() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

}
