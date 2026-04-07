package com.zubariel.heartoflifeadditions.util;

import com.zubariel.heartoflifeadditions.item.ModItems;
import net.minecraft.entity.player.PlayerEntity;

public class HeartScaling {

    public static float getToolSpeedMultiplier(PlayerEntity player) {
        float maxHealth = player.getMaxHealth();
        if (maxHealth <= 18f) return 0.8f;  // 1-9 hearts, 80% speed
        if (maxHealth <= 20f) return 1.0f;  // 10 hearts, base
        if (maxHealth <= 30f) return 1.1f;  // 11-15 hearts, 10% faster
        return 1.2f;                         // 16-20 hearts, 20% faster
    }

    public static float getSwordDamageMultiplier(PlayerEntity player) {
        float maxHealth = player.getMaxHealth();
        if (maxHealth <= 18f) return 0.8f;  // 1-9 hearts, slight penalty
        if (maxHealth <= 20f) return 1.0f;  // 10 hearts, base
        if (maxHealth <= 30f) return 1.1f;  // 11-15 hearts
        return 1.2f;                         // 16-20 hearts
    }

    public static int getHeartTier(PlayerEntity player) {
        float maxHealth = player.getMaxHealth();
        if (maxHealth <= 20f) return 1;   // 1-10 hearts
        if (maxHealth <= 30f) return 2;   // 11-15 hearts
        return 3;                          // 16-20 hearts
    }

    public static boolean isWearingFullHeartArmor(PlayerEntity player) {
        return player.getInventory().getArmorStack(3).isOf(ModItems.HEART_HELMET) &&
                player.getInventory().getArmorStack(2).isOf(ModItems.HEART_CHESTPLATE) &&
                player.getInventory().getArmorStack(1).isOf(ModItems.HEART_LEGGINGS) &&
                player.getInventory().getArmorStack(0).isOf(ModItems.HEART_BOOTS);
    }
}