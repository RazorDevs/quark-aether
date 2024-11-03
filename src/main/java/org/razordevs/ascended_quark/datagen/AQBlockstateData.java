package org.razordevs.ascended_quark.datagen;

import com.aetherteam.aether.block.AetherBlockStateProperties;
import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.aether.data.providers.AetherBlockStateProvider;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.WallSide;
import net.minecraftforge.client.model.generators.*;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import org.razordevs.ascended_quark.AscendedQuark;
import org.razordevs.ascended_quark.blocks.AQWoodenPostBlock;
import org.razordevs.ascended_quark.blocks.CompAQVerticalSlabBlock;
import org.violetmoon.quark.base.Quark;
import org.violetmoon.quark.content.building.block.HedgeBlock;
import org.violetmoon.quark.content.building.block.VerticalSlabBlock;
import org.violetmoon.quark.content.building.block.WoodPostBlock;
import teamrazor.deepaether.init.DABlocks;

import java.util.HashMap;
import java.util.Map;


public class AQBlockstateData extends AetherBlockStateProvider {
    public AQBlockstateData(PackOutput output, ExistingFileHelper helper, HashMap<String, Block> blockMap) {
        super(output, AscendedQuark.MODID, helper);
        this.blockMap = blockMap;
    }

    final HashMap<String, Block> blockMap;
    @Override
    public void registerStatesAndModels() {
        this.woodset("roseroot", DABlocks.ROSEROOT_LOG.get(), DABlocks.STRIPPED_ROSEROOT_LOG.get(), DABlocks.ROSEROOT_PLANKS.get(), DABlocks.ROSEROOT_LEAVES.get());
        this.leafCarpet("blue_roseroot", DABlocks.BLUE_ROSEROOT_LEAVES.get());
        this.leafCarpet("flowering_blue_roseroot", DABlocks.FLOWERING_BLUE_ROSEROOT_LEAVES.get());
        this.leafCarpet("flowering_roseroot", DABlocks.FLOWERING_ROSEROOT_LEAVES.get());

        this.hedge("blue_roseroot", DABlocks.BLUE_ROSEROOT_LEAVES.get(), DABlocks.ROSEROOT_LOG.get());
        this.hedge("flowering_blue_roseroot", DABlocks.FLOWERING_BLUE_ROSEROOT_LEAVES.get(), DABlocks.ROSEROOT_LOG.get());
        this.hedge("flowering_roseroot", DABlocks.FLOWERING_ROSEROOT_LEAVES.get(), DABlocks.ROSEROOT_LOG.get());

        this.woodset("cruderoot", DABlocks.CRUDEROOT_LOG.get(), DABlocks.STRIPPED_CRUDEROOT_LOG.get(), DABlocks.CRUDEROOT_PLANKS.get(), DABlocks.CRUDEROOT_LEAVES.get());
        this.woodset("sunroot", DABlocks.SUNROOT_LOG.get(), DABlocks.STRIPPED_SUNROOT_LOG.get(), DABlocks.SUNROOT_PLANKS.get(), DABlocks.SUNROOT_LEAVES.get());
        this.woodset("yagroot", DABlocks.YAGROOT_LOG.get(), DABlocks.STRIPPED_YAGROOT_LOG.get(), DABlocks.YAGROOT_PLANKS.get(), DABlocks.YAGROOT_LEAVES.get());
        this.woodset("conberry", DABlocks.CONBERRY_LOG.get(), DABlocks.STRIPPED_CONBERRY_LOG.get(), DABlocks.CONBERRY_PLANKS.get(), DABlocks.CONBERRY_LEAVES.get());

        this.verticalPLank("skyroot", AetherBlocks.SKYROOT_PLANKS.get(), "construction/");
        this.pillar((RotatedPillarBlock) blockMap.get("skyroot_stick_block"));
        this.compressed("blue_berry_crate");
        this.compressed("goldenleaf_berries_crate");
        this.hollowLog("skyroot", AetherBlocks.SKYROOT_LOG.get(), AetherBlocks.STRIPPED_SKYROOT_LOG.get(), "natural/");
        this.post("skyroot", AetherBlocks.SKYROOT_LOG.get(), "natural/");
        this.strippedPost("skyroot", AetherBlocks.STRIPPED_SKYROOT_LOG.get(), "natural/");

        this.ladder("skyroot");
        this.hedge("skyroot", AetherBlocks.SKYROOT_LEAVES.get(), AetherBlocks.SKYROOT_LOG.get(), "natural/");
        this.hedge("golden_skyroot", AetherBlocks.GOLDEN_OAK_LEAVES.get(), AetherBlocks.SKYROOT_LOG.get(), "natural/");
        this.hedge("holiday_skyroot", AetherBlocks.HOLIDAY_LEAVES.get(), AetherBlocks.SKYROOT_LOG.get(), "natural/");
        this.hedge("decorated_holiday_skyroot", AetherBlocks.DECORATED_HOLIDAY_LEAVES.get(), AetherBlocks.SKYROOT_LOG.get(), "natural/");
        this.hedge("crystal_skyroot", AetherBlocks.CRYSTAL_LEAVES.get(), AetherBlocks.SKYROOT_LOG.get(), "natural/");
        this.hedge("crystal_fruit_skyroot", AetherBlocks.CRYSTAL_FRUIT_LEAVES.get(), AetherBlocks.SKYROOT_LOG.get(), "natural/");
        this.chest("skyroot", AetherBlocks.SKYROOT_PLANKS.get(), "construction/");

        this.leafCarpet("skyroot", AetherBlocks.SKYROOT_LEAVES.get(), "natural/");
        this.leafCarpet("crystal", AetherBlocks.CRYSTAL_LEAVES.get(), "natural/");
        this.leafCarpet("crystal_fruit", AetherBlocks.CRYSTAL_FRUIT_LEAVES.get(), "natural/");
        this.leafCarpet("holiday", AetherBlocks.HOLIDAY_LEAVES.get(), "natural/");
        this.leafCarpet("decorated_holiday", AetherBlocks.DECORATED_HOLIDAY_LEAVES.get(), "natural/");
        this.leafCarpet("golden_oak", AetherBlocks.GOLDEN_OAK_LEAVES.get(), "natural/");

        this.verticalSlab("skyroot", AetherBlocks.SKYROOT_PLANKS.get(), "construction/");
        this.verticalSlab("aerogel", AetherBlocks.AEROGEL.get(), "construction/");
        this.verticalSlab("holystone_brick", AetherBlocks.HOLYSTONE_BRICKS.get(), "construction/");
        this.verticalSlab("mossy_holystone", AetherBlocks.MOSSY_HOLYSTONE.get(), "natural/");
        this.verticalSlab("holystone", AetherBlocks.HOLYSTONE.get(), "natural/");
        this.verticalSlab("icestone", AetherBlocks.ICESTONE.get(), "natural/");
        this.verticalSlab("angelic", AetherBlocks.ANGELIC_STONE.get(), "dungeon/");
        this.verticalSlab("hellfire", AetherBlocks.HELLFIRE_STONE.get(), "dungeon/");

        this.verticalSlab("mossy_holystone_tile", DABlocks.MOSSY_HOLYSTONE_TILES.get());
        this.verticalSlab("holystone_tile", DABlocks.HOLYSTONE_TILES.get());
        this.verticalSlab("big_holystone_bricks", DABlocks.BIG_HOLYSTONE_BRICKS.get());
        this.verticalSlab("aseterite", DABlocks.ASETERITE.get());
        this.verticalSlab("polished_aseterite", DABlocks.POLISHED_ASETERITE.get());
        this.verticalSlab("aseterite_bricks", DABlocks.ASETERITE_BRICKS.get());
        this.verticalSlab("raw_clorite", DABlocks.RAW_CLORITE.get());
        this.verticalSlab("clorite", DABlocks.CLORITE.get());
        this.verticalSlab("polished_clorite", DABlocks.POLISHED_CLORITE.get());
        this.verticalSlab("aether_mud_bricks", DABlocks.AETHER_MUD_BRICKS.get());

        this.stoneSet("aether_dirt_bricks");
        this.stoneSet("icestone_bricks");
        this.stoneSet("polished_icestone");
        this.stoneSet("quicksoil_bricks");

        this.blockCutout("aether_mud_brick_lattice");
        this.pillar((RotatedPillarBlock) blockMap.get("aether_mud_pillar"));
    }

