package org.razordevs.ascended_quark.module.compat.deep_aether;

import com.aetherteam.aether.item.AetherCreativeTabs;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.fml.ModList;
import org.razordevs.ascended_quark.AscendedQuark;
import org.razordevs.ascended_quark.blocks.AetherMudBrickLatticeBlock;
import org.razordevs.ascended_quark.util.RegistryUtil;
import org.violetmoon.quark.content.building.block.MudBrickLatticeBlock;
import org.violetmoon.zeta.block.ZetaBlock;
import org.violetmoon.zeta.block.ZetaPillarBlock;
import org.violetmoon.zeta.event.bus.LoadEvent;
import org.violetmoon.zeta.event.load.ZRegister;
import org.violetmoon.zeta.module.ZetaLoadModule;
import org.violetmoon.zeta.module.ZetaModule;
import org.violetmoon.zeta.registry.CreativeTabManager;
import teamrazor.deepaether.init.DABlocks;

@ZetaLoadModule(category = "deep_aether")
public class MoreAetherMudBlocksModule extends ZetaModule {
    @LoadEvent
    public final void register(ZRegister event) {
        if(!ModList.get().isLoaded(AscendedQuark.DEEP_AETHER)) return;

        BlockBehaviour.Properties props = BlockBehaviour.Properties.copy(Blocks.MUD_BRICKS);
        //RegistryUtil.addCreativeModeTab(AetherCreativeTabs.AETHER_BUILDING_BLOCKS.getKey(), new ZetaBlock("carved_aether_mud_bricks", this, props), DABlocks.AETHER_MUD_BRICKS_STAIRS);
        Block block = new ZetaPillarBlock("aether_mud_pillar", this, props);
        if(this.enabled) RegistryUtil.addCreativeModeTab(AetherCreativeTabs.AETHER_BUILDING_BLOCKS.getKey(), block, DABlocks.AETHER_MUD_BRICKS_STAIRS, this);

        block = new AetherMudBrickLatticeBlock(this, props);
        if(this.enabled) RegistryUtil.addCreativeModeTab(AetherCreativeTabs.AETHER_BUILDING_BLOCKS.getKey(), block, DABlocks.AETHER_MUD_BRICKS_STAIRS, this);

    }
}
