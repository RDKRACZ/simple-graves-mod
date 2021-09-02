package com.speechrezz.simple_gravestones.registry;

import com.speechrezz.simple_gravestones.SimpleGravestones;
import net.minecraft.block.Block;
import net.minecraft.client.tutorial.OpenInventoryTutorialStepHandler;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {

    //Items
    //public static final Item RUBY_THING = new Item(new Item.Settings().group(Tutorial.ITEM_GROUP));

    //Blocks
    public static final BlockItem GRAVE_BLOCK = new BlockItem(ModBlocks.GRAVE_BLOCK, new Item.Settings().group(ItemGroup.DECORATIONS));

    public static void registerItems() {
        Registry.register(Registry.ITEM, new Identifier(SimpleGravestones.MOD_ID, "gravestone_block"), GRAVE_BLOCK);
    }
}