    public void woodset(String type, Block log, Block stripped, Block planks, Block leaves) {
        this.verticalPLank(type, planks);
        this.leafCarpet(type, leaves);
        this.verticalSlab(type, planks);
        this.hedge(type, leaves, log);
        this.post(type, log);
        this.strippedPost(type, stripped);
        this.ladder(type);
        this.hollowLog(type, log, stripped, "");
        this.bookshelf(type, planks);
    }

    public void stoneSet(String type) {
        Block base = blockMap.get(type);
        this.block(base);
        this.slab(blockMap.get(type+"_slab"), base);
        this.stairs(blockMap.get(type+"_stairs"), base);
        this.wallBlock(blockMap.get(type+"_wall"), base);
        this.verticalSlab(type);
    }

    public void ladder(String type) {
        Block block = blockMap.get(type+"_ladder");
        ResourceLocation location = this.texture(this.name(block));
        ModelFile ladder = this.models().withExistingParent(this.name(block), this.mcLoc("block/block")).renderType(new ResourceLocation("cutout")).ao(false).texture("particle", location).texture("texture", location).element().from(0.0F, 0.0F, 15.2F).to(16.0F, 16.0F, 15.2F).shade(false).face(Direction.NORTH).uvs(0.0F, 0.0F, 16.0F, 16.0F).texture("#texture").end().face(Direction.SOUTH).uvs(16.0F, 0.0F, 0.0F, 16.0F).texture("#texture").end().end();
        this.getVariantBuilder(block).forAllStatesExcept((state) -> {
            Direction direction = state.getValue(LadderBlock.FACING);
            return ConfiguredModel.builder().modelFile(ladder).rotationY((int)(direction.toYRot() + 180.0F) % 360).build();
        }, LadderBlock.WATERLOGGED);
    }

