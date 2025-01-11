package org.razordevs.ascended_quark.mixin;

import com.aetherteam.aether.AetherConfig;
import com.aetherteam.aether.client.renderer.entity.layers.ZephyrTransparencyLayer;
import com.aetherteam.aether.client.renderer.entity.model.ZephyrModel;
import com.aetherteam.aether.entity.monster.Zephyr;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import org.razordevs.ascended_quark.module.AetherVariantAnimalTexturesModule;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = ZephyrTransparencyLayer.class)
public abstract class ZephyrLayerMixin extends RenderLayer<Zephyr, EntityModel<Zephyr>> {

    @Shadow(remap = false) @Final private ZephyrModel transparency;

    public ZephyrLayerMixin(RenderLayerParent<Zephyr, EntityModel<Zephyr>> p_117346_) {
        super(p_117346_);
    }


    @Inject(at = @At("HEAD"), method = "render(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILcom/aetherteam/aether/entity/monster/Zephyr;FFFFFF)V", remap = false)
    public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, Zephyr zephyr, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, CallbackInfo ci) {
        ResourceLocation location = AetherVariantAnimalTexturesModule.Client.getZephyrLayerTexture(zephyr);

        if(!AetherConfig.CLIENT.legacy_models.get() && location != null) {
            if (this.getParentModel() instanceof ZephyrModel && !zephyr.isInvisible()) {
                this.getParentModel().copyPropertiesTo(this.transparency);
                this.transparency.prepareMobModel(zephyr, limbSwing, limbSwingAmount, partialTicks);
                this.transparency.setupAnim(zephyr, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
                VertexConsumer consumer = buffer.getBuffer(RenderType.entityTranslucent(location));
                this.transparency.renderToBuffer(poseStack, consumer, packedLight, LivingEntityRenderer.getOverlayCoords(zephyr, 0.0F), 1.0F, 1.0F, 1.0F, 1.0F);
            }
        }

    }
}
