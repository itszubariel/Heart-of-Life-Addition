package com.zubariel.heartoflifeadditions.item;

import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;

public class ModToolMaterials {
    public static final ToolMaterial HEART = new ToolMaterial(
            BlockTags.INCORRECT_FOR_NETHERITE_TOOL,
            2500,
            10.0f,
            4.5f,
            22,
            ItemTags.REPAIRS_NETHERITE_ARMOR
    );
}