    public void chest(String type, Block planks, String location) {
        Block block = blockMap.get(type+"_chest");
        Block trapped = blockMap.get(type+"_trapped_chest");
        this.getVariantBuilder(block).partialState().addModels(new ConfiguredModel(this.chest(this.name(block), this.texture(BuiltInRegistries.BLOCK.getKey(planks), location))));
        this.getVariantBuilder(trapped).partialState().addModels(new ConfiguredModel(this.chest(this.name(trapped), this.texture(BuiltInRegistries.BLOCK.getKey(planks), location))));
    }

    public void chest(String type, Block planks) {
        Block block = blockMap.get(type+"_chest");
        Block trapped = blockMap.get(type+"_trapped_chest");
        this.getVariantBuilder(block).partialState().addModels(new ConfiguredModel(this.chest(this.name(block), this.texture(BuiltInRegistries.BLOCK.getKey(planks)))));
        this.getVariantBuilder(trapped).partialState().addModels(new ConfiguredModel(this.chest(this.name(trapped), this.texture(BuiltInRegistries.BLOCK.getKey(planks)))));
    }

    public ModelFile chest(String name, ResourceLocation particle) {
        return this.models().getBuilder(name).texture("particle", particle);
    }


    public void pillar(RotatedPillarBlock block) {
        this.axisBlock(block, this.extend(this.texture(this.name(block)), "_side"), this.extend(this.texture(this.name(block)), "_top"));
    }

    public void strippedPost(String type, Block log) {
        this.post("stripped_"+type,log);
    }

    public void strippedPost(String type, Block log, String location) {
        this.post("stripped_"+type,log,location);
    }

    public void post(String type, Block log) {
        Block block = blockMap.get(type+"_post");
        this.postBlock(block, postModel(this.name(block), this.texture(BuiltInRegistries.BLOCK.getKey(log))));
    }

    public void post(String type, Block log, String location) {
        Block block = blockMap.get(type+"_post");
        this.postBlock(block, postModel(this.name(block), this.texture(BuiltInRegistries.BLOCK.getKey(log), location)));
    }

    public ModelFile postModel(String name, ResourceLocation texture) {
        return this.models().withExistingParent(name, new ResourceLocation(Quark.MOD_ID, "block/post"))
                .texture("texture", texture);
    }

    public void postBlock(Block block, ModelFile post) {
        MultiPartBlockStateBuilder builder = this.getMultipartBuilder(block);

        ModelFile small = this.models().getExistingFile(new ResourceLocation(AscendedQuark.MODID, "block/chain_small"));
        ModelFile small_up = this.models().getExistingFile(new ResourceLocation(AscendedQuark.MODID, "block/chain_small_top"));

        builder.part().modelFile(post).addModel().condition(AQWoodenPostBlock.AXIS, Direction.Axis.Y);
        builder.part().modelFile(post).rotationY(90).rotationX(90).addModel().condition(AQWoodenPostBlock.AXIS, Direction.Axis.X);
        builder.part().modelFile(post).rotationX(90).addModel().condition(AQWoodenPostBlock.AXIS, Direction.Axis.Z);

        builder.part().modelFile(small).addModel().condition(AQWoodenPostBlock.CHAINED[0], true);
        builder.part().modelFile(small_up).addModel().condition(AQWoodenPostBlock.CHAINED[1], true);
        builder.part().modelFile(small_up).rotationX(90).addModel().condition(AQWoodenPostBlock.CHAINED[2], true);
        builder.part().modelFile(small).rotationX(90).addModel().condition(AQWoodenPostBlock.CHAINED[3], true);
        builder.part().modelFile(small).rotationX(90).rotationY(90).addModel().condition(AQWoodenPostBlock.CHAINED[4], true);
        builder.part().modelFile(small_up).rotationX(90).rotationY(90).addModel().condition(AQWoodenPostBlock.CHAINED[5], true);
    }

