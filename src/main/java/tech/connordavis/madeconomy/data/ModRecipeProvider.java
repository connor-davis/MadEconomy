package tech.connordavis.madeconomy.data;

import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.data.*;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;
import tech.connordavis.madeconomy.blocks.ModBlocks;
import tech.connordavis.madeconomy.items.ModItems;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
        /*
          Shaped Recipes
         */
        ShapedRecipeBuilder
                .shapedRecipe(ModItems.SILVER_COIN.get())
                .patternLine("##")
                .patternLine("##")
                .key('#', ModItems.SILVER_NUGGET.get())
                .addCriterion("silver_coin", InventoryChangeTrigger.Instance.forItems(ModItems.SILVER_COIN.get()))
                .build(consumer);

        ShapedRecipeBuilder
                .shapedRecipe(ModItems.WALLET.get())
                .patternLine("###")
                .patternLine("#%#")
                .patternLine("###")
                .key('#', Tags.Items.LEATHER)
                .key('%', ModItems.SILVER_COIN.get())
                .addCriterion("wallet", InventoryChangeTrigger.Instance.forItems(ModItems.WALLET.get()))
                .build(consumer);

        /*
          Shapeless Recipes
         */
        ShapelessRecipeBuilder
                .shapelessRecipe(ModItems.SILVER_NUGGET.get(), 9)
                .addIngredient(ModItems.SILVER_INGOT.get())
                .addCriterion("silver_nugget", InventoryChangeTrigger.Instance.forItems(ModItems.SILVER_NUGGET.get()))
                .build(consumer);

        /*
          Smelting Recipes
         */
        CookingRecipeBuilder
                .smeltingRecipe(
                        Ingredient.fromItems(ModBlocks.SILVER_ORE.get()),
                        ModItems.SILVER_INGOT.get(),
                        0,
                        200)
                .addCriterion("silver_ingot", InventoryChangeTrigger.Instance.forItems(ModItems.SILVER_INGOT.get()))
                .build(consumer);

        CookingRecipeBuilder
                .smeltingRecipe(
                        Ingredient.fromItems(ModBlocks.MAGIC_ORE.get()),
                        ModItems.MAGIC_SHARD.get(),
                        0,
                        200)
                .addCriterion("magic_shard", InventoryChangeTrigger.Instance.forItems(ModItems.MAGIC_SHARD.get()))
                .build(consumer);
    }
}
