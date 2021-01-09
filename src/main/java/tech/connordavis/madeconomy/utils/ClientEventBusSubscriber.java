package tech.connordavis.madeconomy.utils;

import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import tech.connordavis.madeconomy.MadEconomy;
import tech.connordavis.madeconomy.containers.ModContainerTypes;
import tech.connordavis.madeconomy.gui.MagicalSafeScreen;
import tech.connordavis.madeconomy.tileentities.renderer.MagicalSafeRenderer;
import tech.connordavis.madeconomy.tileentities.ModTileEntityTypes;

@Mod.EventBusSubscriber(modid = MadEconomy.ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber {
    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        ScreenManager.registerFactory(ModContainerTypes.MAGICAL_SAFE.get(), MagicalSafeScreen::new);

        ClientRegistry.bindTileEntityRenderer(ModTileEntityTypes.MAGICAL_SAFE.get(), MagicalSafeRenderer::new);
    }
}