    public void hedge(String type, Block leaf, Block log) {
        this.hedgeBlock(blockMap.get(type+"_hedge"), this.texture(BuiltInRegistries.BLOCK.getKey(leaf)), this.texture(BuiltInRegistries.BLOCK.getKey(log)));
    }

    public void hedge(String type, Block leaf, Block log, String location) {
        this.hedgeBlock(blockMap.get(type+"_hedge"), this.texture(BuiltInRegistries.BLOCK.getKey(leaf), location), this.texture(BuiltInRegistries.BLOCK.getKey(log), location));
    }

    public void hedgeBlock(Block block, ResourceLocation leaf, ResourceLocation log) {
        String baseName = this.key(block).toString();
        this.fourWayBlockExtended(block,
                this.hedgePost(baseName + "_post", leaf, log),
                this.hedgeSide(baseName + "_side", leaf),
                this.hedgeExtend(baseName+"_extend", leaf));
    }

    public ModelFile hedgePost(String name, ResourceLocation leaves, ResourceLocation log) {
        return this.models().withExistingParent(name, new ResourceLocation(Quark.MOD_ID, "block/hedge_post"))
                .texture("log", log)
                .texture("leaf", leaves)
                .renderType("cutout");
    }

    public ModelFile hedgeSide(String name, ResourceLocation leaves) {
        return this.models().withExistingParent(name, new ResourceLocation(Quark.MOD_ID, "block/hedge_side"))
                .texture("leaf", leaves)
                .renderType("cutout");
    }

    public ModelFile hedgeExtend(String name, ResourceLocation leaves) {
        return this.models().withExistingParent(name, new ResourceLocation(Quark.MOD_ID, "block/hedge_extend"))
                .texture("leaf", leaves)
                .renderType("cutout");
    }

    public void fourWayBlockExtended(Block block, ModelFile post, ModelFile side, ModelFile extend) {
        MultiPartBlockStateBuilder builder = this.getMultipartBuilder(block);
        builder.part().modelFile(post).addModel().condition(HedgeBlock.EXTEND, true);
        builder.part().modelFile(extend).addModel().condition(HedgeBlock.EXTEND, false);

        PipeBlock.PROPERTY_BY_DIRECTION.entrySet().forEach((e) -> {
            Direction dir = e.getKey();
            if (dir.getAxis().isHorizontal()) {
                builder.part().modelFile(side).rotationY(((int)dir.toYRot() + 180) % 360).uvLock(true).addModel().condition(e.getValue(), true);
            }

        });
    }

    public ModelFile cubeAllTranslucent(Block block) {
        return this.models().cubeAll(this.name(block), this.texture(this.name(block))).renderType(new ResourceLocation("translucent"));
    }

    public void blockCutout(String name) {
        Block block = blockMap.get(name);
        this.simpleBlock(block, this.cubeAllTranslucent(block));
    }

    public ModelFile cubeAllCutout(Block block) {
        return this.models().cubeAll(this.name(block), this.texture(this.name(block))).renderType(new ResourceLocation("cutout"));
    }

    public void hollowLog(String type, Block log) {
        Block block = blockMap.get("hollow_"+type+"_log");
        this.hollowLog((RotatedPillarBlock) block,
                this.texture(BuiltInRegistries.BLOCK.getKey(log)),
                this.extend(this.texture(BuiltInRegistries.BLOCK.getKey(log)), "_top"),
                this.extendPrefix(this.texture(BuiltInRegistries.BLOCK.getKey(log)), "stripped_"));
    }

    public void hollowLog(String type, Block log, Block stripped, String prefix) {
        Block block = blockMap.get("hollow_"+type+"_log");
        this.hollowLog((RotatedPillarBlock) block,
                this.texture(BuiltInRegistries.BLOCK.getKey(log), prefix),
                this.extend(this.texture(BuiltInRegistries.BLOCK.getKey(log), prefix), "_top"),
                this.texture(BuiltInRegistries.BLOCK.getKey(stripped), prefix));
    }

    public void hollowLog(RotatedPillarBlock block, ResourceLocation side, ResourceLocation end, ResourceLocation inside) {
        this.axisBlock(block, this.hollowCubeColumn(this.name(block), side, end, inside),
                this.cubeColumnHorizontal(this.name(block) + "_horizontal", side, end, inside));
    }

