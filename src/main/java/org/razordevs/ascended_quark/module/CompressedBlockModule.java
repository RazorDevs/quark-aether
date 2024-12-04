package org.razordevs.ascended_quark.module;

import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.aether.item.AetherCreativeTabs;
import com.google.common.collect.Lists;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import org.razordevs.ascended_quark.util.RegistryUtil;
import org.violetmoon.quark.content.building.module.CompressedBlocksModule;
import org.violetmoon.zeta.block.ZetaFlammableBlock;
import org.violetmoon.zeta.block.ZetaFlammablePillarBlock;
import org.violetmoon.zeta.event.bus.LoadEvent;
import org.violetmoon.zeta.event.load.ZLoadComplete;
import org.violetmoon.zeta.event.load.ZRegister;
import org.violetmoon.zeta.module.ZetaLoadModule;
import org.violetmoon.zeta.module.ZetaModule;

import java.util.List;
import java.util.function.BooleanSupplier;

@ZetaLoadModule(category = "aether")
public class CompressedBlockModule extends ZetaModule {

    private static final List<Block> compostable = Lists.newArrayList();

    @LoadEvent
    public void register(ZRegister register) {
        crate("blue_berry", MapColor.COLOR_BLUE, true, this);
        pillar("skyroot_stick", MapColor.WOOD, false, () -> CompressedBlocksModule.enableStickBlock, 300, this);
    }

    @LoadEvent
    public void loadComplete(ZLoadComplete event) {
        event.enqueueWork(() -> {
            for (Block block : compostable) {
                ComposterBlock.COMPOSTABLES.put(block.asItem(), 1.0F);
            }
        });
    }

    public static void crate(String name, MapColor color, boolean compost, ZetaModule module) {
        Block block = (new ZetaFlammableBlock(name + "_crate", module, 150, BlockBehaviour.Properties.of().mapColor(color).ignitedByLava().strength(1.5F).sound(SoundType.WOOD))).setCreativeTab(AetherCreativeTabs.AETHER_BUILDING_BLOCKS.getKey());
        RegistryUtil.addCreativeModeTab(AetherCreativeTabs.AETHER_BUILDING_BLOCKS.getKey(), block, AetherBlocks.SKYROOT_PLANKS, module);
        if (compost) compostable.add(block);
    }

    public static void disabledCrate(String name, MapColor color, boolean compost, ZetaModule module) {
        Block block = (new ZetaFlammableBlock(name + "_crate", module, 150, BlockBehaviour.Properties.of().mapColor(color).ignitedByLava().strength(1.5F).sound(SoundType.WOOD)));
        if (compost) compostable.add(block);
    }

    private static void pillar(String name, MapColor color, boolean compost, BooleanSupplier cond, int flammability, ZetaModule module) {
        Block block = (new ZetaFlammablePillarBlock(name + "_block", module, flammability, BlockBehaviour.Properties.of().mapColor(color).ignitedByLava().strength(0.5F).sound(SoundType.WOOD))).setCondition(cond);
        RegistryUtil.addCreativeModeTab(AetherCreativeTabs.AETHER_BUILDING_BLOCKS.getKey(), block, AetherBlocks.SKYROOT_PLANKS, module);
        if (compost) compostable.add(block);
    }
}
