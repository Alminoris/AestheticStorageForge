package net.alminoris.aestheticstorage.menu;

import net.alminoris.aestheticstorage.AestheticStorage;
import net.minecraft.core.Registry;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenus
{
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, AestheticStorage.MOD_ID);

    public static final RegistryObject<MenuType<CabinetMenu>> CABINET_MENU =
            MENUS.register("cabinet_menu", () -> IForgeMenuType.create(CabinetMenu::new));

    public static final RegistryObject<MenuType<CupboardMenu>> CUPBOARD_MENU =
            MENUS.register("cupboard_menu", () -> IForgeMenuType.create(CupboardMenu::new));

    public static final RegistryObject<MenuType<HalfcabinetMenu>> HALFCABINET_MENU =
            MENUS.register("halfcabinet_menu", () -> IForgeMenuType.create(HalfcabinetMenu::new));

    public static final RegistryObject<MenuType<HalfcupboardMenu>> HALFCUPBOARD_MENU =
            MENUS.register("halfcupboard_menu", () -> IForgeMenuType.create(HalfcupboardMenu::new));

    public static void register(IEventBus eventBus)
    {
        MENUS.register(eventBus);
    }
}