    public ModelFile hollowCubeColumn(String name, ResourceLocation side, ResourceLocation end, ResourceLocation inside) {
        return this.models().withExistingParent(name, new ResourceLocation(Quark.MOD_ID, "block/hollow_log"))
                .texture("side", side)
                .texture("end", end)
                .texture("inside", inside)
                .renderType(new ResourceLocation("translucent"));
    }

    public ModelFile cubeColumnHorizontal(String name, ResourceLocation side, ResourceLocation end, ResourceLocation inside) {
        return this.models().withExistingParent(name, new ResourceLocation(Quark.MOD_ID, "block/hollow_log_horizontal"))
                .texture("side", side)
                .texture("end", end)
                .texture("inside", inside)
                .renderType(new ResourceLocation("translucent"));
    }

    public void compressed(String type) {
        Block block = blockMap.get(type);
        ModelFile compressed = this.cubeBottomTop(this.name(block), this.texture(this.name(block)), AscendedQuark.asResource("block/crate_bottom"), this.extend(this.texture(this.name(block)), "_top"));
        this.getVariantBuilder(block).partialState().addModels(new ConfiguredModel(compressed));
    }

    public void bookshelf(String type, Block planks){
        Block block = blockMap.get(type + "_bookshelf");
        ModelFile bookshelf = this.cubeBottomTop(this.name(block), this.texture(this.name(block)), this.texture(BuiltInRegistries.BLOCK.getKey(planks)), this.texture(BuiltInRegistries.BLOCK.getKey(planks)));
        this.getVariantBuilder(block).partialState().addModels(new ConfiguredModel(bookshelf));
    }

    public ResourceLocation texture(String name) {
        return this.modLoc("block/"  + name);
    }

    public ResourceLocation texture(ResourceLocation name) {
        return new ResourceLocation(name.getNamespace(), "block/" + name.getPath());
    }

    public ResourceLocation texture(ResourceLocation name, String location) {
        return new ResourceLocation(name.getNamespace(), "block/" + location + name.getPath());
    }


    public ModelFile cubeBottomTop(String block, ResourceLocation side, ResourceLocation bottom, ResourceLocation top) {
        return this.models().cubeBottomTop(block, side, bottom, top);
    }

    public void fenceGateBlockInternal(FenceGateBlock block, String baseName, ResourceLocation texture) {
        ModelFile gate = models().fenceGate(baseName, texture);
        ModelFile gateOpen = models().fenceGateOpen(baseName + "_open", texture);
        ModelFile gateWall = models().fenceGateWall(baseName + "_wall", texture);
        ModelFile gateWallOpen = models().fenceGateWallOpen(baseName + "_wall_open", texture);
        fenceGateBlock(block, gate, gateOpen, gateWall, gateWallOpen);
    }

    public void wood(RotatedPillarBlock block, RotatedPillarBlock baseBlock) {
        this.axisBlock(block, this.texture(this.name(baseBlock)), this.texture(this.name(baseBlock)));
    }

    public void block(Block block) {
        this.simpleBlock(block, this.cubeAll(block));
    }

    public void verticalSlab(String type, Block texture) {
        Block block = blockMap.get(type + "_vertical_slab");
        this.verticalSlabBlock(block, this.verticalSlab(type + "_vertical_slab", this.blockTexture(texture)), this.models().getExistingFile(this.texture(BuiltInRegistries.BLOCK.getKey(texture))));
    }

    public void verticalSlab(String type) {
        verticalSlab(type, type);
    }
    public void verticalSlab(String type, String texture) {
        Block block = blockMap.get(type + "_vertical_slab");
        this.verticalSlabBlock(block, this.verticalSlab(type + "_vertical_slab", this.texture(texture)), this.models().getExistingFile(this.texture(texture)));
    }

    public void verticalSlab(String type, Block texture, String suffix) {
        Block block = blockMap.get(type + "_vertical_slab");
        this.verticalSlabBlock(block, this.verticalSlab(type + "_vertical_slab", this.texture(BuiltInRegistries.BLOCK.getKey(texture), suffix)), this.models().getExistingFile(this.texture(BuiltInRegistries.BLOCK.getKey(texture))));
    }

    public void verticalSlabBlock(Block block, ModelFile halfSlab, ModelFile fullSlab) {
        this.getVariantBuilder(block).partialState().with(VerticalSlabBlock.TYPE, VerticalSlabBlock.VerticalSlabType.NORTH)
                .modelForState().modelFile(halfSlab).rotationY(0).uvLock(true).addModel()

                .partialState().with(VerticalSlabBlock.TYPE, VerticalSlabBlock.VerticalSlabType.SOUTH)
                .modelForState().modelFile(halfSlab).rotationY(180).uvLock(true).addModel()

                .partialState().with(VerticalSlabBlock.TYPE, VerticalSlabBlock.VerticalSlabType.EAST)
                .modelForState().modelFile(halfSlab).rotationY(90).uvLock(true).addModel()

                .partialState().with(VerticalSlabBlock.TYPE, VerticalSlabBlock.VerticalSlabType.WEST)
                .modelForState().modelFile(halfSlab).rotationY(270).uvLock(true).addModel()

                .partialState().with(VerticalSlabBlock.TYPE, VerticalSlabBlock.VerticalSlabType.DOUBLE)
                .modelForState().modelFile(fullSlab).addModel();
    }


