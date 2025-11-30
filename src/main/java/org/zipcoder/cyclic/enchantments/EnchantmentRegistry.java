package org.zipcoder.cyclic.enchantments;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static org.zipcoder.cyclic.Cyclic.MOD_ID;

public class EnchantmentRegistry {
    public final static DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, MOD_ID);
    private static final EquipmentSlot[] ARMOR_SLOTS = new EquipmentSlot[]{EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET};

//    public static final RegistryObject<TravellerEnchant> TRAVELLER = MOB_EFFECTS.register(TravellerEnchant.ID, () -> new TravellerEnchant(Enchantment.Rarity.RARE, EnchantmentCategory.ARMOR_LEGS, EquipmentSlot.LEGS));
//    public static final RegistryObject<MagnetEnchant> MAGNET = MOB_EFFECTS.register(MagnetEnchant.ID, () -> new MagnetEnchant(Enchantment.Rarity.VERY_RARE, EnchantmentCategory.BREAKABLE, EquipmentSlot.MAINHAND));
    public static final RegistryObject<StepEnchant> STEP = ENCHANTMENTS.register(StepEnchant.ID, () -> new StepEnchant(Enchantment.Rarity.UNCOMMON, EnchantmentCategory.ARMOR_FEET, EquipmentSlot.FEET));
//    public static final RegistryObject<BeekeeperEnchant> BEEKEEPER = MOB_EFFECTS.register(BeekeeperEnchant.ID, () -> new BeekeeperEnchant(Enchantment.Rarity.VERY_RARE, EnchantmentCategory.ARMOR_HEAD, EquipmentSlot.HEAD));


    public static void register(IEventBus eventBus) {
        ENCHANTMENTS.register(eventBus);
    }
}
