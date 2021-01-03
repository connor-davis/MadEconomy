package tech.connordavis.madeconomy.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

/**
 * This is a coin item that players can use to trade with around the mod pack playthrough.
 */
public class SilverCoin extends Item {
    public SilverCoin() {
        super(new Item.Properties().group(ItemGroup.MATERIALS));
    }
}