    public void verticalPLank(String type, Block texture) {
        Block block = blockMap.get("vertical_" + type + "_planks");
        this.simpleBlock(block, this.verticalPLank("vertical_" + type + "_planks", this.blockTexture(texture)));
    }

    public void verticalPLank(String type, Block texture, String suffix) {
        Block block = blockMap.get("vertical_" + type + "_planks");
        this.simpleBlock(block, this.verticalPLank("vertical_" + type + "_planks", this.texture(BuiltInRegistries.BLOCK.getKey(texture), suffix)));
    }

    public ModelFile verticalPLank(String name, ResourceLocation texture) {
        return this.models().singleTexture(name, new ResourceLocation(Quark.MOD_ID, "block/vertical_planks"), "all", texture);
    }


    public void leafCarpet(String type, Block texture) {
        Block block = blockMap.get(type + "_leaf_carpet");
        this.simpleBlock(block, this.leafCarpet(type + "_leaf_carpet", this.blockTexture(texture)));
    }

    public void leafCarpet(String type, Block texture, String suffix) {
        Block block = blockMap.get(type + "_leaf_carpet");
        this.simpleBlock(block, this.leafCarpet(type + "_leaf_carpet", this.texture(BuiltInRegistries.BLOCK.getKey(texture), suffix)));
    }

    public ModelFile leafCarpet(String name, ResourceLocation texture) {
        return this.models().singleTexture(name, new ResourceLocation(Quark.MOD_ID, "block/leaf_carpet"), "all", texture).renderType("cutout");
    }

    public ModelFile verticalSlab(String name, ResourceLocation texture) {
        return this.sideBottomTop(name, new ResourceLocation(Quark.MOD_ID, "block/vertical_slab"), texture);
    }

    public ModelFile sideBottomTop(String name, ResourceLocation parent, ResourceLocation location) {
        return this.models().withExistingParent(name, parent).texture("side", location).texture("bottom", location).texture("top", location);
    }



    public void pottedPlant(Block block, Block flower) {
        ModelFile pot = this.models().withExistingParent(this.name(block), this.mcLoc("block/flower_pot_cross")).texture("plant", this.modLoc("block/"  + this.name(flower))).renderType(new ResourceLocation("cutout"));
        this.getVariantBuilder(block).partialState().addModels(new ConfiguredModel(pot));
    }

    public void pottedPlantFix(Block block, Block flower) {
        ModelFile pot = this.models().withExistingParent(this.name(block), this.mcLoc("block/flower_pot_cross")).texture("plant", this.modLoc("block/"  + this.name(flower) + "_pot")).renderType(new ResourceLocation("cutout"));
        this.getVariantBuilder(block).partialState().addModels(new ConfiguredModel(pot));
    }

    public void saplingBlock(Block block) {
        ModelFile sapling = models().cross(this.name(block), this.texture(this.name(block))).renderType(new ResourceLocation("cutout"));
        this.getVariantBuilder(block).forAllStatesExcept(state -> ConfiguredModel.builder().modelFile(sapling).build(), SaplingBlock.STAGE);
    }

    public void stairs(Block block, Block baseBlock) {
        this.stairsBlock((StairBlock) block, this.texture(this.name(baseBlock)));
    }

    public void slab(Block block, Block baseBlock) {
        this.slabBlock((SlabBlock) block, this.texture(this.name(baseBlock)), this.texture(this.name(baseBlock)));
    }

    public void verticalSlabBlock(Block block, Block baseBlock) {
        this.verticalSlabBlock((VerticalSlabBlock) block, this.texture(this.name(baseBlock)), this.texture(this.name(baseBlock)));
    }

    public void verticalSlabBlock(VerticalSlabBlock block, ResourceLocation doubleslab, ResourceLocation texture) {
        verticalSlabBlock(block, doubleslab, texture, texture, texture);
    }

    public void verticalSlabBlock(VerticalSlabBlock block, ResourceLocation doubleslab, ResourceLocation side, ResourceLocation bottom, ResourceLocation top) {
        ModelFile vertical_slab = this.models().withExistingParent(this.name(block), new ResourceLocation("quark", "block/vertical_slab"))
                .texture("bottom", bottom).texture("top", top).texture("side", side);
        this.getVariantBuilder(block).partialState().addModels(new ConfiguredModel(vertical_slab));
    }

