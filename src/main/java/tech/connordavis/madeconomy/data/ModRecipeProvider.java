package tech.connordavis.madeconomy.data;

import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.data.*;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;
import tech.connordavis.madeconomy.blocks.ModBlocks;
import tech.connordavis.madeconomy.items.ModItems;
import tech.connordavis.madeconomy.tags.ModTags;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder
                .shapedRecipe(ModItems.SILVER_COIN.get())
                .patternLine("##")
                .patternLine("##")
                .key('#', ModItems.SILVER_NUGGET.get())
                .addCriterion("silver_coin", InventoryChangeTrigger.Instance.forItems(ModItems.SILVER_COIN.get()))
                .build(consumer);

        ShapelessRecipeBuilder
                .shapelessRecipe(ModItems.SILVER_NUGGET.get(), 9)
                .addIngredient(ModItems.SILVER_INGOT.get())
                .addCriterion("silver_nugget", InventoryChangeTrigger.Instance.forItems(ModItems.SILVER_NUGGET.get()))
                .build(consumer);

        CookingRecipeBuilder
                .smeltingRecipe(
                        Ingredient.fromItems(ModBlocks.SILVER_ORE.get()),
                        ModItems.SILVER_INGOT.get(),
                        0,
                        200)
                .addCriterion("silver_ingot", InventoryChangeTrigger.Instance.forItems(ModItems.SILVER_INGOT.get()))
                .build(consumer);
    }
}
