package net.alminoris.aestheticstorage.block.entity;

import net.alminoris.aestheticstorage.AestheticStorage;
import net.alminoris.aestheticstorage.block.ModBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class ModBlockEntities
{
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, AestheticStorage.MOD_ID);

    public static final RegistryObject<BlockEntityType<CabinetBlockEntity>> CABINET_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("cabinet_be", () ->
                    BlockEntityType.Builder.of(CabinetBlockEntity::new,
                            toBlockArray(ModBlocks.CABINETS.elements(),
                                    ModBlocks.FLIPUP_CABINETS.elements(),
                                    ModBlocks.FLIPDOWN_CABINETS.elements())).build(null));

    public static final RegistryObject<BlockEntityType<CupboardBlockEntity>> CUPBOARD_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("cupboard_be", () ->
                    BlockEntityType.Builder.of(CupboardBlockEntity::new,
                            toBlockArray(ModBlocks.CUPBOARDS.elements())).build(null));

    public static final RegistryObject<BlockEntityType<HalfcabinetBlockEntity>> HALFCABINET_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("halfcabinet_be", () ->
                    BlockEntityType.Builder.of(HalfcabinetBlockEntity::new,
                            toBlockArray(ModBlocks.HALFCABINETS.elements(),
                                    ModBlocks.FLIPUP_HALFCABINETS.elements(),
                                    ModBlocks.FLIPDOWN_HALFCABINETS.elements())).build(null));

    public static final RegistryObject<BlockEntityType<HalfcupboardBlockEntity>> HALFCUPBOARD_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("halfcupboard_be", () ->
                    BlockEntityType.Builder.of(HalfcupboardBlockEntity::new,
                            toBlockArray(ModBlocks.HALFCUPBOARDS.elements())).build(null));

    

    public static void register(IEventBus eventBus)
    {
        BLOCK_ENTITIES.register(eventBus);
    }

    private static Block[] toBlockArray(Enumeration<RegistryObject<Block>>... enumerations)
    {
        List<Block> blocks = new ArrayList<>();
        for(var enumeration : enumerations)
        {
            while (enumeration.hasMoreElements())
            {
                blocks.add(enumeration.nextElement().get());
            }
        }

        return blocks.toArray(new Block[0]);
    }
}