    public void wallBlock(Block block, Block baseBlock) {
        wallBlockInventory(this.name(block) + "_inventory", this.texture(this.name(baseBlock)));
        this.wallBlockInternal((WallBlock) block, this.name(block), this.texture(this.name(baseBlock)));
    }

    public void wallBlockInventory(String name, ResourceLocation texture) {
        this.models().withExistingParent(name, new ResourceLocation("block/wall_inventory"))
                .texture("wall", texture);
    }

    public void blockDoubleDrops(Block block) {
        this.getVariantBuilder(block).forAllStatesExcept(state -> ConfiguredModel.builder().modelFile(this.cubeAll(block)).build(), AetherBlockStateProperties.DOUBLE_DROPS);
    }

    public void wallBlockInternal(WallBlock block, String baseName, ResourceLocation texture) {
        wallBlock(block, models().wallPost(baseName + "_post", texture),
                models().wallSide(baseName + "_side", texture),
                models().wallSideTall(baseName + "_side_tall", texture));
    }

    //GENESIS COMPAT
    protected BlockModelBuilder makeWallPostModel(int width, int height, String name) {
        return models().withExistingParent(name, this.mcLoc("block/block"))
                .element().from(8 - width, 0.0F, 8 - width).to(8 + width, height, 8 + width)
                .face(Direction.DOWN).texture("#top").cullface(Direction.DOWN).end()
                .face(Direction.UP).texture("#top").cullface(Direction.UP).end()
                .face(Direction.NORTH).texture("#side").end()
                .face(Direction.SOUTH).texture("#side").end()
                .face(Direction.WEST).texture("#side").end()
                .face(Direction.EAST).texture("#side").end().end();
    }

    protected BlockModelBuilder makeWallSideModel(int length, int height, String name, ModelBuilder.FaceRotation faceRotation, int u1, int u2) {
        return models().withExistingParent(name, this.mcLoc("block/block"))
                .element().from(5.0F, 0.0F, 0.0F).to(11.0F, height, length)
                .face(Direction.DOWN).texture("#top").rotation(faceRotation).uvs(u1, 5, u2, 11).cullface(Direction.DOWN).end()
                .face(Direction.UP).texture("#top").rotation(faceRotation).uvs(u1, 5, u2, 11).end()
                .face(Direction.NORTH).texture("#side").cullface(Direction.NORTH).end()
                .face(Direction.SOUTH).texture("#side").end()
                .face(Direction.WEST).texture("#side").end()
                .face(Direction.EAST).texture("#side").end().end();
    }

    public void logWallBlock(WallBlock block, Block baseBlock, String location, String modid, boolean postUsesTop, ModelFile postBig, ModelFile postShort, ModelFile postTall, ModelFile side, ModelFile sideAlt, ModelFile sideTall, ModelFile sideTallAlt, ModelFile sideShort, ModelFile sideAltShort, ModelFile sideTallShort, ModelFile sideTallAltShort) {
        this.logWallBlockInternal(block, this.name(block), new ResourceLocation(modid, "block/" + location + this.name(baseBlock)), postUsesTop, postBig, postShort, postTall, side, sideAlt, sideTall, sideTallAlt, sideShort, sideAltShort, sideTallShort, sideTallAltShort);
    }

    private void logWallBlockInternal(WallBlock block, String baseName, ResourceLocation texture, boolean postUsesTop, ModelFile postBig, ModelFile postShort, ModelFile postTall, ModelFile side, ModelFile sideAlt, ModelFile sideTall, ModelFile sideTallAlt, ModelFile sideShort, ModelFile sideAltShort, ModelFile sideTallShort, ModelFile sideTallAltShort) {
        this.logWallBlock(
                this.getMultipartBuilder(block),
                models().getBuilder(baseName + "_post_short").parent(postShort).texture("particle", texture).texture("top", texture).texture("side", texture),
                models().getBuilder(baseName + "_post_tall").parent(postTall).texture("particle", texture).texture("top", texture).texture("side", texture),
                models().getBuilder(baseName + "_side").parent(side).texture("particle", texture).texture("top", texture).texture("side", texture),
                models().getBuilder(baseName + "_side_alt").parent(sideAlt).texture("particle", texture).texture("top", texture).texture("side", texture),
                models().getBuilder(baseName + "_side_tall").parent(sideTall).texture("particle", texture).texture("top", texture).texture("side", texture),
                models().getBuilder(baseName + "_side_tall_alt").parent(sideTallAlt).texture("particle", texture).texture("top", texture).texture("side", texture)
        );

        this.logWallBlockWithPost(
                this.getMultipartBuilder(block),
                models().getBuilder(baseName + "_post").parent(postBig).texture("particle", texture).texture("top", postUsesTop ? (texture + "_top") : texture.toString()).texture("side", texture),
                models().getBuilder(baseName + "_side_short").parent(sideShort).texture("particle", texture).texture("top", texture).texture("side", texture),
                models().getBuilder(baseName + "_side_alt_short").parent(sideAltShort).texture("particle", texture).texture("top", texture).texture("side", texture),
                models().getBuilder(baseName + "_side_tall_short").parent(sideTallShort).texture("particle", texture).texture("top", texture).texture("side", texture),
                models().getBuilder(baseName + "_side_tall_alt_short").parent(sideTallAltShort).texture("particle", texture).texture("top", texture).texture("side", texture)
        );
    }

