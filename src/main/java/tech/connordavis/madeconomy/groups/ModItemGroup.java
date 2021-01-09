package tech.connordavis.madeconomy.groups;

import net.minecraft.item.ItemGroup;

public enum ModItemGroup {
    MAIN_GROUP(new MainItemGroup("madeconomy"));

    private final ItemGroup itemGroup;

    ModItemGroup(ItemGroup itemGroup) {
        this.itemGroup = itemGroup;
    }

    public ItemGroup get() {
        return itemGroup;
    }
}
