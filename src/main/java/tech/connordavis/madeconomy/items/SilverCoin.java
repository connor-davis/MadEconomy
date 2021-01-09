package tech.connordavis.madeconomy.items;

import net.minecraft.item.Item;

import static tech.connordavis.madeconomy.groups.ModItemGroup.MAIN_GROUP;

/**
 * This is a coin item that players can use to trade with around the mod pack playthrough.
 */
public class SilverCoin extends Item {
    public SilverCoin() {
        super(new Item.Properties().group(MAIN_GROUP.get()));
    }
}
