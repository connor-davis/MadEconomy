package tech.connordavis.madeconomy.worldgeneration;

import net.minecraft.block.Block;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.NoPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.fml.RegistryObject;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Optional;

public class CountedOreFeature extends OreFeature<NoPlacementConfig> {
    private final ConfigInt clusterCount;

    public CountedOreFeature(RegistryObject<Block> block, int clusterSize, int clusterCount) {
        super(block, clusterSize);
        this.clusterCount = i(clusterCount, 0, "clusterCount");
    }

    @Override
    protected boolean canGenerate() {
        return super.canGenerate() && clusterCount.get() > 0;
    }

    @Override
    protected Pair<Placement<NoPlacementConfig>, NoPlacementConfig> getPlacement() {
        return Pair.of(Placement.NOPE, IPlacementConfig.NO_PLACEMENT_CONFIG);
    }

    @Override
    public Optional<ConfiguredFeature<?, ?>> createFeature(BiomeLoadingEvent biome) {
        return super.createFeature(biome).map(cf -> cf.repeat(this.clusterCount.get()));
    }
}
