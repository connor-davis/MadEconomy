package tech.connordavis.madeconomy;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tech.connordavis.madeconomy.registry.ModRegistry;
import tech.connordavis.madeconomy.utils.ModProperties;

@Mod(ModProperties.MOD_ID)
@Mod.EventBusSubscriber(modid = ModProperties.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class MadEconomy {
    public static final Logger LOGGER = LogManager.getLogger();

    public MadEconomy() {
        ModRegistry.register();

        MinecraftForge.EVENT_BUS.register(this);
    }

}
