package org.razordevs.ascended_quark.module;

import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.aether.item.AetherCreativeTabs;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import org.razordevs.ascended_quark.AscendedQuark;
import org.razordevs.ascended_quark.blocks.AQVariantFurnaceBlock;
import org.razordevs.ascended_quark.blocks.entity.HolystoneFurnaceBlockEntity;
import org.razordevs.ascended_quark.util.RegistryUtil;
import org.violetmoon.zeta.event.bus.LoadEvent;
import org.violetmoon.zeta.event.load.ZRegister;
import org.violetmoon.zeta.module.ZetaLoadModule;
import org.violetmoon.zeta.module.ZetaModule;

@ZetaLoadModule(category = "aether")
public class HolystoneFurnaceModule extends ZetaModule {
    public static BlockEntityType<HolystoneFurnaceBlockEntity> blockEntityType;

    @LoadEvent
    public void register(ZRegister register) {
        Block furnace = new AQVariantFurnaceBlock("holystone", this, BlockBehaviour.Properties.of().mapColor(MapColor.WOOL).instrument(NoteBlockInstrument.BASEDRUM).strength(0.5F).requiresCorrectToolForDrops().lightLevel(RegistryUtil.litBlockEmission(13)));
        blockEntityType = BlockEntityType.Builder.of(HolystoneFurnaceBlockEntity::new, furnace).build(null);
        AscendedQuark.ZETA.registry.register(blockEntityType, "variant_furnace", Registries.BLOCK_ENTITY_TYPE);

        RegistryUtil.addCreativeModeTab(AetherCreativeTabs.AETHER_FUNCTIONAL_BLOCKS.getKey(), furnace, AetherBlocks.ALTAR, this);
    }
}
