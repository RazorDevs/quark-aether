package org.razordevs.ascended_quark.blocks;

import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.aether.item.AetherCreativeTabs;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;
import org.razordevs.ascended_quark.AscendedQuark;
import org.razordevs.ascended_quark.util.RegistryUtil;
import org.violetmoon.quark.content.building.block.VerticalSlabBlock;
import org.violetmoon.zeta.block.IZetaBlock;
import org.violetmoon.zeta.module.ZetaModule;
import org.violetmoon.zeta.util.BooleanSuppliers;

import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

public class CompAQVerticalSlabBlock extends VerticalSlabBlock implements IZetaBlock {
    private final ZetaModule module;
    private BooleanSupplier enabledSupplier;

    @SuppressWarnings("unchecked")
    public CompAQVerticalSlabBlock(String registryName, RegistryObject<? extends ItemLike> parent, Properties properties, ZetaModule module) {
        super((Supplier<Block>) parent, properties);
        this.enabledSupplier = BooleanSuppliers.TRUE;
        AscendedQuark.ZETA.registry.registerBlock(this, registryName, true);
        this.module = module;

        if (module.category.isAddon()) {
            module.zeta.requiredModTooltipHandler.map(this, module.category.requiredMod);
        }
        RegistryUtil.addCreativeModeTab(AetherCreativeTabs.AETHER_BUILDING_BLOCKS.getKey(), this, parent, module);
    }
    public CompAQVerticalSlabBlock(String registryName, Properties properties, ZetaModule module) {
        this(registryName, AetherBlocks.ANGELIC_STONE, properties, module);
    }

        public CompAQVerticalSlabBlock setCondition(BooleanSupplier enabledSupplier) {
        this.enabledSupplier = enabledSupplier;
        return this;
    }

    public boolean doesConditionApply() {
        return this.enabledSupplier.getAsBoolean();
    }

    public @Nullable ZetaModule getModule() {
        return this.module;
    }

    @Override
    @Nullable
    public String getBlockColorProviderName() {
        return null;
    }

    @Override
    @Nullable
    public String getItemColorProviderName() {
        return null;
    }
}
