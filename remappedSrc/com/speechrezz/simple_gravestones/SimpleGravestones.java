package com.speechrezz.simple_gravestones;

import com.speechrezz.simple_gravestones.registry.ModBlocks;
import com.speechrezz.simple_gravestones.registry.ModItems;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

public class SimpleGravestones implements ModInitializer {
    public static final String MOD_ID = "gravestones";

    public static final Identifier GRAVE = new Identifier(MOD_ID, "gravestone_block");


    @Override
    public void onInitialize() {
        ModItems.registerItems();
        ModBlocks.registerBlocks();
    }
}
