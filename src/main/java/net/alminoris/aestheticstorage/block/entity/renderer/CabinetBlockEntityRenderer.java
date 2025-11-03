package net.alminoris.aestheticstorage.block.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.alminoris.aestheticstorage.block.custom.CabinetBlock;
import net.alminoris.aestheticstorage.block.entity.CabinetBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.Level;

import java.util.List;

public class CabinetBlockEntityRenderer implements BlockEntityRenderer<CabinetBlockEntity>
{
    public CabinetBlockEntityRenderer(BlockEntityRendererProvider.Context context) {}

    @Override
    public void render(CabinetBlockEntity entity, float partialTicks, PoseStack poseStack,
                       MultiBufferSource bufferSource, int combinedLight, int combinedOverlay)
    {
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        List<ItemStack> stacks = entity.getRenderStack();

        if (stacks.isEmpty() || entity.getLevel() == null)
            return;

        Direction facing = entity.getBlockState().getValue(CabinetBlock.FACING);

        poseStack.pushPose();

        poseStack.translate(0.5, 0.5, 0.5);
        switch (facing)
        {
            case NORTH -> poseStack.mulPose(Vector3f.YP.rotationDegrees(0f));
            case SOUTH -> poseStack.mulPose(Vector3f.YP.rotationDegrees(180F));
            case WEST  -> poseStack.mulPose(Vector3f.YP.rotationDegrees(90F));
            case EAST  -> poseStack.mulPose(Vector3f.YP.rotationDegrees(-90f));
        }
        poseStack.translate(-0.5, -0.5, -0.5);

        int counter = 0;
        for (ItemStack stack : stacks)
        {
            poseStack.pushPose();

            switch (counter)
            {
                case 0 -> poseStack.translate(0.25f, 0.65f, 0.25f);
                case 1 -> poseStack.translate(0.75f, 0.65f, 0.25f);
                case 2 -> poseStack.translate(0.25f, 0.65f, 0.75f);
                case 3 -> poseStack.translate(0.75f, 0.65f, 0.75f);
                case 4 -> poseStack.translate(0.25f, 0.2f, 0.25f);
                case 5 -> poseStack.translate(0.75f, 0.2f, 0.25f);
                case 6 -> poseStack.translate(0.25f, 0.2f, 0.75f);
                case 7 -> poseStack.translate(0.75f, 0.2f, 0.75f);
            }

            poseStack.scale(0.25f, 0.25f, 0.25f);

            itemRenderer.renderStatic(
                    stack,
                    ItemTransforms.TransformType.GUI,
                    getLightLevel(entity.getLevel(), entity.getBlockPos()),
                    OverlayTexture.NO_OVERLAY,
                    poseStack,
                    bufferSource,
                    0
            );

            poseStack.popPose();
            counter++;
        }

        poseStack.popPose();
    }

    private int getLightLevel(Level level, BlockPos pos)
    {
        int blockLight = level.getBrightness(LightLayer.BLOCK, pos);
        int skyLight = level.getBrightness(LightLayer.SKY, pos);
        return LightTexture.pack(blockLight, skyLight);
    }
}
