package com.christofmeg.brutalharvest.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.RenderType;
import org.jetbrains.annotations.NotNull;

public class KnifeModel extends Model {
    private final ModelPart root;

    public KnifeModel(ModelPart pRoot) {
        super(RenderType::entityCutoutNoCullZOffset);
        this.root = pRoot;
    }

    public static LayerDefinition createLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        partdefinition.addOrReplaceChild("element1",
                CubeListBuilder.create()
                .texOffs(2, 9).addBox(7.0F, 10.0F, 12.0F, 1.0F, 1.0F, 2.0F)
                .texOffs(3, 8).addBox(7.0F, 9.0F, 11.0F, 1.0F, 1.0F, 3.0F)
                .texOffs(4, 7).addBox(7.0F, 8.0F, 10.0F, 1.0F, 1.0F, 3.0F)
                .texOffs(4, 6).addBox(7.0F, 7.0F, 8.0F, 1.0F, 1.0F, 4.0F)
                .texOffs(5, 5).addBox(7.0F, 6.0F, 7.0F, 1.0F, 1.0F, 4.0F)
                .texOffs(5, 3).addBox(7.0F, 5.0F, 6.0F, 1.0F, 1.0F, 5.0F)
                .texOffs(6, 2).addBox(7.0F, 4.0F, 5.0F, 1.0F, 1.0F, 5.0F)
                .texOffs(7, 1).addBox(7.0F, 3.0F, 4.0F, 1.0F, 1.0F, 5.0F)
                .texOffs(8, 0).addBox(7.0F, 2.0F, 3.0F, 1.0F, 1.0F, 5.0F)
                .texOffs(9, -1).addBox(7.0F, 1.0F, 2.0F, 1.0F, 1.0F, 5.0F)
                .texOffs(10, -1).addBox(7.0F, 0.0F, 2.0F, 1.0F, 1.0F, 4.0F),
                PartPose.ZERO);

        return LayerDefinition.create(meshdefinition, 16, 16);
    }

    @Override
    public void renderToBuffer(@NotNull PoseStack pPoseStack, @NotNull VertexConsumer pBuffer, int pPackedLight, int pPackedOverlay, float pRed, float pGreen, float pBlue, float pAlpha) {
        this.root.render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, pRed, pGreen, pBlue, pAlpha);
    }
}


/*
    public static LayerDefinition createLayer() {
        MeshDefinition $$0 = new MeshDefinition();
        PartDefinition $$1 = $$0.getRoot();
        PartDefinition $$2 = $$1.addOrReplaceChild("pole", CubeListBuilder.create().texOffs(0, 6).addBox(-0.5F, 2.0F, -0.5F, 1.0F, 25.0F, 1.0F), PartPose.ZERO);
        $$2.addOrReplaceChild("base", CubeListBuilder.create().texOffs(4, 0).addBox(-1.5F, 0.0F, -0.5F, 3.0F, 2.0F, 1.0F), PartPose.ZERO);
        $$2.addOrReplaceChild("middle_spike", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, -4.0F, -0.5F, 1.0F, 4.0F, 1.0F), PartPose.ZERO);
        return LayerDefinition.create($$0, 32, 32);
    }

}
*/
