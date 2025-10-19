package org.zipcoder.cyclic.events;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import org.zipcoder.cyclic.items.glowHelmet.GlowHelmet;
import org.zipcoder.cyclic.network.GlowHelmetPacket;
import org.zipcoder.cyclic.network.NetworkHandler;
import top.theillusivec4.curios.api.event.CurioChangeEvent;

import static org.zipcoder.cyclic.Cyclic.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID)
public class ArmorWatcher {

    @SubscribeEvent
    public static void onCurioChange(CurioChangeEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;

        if (event.getTo().getItem() instanceof GlowHelmet) {
            NetworkHandler.sendToClient(new GlowHelmetPacket(true), (ServerPlayer) player);
        } else if (event.getFrom().getItem() instanceof GlowHelmet) {
            NetworkHandler.sendToClient(new GlowHelmetPacket(false), (ServerPlayer) player);
        }
    }

    @SubscribeEvent
    public static void onArmorChange(LivingEquipmentChangeEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;

        EquipmentSlot slot = event.getSlot();
        if (slot.getType() == EquipmentSlot.Type.ARMOR && slot.getIndex() == EquipmentSlot.HEAD.getIndex()) {

            if (event.getTo().getItem() instanceof GlowHelmet) {
                NetworkHandler.sendToClient(new GlowHelmetPacket(true), (ServerPlayer) player);
            } else if (event.getFrom().getItem() instanceof GlowHelmet) {
                NetworkHandler.sendToClient(new GlowHelmetPacket(false), (ServerPlayer) player);
            }
        }
    }

}
