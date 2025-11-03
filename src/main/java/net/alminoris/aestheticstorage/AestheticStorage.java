package net.alminoris.aestheticstorage;

import com.mojang.logging.LogUtils;
import net.alminoris.aestheticstorage.block.ModBlocks;
import net.alminoris.aestheticstorage.block.entity.ModBlockEntities;
import net.alminoris.aestheticstorage.block.entity.renderer.CabinetBlockEntityRenderer;
import net.alminoris.aestheticstorage.block.entity.renderer.CupboardBlockEntityRenderer;
import net.alminoris.aestheticstorage.block.entity.renderer.HalfcabinetBlockEntityRenderer;
import net.alminoris.aestheticstorage.block.entity.renderer.HalfcupboardBlockEntityRenderer;
import net.alminoris.aestheticstorage.item.ModItemGroups;
import net.alminoris.aestheticstorage.item.ModItems;
import net.alminoris.aestheticstorage.menu.*;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(AestheticStorage.MOD_ID)
public class AestheticStorage
{
    public static final String MOD_ID = "aestheticstorage";
    private static final Logger LOGGER = LogUtils.getLogger();


    public AestheticStorage(FMLJavaModLoadingContext context)
    {
        IEventBus modEventBus = context.getModEventBus();

        modEventBus.addListener(this::commonSetup);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModItemGroups.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        ModMenus.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::addCreative);

        context.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {

    }

    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {

    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {

    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            MenuScreens.register(ModMenus.CABINET_MENU.get(), CabinetScreen::new);
            MenuScreens.register(ModMenus.CUPBOARD_MENU.get(), CupboardScreen::new);
            MenuScreens.register(ModMenus.HALFCABINET_MENU.get(), HalfcabinetScreen::new);
            MenuScreens.register(ModMenus.HALFCUPBOARD_MENU.get(), HalfcupboardScreen::new);

            BlockEntityRenderers.register(ModBlockEntities.CABINET_BLOCK_ENTITY.get(), CabinetBlockEntityRenderer::new);
            BlockEntityRenderers.register(ModBlockEntities.CUPBOARD_BLOCK_ENTITY.get(), CupboardBlockEntityRenderer::new);
            BlockEntityRenderers.register(ModBlockEntities.HALFCABINET_BLOCK_ENTITY.get(), HalfcabinetBlockEntityRenderer::new);
            BlockEntityRenderers.register(ModBlockEntities.HALFCUPBOARD_BLOCK_ENTITY.get(), HalfcupboardBlockEntityRenderer::new);
        }
    }
}