package org.zipcoder.cyclic.items.glowHelmet;

import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import org.zipcoder.cyclic.client.utils.ClientGameSettings;
import org.zipcoder.cyclic.materials.ModArmorMaterials;

public class GlowHelmet extends ArmorItem {
    private static double DEFAULT_GAMMA = 1.0D;
    public static final double MAX_GAMMA = 8.5;

    public static void setNightVision(boolean enabled) {
        if(enabled){
            GlowHelmet.DEFAULT_GAMMA = ClientGameSettings.getGammaClamped();
            ClientGameSettings.setGamma(GlowHelmet.MAX_GAMMA);
        }
      else  ClientGameSettings.setGamma(GlowHelmet.DEFAULT_GAMMA);
    }

    public GlowHelmet() {
        super(
                ModArmorMaterials.GLOWING_ARMOR,//Armor material
                Type.HELMET, //Type
                new Item.Properties().stacksTo(1)); //Properties
    }

}