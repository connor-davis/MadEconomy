package tech.connordavis.madeconomy.worldgeneration;

import net.minecraft.block.Block;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.fml.RegistryObject;
import org.apache.commons.lang3.tuple.Pair;

public class ChanceOreFeature extends OreFeature<ChanceConfig> {
    private final ConfigFloat clusterChance;

    public ChanceOreFeature(RegistryObject<Block> block, int clusterSize, float clusterChance) {
        super(block, clusterSize);
        this.clusterChance = f(clusterChance, 0, 1, "clusterChance");
    }

    @Override
    protected boolean canGenerate() {
        return super.canGenerate() && clusterChance.get() > 0;
    }

    @Override
    protected Pair<Placement<ChanceConfig>, ChanceConfig> getPlacement() {
        return Pair.of(Placement.CHANCE,
                new ChanceConfig((int) (1 / clusterChance.getF())));
    }
}