package org.zipcoder.cyclic.items.shield;

import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.zipcoder.cyclic.ItemRegistry;

import static org.zipcoder.cyclic.Cyclic.MOD_ID;

public class MaterialShieldRegistry {

    public static final Material SHIELD_BASE_LEATHER = new Material(Sheets.SHIELD_SHEET, new ResourceLocation(MOD_ID, "entity/shield/leather_base"));
    public static final Material SHIELD_BASE_LEATHER_NOPATTERN = new Material(Sheets.SHIELD_SHEET, new ResourceLocation(MOD_ID, "entity/shield/leather_base_nopattern"));

    public static final Material SHIELD_BASE_BONE = new Material(Sheets.SHIELD_SHEET, new ResourceLocation(MOD_ID, "entity/shield/bone_base"));
    public static final Material SHIELD_BASE_BONE_NOPATTERN = new Material(Sheets.SHIELD_SHEET, new ResourceLocation(MOD_ID, "entity/shield/bone_base_nopattern"));

    public static final Material SHIELD_BASE_OBSIDIAN = new Material(Sheets.SHIELD_SHEET, new ResourceLocation(MOD_ID, "entity/shield/obsidian_base"));
    public static final Material SHIELD_BASE_OBSIDIAN_NOPATTERN = new Material(Sheets.SHIELD_SHEET, new ResourceLocation(MOD_ID, "entity/shield/obsidian_base_nopattern"));

    public static final Material SHIELD_BASE_NETHERITE = new Material(Sheets.SHIELD_SHEET, new ResourceLocation(MOD_ID, "entity/shield/netherite_base"));
    public static final Material SHIELD_BASE_NETHERITE_NOPATTERN = new Material(Sheets.SHIELD_SHEET, new ResourceLocation(MOD_ID, "entity/shield/netherite_base_nopattern"));

    public static void initShields() {
        //this matches up with ShieldCyclicItem where it calls startUsingItem() inside of use()
        net.minecraft.client.renderer.item.ItemPropertyFunction blockFn = (stack, world, entity, seed) -> entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1.0F : 0.0F;

        ItemProperties.register(ItemRegistry.SHIELD_LEATHER.get(), ShieldCyclicItem.BLOCKING, blockFn);
        ItemProperties.register(ItemRegistry.SHIELD_BONE.get(), ShieldCyclicItem.BLOCKING, blockFn);
        ItemProperties.register(ItemRegistry.SHIELD_OBSIDIAN.get(), ShieldCyclicItem.BLOCKING, blockFn);
        ItemProperties.register(ItemRegistry.SHIELD_NETHERITE.get(), ShieldCyclicItem.BLOCKING, blockFn);
    }

    public static Material getMaterial(ItemStack stackIn, boolean isBanner) {
        Material rendermaterial = isBanner ? ModelBakery.SHIELD_BASE : ModelBakery.NO_PATTERN_SHIELD;
        if (stackIn.is(ItemRegistry.SHIELD_LEATHER.get())) {
            rendermaterial = isBanner ? MaterialShieldRegistry.SHIELD_BASE_LEATHER : MaterialShieldRegistry.SHIELD_BASE_LEATHER_NOPATTERN;
        } else if (stackIn.is(ItemRegistry.SHIELD_BONE.get())) {
            rendermaterial = isBanner ? MaterialShieldRegistry.SHIELD_BASE_BONE : MaterialShieldRegistry.SHIELD_BASE_BONE_NOPATTERN;
        } else if (stackIn.is(ItemRegistry.SHIELD_OBSIDIAN.get())) {
            rendermaterial = isBanner ? MaterialShieldRegistry.SHIELD_BASE_OBSIDIAN : MaterialShieldRegistry.SHIELD_BASE_OBSIDIAN_NOPATTERN;
        } else if (stackIn.is(ItemRegistry.SHIELD_NETHERITE.get())) {
            rendermaterial = isBanner ? MaterialShieldRegistry.SHIELD_BASE_NETHERITE : MaterialShieldRegistry.SHIELD_BASE_NETHERITE_NOPATTERN;
        }
        return rendermaterial;
    }


}
