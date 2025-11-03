package net.alminoris.aestheticstorage.block;

import net.alminoris.aestheticstorage.AestheticStorage;
import net.alminoris.aestheticstorage.block.custom.*;
import net.alminoris.aestheticstorage.item.ModItemGroups;
import net.alminoris.aestheticstorage.item.ModItems;
import net.alminoris.aestheticstorage.util.helper.BlockSetsHelper;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.function.Supplier;

public class ModBlocks
{
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, AestheticStorage.MOD_ID);

    public static final Dictionary<String, RegistryObject<Block>> CABINETS = new Hashtable<>()
    {{
        for(String name : BlockSetsHelper.getWoods())
        {
            put(name, registerBlock("cabinet_"+name,
                    () -> new CabinetBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS), false)));
        }
    }};

    public static final Dictionary<String, RegistryObject<Block>> FLIPUP_CABINETS = new Hashtable<>()
    {{
        for(String name : BlockSetsHelper.getWoods())
        {
            put(name, registerBlock("cabinet_flipup_"+name,
                    () -> new CabinetBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS), true)));
        }
    }};

    public static final Dictionary<String, RegistryObject<Block>> FLIPDOWN_CABINETS = new Hashtable<>()
    {{
        for(String name : BlockSetsHelper.getWoods())
        {
            put(name, registerBlock("cabinet_flipdown_"+name,
                    () -> new CabinetBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS), true)));
        }
    }};

    public static final Dictionary<String, RegistryObject<Block>> CUPBOARDS = new Hashtable<>()
    {{
        for(String name : BlockSetsHelper.getWoods())
        {
            put(name, registerBlock("cupboard_"+name,
                    () -> new CupboardBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS))));
        }
    }};

    public static final Dictionary<String, RegistryObject<Block>> HALFCABINETS = new Hashtable<>()
    {{
        for(String name : BlockSetsHelper.getWoods())
        {
            put(name, registerBlock("halfcabinet_"+name,
                    () -> new HalfcabinetBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS), false)));
        }
    }};

    public static final Dictionary<String, RegistryObject<Block>> FLIPUP_HALFCABINETS = new Hashtable<>()
    {{
        for(String name : BlockSetsHelper.getWoods())
        {
            put(name, registerBlock("halfcabinet_flipup_"+name,
                    () -> new HalfcabinetBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS), true)));
        }
    }};

    public static final Dictionary<String, RegistryObject<Block>> FLIPDOWN_HALFCABINETS = new Hashtable<>()
    {{
        for(String name : BlockSetsHelper.getWoods())
        {
            put(name, registerBlock("halfcabinet_flipdown_"+name,
                    () -> new HalfcabinetBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS), true)));
        }
    }};

    public static final Dictionary<String, RegistryObject<Block>> HALFCUPBOARDS = new Hashtable<>()
    {{
        for(String name : BlockSetsHelper.getWoods())
        {
            put(name, registerBlock("halfcupboard_"+name,
                    () -> new HalfcupboardBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS))));
        }
    }};

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block)
    {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block)
    {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(ModItemGroups.ASTRG_TAB)));
    }

    public static void register(IEventBus eventBus)
    {
        BLOCKS.register(eventBus);
    }
}