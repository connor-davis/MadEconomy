package tech.connordavis.madeconomy.items;

import net.minecraft.item.Item;
import tech.connordavis.madeconomy.groups.ModItemGroup;

public class Wallet extends Item {
    public Wallet() {
        super(new Item.Properties().group(ModItemGroup.MAIN_GROUP));
    }
}
