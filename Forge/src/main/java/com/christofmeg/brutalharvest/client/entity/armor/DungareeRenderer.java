package com.christofmeg.brutalharvest.client.entity.armor;

import com.christofmeg.brutalharvest.CommonConstants;
import com.christofmeg.brutalharvest.common.item.DungareeItem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.resources.ResourceLocation;
import org.joml.Matrix4f;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class DungareeRenderer extends GeoArmorRenderer<DungareeItem> {

    public DungareeRenderer(String name) {
        super(new DefaultedItemGeoModel<>(new ResourceLocation(CommonConstants.MOD_ID, "textures/models/armor/" + name + "_dungaree_layer_1.png")) {
            @Override
            public ResourceLocation getModelResource(DungareeItem object) {
                return new ResourceLocation(CommonConstants.MOD_ID, "geo/" + name + "_dungaree.geo.json");
            }

            @Override
            public ResourceLocation getTextureResource(DungareeItem object) {
                return new ResourceLocation(CommonConstants.MOD_ID, "textures/models/armor/" + name + "_dungaree_layer_1.png");
            }

            @Override
            public ResourceLocation getAnimationResource(DungareeItem animatable) {
                return new ResourceLocation(CommonConstants.MOD_ID, "animations/armor.animation.json");
            }
        });
    }


    @Override
    public void preRender(PoseStack poseStack, DungareeItem animatable, BakedGeoModel model, @javax.annotation.Nullable MultiBufferSource bufferSource, @javax.annotation.Nullable VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.entityRenderTranslations = new Matrix4f(poseStack.last().pose());
        this.applyBaseModel(this.baseModel);
        this.grabRelevantBones(this.getGeoModel().getBakedModel(this.getGeoModel().getModelResource(this.animatable)));
        this.applyBaseTransformations(this.baseModel);
        this.scaleModelForBaby(poseStack, animatable, partialTick, isReRender);
        this.scaleModelForRender(this.scaleWidth, this.scaleHeight, poseStack, animatable, model, isReRender, partialTick, packedLight, packedOverlay);

        // Custom method to set the visibility of specific bones
        this.applyBoneVisibilityForCustomRendering();
    }

    protected void applyBoneVisibilityForCustomRendering() {
        this.setAllVisible(false);
        this.setBoneVisible(this.body, true);
        this.setBoneVisible(this.leftLeg, true);
        this.setBoneVisible(this.rightLeg, true);
    }

}