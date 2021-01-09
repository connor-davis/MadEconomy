package tech.connordavis.madeconomy.items;

import net.minecraft.item.Item;

import static tech.connordavis.madeconomy.groups.ModItemGroup.MAIN_GROUP;

public class Wallet extends Item {
    public Wallet() {
        super(new Item.Properties().group(MAIN_GROUP.get()).maxStackSize(1));
    }
}
