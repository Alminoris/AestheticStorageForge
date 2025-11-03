package net.alminoris.aestheticstorage.datagen.loot;

import net.alminoris.aestheticstorage.block.ModBlocks;
import net.alminoris.aestheticstorage.util.helper.BlockSetsHelper;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider
{
    public ModBlockLootTables()
    {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate()
    {
        for(String name : BlockSetsHelper.getWoods())
        {
            dropSelf(ModBlocks.CABINETS.get(name).get());
            dropSelf(ModBlocks.FLIPUP_CABINETS.get(name).get());
            dropSelf(ModBlocks.FLIPDOWN_CABINETS.get(name).get());
            dropSelf(ModBlocks.CUPBOARDS.get(name).get());
            dropSelf(ModBlocks.HALFCABINETS.get(name).get());
            dropSelf(ModBlocks.FLIPUP_HALFCABINETS.get(name).get());
            dropSelf(ModBlocks.FLIPDOWN_HALFCABINETS.get(name).get());
            dropSelf(ModBlocks.HALFCUPBOARDS.get(name).get());
        }
    }

    @Override
    protected Iterable<Block> getKnownBlocks()
    {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
