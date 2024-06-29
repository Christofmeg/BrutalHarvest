package com.christofmeg.brutalharvest.client.renderer.entity;

import com.christofmeg.brutalharvest.CommonConstants;
import com.christofmeg.brutalharvest.client.model.BrutalModelLayers;
import com.christofmeg.brutalharvest.client.model.KnifeModel;
import com.christofmeg.brutalharvest.common.entity.ThrowingKnifeEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class ThrownKnifeRenderer extends EntityRenderer<ThrowingKnifeEntity> {
    public static final ResourceLocation KNIFE_LOCATION = new ResourceLocation(CommonConstants.MOD_ID, "textures/item/copper_knife.png");
    private final KnifeModel model;

    public ThrownKnifeRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
        this.model = new KnifeModel(pContext.bakeLayer(BrutalModelLayers.THROWING_KNIFE));
    }

    public void render(ThrowingKnifeEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, @NotNull MultiBufferSource pBuffer, int pPackedLight) {
        pPoseStack.pushPose();

        // Interpolate rotation angles
        float interpolatedYaw = Mth.lerp(pPartialTicks, pEntity.yRotO, pEntity.getYRot()) - 90.0F;
        float interpolatedPitch = Mth.lerp(pPartialTicks, pEntity.xRotO, pEntity.getXRot());

        // Apply rotations
        pPoseStack.mulPose(Axis.YP.rotationDegrees(interpolatedYaw));
        pPoseStack.mulPose(Axis.ZP.rotationDegrees(interpolatedPitch));

        // Apply an additional 45-degree rotation around the Y-axis
        pPoseStack.mulPose(Axis.YP.rotationDegrees(-90.0F));

        // Adjust scaling for normal size
        float scale = 1.0F; // Adjust this value as necessary
        pPoseStack.scale(scale, scale, scale);

        // Render the knife model
        VertexConsumer vertexConsumer = ItemRenderer.getFoilBufferDirect(pBuffer, this.model.renderType(this.getTextureLocation(pEntity)), false, false);
        this.model.renderToBuffer(pPoseStack, vertexConsumer, pPackedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);

        pPoseStack.popPose();
        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }

    public @NotNull ResourceLocation getTextureLocation(@NotNull ThrowingKnifeEntity pEntity) {
        return KNIFE_LOCATION;
    }

}