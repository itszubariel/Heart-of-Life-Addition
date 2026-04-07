package com.zubariel.heartoflifeadditions;

import com.zubariel.heartoflifeadditions.item.ModItemGroups;
import com.zubariel.heartoflifeadditions.item.ModItems;
import com.zubariel.heartoflifeadditions.util.HeartScaling;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HeartOfLifeAdditions implements ModInitializer {
    public static final String MOD_ID = "heartoflifeadditions";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    private static final Identifier MINING_SPEED_MODIFIER_ID =
            Identifier.of(MOD_ID, "heart_mining_speed");

    @Override
    public void onInitialize() {
        LOGGER.info("Initializing Heart of Life Additions");

        ModItems.registerModItems();
        ModItemGroups.registerItemGroups();

        ServerTickEvents.END_SERVER_TICK.register(server -> {
            for (PlayerEntity player : server.getPlayerManager().getPlayerList()) {
                updateMiningSpeed(player);
                updateSwordDamage(player);
                updateArmorEffects(player);
            }
        });
    }

    private void updateMiningSpeed(PlayerEntity player) {
        boolean holdingHeartTool =
                player.getMainHandStack().isOf(ModItems.HEART_PICKAXE) ||
                        player.getMainHandStack().isOf(ModItems.HEART_AXE) ||
                        player.getMainHandStack().isOf(ModItems.HEART_SHOVEL) ||
                        player.getMainHandStack().isOf(ModItems.HEART_HOE);

        EntityAttributeInstance miningSpeed = player.getAttributeInstance(
                EntityAttributes.BLOCK_BREAK_SPEED
        );
        if (miningSpeed == null) return;
        miningSpeed.removeModifier(MINING_SPEED_MODIFIER_ID);

        if (!holdingHeartTool) return;

        float multiplier = HeartScaling.getToolSpeedMultiplier(player);
        double modifierValue = multiplier - 1.0;

        if (modifierValue == 0.0) return;

        miningSpeed.addTemporaryModifier(new EntityAttributeModifier(
                MINING_SPEED_MODIFIER_ID,
                modifierValue,
                EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
        ));
    }

    private static final Identifier SWORD_DAMAGE_MODIFIER_ID =
            Identifier.of(MOD_ID, "heart_sword_damage");

    private void updateSwordDamage(PlayerEntity player) {
        boolean holdingSword = player.getMainHandStack().isOf(ModItems.HEART_SWORD);

        EntityAttributeInstance attackDamage = player.getAttributeInstance(EntityAttributes.ATTACK_DAMAGE);
        if (attackDamage == null) return;
        LOGGER.info("Updating sword dmg for {} - holding heart sword: {} - multiplier: {}",
                player.getName().getString(), holdingSword, HeartScaling.getSwordDamageMultiplier(player));

        attackDamage.removeModifier(SWORD_DAMAGE_MODIFIER_ID);

        if (!holdingSword) return;

        float multiplier = HeartScaling.getSwordDamageMultiplier(player);
        if (multiplier == 1.0f) return;

        double modifierValue = multiplier - 1.0;
        attackDamage.addTemporaryModifier(new EntityAttributeModifier(
                SWORD_DAMAGE_MODIFIER_ID,
                modifierValue,
                EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
        ));
    }

    private static final Identifier SPEED_MODIFIER_ID =
            Identifier.of(MOD_ID, "heart_armor_speed");

    private final java.util.Map<java.util.UUID, Integer> armorCooldowns = new java.util.HashMap<>();
    private void updateArmorEffects(PlayerEntity player) {
        if (!HeartScaling.isWearingFullHeartArmor(player)) {
            EntityAttributeInstance speed = player.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED);
            if (speed != null) speed.removeModifier(SPEED_MODIFIER_ID);
            return;
        }

        int tier = HeartScaling.getHeartTier(player);
        java.util.UUID uuid = player.getUuid();

        EntityAttributeInstance speed = player.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED);
        if (speed != null) {
            speed.removeModifier(SPEED_MODIFIER_ID);
            if (tier == 3) {
                speed.addPersistentModifier(new EntityAttributeModifier(
                        SPEED_MODIFIER_ID,
                        0.1,  // Roughly +10% movement speed
                        EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
                ));
            }
        }

        // Handle regen + absorption cooldown
        int cooldownTicks = switch (tier) {
            case 1 -> 600;  // 30s
            case 2 -> 500;  // 25s
            default -> 400; // 20s
        };

        int currentCooldown = armorCooldowns.getOrDefault(uuid, 0);
        if (currentCooldown > 0) {
            armorCooldowns.put(uuid, currentCooldown - 1);
            return;
        }

        // Apply effects
        net.minecraft.entity.effect.StatusEffectInstance regen = switch (tier) {
            case 1 -> new net.minecraft.entity.effect.StatusEffectInstance(
                    net.minecraft.entity.effect.StatusEffects.REGENERATION, 60, 0); // Regen I, 3s
            default -> new net.minecraft.entity.effect.StatusEffectInstance(
                    net.minecraft.entity.effect.StatusEffects.REGENERATION, 60, 1); // Regen II, 3s
        };

        net.minecraft.entity.effect.StatusEffectInstance absorption = switch (tier) {
            case 3 -> new net.minecraft.entity.effect.StatusEffectInstance(
                    net.minecraft.entity.effect.StatusEffects.ABSORPTION, 200, 1); // Absorption II, 10s
            default -> new net.minecraft.entity.effect.StatusEffectInstance(
                    net.minecraft.entity.effect.StatusEffects.ABSORPTION, 200, 0); // Absorption I, 10s
        };

        player.addStatusEffect(regen);
        player.addStatusEffect(absorption);
        armorCooldowns.put(uuid, cooldownTicks);
    }
}