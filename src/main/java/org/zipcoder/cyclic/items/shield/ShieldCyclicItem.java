package org.zipcoder.cyclic.items.shield;

import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;

import java.util.List;

public class ShieldCyclicItem extends ShieldItem implements Equipable {

    Item repairItem;
    int useDuration = 72000;
    public static final ResourceLocation BLOCKING = new ResourceLocation("minecraft:blocking");

    public ShieldCyclicItem(Item.Properties properties, Item repairItem) {
        super(properties);
        this.repairItem = repairItem;
        DispenserBlock.registerBehavior(this, ArmorItem.DISPENSE_ITEM_BEHAVIOR);
    }

    @Override
    public boolean canPerformAction(ItemStack stack, ToolAction toolAction) {
        return ToolActions.DEFAULT_SHIELD_ACTIONS.contains(toolAction) || toolAction.equals(ToolActions.SHIELD_BLOCK);
    }

    @Override
    public boolean isValidRepairItem(ItemStack stackShield, ItemStack stackIngredient) {
        if (repairItem != null) {
            return stackIngredient.is(repairItem);
        }
        return false;
    }

    public int getUseDuration(ItemStack p_43107_) {
        return useDuration;
    }

//    @Override
//    public InteractionResultHolder<ItemStack> use(Level world, Player playerIn, InteractionHand hand) {
//        ItemStack itemstack = playerIn.getItemInHand(hand);
//        playerIn.startUsingItem(hand); //important for Blocking property
//        return InteractionResultHolder.consume(itemstack);
//    }
//

    /**
     * A shield is technically an entity, so we need a custom renderer
     *
     * @param consumer
     */
    @Override
    public void initializeClient(java.util.function.Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return ShieldBlockEntityWithoutLevelRenderer.instance;
            }
        });
    }
}
