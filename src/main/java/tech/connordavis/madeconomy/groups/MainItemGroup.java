package tech.connordavis.madeconomy.groups;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import tech.connordavis.madeconomy.items.ModItems;

public class MainItemGroup extends ItemGroup {
    public MainItemGroup(String label) {
        super(label);
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(ModItems.SILVER_COIN.get());
    }
}