    public void logWallBlock(MultiPartBlockStateBuilder builder, ModelFile postShort, ModelFile postTall, ModelFile side, ModelFile sideAlt, ModelFile sideTall, ModelFile sideTallAlt) {
        builder
                // Smaller post when West & East are both short while North & South being none
                .part().modelFile(postShort).addModel()
                .nestedGroup().condition(WallBlock.UP, false).condition(WallBlock.EAST_WALL, WallSide.LOW).condition(WallBlock.WEST_WALL, WallSide.LOW).end().end()
                // Taller thinner post when West & East are both tall while North & South being none
                .part().modelFile(postTall).addModel()
                .nestedGroup().condition(WallBlock.UP, false).condition(WallBlock.EAST_WALL, WallSide.TALL).condition(WallBlock.WEST_WALL, WallSide.TALL).end().end()
                // Rotated small post when West & East are both none while North & South are short
                .part().modelFile(postShort).rotationY(90).addModel()
                .nestedGroup().condition(WallBlock.UP, false).condition(WallBlock.EAST_WALL, WallSide.NONE).condition(WallBlock.NORTH_WALL, WallSide.LOW).condition(WallBlock.WEST_WALL, WallSide.NONE).condition(WallBlock.SOUTH_WALL, WallSide.LOW).end().end()
                // Rotated small post when West & East are both none while North & South are tall
                .part().modelFile(postTall).rotationY(90).addModel()
                .nestedGroup().condition(WallBlock.UP, false).condition(WallBlock.EAST_WALL, WallSide.NONE).condition(WallBlock.NORTH_WALL, WallSide.TALL).condition(WallBlock.WEST_WALL, WallSide.NONE).condition(WallBlock.SOUTH_WALL, WallSide.TALL).end().end();
        WALL_PROPS.entrySet().stream()
                .filter(e -> e.getKey().getAxis().isHorizontal())
                .forEach(e -> {
                    this.logWallSidePart(builder, side, sideAlt, e, WallSide.LOW, false);
                    this.logWallSidePart(builder, sideTall, sideTallAlt, e, WallSide.TALL, false);
                });
    }

    public void logWallBlockWithPost(MultiPartBlockStateBuilder builder, ModelFile postBig, ModelFile side, ModelFile sideAlt, ModelFile sideTall, ModelFile sideTallAlt) {
        builder
                // Big post for connections, typically including angled
                .part().modelFile(postBig).addModel()
                .condition(WallBlock.UP, true).end();
        WALL_PROPS.entrySet().stream()
                .filter(e -> e.getKey().getAxis().isHorizontal())
                .forEach(e -> {
                    this.logWallSidePart(builder, side, sideAlt, e, WallSide.LOW, true);
                    this.logWallSidePart(builder, sideTall, sideTallAlt, e, WallSide.TALL, true);
                });
    }

    private void logWallSidePart(MultiPartBlockStateBuilder builder, ModelFile model, ModelFile modelAlt, Map.Entry<Direction, Property<WallSide>> entry, WallSide height, boolean hasPost) {
        int rotation = (((int) entry.getKey().toYRot()) + 180) % 360;
        builder.part()
                .modelFile(rotation < 180 ? model : modelAlt)
                .rotationY(rotation)
                .addModel()
                .condition(entry.getValue(), height).condition(WallBlock.UP, hasPost);
    }

    public String name(Block block) {
        return key(block).getPath();
    }

    private ResourceLocation key(Block block) {
        return ForgeRegistries.BLOCKS.getKey(block);
    }

    public ResourceLocation extendPrefix(ResourceLocation location, String prefix) {
        return new ResourceLocation(location.getNamespace(), prefix+location.getPath());
    }
}