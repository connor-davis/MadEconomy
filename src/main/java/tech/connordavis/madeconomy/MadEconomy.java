package tech.connordavis.madeconomy;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tech.connordavis.madeconomy.config.ModConfigs;
import tech.connordavis.madeconomy.features.ModWorldFeatures;
import tech.connordavis.madeconomy.registry.ModRegistry;

@Mod(MadEconomy.ID)
public class MadEconomy {
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String ID = "madeconomy";
    public static final int VERSION = 1;

    public MadEconomy() {
        ModRegistry.register();
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGHEST, MadEconomy::onBiomeLoad);
        eventBus.addListener(ModConfigs::onLoad);
        eventBus.addListener(ModConfigs::onReload);

        ModConfigs.register();
    }

    public static void onBiomeLoad(BiomeLoadingEvent event) {
        ModWorldFeatures.reload(event);
    }
}
