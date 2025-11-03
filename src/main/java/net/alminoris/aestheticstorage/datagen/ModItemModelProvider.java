package net.alminoris.aestheticstorage.datagen;

import net.alminoris.aestheticstorage.AestheticStorage;
import net.alminoris.aestheticstorage.item.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider
{
    public ModItemModelProvider(DataGenerator output, ExistingFileHelper existingFileHelper)
    {
        super(output, AestheticStorage.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels()
    {
        generatedItem(ModItems.WRENCH);
    }

    private ItemModelBuilder generatedItem(RegistryObject<Item> item)
    {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/generated")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(AestheticStorage.MOD_ID,"item/" + item.getId().getPath()));
    }

    private ItemModelBuilder handheldItem(RegistryObject<Item> item)
    {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/handheld")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(AestheticStorage.MOD_ID,"item/" + item.getId().getPath()));
    }
}