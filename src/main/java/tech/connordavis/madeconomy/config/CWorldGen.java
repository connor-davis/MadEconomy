package tech.connordavis.madeconomy.config;

import net.minecraftforge.common.ForgeConfigSpec;
import tech.connordavis.madeconomy.features.ModWorldFeatures;

public class CWorldGen extends ConfigBase {
    public ConfigBool disable = b(false, "disableWorldGen", Comments.disable);

    @Override
    protected void registerAll(ForgeConfigSpec.Builder builder) {
        super.registerAll(builder);
        ModWorldFeatures.fillConfig(builder);
    }

    @Override
    public String getName() {
        return "worldgen.v" + ModWorldFeatures.forcedUpdateVersion;
    }

    private static class Comments {
        static String disable = "Prevents all worldgen added by Create from taking effect";
    }

}
