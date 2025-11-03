package net.alminoris.aestheticstorage.datagen;

import net.alminoris.aestheticstorage.AestheticStorage;
import net.alminoris.aestheticstorage.block.ModBlocks;
import net.alminoris.aestheticstorage.item.ModItemGroups;
import net.alminoris.aestheticstorage.util.helper.BlockSetsHelper;
import net.alminoris.aestheticstorage.util.helper.ModJsonHelper;
import net.alminoris.aestheticstorage.util.helper.ModJsonTemplates;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

import java.util.Dictionary;
import java.util.Hashtable;

public class ModBlockStateProvider extends BlockStateProvider
{
    public ModBlockStateProvider(DataGenerator output, ExistingFileHelper exFileHelper)
    {
        super(output, AestheticStorage.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels()
    {
        for(String name : BlockSetsHelper.WOODS)
        {
            String logName = (name.equals("crimson") || name.equals("warped")) ? "stem" : (name.equals("bamboo") ? "block" : "log");
            registerCabinet( ModBlocks.CABINETS.get(name),
                    "minecraft:block/", "cabinet_"+name, "stripped_"+name+"_"+logName, name+"_"+logName);
            registerCabinetFlipup( ModBlocks.FLIPUP_CABINETS.get(name),
                    "minecraft:block/", "cabinet_flipup_"+name, "stripped_"+name+"_"+logName, name+"_"+logName);
            registerCabinetFlipdown( ModBlocks.FLIPDOWN_CABINETS.get(name),
                    "minecraft:block/", "cabinet_flipdown_"+name, "stripped_"+name+"_"+logName, name+"_"+logName);
            registerCupboard( ModBlocks.CUPBOARDS.get(name),
                    "minecraft:block/", "cupboard_"+name, "stripped_"+name+"_"+logName, name+"_"+logName);

            registerHalfCabinet( ModBlocks.HALFCABINETS.get(name),
                    "minecraft:block/", "halfcabinet_"+name, "stripped_"+name+"_"+logName, name+"_"+logName);
            registerHalfCabinetFlipup( ModBlocks.FLIPUP_HALFCABINETS.get(name),
                    "minecraft:block/", "halfcabinet_flipup_"+name, "stripped_"+name+"_"+logName, name+"_"+logName);
            registerHalfCabinetFlipdown( ModBlocks.FLIPDOWN_HALFCABINETS.get(name),
                    "minecraft:block/", "halfcabinet_flipdown_"+name, "stripped_"+name+"_"+logName, name+"_"+logName);
            registerHalfCupboard( ModBlocks.HALFCUPBOARDS.get(name),
                    "minecraft:block/", "halfcupboard_"+name, "stripped_"+name+"_"+logName, name+"_"+logName);
        }

        for(String name : ModItemGroups.AN_WOOD_NAMES)
        {
            registerCabinet( ModBlocks.CABINETS.get(name),
                    "aestheticstorage:block/", "cabinet_"+name, "stripped_"+name+"_log", name+"_log");
            registerCabinetFlipup( ModBlocks.FLIPUP_CABINETS.get(name),
                    "aestheticstorage:block/", "cabinet_flipup_"+name, "stripped_"+name+"_log", name+"_log");
            registerCabinetFlipdown( ModBlocks.FLIPDOWN_CABINETS.get(name),
                    "aestheticstorage:block/", "cabinet_flipdown_"+name, "stripped_"+name+"_log", name+"_log");
            registerCupboard( ModBlocks.CUPBOARDS.get(name),
                    "aestheticstorage:block/", "cupboard_"+name, "stripped_"+name+"_log", name+"_log");

            registerHalfCabinet( ModBlocks.HALFCABINETS.get(name),
                    "aestheticstorage:block/", "halfcabinet_"+name, "stripped_"+name+"_log", name+"_log");
            registerHalfCabinetFlipup( ModBlocks.FLIPUP_HALFCABINETS.get(name),
                    "aestheticstorage:block/", "halfcabinet_flipup_"+name, "stripped_"+name+"_log", name+"_log");
            registerHalfCabinetFlipdown( ModBlocks.FLIPDOWN_HALFCABINETS.get(name),
                    "aestheticstorage:block/", "halfcabinet_flipdown_"+name, "stripped_"+name+"_log", name+"_log");
            registerHalfCupboard( ModBlocks.HALFCUPBOARDS.get(name),
                    "aestheticstorage:block/", "halfcupboard_"+name, "stripped_"+name+"_log", name+"_log");
        }

        for(String name : ModItemGroups.WF_WOOD_NAMES)
        {
            registerCabinet(ModBlocks.CABINETS.get(name), "aestheticstorage:block/", "cabinet_"+name, "stripped_"+name+"_log", name+"_log");
            registerCabinetFlipup( ModBlocks.FLIPUP_CABINETS.get(name),"aestheticstorage:block/", "cabinet_flipup_"+name, "stripped_"+name+"_log", name+"_log");
            registerCabinetFlipdown( ModBlocks.FLIPDOWN_CABINETS.get(name),"aestheticstorage:block/", "cabinet_flipdown_"+name, "stripped_"+name+"_log", name+"_log");
            registerCupboard( ModBlocks.CUPBOARDS.get(name),"aestheticstorage:block/", "cupboard_"+name, "stripped_"+name+"_log", name+"_log");

            registerHalfCabinet( ModBlocks.HALFCABINETS.get(name),
                    "aestheticstorage:block/", "halfcabinet_"+name, "stripped_"+name+"_log", name+"_log");
            registerHalfCabinetFlipup( ModBlocks.FLIPUP_HALFCABINETS.get(name),
                    "aestheticstorage:block/", "halfcabinet_flipup_"+name, "stripped_"+name+"_log", name+"_log");
            registerHalfCabinetFlipdown( ModBlocks.FLIPDOWN_HALFCABINETS.get(name),
                    "aestheticstorage:block/", "halfcabinet_flipdown_"+name, "stripped_"+name+"_log", name+"_log");
            registerHalfCupboard( ModBlocks.HALFCUPBOARDS.get(name),
                    "aestheticstorage:block/", "halfcupboard_"+name, "stripped_"+name+"_log", name+"_log");
        }

        for(String name : ModItemGroups.WT_WOOD_NAMES)
        {
            registerCabinet(ModBlocks.CABINETS.get(name), "aestheticstorage:block/", "cabinet_"+name, "stripped_"+name+"_log", name+"_log");
            registerCabinetFlipup( ModBlocks.FLIPUP_CABINETS.get(name),"aestheticstorage:block/", "cabinet_flipup_"+name, "stripped_"+name+"_log", name+"_log");
            registerCabinetFlipdown( ModBlocks.FLIPDOWN_CABINETS.get(name),"aestheticstorage:block/", "cabinet_flipdown_"+name, "stripped_"+name+"_log", name+"_log");
            registerCupboard( ModBlocks.CUPBOARDS.get(name),"aestheticstorage:block/", "cupboard_"+name, "stripped_"+name+"_log", name+"_log");

            registerHalfCabinet( ModBlocks.HALFCABINETS.get(name),
                    "aestheticstorage:block/", "halfcabinet_"+name, "stripped_"+name+"_log", name+"_log");
            registerHalfCabinetFlipup( ModBlocks.FLIPUP_HALFCABINETS.get(name),
                    "aestheticstorage:block/", "halfcabinet_flipup_"+name, "stripped_"+name+"_log", name+"_log");
            registerHalfCabinetFlipdown( ModBlocks.FLIPDOWN_HALFCABINETS.get(name),
                    "aestheticstorage:block/", "halfcabinet_flipdown_"+name, "stripped_"+name+"_log", name+"_log");
            registerHalfCupboard( ModBlocks.HALFCUPBOARDS.get(name),
                    "aestheticstorage:block/", "halfcupboard_"+name, "stripped_"+name+"_log", name+"_log");
        }

        for(String name : ModItemGroups.ST_WOOD_NAMES)
        {
            registerCabinet(ModBlocks.CABINETS.get(name), "aestheticstorage:block/", "cabinet_"+name, "stripped_"+name+"_log", name+"_log");
            registerCabinetFlipup( ModBlocks.FLIPUP_CABINETS.get(name),"aestheticstorage:block/", "cabinet_flipup_"+name, "stripped_"+name+"_log", name+"_log");
            registerCabinetFlipdown( ModBlocks.FLIPDOWN_CABINETS.get(name),"aestheticstorage:block/", "cabinet_flipdown_"+name, "stripped_"+name+"_log", name+"_log");
            registerCupboard( ModBlocks.CUPBOARDS.get(name),"aestheticstorage:block/", "cupboard_"+name, "stripped_"+name+"_log", name+"_log");

            registerHalfCabinet( ModBlocks.HALFCABINETS.get(name),
                    "aestheticstorage:block/", "halfcabinet_"+name, "stripped_"+name+"_log", name+"_log");
            registerHalfCabinetFlipup( ModBlocks.FLIPUP_HALFCABINETS.get(name),
                    "aestheticstorage:block/", "halfcabinet_flipup_"+name, "stripped_"+name+"_log", name+"_log");
            registerHalfCabinetFlipdown( ModBlocks.FLIPDOWN_HALFCABINETS.get(name),
                    "aestheticstorage:block/", "halfcabinet_flipdown_"+name, "stripped_"+name+"_log", name+"_log");
            registerHalfCupboard( ModBlocks.HALFCUPBOARDS.get(name),
                    "aestheticstorage:block/", "halfcupboard_"+name, "stripped_"+name+"_log", name+"_log");
        }

        for(String name : ModItemGroups.MT_WOOD_NAMES)
        {
            registerCabinet(ModBlocks.CABINETS.get(name), "aestheticstorage:block/", "cabinet_"+name, "stripped_"+name+"_log", name+"_log");
            registerCabinetFlipup( ModBlocks.FLIPUP_CABINETS.get(name),"aestheticstorage:block/", "cabinet_flipup_"+name, "stripped_"+name+"_log", name+"_log");
            registerCabinetFlipdown( ModBlocks.FLIPDOWN_CABINETS.get(name),"aestheticstorage:block/", "cabinet_flipdown_"+name, "stripped_"+name+"_log", name+"_log");
            registerCupboard( ModBlocks.CUPBOARDS.get(name),"aestheticstorage:block/", "cupboard_"+name, "stripped_"+name+"_log", name+"_log");

            registerHalfCabinet( ModBlocks.HALFCABINETS.get(name),
                    "aestheticstorage:block/", "halfcabinet_"+name, "stripped_"+name+"_log", name+"_log");
            registerHalfCabinetFlipup( ModBlocks.FLIPUP_HALFCABINETS.get(name),
                    "aestheticstorage:block/", "halfcabinet_flipup_"+name, "stripped_"+name+"_log", name+"_log");
            registerHalfCabinetFlipdown( ModBlocks.FLIPDOWN_HALFCABINETS.get(name),
                    "aestheticstorage:block/", "halfcabinet_flipdown_"+name, "stripped_"+name+"_log", name+"_log");
            registerHalfCupboard( ModBlocks.HALFCUPBOARDS.get(name),
                    "aestheticstorage:block/", "halfcupboard_"+name, "stripped_"+name+"_log", name+"_log");
        }

        for(String name : ModItemGroups.NSS_WOOD_NAMES)
        {
            registerCabinet(ModBlocks.CABINETS.get(name), "aestheticstorage:block/", "cabinet_"+name, "stripped_"+name+"_log", name+"_log");
            registerCabinetFlipup( ModBlocks.FLIPUP_CABINETS.get(name),"aestheticstorage:block/", "cabinet_flipup_"+name, "stripped_"+name+"_log", name+"_log");
            registerCabinetFlipdown( ModBlocks.FLIPDOWN_CABINETS.get(name),"aestheticstorage:block/", "cabinet_flipdown_"+name, "stripped_"+name+"_log", name+"_log");
            registerCupboard( ModBlocks.CUPBOARDS.get(name),"aestheticstorage:block/", "cupboard_"+name, "stripped_"+name+"_log", name+"_log");

            registerHalfCabinet( ModBlocks.HALFCABINETS.get(name),
                    "aestheticstorage:block/", "halfcabinet_"+name, "stripped_"+name+"_log", name+"_log");
            registerHalfCabinetFlipup( ModBlocks.FLIPUP_HALFCABINETS.get(name),
                    "aestheticstorage:block/", "halfcabinet_flipup_"+name, "stripped_"+name+"_log", name+"_log");
            registerHalfCabinetFlipdown( ModBlocks.FLIPDOWN_HALFCABINETS.get(name),
                    "aestheticstorage:block/", "halfcabinet_flipdown_"+name, "stripped_"+name+"_log", name+"_log");
            registerHalfCupboard( ModBlocks.HALFCUPBOARDS.get(name),
                    "aestheticstorage:block/", "halfcupboard_"+name, "stripped_"+name+"_log", name+"_log");
        }
    }

    public void registerHalfCabinet(RegistryObject<Block> block, String modId, String name, String baseName, String legName)
    {
        ModJsonHelper.registerCabinetBlockModel(ModJsonTemplates.HALFCABINET_RIGHT_OPEN, name, modId+baseName, modId+legName, "right", true);
        ModJsonHelper.registerCabinetBlockModel(ModJsonTemplates.HALFCABINET_LEFT_OPEN, name, modId+baseName, modId+legName, "left", true);
        ModJsonHelper.registerCabinetBlockModel(ModJsonTemplates.HALFCABINET_LEFT, name, modId+baseName, modId+legName, "left", false);
        ModJsonHelper.registerCabinetBlockModel(ModJsonTemplates.HALFCABINET_RIGHT, name, modId+baseName, modId+legName, "right", false);
        ModJsonHelper.createBlockstate(ModJsonTemplates.CABINET_BLOCKSTATE, name);
        blockItem(block, "block/"+ name+"_right");
    }

    public void registerHalfCabinetFlipup(RegistryObject<Block> block, String modId, String name, String baseName, String legName)
    {
        ModJsonHelper.registerCabinetBlockModel(ModJsonTemplates.FLIPUP_HALFCABINET_OPEN, name, modId+baseName, modId+legName, "normal", true);
        ModJsonHelper.registerCabinetBlockModel(ModJsonTemplates.FLIPUP_HALFCABINET_RIGHT_OPEN, name, modId+baseName, modId+legName, "right", true);
        ModJsonHelper.registerCabinetBlockModel(ModJsonTemplates.FLIPUP_HALFCABINET_LEFT_OPEN, name, modId+baseName, modId+legName, "left", true);
        ModJsonHelper.registerCabinetBlockModel(ModJsonTemplates.FLIPUP_HALFCABINET, name, modId+baseName, modId+legName, "normal", false);
        ModJsonHelper.registerCabinetBlockModel(ModJsonTemplates.FLIPUP_HALFCABINET_LEFT, name, modId+baseName, modId+legName, "left", false);
        ModJsonHelper.registerCabinetBlockModel(ModJsonTemplates.FLIPUP_HALFCABINET_RIGHT, name, modId+baseName, modId+legName, "right", false);
        ModJsonHelper.createBlockstate(ModJsonTemplates.FLIP_CABINET_BLOCKSTATE, name);
        blockItem(block, "block/"+ name);
    }

    public void registerHalfCabinetFlipdown(RegistryObject<Block> block, String modId, String name, String baseName, String legName)
    {
        ModJsonHelper.registerCabinetBlockModel(ModJsonTemplates.FLIPDOWN_HALFCABINET_OPEN, name, modId+baseName, modId+legName, "normal", true);
        ModJsonHelper.registerCabinetBlockModel(ModJsonTemplates.FLIPDOWN_HALFCABINET_RIGHT_OPEN, name, modId+baseName, modId+legName, "right", true);
        ModJsonHelper.registerCabinetBlockModel(ModJsonTemplates.FLIPDOWN_HALFCABINET_LEFT_OPEN, name, modId+baseName, modId+legName, "left", true);
        ModJsonHelper.registerCabinetBlockModel(ModJsonTemplates.FLIPDOWN_HALFCABINET, name, modId+baseName, modId+legName, "normal", false);
        ModJsonHelper.registerCabinetBlockModel(ModJsonTemplates.FLIPDOWN_HALFCABINET_LEFT, name, modId+baseName, modId+legName, "left", false);
        ModJsonHelper.registerCabinetBlockModel(ModJsonTemplates.FLIPDOWN_HALFCABINET_RIGHT, name, modId+baseName, modId+legName, "right", false);
        ModJsonHelper.createBlockstate(ModJsonTemplates.FLIP_CABINET_BLOCKSTATE, name);
        blockItem(block, "block/"+ name);
    }

    public void registerHalfCupboard(RegistryObject<Block> block, String modId, String name, String baseName, String legName)
    {
        ModJsonHelper.registerCupboardBlockModel(ModJsonTemplates.HALFCUPBOARD_RIGHT, name, modId+baseName, modId+legName, "normal",
                false, false);
        ModJsonHelper.registerCupboardBlockModel(ModJsonTemplates.HALFCUPBOARD_UP, name, modId+baseName, modId+legName, "up",
                false, false);
        ModJsonHelper.registerCupboardBlockModel(ModJsonTemplates.HALFCUPBOARD_DOWN, name, modId+baseName, modId+legName, "down",
                false, false);
        ModJsonHelper.registerCupboardBlockModel(ModJsonTemplates.HALFCUPBOARD_CENTER, name, modId+baseName, modId+legName, "center",
                false, false);
        ModJsonHelper.registerCupboardBlockModel(ModJsonTemplates.HALFCUPBOARD_RIGHT_OPEN, name, modId+baseName, modId+legName, "normal",
                true, false);
        ModJsonHelper.registerCupboardBlockModel(ModJsonTemplates.HALFCUPBOARD_UP_OPEN, name, modId+baseName, modId+legName, "up",
                true, false);
        ModJsonHelper.registerCupboardBlockModel(ModJsonTemplates.HALFCUPBOARD_DOWN_OPEN, name, modId+baseName, modId+legName, "down",
                true, false);
        ModJsonHelper.registerCupboardBlockModel(ModJsonTemplates.HALFCUPBOARD_CENTER_OPEN, name, modId+baseName, modId+legName, "center",
                true, false);
        ModJsonHelper.registerCupboardBlockModel(ModJsonTemplates.HALFCUPBOARD_LEFT, name, modId+baseName, modId+legName, "normal",
                false, true);
        ModJsonHelper.registerCupboardBlockModel(ModJsonTemplates.HALFCUPBOARD_UP_FLIPPED, name, modId+baseName, modId+legName, "up",
                false, true);
        ModJsonHelper.registerCupboardBlockModel(ModJsonTemplates.HALFCUPBOARD_DOWN_FLIPPED, name, modId+baseName, modId+legName, "down",
                false, true);
        ModJsonHelper.registerCupboardBlockModel(ModJsonTemplates.HALFCUPBOARD_CENTER_FLIPPED, name, modId+baseName, modId+legName, "center",
                false, true);
        ModJsonHelper.registerCupboardBlockModel(ModJsonTemplates.HALFCUPBOARD_LEFT_OPEN, name, modId+baseName, modId+legName, "normal",
                true, true);
        ModJsonHelper.registerCupboardBlockModel(ModJsonTemplates.HALFCUPBOARD_UP_OPEN_FLIPPED, name, modId+baseName, modId+legName, "up",
                true, true);
        ModJsonHelper.registerCupboardBlockModel(ModJsonTemplates.HALFCUPBOARD_DOWN_OPEN_FLIPPED, name, modId+baseName, modId+legName, "down",
                true, true);
        ModJsonHelper.registerCupboardBlockModel(ModJsonTemplates.HALFCUPBOARD_CENTER_OPEN_FLIPPED, name, modId+baseName, modId+legName, "center",
                true, true);
        ModJsonHelper.createBlockstate(ModJsonTemplates.CUPBOARD_BLOCKSTATE, name);
        blockItem(block, "block/"+ name);
    }

    public void registerCabinet(RegistryObject<Block> block, String modId, String name, String baseName, String legName)
    {
        ModJsonHelper.registerCabinetBlockModel(ModJsonTemplates.CABINET_RIGHT_OPEN, name, modId+baseName, modId+legName, "right", true);
        ModJsonHelper.registerCabinetBlockModel(ModJsonTemplates.CABINET_LEFT_OPEN, name, modId+baseName, modId+legName, "left", true);
        ModJsonHelper.registerCabinetBlockModel(ModJsonTemplates.CABINET_LEFT, name, modId+baseName, modId+legName, "left", false);
        ModJsonHelper.registerCabinetBlockModel(ModJsonTemplates.CABINET_RIGHT, name, modId+baseName, modId+legName, "right", false);
        ModJsonHelper.createBlockstate(ModJsonTemplates.CABINET_BLOCKSTATE, name);
        blockItem(block, "block/"+ name+"_right");
    }

    public void registerCabinetFlipup(RegistryObject<Block> block, String modId, String name, String baseName, String legName)
    {
        ModJsonHelper.registerCabinetBlockModel(ModJsonTemplates.FLIPUP_CABINET_OPEN, name, modId+baseName, modId+legName, "normal", true);
        ModJsonHelper.registerCabinetBlockModel(ModJsonTemplates.FLIPUP_CABINET_RIGHT_OPEN, name, modId+baseName, modId+legName, "right", true);
        ModJsonHelper.registerCabinetBlockModel(ModJsonTemplates.FLIPUP_CABINET_LEFT_OPEN, name, modId+baseName, modId+legName, "left", true);
        ModJsonHelper.registerCabinetBlockModel(ModJsonTemplates.FLIPUP_CABINET, name, modId+baseName, modId+legName, "normal", false);
        ModJsonHelper.registerCabinetBlockModel(ModJsonTemplates.FLIPUP_CABINET_LEFT, name, modId+baseName, modId+legName, "left", false);
        ModJsonHelper.registerCabinetBlockModel(ModJsonTemplates.FLIPUP_CABINET_RIGHT, name, modId+baseName, modId+legName, "right", false);
        ModJsonHelper.createBlockstate(ModJsonTemplates.FLIP_CABINET_BLOCKSTATE, name);
        blockItem(block, "block/"+ name);
    }

    public void registerCabinetFlipdown(RegistryObject<Block> block, String modId, String name, String baseName, String legName)
    {
        ModJsonHelper.registerCabinetBlockModel(ModJsonTemplates.FLIPDOWN_CABINET_OPEN, name, modId+baseName, modId+legName, "normal", true);
        ModJsonHelper.registerCabinetBlockModel(ModJsonTemplates.FLIPDOWN_CABINET_RIGHT_OPEN, name, modId+baseName, modId+legName, "right", true);
        ModJsonHelper.registerCabinetBlockModel(ModJsonTemplates.FLIPDOWN_CABINET_LEFT_OPEN, name, modId+baseName, modId+legName, "left", true);
        ModJsonHelper.registerCabinetBlockModel(ModJsonTemplates.FLIPDOWN_CABINET, name, modId+baseName, modId+legName, "normal", false);
        ModJsonHelper.registerCabinetBlockModel(ModJsonTemplates.FLIPDOWN_CABINET_LEFT, name, modId+baseName, modId+legName, "left", false);
        ModJsonHelper.registerCabinetBlockModel(ModJsonTemplates.FLIPDOWN_CABINET_RIGHT, name, modId+baseName, modId+legName, "right", false);
        ModJsonHelper.createBlockstate(ModJsonTemplates.FLIP_CABINET_BLOCKSTATE, name);
        blockItem(block, "block/"+ name);
    }

    public void registerCupboard(RegistryObject<Block> block, String modId, String name, String baseName, String legName)
    {
        ModJsonHelper.registerCupboardBlockModel(ModJsonTemplates.CUPBOARD_RIGHT, name, modId+baseName, modId+legName, "normal",
                false, false);
        ModJsonHelper.registerCupboardBlockModel(ModJsonTemplates.CUPBOARD_UP, name, modId+baseName, modId+legName, "up",
                false, false);
        ModJsonHelper.registerCupboardBlockModel(ModJsonTemplates.CUPBOARD_DOWN, name, modId+baseName, modId+legName, "down",
                false, false);
        ModJsonHelper.registerCupboardBlockModel(ModJsonTemplates.CUPBOARD_CENTER, name, modId+baseName, modId+legName, "center",
                false, false);
        ModJsonHelper.registerCupboardBlockModel(ModJsonTemplates.CUPBOARD_RIGHT_OPEN, name, modId+baseName, modId+legName, "normal",
                true, false);
        ModJsonHelper.registerCupboardBlockModel(ModJsonTemplates.CUPBOARD_UP_OPEN, name, modId+baseName, modId+legName, "up",
                true, false);
        ModJsonHelper.registerCupboardBlockModel(ModJsonTemplates.CUPBOARD_DOWN_OPEN, name, modId+baseName, modId+legName, "down",
                true, false);
        ModJsonHelper.registerCupboardBlockModel(ModJsonTemplates.CUPBOARD_CENTER_OPEN, name, modId+baseName, modId+legName, "center",
                true, false);
        ModJsonHelper.registerCupboardBlockModel(ModJsonTemplates.CUPBOARD_LEFT, name, modId+baseName, modId+legName, "normal",
                false, true);
        ModJsonHelper.registerCupboardBlockModel(ModJsonTemplates.CUPBOARD_UP_FLIPPED, name, modId+baseName, modId+legName, "up",
                false, true);
        ModJsonHelper.registerCupboardBlockModel(ModJsonTemplates.CUPBOARD_DOWN_FLIPPED, name, modId+baseName, modId+legName, "down",
                false, true);
        ModJsonHelper.registerCupboardBlockModel(ModJsonTemplates.CUPBOARD_CENTER_FLIPPED, name, modId+baseName, modId+legName, "center",
                false, true);
        ModJsonHelper.registerCupboardBlockModel(ModJsonTemplates.CUPBOARD_LEFT_OPEN, name, modId+baseName, modId+legName, "normal",
                true, true);
        ModJsonHelper.registerCupboardBlockModel(ModJsonTemplates.CUPBOARD_UP_OPEN_FLIPPED, name, modId+baseName, modId+legName, "up",
                true, true);
        ModJsonHelper.registerCupboardBlockModel(ModJsonTemplates.CUPBOARD_DOWN_OPEN_FLIPPED, name, modId+baseName, modId+legName, "down",
                true, true);
        ModJsonHelper.registerCupboardBlockModel(ModJsonTemplates.CUPBOARD_CENTER_OPEN_FLIPPED, name, modId+baseName, modId+legName, "center",
                true, true);
        ModJsonHelper.createBlockstate(ModJsonTemplates.CUPBOARD_BLOCKSTATE, name);
        blockItem(block, "block/"+ name);
    }

    private void blockItem(RegistryObject<? extends Block> blockRegistryObject, String name)
    {
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile("aestheticstorage:" + name));
    }
}