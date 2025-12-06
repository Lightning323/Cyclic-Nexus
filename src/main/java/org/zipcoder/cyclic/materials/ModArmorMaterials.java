package org.zipcoder.cyclic.materials;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import org.zipcoder.cyclic.Cyclic;
import org.zipcoder.cyclic.sound.SoundRegistry;

import java.util.function.Supplier;

import net.minecraft.world.item.ArmorMaterials;

//net.minecraft.world.item.ArmorMaterials
public enum ModArmorMaterials implements ArmorMaterial {

    //Glow
    GLOWING_ARMOR("glowing", 33, // durability
            new int[]{ArmorMaterials.IRON.getDefenseForType(ArmorItem.Type.HELMET),// helmtet
                    0, 0, 0
            }, 12, // enchantability
            SoundEvents.ARMOR_EQUIP_GENERIC, // equip sound
            0.10F, // toughness
            0F, // knockback resistance
            () -> Ingredient.of(Items.GLOWSTONE_DUST)),

    //Copper has iron protection but leather durability
    COPPER_ARMOR("copper", 5, // durability
            new int[]{ArmorMaterials.IRON.getDefenseForType(ArmorItem.Type.HELMET),// helmtet
                    5,// chestplate
                    ArmorMaterials.IRON.getDefenseForType(ArmorItem.Type.LEGGINGS),// leggings
                    ArmorMaterials.IRON.getDefenseForType(ArmorItem.Type.BOOTS), // boots
            }, 12, // enchantability
            SoundEvents.ARMOR_EQUIP_GENERIC, // equip sound
            0F, // toughness
            0F, // knockback resistance
            () -> Ingredient.of(Items.COPPER_INGOT)), // repair material

    //Emerald has iron protection but with a diamond durability, netherite toughness and higher enchantment value than any other armor
    EMERALD_ARMOR("emerald", 40, // durability
            new int[]{ // protection
                    ArmorMaterials.IRON.getDefenseForType(ArmorItem.Type.HELMET),// helmtet
                    ArmorMaterials.IRON.getDefenseForType(ArmorItem.Type.CHESTPLATE) + 1,// chestplate
                    ArmorMaterials.IRON.getDefenseForType(ArmorItem.Type.LEGGINGS),// leggings
                    ArmorMaterials.IRON.getDefenseForType(ArmorItem.Type.BOOTS), // boots
            }, 35, // enchantability
            SoundRegistry.EQUIP_EMERALD.get(), // equip sound
            0.25F, // toughness
            0.2F, // knockback resistance
            () -> Ingredient.of(Items.EMERALD_BLOCK)); // repair material

    private final String name;
    private final int durabilityMultiplier;
    private final int[] protectionAmounts;
    private final int enchantmentValue;
    private final SoundEvent equipSound;
    private final float toughness;
    private final float knockbackResistance;
    private final Supplier<Ingredient> repairIngredient;

    private static final int[] BASE_DURABILITY = new int[]{11, 16, 16, 13};

    ModArmorMaterials(String name, int durability, int[] protectionAmounts, int enchantability, SoundEvent equipSound, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient) {
        this.name = name;
        this.durabilityMultiplier = durability;
        this.protectionAmounts = protectionAmounts;
        this.enchantmentValue = enchantability;
        this.equipSound = equipSound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = repairIngredient;
    }

    @Override
    public int getDurabilityForType(ArmorItem.Type type) {
        return BASE_DURABILITY[type.ordinal()] * durabilityMultiplier;
    }

    @Override
    public int getDefenseForType(ArmorItem.Type type) {
        return this.protectionAmounts[type.ordinal()];
    }

    @Override
    public int getEnchantmentValue() {
        return enchantmentValue;
    }

    @Override
    public SoundEvent getEquipSound() {
        return equipSound;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return repairIngredient.get();
    }

    @Override
    public String getName() {
        return Cyclic.MOD_ID + ":" + name;
    }

    @Override
    public float getToughness() {
        return toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return knockbackResistance;
    }
}
