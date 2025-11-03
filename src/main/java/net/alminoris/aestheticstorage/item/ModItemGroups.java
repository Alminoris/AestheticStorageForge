package net.alminoris.aestheticstorage.item;

import net.alminoris.aestheticstorage.AestheticStorage;
import net.alminoris.aestheticstorage.block.ModBlocks;
import net.alminoris.aestheticstorage.util.helper.BlockSetsHelper;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import static net.alminoris.aestheticstorage.util.helper.BlockSetsHelper.*;
import static net.alminoris.aestheticstorage.util.helper.BlockSetsHelper.NSS_WOOD_NAMES;

@Mod.EventBusSubscriber(modid = AestheticStorage.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModItemGroups
{
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, AestheticStorage.MOD_ID);

    public static final RegistryObject<CreativeModeTab> ASTRG_TAB = CREATIVE_MODE_TABS.register("astrgtab", () -> CreativeModeTab.builder()
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(ModBlocks.CABINETS.get("oak").get().asItem()::getDefaultInstance)
            .title(Component.translatable("itemgroup.astrgtab"))
            .displayItems((parameters, entries) ->
            {
                if (!ModList.get().isLoaded("aestheticseating"))
                {
                    entries.accept(ModItems.WRENCH.get());
                }

                for(String name : BlockSetsHelper.WOODS)
                {
                    entries.accept(ModBlocks.CABINETS.get(name).get());
                    entries.accept(ModBlocks.FLIPDOWN_CABINETS.get(name).get());
                    entries.accept(ModBlocks.FLIPUP_CABINETS.get(name).get());
                    entries.accept(ModBlocks.CUPBOARDS.get(name).get());
                    entries.accept(ModBlocks.HALFCABINETS.get(name).get());
                    entries.accept(ModBlocks.FLIPDOWN_HALFCABINETS.get(name).get());
                    entries.accept(ModBlocks.FLIPUP_HALFCABINETS.get(name).get());
                    entries.accept(ModBlocks.HALFCUPBOARDS.get(name).get());
                }

                if (ModList.get().isLoaded("arborealnature"))
                {
                    for(String name : BlockSetsHelper.EXTRA_WOODS_AN)
                    {
                        entries.accept(ModBlocks.CABINETS.get(name).get());
                        entries.accept(ModBlocks.FLIPDOWN_CABINETS.get(name).get());
                        entries.accept(ModBlocks.FLIPUP_CABINETS.get(name).get());
                        entries.accept(ModBlocks.CUPBOARDS.get(name).get());
                        entries.accept(ModBlocks.HALFCABINETS.get(name).get());
                        entries.accept(ModBlocks.FLIPDOWN_HALFCABINETS.get(name).get());
                        entries.accept(ModBlocks.FLIPUP_HALFCABINETS.get(name).get());
                        entries.accept(ModBlocks.HALFCUPBOARDS.get(name).get());
                    }
                }

                if (ModList.get().isLoaded("wildfields"))
                {
                    for(String name : BlockSetsHelper.EXTRA_WOODS_WF)
                    {
                        entries.accept(ModBlocks.CABINETS.get(name).get());
                        entries.accept(ModBlocks.FLIPDOWN_CABINETS.get(name).get());
                        entries.accept(ModBlocks.FLIPUP_CABINETS.get(name).get());
                        entries.accept(ModBlocks.CUPBOARDS.get(name).get());
                        entries.accept(ModBlocks.HALFCABINETS.get(name).get());
                        entries.accept(ModBlocks.FLIPDOWN_HALFCABINETS.get(name).get());
                        entries.accept(ModBlocks.FLIPUP_HALFCABINETS.get(name).get());
                        entries.accept(ModBlocks.HALFCUPBOARDS.get(name).get());
                    }
                }

                if (ModList.get().isLoaded("whisperleaftrees"))
                {
                    for(String name : BlockSetsHelper.WT_WOOD_NAMES)
                    {
                        entries.accept(ModBlocks.CABINETS.get(name).get());
                        entries.accept(ModBlocks.FLIPDOWN_CABINETS.get(name).get());
                        entries.accept(ModBlocks.FLIPUP_CABINETS.get(name).get());
                        entries.accept(ModBlocks.CUPBOARDS.get(name).get());
                        entries.accept(ModBlocks.HALFCABINETS.get(name).get());
                        entries.accept(ModBlocks.FLIPDOWN_HALFCABINETS.get(name).get());
                        entries.accept(ModBlocks.FLIPUP_HALFCABINETS.get(name).get());
                        entries.accept(ModBlocks.HALFCUPBOARDS.get(name).get());
                    }
                }

                if (ModList.get().isLoaded("silverwoodtrees"))
                {
                    for(String name : BlockSetsHelper.ST_WOOD_NAMES)
                    {
                        entries.accept(ModBlocks.CABINETS.get(name).get());
                        entries.accept(ModBlocks.FLIPDOWN_CABINETS.get(name).get());
                        entries.accept(ModBlocks.FLIPUP_CABINETS.get(name).get());
                        entries.accept(ModBlocks.CUPBOARDS.get(name).get());
                        entries.accept(ModBlocks.HALFCABINETS.get(name).get());
                        entries.accept(ModBlocks.FLIPDOWN_HALFCABINETS.get(name).get());
                        entries.accept(ModBlocks.FLIPUP_HALFCABINETS.get(name).get());
                        entries.accept(ModBlocks.HALFCUPBOARDS.get(name).get());
                    }
                }

                if (ModList.get().isLoaded("missingtrees"))
                {
                    for(String name : BlockSetsHelper.MT_WOOD_NAMES)
                    {
                        entries.accept(ModBlocks.CABINETS.get(name).get());
                        entries.accept(ModBlocks.FLIPDOWN_CABINETS.get(name).get());
                        entries.accept(ModBlocks.FLIPUP_CABINETS.get(name).get());
                        entries.accept(ModBlocks.CUPBOARDS.get(name).get());
                        entries.accept(ModBlocks.HALFCABINETS.get(name).get());
                        entries.accept(ModBlocks.FLIPDOWN_HALFCABINETS.get(name).get());
                        entries.accept(ModBlocks.FLIPUP_HALFCABINETS.get(name).get());
                        entries.accept(ModBlocks.HALFCUPBOARDS.get(name).get());
                    }
                }

                if (ModList.get().isLoaded("natures_spirit"))
                {
                    for(String name : BlockSetsHelper.NSS_WOOD_NAMES)
                    {
                        entries.accept(ModBlocks.CABINETS.get(name).get());
                        entries.accept(ModBlocks.FLIPDOWN_CABINETS.get(name).get());
                        entries.accept(ModBlocks.FLIPUP_CABINETS.get(name).get());
                        entries.accept(ModBlocks.CUPBOARDS.get(name).get());
                        entries.accept(ModBlocks.HALFCABINETS.get(name).get());
                        entries.accept(ModBlocks.FLIPDOWN_HALFCABINETS.get(name).get());
                        entries.accept(ModBlocks.FLIPUP_HALFCABINETS.get(name).get());
                        entries.accept(ModBlocks.HALFCUPBOARDS.get(name).get());
                    }
                }
            }).build());

    public static void register(IEventBus eventBus)
    {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}