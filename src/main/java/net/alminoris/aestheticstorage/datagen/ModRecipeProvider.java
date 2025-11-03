package net.alminoris.aestheticstorage.datagen;

import net.alminoris.aestheticstorage.block.ModBlocks;
import net.alminoris.aestheticstorage.item.ModItemGroups;
import net.alminoris.aestheticstorage.item.ModItems;
import net.alminoris.aestheticstorage.util.helper.BlockSetsHelper;
import net.alminoris.aestheticstorage.util.helper.ModJsonHelper;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder
{
    public ModRecipeProvider(DataGenerator pOutput)
    {
        super(pOutput);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> recipeExporter)
    {
        ShapedRecipeBuilder.shaped(ModItems.WRENCH.get(), 1)
                .pattern(" # ")
                .pattern(" ##")
                .pattern("/  ")
                .define('#', Items.IRON_INGOT)
                .define('/', Items.STICK)
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                .save(recipeExporter);

        for(String name : BlockSetsHelper.WOODS)
        {
            String blockName = (name.equals("crimson") || name.equals("warped")) ? "stem" : (name.equals("bamboo") ? "block" : "log");
            Block block = ForgeRegistries.BLOCKS.getValue(ResourceLocation.withDefaultNamespace("stripped_"+name+"_"+blockName));
            Block block1 = ForgeRegistries.BLOCKS.getValue(ResourceLocation.withDefaultNamespace(name+"_"+blockName));
            registerCabinet(recipeExporter, ModBlocks.CABINETS.get(name).get(), block1, block);
            registerCabinetFlipdown(recipeExporter, ModBlocks.FLIPDOWN_CABINETS.get(name).get(), block1, block);
            registerCabinetFlipup(recipeExporter, ModBlocks.FLIPUP_CABINETS.get(name).get(), block1, block);
            registerCupboard(recipeExporter, ModBlocks.CUPBOARDS.get(name).get(), block1, block);

            registerHalfCabinet(recipeExporter, ModBlocks.HALFCABINETS.get(name).get(), block1, block);
            registerHalfCabinetFlipdown(recipeExporter, ModBlocks.FLIPDOWN_HALFCABINETS.get(name).get(), block1, block);
            registerHalfCabinetFlipup(recipeExporter, ModBlocks.FLIPUP_HALFCABINETS.get(name).get(), block1, block);
            registerHalfCupboard(recipeExporter, ModBlocks.HALFCUPBOARDS.get(name).get(), block1, block);
        }

        for(String name : ModItemGroups.AN_WOOD_NAMES)
        {
            ModJsonHelper.createShapedRecipe("cabinet_" + name, "4", "arborealnature:" + name + "_log", "arborealnature:stripped_" + name + "_log",
                    "\"/#\",", "\"/#\"", "");

            ModJsonHelper.createShapedRecipe("cabinet_flipdown_" + name, "4", "arborealnature:" + name + "_log", "arborealnature:stripped_" + name + "_log",
                    "\"##\",", "\"//\"", "");

            ModJsonHelper.createShapedRecipe("cabinet_flipup_" + name, "4", "arborealnature:" + name + "_log", "arborealnature:stripped_" + name + "_log",
                    "\"//\",", "\"##\"", "");

            ModJsonHelper.createShapedRecipe("cupboard_" + name, "4", "arborealnature:" + name + "_log", "arborealnature:stripped_" + name + "_log",
                    "\"/#\",", "\"/#\",", "\"/#\"");

            ModJsonHelper.createShapedRecipe("halfcabinet_" + name, "4", "arborealnature:" + name + "_log", "arborealnature:stripped_" + name + "_log",
                    "\"/ \",", "\" #\"", "");

            ModJsonHelper.createShapedRecipe("halfcabinet_flipdown_" + name, "4", "arborealnature:" + name + "_log", "arborealnature:stripped_" + name + "_log",
                    "\"#\",", "\"/\"", "");

            ModJsonHelper.createShapedRecipe("halfcabinet_flipup_" + name, "4", "arborealnature:" + name + "_log", "arborealnature:stripped_" + name + "_log",
                    "\"/\",", "\"#\"", "");

            ModJsonHelper.createShapedRecipe("halfcupboard_" + name, "4", "arborealnature:" + name + "_log", "arborealnature:stripped_" + name + "_log",
                    "\"#\",", "\"/\",", "\"#\"");
        }

        for(String name : ModItemGroups.WF_WOOD_NAMES)
        {
            ModJsonHelper.createShapedRecipe("cabinet_" + name, "4", "wildfields:" + name + "_log", "wildfields:stripped_" + name + "_log",
                    "\"/#\",", "\"/#\"", "");

            ModJsonHelper.createShapedRecipe("cabinet_flipdown_" + name, "4", "wildfields:" + name + "_log", "wildfields:stripped_" + name + "_log",
                    "\"##\",", "\"//\"", "");

            ModJsonHelper.createShapedRecipe("cabinet_flipup_" + name, "4", "wildfields:" + name + "_log", "wildfields:stripped_" + name + "_log",
                    "\"//\",", "\"##\"", "");

            ModJsonHelper.createShapedRecipe("cupboard_" + name, "4", "wildfields:" + name + "_log", "wildfields:stripped_" + name + "_log",
                    "\"/#\",", "\"/#\",", "\"/#\"");

            ModJsonHelper.createShapedRecipe("halfcabinet_" + name, "4", "wildfields:" + name + "_log", "wildfields:stripped_" + name + "_log",
                    "\"/ \",", "\" #\"", "");

            ModJsonHelper.createShapedRecipe("halfcabinet_flipdown_" + name, "4", "wildfields:" + name + "_log", "wildfields:stripped_" + name + "_log",
                    "\"#\",", "\"/\"", "");

            ModJsonHelper.createShapedRecipe("halfcabinet_flipup_" + name, "4", "wildfields:" + name + "_log", "wildfields:stripped_" + name + "_log",
                    "\"/\",", "\"#\"", "");

            ModJsonHelper.createShapedRecipe("halfcupboard_" + name, "4", "wildfields:" + name + "_log", "wildfields:stripped_" + name + "_log",
                    "\"#\",", "\"/\",", "\"#\"");
        }

        for(String name : ModItemGroups.WT_WOOD_NAMES)
        {
            ModJsonHelper.createShapedRecipe("cabinet_" + name, "4", "whisperleaftrees:" + name + "_log", "whisperleaftrees:stripped_" + name + "_log",
                    "\"/#\",", "\"/#\"", "");

            ModJsonHelper.createShapedRecipe("cabinet_flipdown_" + name, "4", "whisperleaftrees:" + name + "_log", "whisperleaftrees:stripped_" + name + "_log",
                    "\"##\",", "\"//\"", "");

            ModJsonHelper.createShapedRecipe("cabinet_flipup_" + name, "4", "whisperleaftrees:" + name + "_log", "whisperleaftrees:stripped_" + name + "_log",
                    "\"//\",", "\"##\"", "");

            ModJsonHelper.createShapedRecipe("cupboard_" + name, "4", "whisperleaftrees:" + name + "_log", "whisperleaftrees:stripped_" + name + "_log",
                    "\"/#\",", "\"/#\",", "\"/#\"");

            ModJsonHelper.createShapedRecipe("halfcabinet_" + name, "4", "whisperleaftrees:" + name + "_log", "whisperleaftrees:stripped_" + name + "_log",
                    "\"/ \",", "\" #\"", "");

            ModJsonHelper.createShapedRecipe("halfcabinet_flipdown_" + name, "4", "whisperleaftrees:" + name + "_log", "whisperleaftrees:stripped_" + name + "_log",
                    "\"#\",", "\"/\"", "");

            ModJsonHelper.createShapedRecipe("halfcabinet_flipup_" + name, "4", "whisperleaftrees:" + name + "_log", "whisperleaftrees:stripped_" + name + "_log",
                    "\"/\",", "\"#\"", "");

            ModJsonHelper.createShapedRecipe("halfcupboard_" + name, "4", "whisperleaftrees:" + name + "_log", "whisperleaftrees:stripped_" + name + "_log",
                    "\"#\",", "\"/\",", "\"#\"");
        }

        for(String name : ModItemGroups.ST_WOOD_NAMES)
        {
            ModJsonHelper.createShapedRecipe("cabinet_" + name, "4", "silverwoodtrees:" + name + "_log", "silverwoodtrees:stripped_" + name + "_log",
                    "\"/#\",", "\"/#\"", "");

            ModJsonHelper.createShapedRecipe("cabinet_flipdown_" + name, "4", "silverwoodtrees:" + name + "_log", "silverwoodtrees:stripped_" + name + "_log",
                    "\"##\",", "\"//\"", "");

            ModJsonHelper.createShapedRecipe("cabinet_flipup_" + name, "4", "silverwoodtrees:" + name + "_log", "silverwoodtrees:stripped_" + name + "_log",
                    "\"//\",", "\"##\"", "");

            ModJsonHelper.createShapedRecipe("cupboard_" + name, "4", "silverwoodtrees:" + name + "_log", "silverwoodtrees:stripped_" + name + "_log",
                    "\"/#\",", "\"/#\",", "\"/#\"");

            ModJsonHelper.createShapedRecipe("halfcabinet_" + name, "4", "silverwoodtrees:" + name + "_log", "silverwoodtrees:stripped_" + name + "_log",
                    "\"/ \",", "\" #\"", "");

            ModJsonHelper.createShapedRecipe("halfcabinet_flipdown_" + name, "4", "silverwoodtrees:" + name + "_log", "silverwoodtrees:stripped_" + name + "_log",
                    "\"#\",", "\"/\"", "");

            ModJsonHelper.createShapedRecipe("halfcabinet_flipup_" + name, "4", "silverwoodtrees:" + name + "_log", "silverwoodtrees:stripped_" + name + "_log",
                    "\"/\",", "\"#\"", "");

            ModJsonHelper.createShapedRecipe("halfcupboard_" + name, "4", "silverwoodtrees:" + name + "_log", "silverwoodtrees:stripped_" + name + "_log",
                    "\"#\",", "\"/\",", "\"#\"");
        }

        for(String name : ModItemGroups.MT_WOOD_NAMES)
        {
            ModJsonHelper.createShapedRecipe("cabinet_" + name, "4", "missingtrees:" + name + "_log", "missingtrees:stripped_" + name + "_log",
                    "\"/#\",", "\"/#\"", "");

            ModJsonHelper.createShapedRecipe("cabinet_flipdown_" + name, "4", "missingtrees:" + name + "_log", "missingtrees:stripped_" + name + "_log",
                    "\"##\",", "\"//\"", "");

            ModJsonHelper.createShapedRecipe("cabinet_flipup_" + name, "4", "missingtrees:" + name + "_log", "missingtrees:stripped_" + name + "_log",
                    "\"//\",", "\"##\"", "");

            ModJsonHelper.createShapedRecipe("cupboard_" + name, "4", "missingtrees:" + name + "_log", "missingtrees:stripped_" + name + "_log",
                    "\"/#\",", "\"/#\",", "\"/#\"");

            ModJsonHelper.createShapedRecipe("halfcabinet_" + name, "4", "missingtrees:" + name + "_log", "missingtrees:stripped_" + name + "_log",
                    "\"/ \",", "\" #\"", "");

            ModJsonHelper.createShapedRecipe("halfcabinet_flipdown_" + name, "4", "missingtrees:" + name + "_log", "missingtrees:stripped_" + name + "_log",
                    "\"#\",", "\"/\"", "");

            ModJsonHelper.createShapedRecipe("halfcabinet_flipup_" + name, "4", "missingtrees:" + name + "_log", "missingtrees:stripped_" + name + "_log",
                    "\"/\",", "\"#\"", "");

            ModJsonHelper.createShapedRecipe("halfcupboard_" + name, "4", "missingtrees:" + name + "_log", "missingtrees:stripped_" + name + "_log",
                    "\"#\",", "\"/\",", "\"#\"");
        }

        for(String name : ModItemGroups.NSS_WOOD_NAMES)
        {
            ModJsonHelper.createShapedRecipe("cabinet_" + name, "4", "natures_spirit:" + name.replace("_nss", "")
                            + "_log", "natures_spirit:stripped_" + name.replace("_nss", "") + "_log",
                    "\"/#\",", "\"/#\"", "");

            ModJsonHelper.createShapedRecipe("cabinet_flipdown_" + name, "4", "natures_spirit:" + name.replace("_nss", "")
                            + "_log", "natures_spirit:stripped_" + name.replace("_nss", "") + "_log",
                    "\"##\",", "\"//\"", "");

            ModJsonHelper.createShapedRecipe("cabinet_flipup_" + name, "4", "natures_spirit:" + name.replace("_nss", "")
                            + "_log", "natures_spirit:stripped_" + name.replace("_nss", "") + "_log",
                    "\"//\",", "\"##\"", "");

            ModJsonHelper.createShapedRecipe("cupboard_" + name, "4", "natures_spirit:" + name.replace("_nss", "")
                            + "_log", "natures_spirit:stripped_" + name.replace("_nss", "") + "_log",
                    "\"/#\",", "\"/#\",", "\"/#\"");

            ModJsonHelper.createShapedRecipe("halfcabinet_" + name, "4", "natures_spirit:" + name.replace("_nss", "")
                            + "_log", "natures_spirit:stripped_" + name.replace("_nss", "") + "_log",
                    "\"/ \",", "\" #\"", "");

            ModJsonHelper.createShapedRecipe("halfcabinet_flipdown_" + name, "4", "natures_spirit:" + name.replace("_nss", "")
                            + "_log", "natures_spirit:stripped_" + name.replace("_nss", "") + "_log",
                    "\"#\",", "\"/\"", "");

            ModJsonHelper.createShapedRecipe("halfcabinet_flipup_" + name, "4", "natures_spirit:" + name.replace("_nss", "")
                            + "_log", "natures_spirit:stripped_" + name.replace("_nss", "") + "_log",
                    "\"/\",", "\"#\"", "");

            ModJsonHelper.createShapedRecipe("halfcupboard_" + name, "4", "natures_spirit:" + name.replace("_nss", "")
                            + "_log", "natures_spirit:stripped_" + name.replace("_nss", "") + "_log",
                    "\"#\",", "\"/\",", "\"#\"");
        }
    }

    private void registerHalfCabinet(Consumer<FinishedRecipe> recipeExporter, Block output, Block ing1, Block ing2)
    {
        ShapedRecipeBuilder.shaped(output, 3)
                .pattern("/ ")
                .pattern(" #")
                .define('#', ing1)
                .define('/', ing2)
                .unlockedBy(getHasName(ing1), has(ing1))
                .unlockedBy(getHasName(ing2), has(ing2))
                .save(recipeExporter);
    }

    private void registerHalfCabinetFlipup(Consumer<FinishedRecipe> recipeExporter, Block output, Block ing1, Block ing2)
    {
        ShapedRecipeBuilder.shaped(output, 3)
                .pattern("/")
                .pattern("#")
                .define('#', ing1)
                .define('/', ing2)
                .unlockedBy(getHasName(ing1), has(ing1))
                .unlockedBy(getHasName(ing2), has(ing2))
                .save(recipeExporter);
    }

    private void registerHalfCabinetFlipdown(Consumer<FinishedRecipe> recipeExporter, Block output, Block ing1, Block ing2)
    {
        ShapedRecipeBuilder.shaped(output, 3)
                .pattern("#")
                .pattern("/")
                .define('#', ing1)
                .define('/', ing2)
                .unlockedBy(getHasName(ing1), has(ing1))
                .unlockedBy(getHasName(ing2), has(ing2))
                .save(recipeExporter);
    }

    private void registerHalfCupboard(Consumer<FinishedRecipe> recipeExporter, Block output, Block ing1, Block ing2)
    {
        ShapedRecipeBuilder.shaped(output, 3)
                .pattern("#")
                .pattern("/")
                .pattern("#")
                .define('#', ing1)
                .define('/', ing2)
                .unlockedBy(getHasName(ing1), has(ing1))
                .unlockedBy(getHasName(ing2), has(ing2))
                .save(recipeExporter);
    }

    private void registerCabinet(Consumer<FinishedRecipe> recipeExporter, Block output, Block ing1, Block ing2)
    {
        ShapedRecipeBuilder.shaped(output, 4)
                .pattern("/#")
                .pattern("/#")
                .define('#', ing1)
                .define('/', ing2)
                .unlockedBy(getHasName(ing1), has(ing1))
                .unlockedBy(getHasName(ing2), has(ing2))
                .save(recipeExporter);
    }

    private void registerCabinetFlipup(Consumer<FinishedRecipe> recipeExporter, Block output, Block ing1, Block ing2)
    {
        ShapedRecipeBuilder.shaped(output, 4)
                .pattern("//")
                .pattern("##")
                .define('#', ing1)
                .define('/', ing2)
                .unlockedBy(getHasName(ing1), has(ing1))
                .unlockedBy(getHasName(ing2), has(ing2))
                .save(recipeExporter);
    }

    private void registerCabinetFlipdown(Consumer<FinishedRecipe> recipeExporter, Block output, Block ing1, Block ing2)
    {
        ShapedRecipeBuilder.shaped(output, 4)
                .pattern("##")
                .pattern("//")
                .define('#', ing1)
                .define('/', ing2)
                .unlockedBy(getHasName(ing1), has(ing1))
                .unlockedBy(getHasName(ing2), has(ing2))
                .save(recipeExporter);
    }

    private void registerCupboard(Consumer<FinishedRecipe> recipeExporter, Block output, Block ing1, Block ing2)
    {
        ShapedRecipeBuilder.shaped(output, 4)
                .pattern("/#")
                .pattern("/#")
                .pattern("/#")
                .define('#', ing1)
                .define('/', ing2)
                .unlockedBy(getHasName(ing1), has(ing1))
                .unlockedBy(getHasName(ing2), has(ing2))
                .save(recipeExporter);
    }
}