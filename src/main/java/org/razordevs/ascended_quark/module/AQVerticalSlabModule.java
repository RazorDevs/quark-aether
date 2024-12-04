package org.razordevs.ascended_quark.module;

import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.aether.item.AetherCreativeTabs;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.fml.ModList;
import org.razordevs.ascended_quark.AscendedQuark;
import org.razordevs.ascended_quark.util.RegistryUtil;
import org.razordevs.ascended_quark.blocks.AQVerticalSlabBlock;
import org.razordevs.ascended_quark.blocks.CompAQVerticalSlabBlock;
import org.violetmoon.quark.content.building.module.VerticalSlabsModule;
import org.violetmoon.zeta.event.bus.LoadEvent;
import org.violetmoon.zeta.event.load.ZRegister;
import org.violetmoon.zeta.module.ZetaLoadModule;
import org.violetmoon.zeta.module.ZetaModule;
import teamrazor.deepaether.init.DABlocks;

@ZetaLoadModule(category = "aether", name = "Ascended Quark Vertical Slabs")
public class AQVerticalSlabModule extends ZetaModule {

    @LoadEvent
    public void postRegister(ZRegister.Post e) {
        new CompAQVerticalSlabBlock("angelic_vertical_slab", AetherBlocks.ANGELIC_STONE, BlockBehaviour.Properties.of().mapColor(MapColor.SAND).instrument(NoteBlockInstrument.BASEDRUM).strength(0.5F, 6.0F).requiresCorrectToolForDrops(),this);
        new CompAQVerticalSlabBlock("hellfire_vertical_slab", AetherBlocks.HELLFIRE_STONE, BlockBehaviour.Properties.of().mapColor(MapColor.NETHER).instrument(NoteBlockInstrument.BASEDRUM).strength(0.5F, 6.0F).requiresCorrectToolForDrops(), this);
        new CompAQVerticalSlabBlock("holystone_vertical_slab", AetherBlocks.HOLYSTONE, BlockBehaviour.Properties.of().mapColor(MapColor.WOOL).instrument(NoteBlockInstrument.BASEDRUM).strength(0.5F).requiresCorrectToolForDrops(), this);
        new CompAQVerticalSlabBlock("mossy_holystone_vertical_slab", AetherBlocks.MOSSY_HOLYSTONE, BlockBehaviour.Properties.of().mapColor(MapColor.WOOL).instrument(NoteBlockInstrument.BASEDRUM).strength(0.5F).requiresCorrectToolForDrops(), this);
        new CompAQVerticalSlabBlock("icestone_vertical_slab", AetherBlocks.ICESTONE, BlockBehaviour.Properties.of().mapColor(MapColor.ICE).instrument(NoteBlockInstrument.CHIME).strength(0.5F).randomTicks().sound(SoundType.GLASS).requiresCorrectToolForDrops(), this);
        new CompAQVerticalSlabBlock("holystone_brick_vertical_slab", AetherBlocks.HOLYSTONE_BRICKS, BlockBehaviour.Properties.of().mapColor(MapColor.WOOL).instrument(NoteBlockInstrument.BASEDRUM).strength(2.0F, 10.0F).requiresCorrectToolForDrops(), this);
        new CompAQVerticalSlabBlock("aerogel_vertical_slab", AetherBlocks.AEROGEL, BlockBehaviour.Properties.of().mapColor(MapColor.DIAMOND).instrument(NoteBlockInstrument.IRON_XYLOPHONE).strength(1.0F, 2000.0F).sound(SoundType.METAL).noOcclusion().requiresCorrectToolForDrops().isViewBlocking((var1, var2 , var3) -> false), this);

        AscendedQuark.ZETA.variantRegistry.slabs.forEach((b) -> {
            if (b instanceof VerticalSlabsModule.IVerticalSlabProvider provider) {
                provider.getVerticalSlab(b, this);
            } else {
                new AQVerticalSlabBlock(b, this);
            }
        });
    }
}
