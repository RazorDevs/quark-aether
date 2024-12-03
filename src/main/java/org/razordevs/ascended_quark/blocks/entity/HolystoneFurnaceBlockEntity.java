package org.razordevs.ascended_quark.blocks.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.FurnaceMenu;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.razordevs.ascended_quark.module.HolystoneFurnaceModule;

public class HolystoneFurnaceBlockEntity extends AbstractFurnaceBlockEntity {
    public HolystoneFurnaceBlockEntity(BlockPos pos, BlockState state) {
        super(HolystoneFurnaceModule.blockEntityType, pos, state, RecipeType.SMELTING);
    }

    protected @NotNull Component getDefaultName() {
        return Component.translatable("container.furnace");
    }

    protected @NotNull AbstractContainerMenu createMenu(int id, @NotNull Inventory playerInventory) {
        return new FurnaceMenu(id, playerInventory, this, this.dataAccess);
    }
}

