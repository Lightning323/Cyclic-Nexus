package org.zipcoder.cyclic;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.zipcoder.cyclic.effects.EffectRegistry;
import org.zipcoder.cyclic.potions.BrewingRecipe;

import static org.zipcoder.cyclic.Cyclic.MOD_ID;
import static org.zipcoder.cyclic.Cyclic.preInit;

public class PotionsRegistry {
    public final static DeferredRegister<Potion> POTIONS =
            DeferredRegister.create(ForgeRegistries.POTIONS, MOD_ID);

    static final int dur3Min = 180 * 20;
    static final int dur1Min = 60 * 20;

    //Freeze 1
    public static final RegistryObject<Potion> FREEZE_POTION = POTIONS.register("freeze_potion",
            () -> new Potion(MOD_ID + "_freeze", new MobEffectInstance(EffectRegistry.FREEZE.get(),
                    20 * preInit.potion_freeze1Duration, 0)));

    //Freeze 2
    public static final RegistryObject<Potion> FREEZE_POTION_2 = POTIONS.register("freeze_potion_2",
            () -> new Potion(MOD_ID + "_freeze", new MobEffectInstance(EffectRegistry.FREEZE.get(),
                    20 * preInit.potion_freeze2Duration, 0)));

    //Reach
    public static final RegistryObject<Potion> REACH_POTION = preInit.reachDistance ?
            POTIONS.register("reach_distance",
                    () -> new Potion(MOD_ID + "_reach_distance", new MobEffectInstance(EffectRegistry.REACH_DISTANCE.get(),
                            dur3Min, 0)))
            : null;

    //Attack range
    public static final RegistryObject<Potion> ATTACK_RANGE_POTION = preInit.attackRange ?
            POTIONS.register("attach_range",
                    () -> new Potion(MOD_ID + "_attack_range", new MobEffectInstance(EffectRegistry.ATTACK_RANGE.get(),
                            dur3Min, 0)))
            : null;

    //Blind (Uses existing effects)
    public static final RegistryObject<Potion> BLIND_POTION = POTIONS.register("blind",
            () -> new Potion(MOD_ID + "_blind", new MobEffectInstance(MobEffects.BLINDNESS, dur3Min)));


    public static void register(IEventBus eventBus) {
        POTIONS.register(eventBus);
    }

    /**
     * Add potion recipes
     */
    public static void setup() {
        //Starter potions
        final ItemStack awkwardPotion = PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.AWKWARD);
        final ItemStack thickPotion = PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.THICK);
        final ItemStack nightVisionPotion = PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.NIGHT_VISION);
        final ItemStack slowPotion = PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.SLOWNESS);

        basicBrewing(BLIND_POTION.get(), nightVisionPotion.copy(), Items.GOLDEN_APPLE); //Blindness

        basicBrewing(FREEZE_POTION.get(), slowPotion.copy(), ItemRegistry.SNOWFLAKE.get()); //freeze

        final ItemStack freeze_potion = PotionUtils.setPotion(new ItemStack(Items.POTION), FREEZE_POTION.get());
        basicBrewing(FREEZE_POTION_2.get(), freeze_potion.copy(), ItemRegistry.GOLD_SNOWFLAKE.get());//freeze 2


        //Reach distance
        if (REACH_POTION != null) {
            basicBrewing(REACH_POTION.get(), awkwardPotion.copy(), ItemRegistry.GEM_AMETHYST.get());
        }

        //Attack range
        if (ATTACK_RANGE_POTION != null) {
            ItemStack reach_potion = thickPotion;
            if (REACH_POTION != null)
                reach_potion = PotionUtils.setPotion(new ItemStack(Items.POTION), REACH_POTION.get());
            basicBrewing(ATTACK_RANGE_POTION.get(), reach_potion.copy(), ItemRegistry.GEM_PRISMARINE.get());
        }
    }

    private static void basicBrewing(Potion outputPotion, ItemStack inputPotion, Item ingredient) {
        BrewingRecipeRegistry.addRecipe(new BrewingRecipe(inputPotion, Ingredient.of(ingredient), PotionUtils.setPotion(new ItemStack(Items.POTION), outputPotion)));
    }
}
