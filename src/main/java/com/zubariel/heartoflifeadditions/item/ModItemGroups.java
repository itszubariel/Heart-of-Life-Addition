package com.zubariel.heartoflifeadditions.item;

import com.zubariel.heartoflifeadditions.HeartOfLifeAdditions;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup HEART_OF_LIFE_ADDITIONS_GROUP = Registry.register(
            Registries.ITEM_GROUP,
            Identifier.of(HeartOfLifeAdditions.MOD_ID, "heart_of_life_additions"),
            FabricItemGroup.builder()
                    .icon(() -> new ItemStack(ModItems.HEART_SWORD))
                    .displayName(Text.translatable("itemgroup.heartoflifeadditions"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.HEART_UPGRADE_TEMPLATE);
                        // Tools
                        entries.add(ModItems.HEART_SWORD);
                        entries.add(ModItems.HEART_PICKAXE);
                        entries.add(ModItems.HEART_AXE);
                        entries.add(ModItems.HEART_SHOVEL);
                        entries.add(ModItems.HEART_HOE);

                        // Armor
                        entries.add(ModItems.HEART_HELMET);
                        entries.add(ModItems.HEART_CHESTPLATE);
                        entries.add(ModItems.HEART_LEGGINGS);
                        entries.add(ModItems.HEART_BOOTS);
                    })
                    .build()
    );

    public static void registerItemGroups() {
        HeartOfLifeAdditions.LOGGER.info("Registering item groups for " + HeartOfLifeAdditions.MOD_ID);
    }
}