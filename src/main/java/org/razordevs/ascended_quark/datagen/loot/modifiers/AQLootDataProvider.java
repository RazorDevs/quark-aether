package org.razordevs.ascended_quark.datagen.loot.modifiers;

import com.aetherteam.aether.loot.AetherLoot;
import net.minecraft.data.PackOutput;
import net.minecraft.util.random.WeightedEntry;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;
import org.razordevs.ascended_quark.AscendedQuark;
import org.razordevs.ascended_quark.module.AQPickarangModule;

import java.util.HashMap;
import java.util.List;

public class AQLootDataProvider extends GlobalLootModifierProvider {

    private HashMap<String, Item> itemMap;

    public AQLootDataProvider(PackOutput output, HashMap<String, Item> itemMap)
    {
        super(output, AscendedQuark.MODID);
        this.itemMap = itemMap;
    }

    @Override
    protected void start() {
        add("pickarang_bronze_loot_modifiers", new AQDungeonLootModifier(
                new LootItemCondition[] { LootTableIdCondition.builder(AetherLoot.BRONZE_DUNGEON).build() },
                List.of(
                        WeightedEntry.wrap(new ItemStack(AQPickarangModule.valk_pickarang.asItem()), 5)
                ),
                70,
                0.2f
        ));

        add("pickarang_silver_loot_modifiers", new AQDungeonLootModifier(
                new LootItemCondition[] { LootTableIdCondition.builder(AetherLoot.SILVER_DUNGEON).build() },
                List.of(
                        WeightedEntry.wrap(new ItemStack(AQPickarangModule.valk_pickarang.asItem()), 6),
                        WeightedEntry.wrap(new ItemStack(AQPickarangModule.phoenix_flamerang.asItem()), 4)

                ),
                70,
                0.3f
        ));
    }
}
