package tech.connordavis.madeconomy.items;

import net.minecraft.item.Item;
import tech.connordavis.madeconomy.groups.ModItemGroup;

/**
 * This is a coin item that players can use to trade with around the mod pack playthrough.
 */
public class SilverCoin extends Item {
    public SilverCoin() {
        super(new Item.Properties().group(ModItemGroup.MAIN_GROUP));
    }
}
