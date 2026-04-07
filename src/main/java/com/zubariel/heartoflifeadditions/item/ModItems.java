package com.zubariel.heartoflifeadditions.item;

import com.zubariel.heartoflifeadditions.HeartOfLifeAdditions;
import net.minecraft.item.*;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import java.util.function.Function;

public class ModItems {

    // Template
    public static final Item HEART_UPGRADE_TEMPLATE = Registry.register(
            Registries.ITEM,
            Identifier.of(HeartOfLifeAdditions.MOD_ID, "heart_upgrade_template"),
            HeartUpgradeTemplateItem.createHeartUpgradeTemplate()
    );
    // Tools
    public static final Item HEART_SWORD = registerItem("heart_sword",
            settings -> new SwordItem(ModToolMaterials.HEART, 3.0f, -2.4f, settings.fireproof()));

    public static final Item HEART_PICKAXE = registerItem("heart_pickaxe",
            settings -> new PickaxeItem(ModToolMaterials.HEART, 1.0f, -2.8f, settings.fireproof()));

    public static final Item HEART_AXE = registerItem("heart_axe",
            settings -> new AxeItem(ModToolMaterials.HEART, 5.0f, -3.0f, settings.fireproof()));

    public static final Item HEART_SHOVEL = registerItem("heart_shovel",
            settings -> new ShovelItem(ModToolMaterials.HEART, 1.5f, -3.0f, settings.fireproof()));

    public static final Item HEART_HOE = registerItem("heart_hoe",
            settings -> new HoeItem(ModToolMaterials.HEART, -3.0f, 0.0f, settings.fireproof()));

    // Armor
    public static final Item HEART_HELMET = registerItem("heart_helmet",
            settings -> new ArmorItem(ModArmorMaterials.HEART, EquipmentType.HELMET,
                    settings.maxDamage(EquipmentType.HELMET.getMaxDamage(33)).fireproof()));

    public static final Item HEART_CHESTPLATE = registerItem("heart_chestplate",
            settings -> new ArmorItem(ModArmorMaterials.HEART, EquipmentType.CHESTPLATE,
                    settings.maxDamage(EquipmentType.CHESTPLATE.getMaxDamage(33)).fireproof()));

    public static final Item HEART_LEGGINGS = registerItem("heart_leggings",
            settings -> new ArmorItem(ModArmorMaterials.HEART, EquipmentType.LEGGINGS,
                    settings.maxDamage(EquipmentType.LEGGINGS.getMaxDamage(33)).fireproof()));

    public static final Item HEART_BOOTS = registerItem("heart_boots",
            settings -> new ArmorItem(ModArmorMaterials.HEART, EquipmentType.BOOTS,
                    settings.maxDamage(EquipmentType.BOOTS.getMaxDamage(33)).fireproof()));

    // Registering
    private static Item registerItem(String name, Function<Item.Settings, Item> factory) {
        RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(HeartOfLifeAdditions.MOD_ID, name));
        return Registry.register(Registries.ITEM, key, factory.apply(new Item.Settings().registryKey(key)));
    }

    public static void registerModItems() {
        HeartOfLifeAdditions.LOGGER.info("Registering items for " + HeartOfLifeAdditions.MOD_ID);
        ModArmorMaterials.initialize();
    }
}