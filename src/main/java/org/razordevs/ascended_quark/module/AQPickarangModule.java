package org.razordevs.ascended_quark.module;

import com.aetherteam.aether.loot.AetherLoot;
import com.google.common.collect.Lists;
import it.unimi.dsi.fastutil.objects.Object2IntArrayMap;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import org.jetbrains.annotations.Nullable;
import org.razordevs.ascended_quark.util.RegistryUtil;
import org.violetmoon.quark.base.Quark;
import org.violetmoon.quark.content.tools.client.render.entity.PickarangRenderer;
import org.violetmoon.quark.content.tools.config.PickarangType;
import org.violetmoon.quark.content.tools.entity.rang.AbstractPickarang;
import org.violetmoon.quark.content.tools.entity.rang.Flamerang;
import org.violetmoon.quark.content.tools.entity.rang.Pickarang;
import org.violetmoon.quark.content.tools.item.PickarangItem;
import org.violetmoon.quark.content.tools.module.PickarangModule;
import org.violetmoon.zeta.advancement.ManualTrigger;
import org.violetmoon.zeta.client.event.load.ZClientSetup;
import org.violetmoon.zeta.config.Config;
import org.violetmoon.zeta.event.bus.LoadEvent;
import org.violetmoon.zeta.event.bus.PlayEvent;
import org.violetmoon.zeta.event.load.*;
import org.violetmoon.zeta.event.play.loading.ZLootTableLoad;
import org.violetmoon.zeta.module.ZetaLoadModule;
import org.violetmoon.zeta.module.ZetaModule;
import org.violetmoon.zeta.util.BooleanSuppliers;
import org.violetmoon.zeta.util.Hint;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BooleanSupplier;

@ZetaLoadModule(category = "aether")
public class AQPickarangModule extends ZetaModule {

    @Config(flag = "phoenix_flamerang")
    public static boolean enableFlamerang = true;

    @Hint
    public static Item valk_pickarang;
    @Hint("flamerang")
    public static Item phoenix_flamerang;

    private static final List<PickarangType<?>> knownTypes = new ArrayList<>();
    private static boolean isEnabled;

    private static String loot(ResourceLocation lootLoc, int defaultWeight) {
        return lootLoc.toString() + "," + defaultWeight;
    }

//    @Config(description = "Format is lootTable,weight. i.e. \"aether:chests/bronze_dungeon,30\"")
//    public static List<String> lootTables = Lists.newArrayList(
//            loot(AetherLoot.BRONZE_DUNGEON, 5),
//            loot(AetherLoot.SILVER_DUNGEON, 10)
//    );

//    private static final Object2IntMap<ResourceLocation> lootTableWeights = new Object2IntArrayMap<>();


    @LoadEvent
    public final void register(ZRegister event) {
        valk_pickarang = makePickarang(PickarangModule.pickarangType, "valkyrie_pickarang", Pickarang::new, Pickarang::new, BooleanSuppliers.TRUE).setCreativeTab(CreativeModeTabs.TOOLS_AND_UTILITIES, Items.DIAMOND_HOE, false);
        phoenix_flamerang = makePickarang(PickarangModule.flamerangType, "phoenix_flamerang", Flamerang::new, Flamerang::new, () -> enableFlamerang).setCreativeTab(CreativeModeTabs.TOOLS_AND_UTILITIES, Items.NETHERITE_HOE, false);
    }

    private <T extends AbstractPickarang<T>> PickarangItem makePickarang(PickarangType<T> type, String name,
                                                                         EntityType.EntityFactory<T> entityFactory,
                                                                         PickarangType.PickarangConstructor<T> thrownFactory,
                                                                         BooleanSupplier condition) {

        EntityType<T> entityType = EntityType.Builder.of(entityFactory, MobCategory.MISC)
                .sized(0.4F, 0.4F)
                .clientTrackingRange(4)
                .updateInterval(10)
                .setCustomClientFactory((t, l) -> entityFactory.create(type.getEntityType(), l))
                .build(name);
        Quark.ZETA.registry.register(entityType, name, Registries.ENTITY_TYPE);

        knownTypes.add(type);
        type.setEntityType(entityType, thrownFactory);
        return (PickarangItem) new PickarangItem(name, this, propertiesFor(type.durability, type.isFireResistant()), type).setCondition(condition);
    }

    private Item.Properties propertiesFor(int durability, boolean fireResist) {
        Item.Properties properties = new Item.Properties()
                .stacksTo(1);

        if (durability > 0)
            properties.durability(durability);

        if (fireResist)
            properties.fireResistant();

        return properties;
    }

//    @LoadEvent
//    public final void configChanged(ZConfigChanged event) {
//        // Pass over to a static reference for easier computing the coremod hook
//        isEnabled = this.enabled;
//        lootTableWeights.clear();
//        for(String table : lootTables) {
//            String[] split = table.split(",");
//            if(split.length == 2) {
//                int weight;
//                ResourceLocation loc = new ResourceLocation(split[0]);
//                try {
//                    weight = Integer.parseInt(split[1]);
//                } catch (NumberFormatException e) {
//                    continue;
//                }
//                if(weight > 0)
//                    lootTableWeights.put(loc, weight);
//            }
//        }
//    }

    public static boolean getIsFireResistant(boolean vanillaVal, Entity entity) {
        if (!isEnabled || vanillaVal)
            return vanillaVal;

        Entity riding = entity.getVehicle();
        if (riding instanceof AbstractPickarang<?> pick)
            return pick.getPickarangType().isFireResistant();

        return false;
    }

    /* This doesn't work, as it empties the current loot table and just puts in the pickarang instead of adding it
    @PlayEvent
    public void onLootTableLoad(ZLootTableLoad event) {
        RegistryUtil.registerModifiedLootTable(AetherLoot.BRONZE_DUNGEON, AQPickarangModule.valk_pickarang, 5, 1, event);
        RegistryUtil.registerModifiedLootTable(AetherLoot.SILVER_DUNGEON, AQPickarangModule.phoenix_flamerang, 5, 1, event);
    }
     */

    @ZetaLoadModule(clientReplacement = true)
    public static class Client extends AQPickarangModule {
        @LoadEvent
        public final void clientSetup(ZClientSetup event) {
            knownTypes.forEach(t -> EntityRenderers.register(t.getEntityType(), PickarangRenderer::new));
        }
    }
}
