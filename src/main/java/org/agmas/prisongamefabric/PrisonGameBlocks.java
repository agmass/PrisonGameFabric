package org.agmas.prisongamefabric;

import eu.pb4.polymer.core.api.item.PolymerBlockItem;
import net.fabricmc.loader.impl.util.log.Log;
import net.fabricmc.loader.impl.util.log.LogCategory;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.WoodType;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.SignItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.agmas.prisongamefabric.block.LeveledDoor;
import org.agmas.prisongamefabric.block.RefillBlock;
import org.agmas.prisongamefabric.block.RegenerativeBlock;
import org.agmas.prisongamefabric.prisons.upgrades.PrisonUpgrade;
import org.agmas.prisongamefabric.util.StateSaverAndLoader;

import java.util.HashMap;

public class PrisonGameBlocks {

    public static final Block ZERO_DOOR = new LeveledDoor(0, Blocks.ACACIA_DOOR);
    public static final Block ONE_DOOR = new LeveledDoor(1,Blocks.COPPER_DOOR);
    public static final Block TWO_DOOR = new LeveledDoor(2,Blocks.WARPED_DOOR);
    public static final Block THREE_DOOR = new LeveledDoor(3,Blocks.CRIMSON_DOOR);

    public static final Block REFILLBLOCK = new RefillBlock(AbstractBlock.Settings.create());
    public static final Block POOPBLOCK = new RegenerativeBlock(Blocks.COARSE_DIRT, 80);


    public static void initalize() {
        Registry.register(Registries.BLOCK, Identifier.of("prisongamefabric", "levelonedoor"), ONE_DOOR);
        Registry.register(Registries.BLOCK, Identifier.of("prisongamefabric", "levelzerodoor"), ZERO_DOOR);
        Registry.register(Registries.BLOCK, Identifier.of("prisongamefabric", "leveltwodoor"), TWO_DOOR);
        Registry.register(Registries.BLOCK, Identifier.of("prisongamefabric", "levelthreedoor"), THREE_DOOR);
        Registry.register(Registries.BLOCK, Identifier.of("prisongamefabric", "refill"), REFILLBLOCK);
        Registry.register(Registries.BLOCK, Identifier.of("prisongamefabric", "poop"), POOPBLOCK);

    }

    public static void itemsInitalize() {

        Registry.register(Registries.ITEM, Identifier.of("prisongamefabric", "levelzerodoor"), new PolymerBlockItem(ZERO_DOOR, new Item.Settings(), Items.ACACIA_DOOR));
        Registry.register(Registries.ITEM, Identifier.of("prisongamefabric", "levelonedoor"), new PolymerBlockItem(ONE_DOOR, new Item.Settings(), Items.COPPER_DOOR));
        Registry.register(Registries.ITEM, Identifier.of("prisongamefabric", "leveltwodoor"), new PolymerBlockItem(TWO_DOOR, new Item.Settings(), Items.WARPED_DOOR));
        Registry.register(Registries.ITEM, Identifier.of("prisongamefabric", "levelthreedoor"), new PolymerBlockItem(THREE_DOOR, new Item.Settings(), Items.CRIMSON_DOOR));
        Registry.register(Registries.ITEM, Identifier.of("prisongamefabric", "refill"), new PolymerBlockItem(REFILLBLOCK, new Item.Settings(), Items.BARREL));
        Registry.register(Registries.ITEM, Identifier.of("prisongamefabric", "poop"), new PolymerBlockItem(POOPBLOCK, new Item.Settings(), Items.COARSE_DIRT));
    }
}
