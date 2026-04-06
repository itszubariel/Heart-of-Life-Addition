package com.zubariel.heartoflifeadditions.item;

import net.minecraft.item.Item;
import net.minecraft.item.SmithingTemplateItem;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.Util;
import java.util.List;

public class HeartUpgradeTemplateItem {

    private static final Formatting TITLE_FORMATTING = Formatting.GRAY;
    private static final Formatting DESCRIPTION_FORMATTING = Formatting.BLUE;

    private static final Text APPLIES_TO = Text.translatable(
            Util.createTranslationKey("item", Identifier.of("heartoflifeadditions", "smithing_template.heart_upgrade.applies_to"))
    ).formatted(DESCRIPTION_FORMATTING);

    private static final Text INGREDIENTS = Text.translatable(
            Util.createTranslationKey("item", Identifier.of("heartoflifeadditions", "smithing_template.heart_upgrade.ingredients"))
    ).formatted(DESCRIPTION_FORMATTING);

    private static final Text BASE_SLOT_DESCRIPTION = Text.translatable(
            Util.createTranslationKey("item", Identifier.of("heartoflifeadditions", "smithing_template.heart_upgrade.base_slot_description"))
    );

    private static final Text ADDITIONS_SLOT_DESCRIPTION = Text.translatable(
            Util.createTranslationKey("item", Identifier.of("heartoflifeadditions", "smithing_template.heart_upgrade.additions_slot_description"))
    );

    public static SmithingTemplateItem createHeartUpgradeTemplate() {
        RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM,
                Identifier.of("heartoflifeadditions", "heart_upgrade_template"));

        return new SmithingTemplateItem(
                APPLIES_TO,
                INGREDIENTS,
                BASE_SLOT_DESCRIPTION,
                ADDITIONS_SLOT_DESCRIPTION,
                List.of(
                        Identifier.ofVanilla("container/slot/helmet"),
                        Identifier.ofVanilla("container/slot/sword"),
                        Identifier.ofVanilla("container/slot/chestplate"),
                        Identifier.ofVanilla("container/slot/pickaxe"),
                        Identifier.ofVanilla("container/slot/leggings"),
                        Identifier.ofVanilla("container/slot/axe"),
                        Identifier.ofVanilla("container/slot/boots"),
                        Identifier.ofVanilla("container/slot/hoe"),
                        Identifier.ofVanilla("container/slot/shovel")
                ),
                List.of(
                        Identifier.ofVanilla("container/slot/ingot")
                ),
                new Item.Settings().registryKey(key).rarity(Rarity.EPIC)
        );
    }
}