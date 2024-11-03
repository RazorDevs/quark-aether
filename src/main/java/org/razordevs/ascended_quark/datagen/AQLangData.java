package org.razordevs.ascended_quark.datagen;

import com.aetherteam.nitrogen.data.providers.NitrogenLanguageProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraftforge.registries.RegistryObject;
import org.codehaus.plexus.util.StringUtils;
import org.razordevs.ascended_quark.AscendedQuark;
import org.razordevs.ascended_quark.blocks.AQHollowLogBlock;
import org.razordevs.ascended_quark.blocks.AQWoodenPostBlock;
import org.violetmoon.quark.content.building.block.HedgeBlock;
import org.violetmoon.quark.content.building.block.LeafCarpetBlock;
import org.violetmoon.quark.content.building.block.VariantLadderBlock;
import org.violetmoon.quark.content.building.block.VerticalSlabBlock;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class AQLangData extends NitrogenLanguageProvider {

    final HashMap<String, Item> itemMap;
    final HashMap<String, Block> blockMap;
    public AQLangData(PackOutput output, HashMap<String, Item> itemMap, HashMap<String, Block> blockMap) {
        super(output, AscendedQuark.MODID);
        this.itemMap = itemMap;
        this.blockMap = blockMap;
    }

    @Override
    protected void addTranslations() {
        List<Item> items = new ArrayList<>(itemMap.values());
        for (String string : blockMap.keySet()) {
            String newString = string;
            if(string.contains("bricks_"))
                newString = newString.replace("bricks_", "brick_");

            Block block = blockMap.get(string);
            this.add(block, this.getName(newString));

            items.remove(block.asItem());

            if(block instanceof HedgeBlock)
                this.addHedgeLore(block);
            else if(block instanceof LeafCarpetBlock)
                this.addCarpetLore(block);
            else if(block instanceof AQWoodenPostBlock)
                this.addPostLore(block);
            else if(block instanceof VerticalSlabBlock)
                this.addVerticalSlabLore(block);
            else if(block instanceof SlabBlock)
                this.addSlabLore(block);
            else if(block instanceof StairBlock)
                this.addStairsLore(block);
            else if(block instanceof WallBlock)
                this.addWallLore(block);
            else if(block instanceof AQHollowLogBlock)
                this.addHollowLogLore(block);
            else if(block instanceof VariantLadderBlock)
                this.addLadderLore(block);

        }

        for (Item item : items)
        {
            this.add(item, this.getName(item.toString()));
        }

        this.add("lore.item.ascended_quark.ambrosium_torch_arrow", "An arrow tied to an Ambrosium Torch. It imbues the healing properties of Ambrosium, and places the torch on impact.");
        this.add("lore.item.ascended_quark.blue_swet_in_a_bucket", "Seems like you caught a Blue Swet! Not that useful, but it's an easy way to transport them. They might get excited too!");
        this.add("lore.item.ascended_quark.blue_swet_in_a_skyroot_bucket", "Seems like you caught a Blue Swet! Not that useful, but it's an easy way to transport them. They might get excited too!");
        this.add("lore.item.ascended_quark.golden_swet_in_a_bucket", "Seems like you caught a Golden Swet! Not that useful, but it's an easy way to transport them. They might get excited too!");
        this.add("lore.item.ascended_quark.golden_swet_in_a_skyroot_bucket", "Seems like you caught a Golden Swet! Not that useful, but it's an easy way to transport them. They might get excited too!");
        this.add("lore.item.ascended_quark.quicksoil_glass_shard", "Apparently something broke. You can still use some of the shards to craft the glass again.");

        this.add("lore.block.ascended_quark.holystone_furnace", "A versatile furnace that can also use Ambrosium as fuel. Also looks fancier in white.");
        this.add("lore.block.ascended_quark.ambrosium_lamp", "A lamp made out of Holystone and Ambrosium. Ambrosium Blocks can increase its light up to 4 times.");
        this.add("lore.block.ascended_quark.blue_berry_crate", "A handy crate for storing blue berries! A very good decoration block!");
        this.add("lore.block.ascended_quark.goldenleaf_berries_crate", "A handy crate for storing Goldenleaf berries! A very good decoration block!");
        this.add("lore.block.ascended_quark.quicksoil_framed_glass", "Combine Iron Ingots and Quicksoil Glass, and you get yourself some fancy Quicksoil Framed Glass. Great for some more rustic or medieval builds.");
        this.add("lore.block.ascended_quark.quicksoil_framed_glass_pane", "A thin decorative variant of Quicksoil Framed Glass. Great for some more rustic or medieval builds.");
        this.add("lore.block.ascended_quark.skyroot_chest", "A nice looking chest made out of Skyroot. Perfect blend for your storage room.");
        this.add("lore.block.ascended_quark.skyroot_stool", "Stools can be created the same way you'd make a Bed, but using slabs instead of full wood blocks. As you can expect, you can sit on them!");
        this.add("lore.block.ascended_quark.skyroot_stick_block", "Cool looking pillar to store your sticks, or to integrate them into your builds!");

        addBricksLore(blockMap.get("quicksoil_bricks"));
        addBricksLore(blockMap.get("aether_dirt_bricks"));
        addBricksLore(blockMap.get("polished_icestone"));
        addBricksLore(blockMap.get("icestone_bricks"));

        addMenuLangComponents();
    }


    private void addMenuLangComponents() {
        this.add(AscendedQuark.MODID + ".category.aether", "The Aether");
        this.add(AscendedQuark.MODID + ".category.deep_aether", "Deep Aether");
        this.add(AscendedQuark.MODID + ".category.general", "General Settings");
        this.add(AscendedQuark.MODID + ".gui.config.social.discord", "RazorDevs Discord");
    }

    private void addLadderLore(Block block) {
        this.add("lore." + block.getDescriptionId(), "Ladders made out of " + clearBlockOrigin(block, 7) + "! Much better decoration than plain ol' stairs...");
    }

    private void addHollowLogLore(Block block) {
        this.add("lore." + block.getDescriptionId(), "Can be crafted with four " + clearBlockOrigin(block, 7) + " Logs. You can sneak into them horizontally, and climb inside of them vertically!");
    }

    private void addBricksLore(Block block){
        this.add("lore." + block.getDescriptionId(), "Used as a building material native to the Aether. It is made from " + clearBlockOrigin(block, 7) + " and is sturdier than it too.");
    }
    private void addSlabLore(Block block){
        this.add("lore." + block.getDescriptionId(), "Crafted from " + clearBlockOrigin(block, 5) + ". Slabs are half blocks, versatile for decoration and smooth slopes. Try adding some to a building's roofing!");
    }
    private void addVerticalSlabLore(Block block){
        this.add("lore." + block.getDescriptionId(), "Crafted from " + clearBlockOrigin(block, 14) + ". Vertical Slabs are half blocks, versatile for decoration. Try adding some to a building's window!");
    }
    private void addStairsLore(Block block){
        this.add("lore." + block.getDescriptionId(), "Crafted from " + clearBlockOrigin(block, 7) + ". Stairs are useful for adding verticality to builds and are often used for decoration too!");
    }
    private void addWallLore(Block block){
        this.add("lore." + block.getDescriptionId(), "Crafted from " + clearBlockOrigin(block, 5) + ". Can be used for decorative enclosures and defenses. Great for keeping nasty intruders away!");
    }
    private void addHedgeLore(Block block){
        this.add("lore." + block.getDescriptionId(), "Crafted from " + clearBlockOrigin(block, 6) + ". They connect to each other like fences, and have the same bounding box. You can even put flowers on them!");
    }
    private void addPostLore(Block block){
        this.add("lore." + block.getDescriptionId(), "Crafted from " + clearBlockOrigin(block, 5) + ". These are slim log style blocks you can lay down vertically or horizontally. Chains and lanterns can also connect to them.");
    }
    private void addCarpetLore(Block block){
        this.add("lore." + block.getDescriptionId(), "Crafted from " + clearBlockOrigin(block, 7) + ". Leaf Carpets are a great way to add natural elements to your builds!");
    }
    private void addVerticalPlanksLore(Block block){
        this.add("lore." + block.getDescriptionId(), "Simple vertical planks to spice up your creativity.");
    }


    /**
     * Returns the block of origin, cleaning the string and adding uppercase letters from the descriptionId of the passed block.
     * Used in the Lang datageneration for various Blocks lore.
     *
     * @param block
     * Block to elaborate.
     *
     * @param endIndex
     * Index position used to exclude the suffix depending on the type of block.
     */
    private String clearBlockOrigin(Block block, int endIndex){
        return StringUtils.capitaliseAllWords(block.getDescriptionId().substring(21, block.getDescriptionId().length() - endIndex).replace("_"," "));
    }


    private String getName(String name) {
        char[] nameCharArray = name.toCharArray();
        String temp;
        temp = String.valueOf(nameCharArray[0]);
        nameCharArray[0] = temp.toUpperCase().toCharArray()[0];
        for(int i = 0; i < name.length(); i++) {
            if(name.charAt(i) == '_') {
                nameCharArray[i] = ' ';
                temp = String.valueOf(nameCharArray[i+1]);
                nameCharArray[i+1] =  temp.toUpperCase().toCharArray()[0];
            }
        }

        String name2 = "";
        for(int i = 0; i < nameCharArray.length; i++) {
            name2 = name2 + nameCharArray[i];
        }

        return name2;
    }

}
