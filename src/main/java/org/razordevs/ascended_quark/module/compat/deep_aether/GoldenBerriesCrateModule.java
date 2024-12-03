package org.razordevs.ascended_quark.module.compat.deep_aether;

import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.fml.ModList;
import org.razordevs.ascended_quark.AscendedQuark;
import net.minecraftforge.fml.ModList;
import org.razordevs.ascended_quark.AscendedQuark;
import org.razordevs.ascended_quark.module.CompressedBlockModule;
import org.violetmoon.zeta.event.bus.LoadEvent;
import org.violetmoon.zeta.event.load.ZRegister;
import org.violetmoon.zeta.module.ZetaLoadModule;
import org.violetmoon.zeta.module.ZetaModule;

@ZetaLoadModule(category = "deep_aether")
public class GoldenBerriesCrateModule extends ZetaModule {

    @LoadEvent
    public void register(ZRegister register) {
        if(!ModList.get().isLoaded(AscendedQuark.DEEP_AETHER)) return;
        CompressedBlockModule.crate("goldenleaf_berries", MapColor.COLOR_YELLOW, true, this);
    }
}
