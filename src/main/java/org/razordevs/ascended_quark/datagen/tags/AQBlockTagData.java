package org.razordevs.ascended_quark.datagen.tags;

import com.aetherteam.aether.AetherTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.razordevs.ascended_quark.AscendedQuark;
import org.razordevs.ascended_quark.blocks.AQHedgeBlock;
import org.razordevs.ascended_quark.blocks.AQHollowLogBlock;
import org.razordevs.ascended_quark.blocks.AQTrappedVariantChestBlock;
import org.razordevs.ascended_quark.blocks.AQWoodenPostBlock;
import org.violetmoon.quark.base.Quark;
import org.violetmoon.quark.content.building.block.VerticalSlabBlock;
import teamrazor.deepaether.init.DABlocks;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class AQBlockTagData extends BlockTagsProvider {

    final HashMap<String, Block> blockMap;
    public AQBlockTagData(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, @Nullable ExistingFileHelper helper, HashMap<String, Block> blockMap) {
        super(output, registries, AscendedQuark.MODID, helper);
        this.blockMap = blockMap;
    }

    private final String[] ShovelBricks = {
            "aether_dirt_bricks",
            "quicksoil_bricks"
    };

    private final String[] pickaxeBricks = {
            "icestone",
            "aseterite",
            "aether_mud_bricks",
            "clorite"
    };

    @Nonnull
    @Override
    public String getName() {
        return "Ascended Quark Block Tags";
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        List<Block> blocks = new ArrayList<>(blockMap.values());

        IntrinsicTagAppender<Block> tag = this.tag(AetherTags.Blocks.TREATED_AS_AETHER_BLOCK);
        blocks.forEach(tag::add);



        blockMap.keySet().forEach(block -> {
            for(String type : ShovelBricks) {
                if(block.contains(type)) {
                    tag(BlockTags.MINEABLE_WITH_SHOVEL).add(blockMap.get(block));
                    break;
                }
            }
        });

        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
                blockMap.get("holystone_furnace"),
                blockMap.get("ambrosium_lamp"),
                blockMap.get("angelic_vertical_slab"),
                blockMap.get("hellfire_vertical_slab"),
                blockMap.get("holystone_brick_vertical_slab"),
                blockMap.get("mossy_holystone_vertical_slab"),
                blockMap.get("holystone_brick_vertical_slab"),
                blockMap.get("aerogel_vertical_slab"),
                blockMap.get("aether_mud_brick_lattice"),
                blockMap.get("aether_mud_pillar"),
                blockMap.get("mossy_holystone_tile_vertical_slab"),
                blockMap.get("holystone_tile_vertical_slab"),
                blockMap.get("big_holystone_bricks_vertical_slab")
        );
        blockMap.keySet().forEach(block -> {
            for(String type : pickaxeBricks) {
                if(block.contains(type)) {
                    tag(BlockTags.MINEABLE_WITH_PICKAXE).add(blockMap.get(block));
                    break;
                }
            }
        });

        tag(BlockTags.MINEABLE_WITH_AXE).add(
                blockMap.get("blue_berry_crate"),
                blockMap.get("goldenleaf_berries_crate")
        );
        blockMap.keySet().forEach(block -> {
            for(String type : AQItemTagData.Wood) {
                if(block.contains(type)) {
                    tag(BlockTags.MINEABLE_WITH_AXE).add(blockMap.get(block));
                    break;
                }
            }
        });

        //tag(BlockTags.MINEABLE_WITH_HOE).add();
        blockMap.keySet().forEach(block -> {
            if (block.contains("leaf_carpet")) {
                tag(BlockTags.MINEABLE_WITH_HOE).add(blockMap.get(block));
            }
        });

        blocks.forEach(block -> {
            if (block instanceof SlabBlock)
                tag(BlockTags.SLABS).add(block);
        });

        blocks.forEach(block -> {
            if (block instanceof StairBlock)
                tag(BlockTags.STAIRS).add(block);
        });

        blocks.forEach(block -> {
            if (block instanceof WallBlock)
                tag(BlockTags.WALLS).add(block);
        });

        blocks.forEach(block -> {
            if (block instanceof AQWoodenPostBlock)
                tag(BlockTags.create(new ResourceLocation(Quark.MOD_ID, "posts"))).add(block);
        });

        tag(BlockTags.create(new ResourceLocation(Quark.MOD_ID, "stools"))).add(
                blockMap.get("skyroot_stool")
        );

        blocks.forEach(block -> {
            if (block instanceof AQHedgeBlock)
                tag(BlockTags.create(new ResourceLocation(Quark.MOD_ID, "hedges"))).add(block);
        });

        blocks.forEach(block -> {
            if (block instanceof AQHollowLogBlock)
                tag(BlockTags.create(new ResourceLocation(Quark.MOD_ID, "hollow_logs"))).add(block);
        });

        blocks.forEach(block -> {
            if (block instanceof ChestBlock) {
                tag(Tags.Blocks.CHESTS_WOODEN).add(block);
                tag(Tags.Blocks.CHESTS).add(block);
                tag(BlockTags.GUARDED_BY_PIGLINS).add(block);

                if((block instanceof AQTrappedVariantChestBlock)) {
                    tag(Tags.Blocks.CHESTS_TRAPPED).add(block);
                }
            }
        });

        blocks.forEach(block -> {
            if (block instanceof LadderBlock) {
                tag(BlockTags.create(new ResourceLocation(Quark.MOD_ID, "ladders"))).add(block);
                tag(BlockTags.FALL_DAMAGE_RESETTING).add(block);
                tag(BlockTags.CLIMBABLE).add(block);
            }
        });

        blockMap.keySet().forEach(s -> {
            if (blockMap.get(s) instanceof VerticalSlabBlock block) {
                tag(BlockTags.create(new ResourceLocation(Quark.MOD_ID, "vertical_slabs"))).add(block);
                for(String string : AQItemTagData.Wood) {
                    if(s.contains(string)) {
                        tag(BlockTags.create(new ResourceLocation(Quark.MOD_ID, "wooden_vertical_slabs"))).add(block);
                        break;
                    }
                }
            }
        });

        blockMap.keySet().forEach(s -> {
            if(s.contains("bookshelf")) {
                tag(Tags.Blocks.BOOKSHELVES).add(blockMap.get(s));
            }
        });
    }
}
