package org.razordevs.ascended_quark.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.razordevs.ascended_quark.blocks.entity.HolystoneFurnaceBlockEntity;
import org.razordevs.ascended_quark.module.HolystoneFurnaceModule;
import org.violetmoon.quark.content.building.block.VariantFurnaceBlock;
import org.violetmoon.zeta.module.ZetaModule;

public class AQVariantFurnaceBlock extends VariantFurnaceBlock {
    public AQVariantFurnaceBlock(String type, ZetaModule module, Properties props) {
        super(type, module, props);
    }

    public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return new HolystoneFurnaceBlockEntity(pos, state);
    }

    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(@NotNull Level world, @NotNull BlockState state, @NotNull BlockEntityType<T> beType) {
        return createFurnaceTicker(world, beType, HolystoneFurnaceModule.blockEntityType);
    }

    @Override
    public Block setCreativeTab(ResourceKey<CreativeModeTab> tab, ItemLike parent, boolean behindParent) {
        return this;
    }
}
