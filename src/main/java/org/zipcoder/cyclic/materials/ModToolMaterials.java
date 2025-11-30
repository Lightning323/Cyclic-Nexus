package org.zipcoder.cyclic.materials;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;
import org.zipcoder.cyclic.ItemRegistry;

import java.util.List;

import static org.zipcoder.cyclic.Cyclic.MOD_ID;

public class ModToolMaterials {

    //Comes after stone
    //Copper tools have iron speed but stone durability
    public static final Tier COPPER = TierSortingRegistry.registerTier(
            //harvestLevel, uses, toolSpeed, damage, enchantability
            //Tiers.STONE
            new ForgeTier(
                    1,
                    200,
                    Tiers.IRON.getSpeed(),
                    1.0F,
                    8,
                    BlockTags.create(new ResourceLocation(MOD_ID, "needs_copper_tool")),
                    () -> Ingredient.of(Items.COPPER_INGOT)),
            new ResourceLocation(MOD_ID, "copper"),
            List.of(Tiers.STONE), //after
            List.of(Tiers.IRON)); //before


//    public static final Tier AMETHYST = TierSortingRegistry.registerTier(
//            //harvestLevel, uses, toolSpeed, damage, enchantability
//            //Tiers.IRON
//            new ForgeTier(
//                    2,
//                    300,
//                    Tiers.IRON.getSpeed(),
//                    1.8F,
//                    15,
//                    BlockTags.create(new ResourceLocation(MOD_ID, "needs_amethyst_tool")),
//                    () -> Ingredient.of(Items.AMETHYST_SHARD)),
//            new ResourceLocation(MOD_ID, "amethyst"),
//            List.of(Tiers.IRON), //after
//            List.of(Tiers.DIAMOND)); //before

    public static final Tier AMETHYST = TierSortingRegistry.registerTier(
            //harvestLevel, uses, toolSpeed, damage, enchantability
            //Tiers.IRON
            new ForgeTier(
                    2,
                    1000,
                    Tiers.IRON.getSpeed(),
                    2.0F,
                    26,
                    BlockTags.create(new ResourceLocation(MOD_ID, "needs_amethyst_tool")),
                    () -> Ingredient.of(Items.AMETHYST_SHARD)),
            new ResourceLocation(MOD_ID, "amethyst"),
            List.of(Tiers.IRON),  //after
            List.of(Tiers.DIAMOND)); //before


    //Emerald tools have gold speed (fast) and almost diamond durability
    public static final Tier EMERALD = TierSortingRegistry.registerTier(
            //harvestLevel, uses, toolSpeed, damage, enchantability
//            Tiers.GOLD
            new ForgeTier(
                    0,
                    (int) (Tiers.DIAMOND.getUses() * 0.75f),
                    12.0F,
                    0.0F,
                    15,

                    BlockTags.create(new ResourceLocation(MOD_ID, "needs_emerald_tool")),
                    () -> Ingredient.of(Items.EMERALD)),//Repair
            new ResourceLocation(MOD_ID, "emerald"),
            List.of(Tiers.GOLD), //after
            List.of(Tiers.DIAMOND) //before
    );

    //Creative tier
    public static final Tier CREATIVE = TierSortingRegistry.registerTier(
            //harvestLevel, uses, toolSpeed, damage, enchantability
            new ForgeTier(
                    Tiers.NETHERITE.getLevel() + 1,
                    Tiers.NETHERITE.getUses() * 9999999,
                    Tiers.NETHERITE.getSpeed() * 9999999, // uses aka durability
                    Tiers.NETHERITE.getAttackDamageBonus() * 99999999,  //Attack damage bonus
                    Tiers.NETHERITE.getEnchantmentValue() * 9999999,

                    BlockTags.create(new ResourceLocation(MOD_ID, "needs_netherite_tool")), //Tag

                    () -> Ingredient.of(Items.DIAMOND)),//Repair ingredient

            new ResourceLocation(MOD_ID, "creative"),
            List.of(Tiers.NETHERITE), List.of());

}