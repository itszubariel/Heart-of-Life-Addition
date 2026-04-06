package com.zubariel.heartoflifeadditions;

import com.zubariel.heartoflifeadditions.item.ModItemGroups;
import com.zubariel.heartoflifeadditions.item.ModItems;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HeartOfLifeAdditions implements ModInitializer {
    public static final String MOD_ID = "heartoflifeadditions";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("Initializing Heart of Life Additions");

        ModItems.registerModItems();
        ModItemGroups.registerItemGroups();
    }
}