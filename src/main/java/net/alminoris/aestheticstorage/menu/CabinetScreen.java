package net.alminoris.aestheticstorage.menu;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.alminoris.aestheticstorage.AestheticStorage;
import net.alminoris.aestheticstorage.block.ModBlocks;
import net.alminoris.aestheticstorage.block.entity.CabinetBlockEntity;
import net.alminoris.aestheticstorage.util.helper.BlockSetsHelper;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Hashtable;
import java.util.Map;
import java.util.Objects;

import static net.alminoris.aestheticstorage.util.helper.BlockSetsHelper.WOOD_COLORS;

public class CabinetScreen extends AbstractContainerScreen<CabinetMenu>
{
    private final String NAME = ForgeRegistries.BLOCKS.getKey(Objects.requireNonNull(menu.getSlot(0).container instanceof CabinetBlockEntity be
            ? be.getLevel().getBlockState(be.getBlockPos()).getBlock() : null)).getPath();

    private final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(AestheticStorage.MOD_ID,
            "textures/gui/" + getWoodName() + ".png");

    public CabinetScreen(CabinetMenu menu, Inventory inventory, Component title)
    {
        super(menu, inventory, title);
    }

    private String getWoodName()
    {
        Block block = Objects.requireNonNull(menu.getSlot(0).container instanceof CabinetBlockEntity be
                ? be.getLevel().getBlockState(be.getBlockPos()).getBlock() : null);

        String value;

        value = getKeyByValue((Hashtable<String, RegistryObject<Block>>) ModBlocks.CABINETS, block);
        if (value != null) return value;

        value = getKeyByValue((Hashtable<String, RegistryObject<Block>>) ModBlocks.FLIPDOWN_CABINETS, block);
        if (value != null) return value;

        value = getKeyByValue((Hashtable<String, RegistryObject<Block>>) ModBlocks.FLIPUP_CABINETS, block);
        if (value != null) return value;

        return "";
    }

    private static String getKeyByValue(Hashtable<String, RegistryObject<Block>> table, Block value) {
        for (Map.Entry<String, RegistryObject<Block>> entry : table.entrySet()) {
            if (entry.getValue().get().equals(value)) {
                return entry.getKey();
            }
        }
        return null;
    }

    @Override
    protected void init() {
        super.init();
        titleLabelY = 10;
        titleLabelX = 10;
        inventoryLabelX = 10;
        inventoryLabelY = 68;
    }

    @Override
    protected void renderLabels(PoseStack graphics, int mouseX, int mouseY) {
        int color = 0xFFFFFF;
        for (String name : BlockSetsHelper.getWoods()) {
            if (NAME.endsWith("_" + name)) {
                color = WOOD_COLORS.get(name);
            }
        }

        drawString(graphics, this.font, this.title, this.titleLabelX, this.titleLabelY, color);
        drawString(graphics, this.font, this.playerInventoryTitle, this.inventoryLabelX, this.inventoryLabelY, color);
    }

    @Override
    protected void renderBg(PoseStack graphics, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        RenderSystem.setShaderTexture(0, TEXTURE);

        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        blit(graphics, x, y, 0, 0, imageWidth, imageHeight);
    }

    @Override
    public void render(PoseStack graphics, int mouseX, int mouseY, float partialTicks) {
        renderBackground(graphics);
        super.render(graphics, mouseX, mouseY, partialTicks);
        renderTooltip(graphics, mouseX, mouseY);
    }
}
