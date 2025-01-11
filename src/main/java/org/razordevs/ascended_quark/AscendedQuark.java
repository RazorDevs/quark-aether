package org.razordevs.ascended_quark;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.MissingMappingsEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.ApiStatus;
import org.razordevs.ascended_quark.datagen.AQBlockstateData;
import org.razordevs.ascended_quark.datagen.AQItemModelData;
import org.razordevs.ascended_quark.datagen.AQLangData;
import org.razordevs.ascended_quark.datagen.AQRecipeData;
import org.razordevs.ascended_quark.datagen.loot.AQLootTableData;
import org.razordevs.ascended_quark.datagen.loot.modifiers.AQGlobalLootModifiers;
import org.razordevs.ascended_quark.datagen.loot.modifiers.AQLootDataProvider;
import org.razordevs.ascended_quark.datagen.tags.AQBlockTagData;
import org.razordevs.ascended_quark.datagen.tags.AQItemTagData;
import org.razordevs.ascended_quark.mixin.ZetaRegistryAccessor;
import org.razordevs.ascended_quark.proxy.ACClientProxy;
import org.razordevs.ascended_quark.proxy.ACCommonProxy;
import org.violetmoon.zeta.Zeta;
import org.violetmoon.zeta.multiloader.Env;
import org.violetmoon.zetaimplforge.ForgeZeta;

import java.util.HashMap;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@Mod(AscendedQuark.MODID)
public class AscendedQuark {

    //TODO: Pickarangs LootTables

    public static final String MODID = "ascended_quark";
    public static final String DEEP_AETHER = "deep_aether";
    public static final Logger LOGGER = LogManager.getLogger(MODID);

    public static AscendedQuark instance;
    public static ACCommonProxy proxy;
    public static final Zeta ZETA = new ForgeZeta(MODID, LogManager.getLogger("aq-zeta"));

    public AscendedQuark() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        instance = this;
        ZETA.start();

        proxy = Env.unsafeRunForDist(() -> ACClientProxy::new, () -> ACCommonProxy::new);
        proxy.start();

        MinecraftForge.EVENT_BUS.addListener(this::missingMappings);
        bus.addListener(this::dataSetup);

        AQGlobalLootModifiers.LOOT_MODIFIERS.register(bus);

        MinecraftForge.EVENT_BUS.register(this);
    }

    public void dataSetup(GatherDataEvent event) {

        ZetaRegistryAccessor accessor = (ZetaRegistryAccessor) ZETA.registry;

        HashMap<String, Block> blockMap = new HashMap<>();
        HashMap<String, Item> itemBlockMap = new HashMap<>();
        HashMap<String, Item> itemMap = new HashMap<>();

        for (Object value : accessor.getInternalNames().keySet()) {
            ResourceLocation location = accessor.getInternalNames().get(value);
            if (value instanceof Block block)
                blockMap.put(location.getPath(), block);
            else if(value instanceof Item item)
                itemBlockMap.put(location.getPath(), item);
        }

        itemBlockMap.forEach((s, item) -> {
            if(!blockMap.containsKey(s))
                itemMap.put(s, item);
        });
        itemBlockMap.clear();

        DataGenerator generator = event.getGenerator();
        ExistingFileHelper fileHelper = event.getExistingFileHelper();
        PackOutput output = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        // Client Data
        generator.addProvider(event.includeClient(), new AQBlockstateData(output, fileHelper, blockMap));
        generator.addProvider(event.includeClient(), new AQItemModelData(output, fileHelper, itemMap, blockMap));
        generator.addProvider(event.includeClient(), new AQLangData(output, itemMap, blockMap));

        // Server Data
        generator.addProvider(event.includeServer(), new AQRecipeData(output, itemMap, blockMap));
        generator.addProvider(event.includeServer(), AQLootTableData.create(output, blockMap));
        AQBlockTagData blockTags = new AQBlockTagData(output, lookupProvider, fileHelper, blockMap);
        generator.addProvider(event.includeServer(), new AQLootDataProvider(output, itemMap));


        generator.addProvider(event.includeServer(), blockTags);
        generator.addProvider(event.includeServer(), new AQItemTagData(output, lookupProvider, blockTags.contentsGetter(), fileHelper, itemMap, blockMap));
    }

    @Deprecated(forRemoval = true)
    @ApiStatus.ScheduledForRemoval(inVersion = "1.21.1")
    public void missingMappings(MissingMappingsEvent event) {
        Stream<MissingMappingsEvent.Mapping<Block>> stream = event.getMappings(ForgeRegistries.Keys.BLOCKS, AscendedQuark.MODID).stream();
        stream.filter(mapping -> mapping.getKey().getPath().contains("brick_"))
                .forEach(blockMapping -> {
                    Block block = ForgeRegistries.BLOCKS.getValue(asResource(blockMapping.getKey().getPath().replace("brick_", "bricks_")));
                    if(block != null)
                        blockMapping.remap(block);

                });

        Stream<MissingMappingsEvent.Mapping<Item>> itemSteam = event.getMappings(ForgeRegistries.Keys.ITEMS, AscendedQuark.MODID).stream();
        itemSteam.filter(mapping -> mapping.getKey().getPath().contains("brick_"))
                .forEach(itemMapping -> {
                    Block block = ForgeRegistries.BLOCKS.getValue(asResource(itemMapping.getKey().getPath().replace("brick_", "bricks_")));
                    if(block != null)
                        itemMapping.remap(block.asItem());
                });
    }

    public static ResourceLocation asResource(String name) {
        return new ResourceLocation(MODID, name);
    }

    public static <T> ResourceKey<T> asResourceKey(ResourceKey<? extends Registry<T>> base, String name) {
        return ResourceKey.create(base, asResource(name));
    }
}
