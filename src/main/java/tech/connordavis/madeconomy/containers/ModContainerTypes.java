package tech.connordavis.madeconomy.containers;

import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import tech.connordavis.madeconomy.registry.ModRegistry;

public class ModContainerTypes {
    public static final RegistryObject<ContainerType<MagicalSafeContainer>> MAGICAL_SAFE = ModRegistry.CONTAINER_TYPES.register("magical_safe", () ->
            IForgeContainerType.create(MagicalSafeContainer::new));

    public static void register() {
    }
}
