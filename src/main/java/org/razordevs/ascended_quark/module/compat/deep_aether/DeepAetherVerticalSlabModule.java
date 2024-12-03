package org.razordevs.ascended_quark.module.compat.deep_aether;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.fml.ModList;
import org.razordevs.ascended_quark.AscendedQuark;
import org.razordevs.ascended_quark.blocks.CompAQVerticalSlabBlock;
import org.violetmoon.zeta.event.bus.LoadEvent;
import org.violetmoon.zeta.event.load.ZRegister;
import org.violetmoon.zeta.module.ZetaLoadModule;
import org.violetmoon.zeta.module.ZetaModule;
import teamrazor.deepaether.init.DABlocks;

@ZetaLoadModule(category = "deep_aether")
public class DeepAetherVerticalSlabModule extends ZetaModule {

    @LoadEvent
    public void register(ZRegister register) {
        if(!ModList.get().isLoaded(AscendedQuark.DEEP_AETHER)) return;
        if(this.enabled) {
            new CompAQVerticalSlabBlock("mossy_holystone_tile_vertical_slab", DABlocks.MOSSY_HOLYSTONE_TILE_SLAB, BlockBehaviour.Properties.copy(Blocks.MOSSY_STONE_BRICKS), this);
            new CompAQVerticalSlabBlock("holystone_tile_vertical_slab", DABlocks.HOLYSTONE_TILE_SLAB, BlockBehaviour.Properties.copy(Blocks.MOSSY_STONE_BRICKS), this);
            new CompAQVerticalSlabBlock("big_holystone_bricks_vertical_slab", DABlocks.BIG_HOLYSTONE_BRICKS_SLAB, BlockBehaviour.Properties.of().sound(SoundType.STONE).strength(1.0F, 10.0F).requiresCorrectToolForDrops(), this);
            new CompAQVerticalSlabBlock("aseterite_vertical_slab", DABlocks.ASETERITE_SLAB, BlockBehaviour.Properties.copy(Blocks.STONE), this);
            new CompAQVerticalSlabBlock("polished_aseterite_vertical_slab", DABlocks.POLISHED_ASETERITE_SLAB, BlockBehaviour.Properties.copy(Blocks.SMOOTH_STONE), this);
            new CompAQVerticalSlabBlock("aseterite_bricks_vertical_slab", DABlocks.ASETERITE_BRICKS_SLAB, BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS), this);
            new CompAQVerticalSlabBlock("raw_clorite_vertical_slab", DABlocks.RAW_CLORITE_SLAB, BlockBehaviour.Properties.copy(Blocks.STONE), this);
            new CompAQVerticalSlabBlock("clorite_vertical_slab", DABlocks.CLORITE_SLAB, BlockBehaviour.Properties.copy(Blocks.STONE), this);
            new CompAQVerticalSlabBlock("polished_clorite_vertical_slab", DABlocks.POLISHED_CLORITE_SLAB, BlockBehaviour.Properties.copy(Blocks.SMOOTH_STONE), this);
            new CompAQVerticalSlabBlock("aether_mud_bricks_vertical_slab", DABlocks.AETHER_MUD_BRICKS_SLAB, BlockBehaviour.Properties.copy(Blocks.MUD_BRICKS), this);
        } else {
            new CompAQVerticalSlabBlock("mossy_holystone_brick_vertical_slab", BlockBehaviour.Properties.copy(Blocks.MOSSY_STONE_BRICKS), this);
            new CompAQVerticalSlabBlock("holystone_brick_vertical_slab", BlockBehaviour.Properties.copy(Blocks.MOSSY_STONE_BRICKS), this);
            new CompAQVerticalSlabBlock("big_holystone_bricks_vertical_slab", BlockBehaviour.Properties.of().sound(SoundType.STONE).strength(1.0F, 10.0F).requiresCorrectToolForDrops(), this);
            new CompAQVerticalSlabBlock("aseterite_vertical_slab", BlockBehaviour.Properties.copy(Blocks.STONE), this);
            new CompAQVerticalSlabBlock("polished_aseterite_vertical_slab", BlockBehaviour.Properties.copy(Blocks.SMOOTH_STONE), this);
            new CompAQVerticalSlabBlock("aseterite_bricks_vertical_slab", BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS), this);
            new CompAQVerticalSlabBlock("raw_clorite_vertical_slab", BlockBehaviour.Properties.copy(Blocks.STONE), this);
            new CompAQVerticalSlabBlock("clorite_vertical_slab", BlockBehaviour.Properties.copy(Blocks.STONE), this);
            new CompAQVerticalSlabBlock("polished_clorite_vertical_slab", BlockBehaviour.Properties.copy(Blocks.SMOOTH_STONE), this);
            new CompAQVerticalSlabBlock("aether_mud_bricks_vertical_slab", BlockBehaviour.Properties.copy(Blocks.MUD_BRICKS), this);
        }
    }
}
