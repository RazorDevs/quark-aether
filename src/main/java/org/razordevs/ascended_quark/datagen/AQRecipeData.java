package org.razordevs.ascended_quark.datagen;

import com.aetherteam.aether.AetherTags;
import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.aether.item.AetherItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;
import org.razordevs.ascended_quark.AscendedQuark;
import org.razordevs.ascended_quark.datagen.recipe.ConditionalShapedRecipeBuilder;
import org.razordevs.ascended_quark.datagen.recipe.ConditionalShapelessRecipeBuilder;
import org.razordevs.ascended_quark.datagen.recipe.ConditionalSingleItemBuilder;
import org.violetmoon.quark.base.Quark;
import oshi.util.tuples.Pair;
import teamrazor.deepaether.init.DABlocks;

import java.util.HashMap;
import java.util.function.Consumer;

public class AQRecipeData extends RecipeProvider {

    public static final ResourceLocation DEFAULT_FLAG = new ResourceLocation(AscendedQuark.MODID, "flag");
    public static final ResourceLocation QUARK_FLAG = new ResourceLocation(Quark.MOD_ID, "flag");

    private final HashMap<String, Item> itemMap;
    private final HashMap<String, Block> blockMap;

    private static final String DA_WOOD = "deep_aether_wood";

    public AQRecipeData(PackOutput output, HashMap<String, Item> itemMap, HashMap<String, Block> blockMap) {
        super(output);
        this.itemMap = itemMap;
        this.blockMap = blockMap;
    }

    @Override
    protected void buildRecipes(@NotNull Consumer<FinishedRecipe> consumer) {
        skyrootHedge(blockMap.get("decorated_holiday_skyroot_hedge"), AetherBlocks.DECORATED_HOLIDAY_LEAVES.get(), consumer);

        woodset("roseroot", DABlocks.ROSEROOT_PLANKS.get(), DABlocks.ROSEROOT_LOG.get(), DABlocks.ROSEROOT_WOOD.get(), DABlocks.STRIPPED_ROSEROOT_WOOD.get(), DABlocks.ROSEROOT_LEAVES.get(), DABlocks.ROSEROOT_SLAB.get(), DA_WOOD, consumer);
        this.hedge(blockMap.get("flowering_roseroot_hedge"), DABlocks.FLOWERING_ROSEROOT_LEAVES.get(), DABlocks.ROSEROOT_LOG.get(), DEFAULT_FLAG, DA_WOOD, consumer);
        this.hedge(blockMap.get("blue_roseroot_hedge"), DABlocks.BLUE_ROSEROOT_LEAVES.get(), DABlocks.ROSEROOT_LOG.get(), DEFAULT_FLAG, DA_WOOD, consumer);
        this.hedge(blockMap.get("flowering_blue_roseroot_hedge"), DABlocks.FLOWERING_BLUE_ROSEROOT_LEAVES.get(), DABlocks.ROSEROOT_LOG.get(), DEFAULT_FLAG, DA_WOOD, consumer);
        this.carpet(blockMap.get("flowering_roseroot_leaf_carpet"), DABlocks.FLOWERING_ROSEROOT_LEAVES.get(), DA_WOOD, consumer);
        this.carpet(blockMap.get("blue_roseroot_leaf_carpet"), DABlocks.BLUE_ROSEROOT_LEAVES.get(), DA_WOOD, consumer);
        this.carpet(blockMap.get("flowering_blue_roseroot_leaf_carpet"), DABlocks.FLOWERING_BLUE_ROSEROOT_LEAVES.get(), DA_WOOD, consumer);

        woodset("cruderoot", DABlocks.CRUDEROOT_PLANKS.get(), DABlocks.CRUDEROOT_LOG.get(), DABlocks.CRUDEROOT_WOOD.get(), DABlocks.STRIPPED_CRUDEROOT_WOOD.get(), DABlocks.CRUDEROOT_LEAVES.get(), DABlocks.CRUDEROOT_SLAB.get(), DA_WOOD, consumer);
        woodset("sunroot", DABlocks.SUNROOT_PLANKS.get(), DABlocks.SUNROOT_LOG.get(), DABlocks.SUNROOT_WOOD.get(), DABlocks.STRIPPED_SUNROOT_WOOD.get(), DABlocks.SUNROOT_LEAVES.get(), DABlocks.SUNROOT_SLAB.get(), DA_WOOD, consumer);
        woodset("yagroot", DABlocks.YAGROOT_PLANKS.get(), DABlocks.YAGROOT_LOG.get(), DABlocks.YAGROOT_WOOD.get(), DABlocks.STRIPPED_YAGROOT_WOOD.get(), DABlocks.YAGROOT_LEAVES.get(), DABlocks.YAGROOT_SLAB.get(), DA_WOOD, consumer);
        woodset("conberry", DABlocks.CONBERRY_PLANKS.get(), DABlocks.CONBERRY_LOG.get(), DABlocks.CONBERRY_WOOD.get(), DABlocks.STRIPPED_CONBERRY_WOOD.get(), DABlocks.CONBERRY_LEAVES.get(), DABlocks.CONBERRY_SLAB.get(), DA_WOOD, consumer);

        ConditionalShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, blockMap.get("aether_mud_pillar"))
                .define('A', DABlocks.AETHER_MUD_BRICKS_SLAB.get())
                .pattern("A")
                .pattern("A")
                .condition(QUARK_FLAG, "more_mud_blocks")
                .condition(DEFAULT_FLAG, "more_aether_mud_blocks")
                .unlockedBy(getHasName(DABlocks.AETHER_MUD_BRICKS_SLAB.get()), has(DABlocks.AETHER_MUD_BRICKS_SLAB.get()))
                .save(consumer);

        ConditionalShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, blockMap.get("aether_mud_brick_lattice"), 4)
                .define('A', DABlocks.AETHER_MUD_BRICKS.get())
                .pattern(" A ")
                .pattern("A A")
                .pattern(" A ")
                .condition(QUARK_FLAG, "more_mud_blocks")
                .condition(DEFAULT_FLAG, "more_aether_mud_blocks")
                .unlockedBy(getHasName(DABlocks.AETHER_MUD_BRICKS.get()), has(DABlocks.AETHER_MUD_BRICKS.get()))
                .save(consumer);

        stonecuttingRecipe(blockMap.get("aether_mud_pillar"), DABlocks.AETHER_MUD_BRICKS.get(), consumer,
                new Pair<>(QUARK_FLAG, "more_mud_blocks"),
                new Pair<>(DEFAULT_FLAG, "more_aether_mud_blocks"));
    }

    private void woodset(String type, Block planks, Block log, Block wood, Block strippedWood, Block leaves, Block slab, String flag, Consumer<FinishedRecipe> consumer) {
        this.verticalPlanks(blockMap.get("vertical_" + type + "_planks"), planks, flag, consumer);
        this.hedge(blockMap.get(type+"_hedge"), leaves, log, DEFAULT_FLAG, flag, consumer);
        this.verticalSlab(blockMap.get(type + "_vertical_slab"), slab, flag, consumer);
        this.carpet(blockMap.get(type + "_leaf_carpet"), leaves, flag, consumer);
        this.post(blockMap.get(type + "_post"), wood, flag, consumer);
        this.post(blockMap.get("stripped_" + type + "_post"), strippedWood, flag, consumer);
        this.chest(blockMap.get(type+"_chest"), planks, flag, consumer);
        this.chest(blockMap.get(type+"_trapped_chest"), planks, flag, consumer);
        this.hollowLog(blockMap.get("hollow_" + type + "_log"), log, flag, consumer);
        this.ladder(blockMap.get(type + "_ladder"), planks, flag, consumer);
        this.bookshelf(blockMap.get(type + "_bookshelf"), planks, flag, consumer);
    }

    private void bookshelf(Block bookshelf, Block planks, String flag, Consumer<FinishedRecipe> consumer) {
        ConditionalShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, bookshelf, 1)
                .define('A', planks)
                .define('B', Items.BOOK)
                .pattern("AAA")
                .pattern("BBB")
                .pattern("AAA")
                .condition(new ResourceLocation(Quark.MOD_ID, "flag"), "variant_bookshelves")
                .condition(DEFAULT_FLAG, flag)
                .unlockedBy(getHasName(planks), has(planks)).save(consumer);
    }

    void slab(Block slab, Block texture, Consumer<FinishedRecipe> consumer) {
        slabBuilder(RecipeCategory.BUILDING_BLOCKS, slab, Ingredient.of(texture)).unlockedBy(getHasName(texture), has(texture)).save(consumer);
    }

    void verticalSlab(Block vertical, Block slab, String flag, Consumer<FinishedRecipe> consumer) {
        verticalSlabBuilder(vertical, Ingredient.of(slab), flag).unlockedBy(getHasName(slab), has(slab)).save(consumer);
        verticalSlabRevert(vertical, slab, consumer, flag);
    }

    protected static RecipeBuilder verticalSlabBuilder(ItemLike itemLike, Ingredient ingredient, String flag) {
        return ConditionalShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, itemLike, 3).define('#', ingredient)
                .pattern("#")
                .pattern("#")
                .pattern("#")
                .condition(new ResourceLocation(Quark.MOD_ID, "flag"), "vertical_slabs")
                .condition(DEFAULT_FLAG, flag);
    }

    void carpet(ItemLike carpet, ItemLike leaf, String flag, Consumer<FinishedRecipe> consumer) {
        ConditionalShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, carpet, 3).define('A', leaf)
                .pattern("AA")
                .condition(new ResourceLocation(Quark.MOD_ID, "flag"), "leaf_carpet")
                .condition(DEFAULT_FLAG, flag)
                .unlockedBy(getHasName(leaf), has(leaf)).save(consumer);
    }

    void post(ItemLike post, ItemLike wood, String flag, Consumer<FinishedRecipe> consumer) {
        ConditionalShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, post, 8)
                .define('A', wood)
                .pattern("A")
                .pattern("A")
                .pattern("A")
                .condition(new ResourceLocation(Quark.MOD_ID, "flag"), "wooden_posts")
                .condition(DEFAULT_FLAG, flag)
                .unlockedBy(getHasName(wood), has(wood)).save(consumer);
    }

    void verticalPlanks(ItemLike verticalPlanks, ItemLike planks, String flag, Consumer<FinishedRecipe> consumer) {
        ConditionalShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, verticalPlanks, 3)
                .define('A', planks)
                .pattern("A")
                .pattern("A")
                .pattern("A")
                .condition(new ResourceLocation(Quark.MOD_ID, "flag"), "vertical_planks")
                .condition(DEFAULT_FLAG, flag)
                .unlockedBy(getHasName(planks), has(planks)).save(consumer);

        ConditionalShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, planks)
                .condition(new ResourceLocation(Quark.MOD_ID, "flag"), "vertical_slabs")
                .condition(DEFAULT_FLAG, flag).requires(verticalPlanks)
                .unlockedBy(getHasName(planks), has(planks)).save(consumer, getItemName(verticalPlanks) + "_from_" + getItemName(planks));
    }

    void chest(ItemLike chest, ItemLike planks, String flag, Consumer<FinishedRecipe> consumer) {
        ConditionalShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, chest)
                .define('A', planks)
                .pattern("AAA")
                .pattern("A A")
                .pattern("AAA")
                .condition(new ResourceLocation(Quark.MOD_ID, "flag"), "variant_chests")
                .condition(DEFAULT_FLAG, flag)
                .unlockedBy(getHasName(planks), has(planks)).save(consumer);
    }

    void hollowLog(ItemLike hollowLog, ItemLike log, String flag, Consumer<FinishedRecipe> consumer) {
        ConditionalShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, hollowLog, 4)
                .define('A', log)
                .pattern(" A ")
                .pattern("A A")
                .pattern(" A ")
                .condition(new ResourceLocation(Quark.MOD_ID, "flag"), "hollow_logs")
                .condition(DEFAULT_FLAG, flag)
                .unlockedBy(getHasName(log), has(log)).save(consumer);
    }

    void ladder(ItemLike ladder, ItemLike planks, String flag, Consumer<FinishedRecipe> consumer){
        ConditionalShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ladder, 4)
                .define('A', planks)
                .define('-', AetherItems.SKYROOT_STICK.get())
                .pattern("- -")
                .pattern("-A-")
                .pattern("- -")
                .condition(new ResourceLocation(Quark.MOD_ID, "flag"), "variant_ladders")
                .condition(DEFAULT_FLAG, flag)
                .unlockedBy(getHasName(planks), has(planks)).save(consumer);
    }

    void verticalSlabRevert(Block slab, Block reverted, Consumer<FinishedRecipe> consumer, String flag) {
        verticalSlabRevertBuilder(reverted, Ingredient.of(slab), flag).unlockedBy(getHasName(slab), has(slab)).save(consumer, getItemName(reverted) + "_from_" + getItemName(slab));
    }

    void hedge(ItemLike hedge, ItemLike leaves, TagKey<Item> stem, ResourceLocation conditionLocation, String conditionName, Consumer<FinishedRecipe> consumer) {
        ConditionalShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, hedge,2)
                .define('A', stem)
                .define('B', leaves)
                .pattern("B")
                .pattern("A").unlockedBy(getHasName(leaves), has(leaves))
                .condition(new ResourceLocation(Quark.MOD_ID, "flag"), "hedges")
                .condition(conditionLocation, conditionName)
                .save(consumer);
    }

    void hedge(ItemLike hedge, ItemLike leaves, ItemLike stem, ResourceLocation conditionLocation, String conditionName, Consumer<FinishedRecipe> consumer) {
        ConditionalShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, hedge,2).define('A', stem)
                .define('B', leaves)
                .pattern("B")
                .pattern("A")
                .condition(new ResourceLocation(Quark.MOD_ID, "flag"), "hedges")
                .condition(conditionLocation, conditionName)
                .unlockedBy(getHasName(leaves), has(leaves)).save(consumer);
    }

    void skyrootHedge(ItemLike hedge, ItemLike leaves, Consumer<FinishedRecipe> consumer) {
        hedge(hedge, leaves, AetherTags.Items.SKYROOT_LOGS, DEFAULT_FLAG, "skyroot_quark_blocks", consumer);
    }
    protected static RecipeBuilder verticalSlabRevertBuilder(ItemLike itemLike, Ingredient ingredient, String flag) {
        return ConditionalShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, itemLike).condition(new ResourceLocation(Quark.MOD_ID, "flag"), "vertical_slabs")
                .condition(DEFAULT_FLAG, flag).requires(ingredient);
    }

    void slabRevert(Block slab, Block reverted, Consumer<FinishedRecipe> consumer) {
        slabRevertBuilder(reverted, Ingredient.of(slab)).unlockedBy(getHasName(slab), has(slab)).save(consumer, getItemName(reverted) + "_from_" + getItemName(slab));
    }

    protected static RecipeBuilder slabRevertBuilder(ItemLike itemLike, Ingredient ingredient) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, itemLike).define('A', ingredient).pattern("AA");
    }

    void stairs(Block stairs, Block texture, Consumer<FinishedRecipe> consumer) {
        stairBuilder(stairs, Ingredient.of(texture)).unlockedBy(getHasName(texture), has(texture)).save(consumer);
    }

    void wall(Block wall, Block texture, Consumer<FinishedRecipe> consumer) {
        wallBuilder(RecipeCategory.BUILDING_BLOCKS, wall, Ingredient.of(texture)).unlockedBy(getHasName(texture), has(texture)).save(consumer);
    }

    protected ResourceLocation name(String name) {
        return new ResourceLocation(AscendedQuark.MODID, name);
    }

    protected void stonecuttingRecipe(ItemLike item, ItemLike ingredient, Consumer<FinishedRecipe> consumer, Pair<ResourceLocation, String>... condition) {
        stonecuttingRecipe(item, ingredient, 1, consumer, condition);
    }

    protected void stonecuttingRecipe(ItemLike item, ItemLike ingredient, int count, Consumer<FinishedRecipe> consumer, Pair<ResourceLocation, String>[] condition) {
        ConditionalSingleItemBuilder.stonecutting(Ingredient.of(ingredient), RecipeCategory.BUILDING_BLOCKS, item, count)
                .condition(condition)
                .unlockedBy(getHasName(ingredient), has(ingredient)).save(consumer, name(getConversionRecipeName(item, ingredient) + "_stonecutting"));
    }
}
