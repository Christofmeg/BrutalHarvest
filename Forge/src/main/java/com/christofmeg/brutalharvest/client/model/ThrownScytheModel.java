package com.christofmeg.brutalharvest.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class ThrownScytheModel extends Model {
    private final ModelPart blade;
    private final ModelPart handle;

    public ThrownScytheModel(ModelPart root) {
        super(RenderType::entitySolid);
        this.blade = root.getChild("blade");
        this.handle = root.getChild("handle");
    }

    public static LayerDefinition createLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        partdefinition.addOrReplaceChild("blade", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0.0F, 4.0F, 0.0F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 2).mirror().addBox(-1.0F, 5.0F, 0.0F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 4).mirror().addBox(-1.0F, 6.0F, 0.0F, 9.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 6).mirror().addBox(5.0F, 7.0F, 0.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 8).mirror().addBox(6.0F, 8.0F, 0.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 10).mirror().addBox(7.0F, 9.0F, 0.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 12).mirror().addBox(8.0F, 10.0F, 0.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(14, 10).mirror().addBox(9.0F, 12.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(8, 8).mirror().addBox(-2.0F, 8.0F, 0.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(8, 10).mirror().addBox(-2.0F, 9.0F, 0.0F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(10, 6).mirror().addBox(-2.0F, 7.0F, 0.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(16, 8).addBox(-2.0F, 13.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 16.0F, 0.0F, 0.0F, 3.1416F, 2.618F));

        partdefinition.addOrReplaceChild("handle", CubeListBuilder.create().texOffs(14, 13).addBox(-3.0F, 13.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 15).addBox(-4.0F, 14.0F, 0.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 17).addBox(-5.0F, 15.0F, 0.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(8, 15).addBox(-6.0F, 16.0F, 0.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(8, 17).addBox(-6.0F, 17.0F, 0.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 16.0F, 0.0F, 0.0F, 3.1416F, 2.618F));

        return LayerDefinition.create(meshdefinition, 32, 32);
    }

    public void renderToBuffer(@NotNull PoseStack pPoseStack, @NotNull VertexConsumer pBuffer, int pPackedLight, int pPackedOverlay, float pRed, float pGreen, float pBlue, float pAlpha) {
        this.blade.render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, pRed, pGreen, pBlue, pAlpha);
        this.handle.render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, pRed, pGreen, pBlue, pAlpha);
    }
}
