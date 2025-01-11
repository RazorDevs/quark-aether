package org.razordevs.ascended_quark.mixin;

import com.aetherteam.aether.AetherConfig;
import com.aetherteam.aether.client.renderer.AetherModelLayers;
import com.aetherteam.aether.client.renderer.entity.MultiModelRenderer;
import com.aetherteam.aether.client.renderer.entity.ZephyrRenderer;
import com.aetherteam.aether.client.renderer.entity.layers.ZephyrTransparencyLayer;
import com.aetherteam.aether.client.renderer.entity.model.ClassicZephyrModel;
import com.aetherteam.aether.client.renderer.entity.model.ZephyrModel;
import com.aetherteam.aether.entity.monster.Cockatrice;
import com.aetherteam.aether.entity.monster.Zephyr;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.razordevs.ascended_quark.module.AetherVariantAnimalTexturesModule;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Do NOT edit this mixin or ZephyrLayerMixin.
 * Variable names need to be the same as the original for stuff to work.
 */
@Mixin(value = ZephyrRenderer.class)
public abstract class ZephyrRendererMixin extends MultiModelRenderer<Zephyr, EntityModel<Zephyr>, ZephyrModel, ClassicZephyrModel> {
    public ZephyrRendererMixin(EntityRendererProvider.Context context, ZephyrModel defaultModel, float shadowRadius) {
        super(context, defaultModel, shadowRadius);
    }

    @Override
    @NotNull
    public ResourceLocation getTextureLocation(Zephyr entity) {
        if(AetherConfig.CLIENT.legacy_models.get())
            return this.getOldTexture();
        else {
            ResourceLocation resourceLocation = AetherVariantAnimalTexturesModule.Client.getZephyrTexture(entity);
            if(resourceLocation != null)
                return resourceLocation;
            else return this.getDefaultTexture();
        }
    }
}
