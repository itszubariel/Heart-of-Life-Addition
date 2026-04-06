package com.zubariel.heartoflifeadditions.item;

import com.zubariel.heartoflifeadditions.HeartOfLifeAdditions;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.EquipmentAsset;
import net.minecraft.item.equipment.EquipmentAssetKeys;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

import java.util.Map;

public class ModArmorMaterials {
    public static final RegistryKey<EquipmentAsset> HEART_ASSET_KEY = RegistryKey.of(
            EquipmentAssetKeys.REGISTRY_KEY,
            Identifier.of(HeartOfLifeAdditions.MOD_ID, "heart")
    );

    public static final ArmorMaterial HEART = new ArmorMaterial(
            45,                                          // base durability
            Map.of(
                    EquipmentType.HELMET,     3,
                    EquipmentType.CHESTPLATE, 8,
                    EquipmentType.LEGGINGS,   6,
                    EquipmentType.BOOTS,      3
            ),
            15,                                          // enchantability
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE,
            4.0f,                                        // toughness
            0.15f,                                        // knockback resistance
            ItemTags.REPAIRS_NETHERITE_ARMOR,
            HEART_ASSET_KEY
    );

    public static void initialize() {}
}