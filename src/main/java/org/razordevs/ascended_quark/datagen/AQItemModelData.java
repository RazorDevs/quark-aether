package org.razordevs.ascended_quark.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.level.block.*;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.Level;
import org.razordevs.ascended_quark.AscendedQuark;
import org.razordevs.ascended_quark.blocks.AQHedgeBlock;
import org.razordevs.ascended_quark.blocks.AQStoolBlock;
import org.razordevs.ascended_quark.items.AQSlimeInABucketItem;
import org.razordevs.ascended_quark.items.AQSwetInABucketItem;
import org.violetmoon.quark.content.building.block.HedgeBlock;
import teamrazor.deepaether.item.gear.skyjade.SkyjadeToolsAxeItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AQItemModelData extends ItemModelProvider {

    final HashMap<String, Item> itemMap;
    final HashMap<String, Block> blockMap;

    public AQItemModelData(PackOutput output, ExistingFileHelper helper, HashMap<String, Item> itemMap, HashMap<String, Block> blockMap) {
        super(output, AscendedQuark.MODID, helper);
        this.itemMap = itemMap;
        this.blockMap = blockMap;
    }
    
    
    @Override
    protected void registerModels() {
        List<Item> toGenerateBlockItem = new ArrayList<>();
        toGenerateBlockItem.addAll(itemMap.values());

        List<Block> toGenerateBlock = new ArrayList<>();
        toGenerateBlock.addAll(blockMap.values());

        for(Item item : toGenerateBlockItem) {
            if (item instanceof AQSlimeInABucketItem || item instanceof AQSwetInABucketItem) {
                continue;
            }

            System.out.println(item);
            if (item instanceof TieredItem) {
                this.handheldItem(item);
            }
            else {
                this.item(item);
            }
        }

        toGenerateBlock.remove(blockMap.get("ambrosium_lamp"));
        this.itemBlock(blockMap.get("ambrosium_lamp"), new ResourceLocation(AscendedQuark.MODID, "block/ambrosium_lamp_0"));
        toGenerateBlock.remove(blockMap.get("quicksoil_framed_glass_pane"));
        this.itemBlockFlatName(blockMap.get("quicksoil_framed_glass_pane"), "quicksoil_framed_glass" );

        for(Block block : toGenerateBlock) {
             if(block instanceof AQHedgeBlock)
                 this.itemBlock(block, new ResourceLocation(AscendedQuark.MODID,  "block/" + this.blockName(block) + "_post"));
             else if(block instanceof WallBlock)
                this.itemBlock(block, new ResourceLocation(AscendedQuark.MODID,  "block/" + this.blockName(block) + "_inventory"));

             else if(block instanceof ChestBlock) {
                 this.itemChest(block);
             }
             else if(block instanceof LadderBlock) {
                 this.itemBlockFlat(block);
             }
             else {
                 this.itemBlock(block);
             }
        }
    }

    public void handheldItem(Item item) {
        this.withExistingParent(this.itemName(item), this.mcLoc("item/handheld"))
                .texture("layer0", this.modLoc("item/"  + this.itemName(item)));
    }

    public void itemBlock(Block block) {
        this.withExistingParent(this.blockName(block), this.texture(this.blockName(block)));
    }

    public void itemBlock(Block block, ResourceLocation location) {
        this.withExistingParent(this.blockName(block), location);
    }

    public void itemChest(Block block) {
        this.withExistingParent(this.blockName(block), this.mcLoc("item/" + this.blockName(Blocks.CHEST)));
    }

    public void item(Item item) {
        this.withExistingParent(this.itemName(item), mcLoc("item/generated"))
                .texture("layer0", modLoc("item/" + this.itemName(item)));
    }
    public void itemFence(Block block, Block baseBlock) {
        this.withExistingParent(this.blockName(block), this.mcLoc("block/fence_inventory"))
                .texture("texture", this.texture(this.blockName(baseBlock)));
    }

    public void itemBlockFlatName(Block block, String location) {
        this.withExistingParent(this.blockName(block), this.mcLoc("item/generated")).texture("layer0", this.texture(location));
    }
    public void itemButton(Block block, Block baseBlock) {
        this.withExistingParent(this.blockName(block), this.mcLoc("block/button_inventory"))
                .texture("texture", this.texture(this.blockName(baseBlock)));
    }
    public void eggItem(Item item) {
        this.withExistingParent(this.itemName(item), this.mcLoc("item/template_spawn_egg"));
    }
    public void itemWallBlock(Block block, Block baseBlock) {
        this.wallInventory(this.blockName(block), this.texture(this.blockName(baseBlock)));
    }

    public void itemBlockFlat(Block block) {
        this.withExistingParent(this.blockName(block), this.mcLoc("item/generated"))
                .texture("layer0", this.texture(this.blockName(block)));
    }

    public String blockName(Block block) {
        ResourceLocation location = ForgeRegistries.BLOCKS.getKey(block);
        if (location != null) {
            return location.getPath();
        } else {
            throw new IllegalStateException("Unknown block: " + block.toString());
        }
    }

    public String itemName(Item item) {
        ResourceLocation location = ForgeRegistries.ITEMS.getKey(item);
        if (location != null) {
            return location.getPath();
        } else {
            throw new IllegalStateException("Unknown item: " + item.toString());
        }
    }
    protected ResourceLocation texture(String name) {
        return this.modLoc("block/" + name);
    }
}