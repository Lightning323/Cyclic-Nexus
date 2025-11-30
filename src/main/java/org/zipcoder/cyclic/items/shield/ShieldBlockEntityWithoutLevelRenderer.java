package org.zipcoder.cyclic.items.shield;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ShieldModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterClientReloadListenersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.zipcoder.cyclic.ItemRegistry;
import org.zipcoder.cyclic.utils.mixin.I_BlockEntityWithoutLevelRenderer;

import static org.zipcoder.cyclic.Cyclic.MOD_ID;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ShieldBlockEntityWithoutLevelRenderer extends BlockEntityWithoutLevelRenderer {

    /**
     * Static instance of the ShieldBlockEntityWithoutLevelRenderer
     */
    public static ShieldBlockEntityWithoutLevelRenderer instance;

    @SubscribeEvent
    public static void onRegisterReloadListener(RegisterClientReloadListenersEvent event) {
        instance = new ShieldBlockEntityWithoutLevelRenderer(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
        event.registerReloadListener(instance);
    }
    //---------------------------------------------------------------


    public ShieldBlockEntityWithoutLevelRenderer(BlockEntityRenderDispatcher rd, EntityModelSet ems) {
        super(rd, ems);
    }

    I_BlockEntityWithoutLevelRenderer mixinThis = (I_BlockEntityWithoutLevelRenderer) this;

    @Override
    public void renderByItem(ItemStack stackIn, ItemDisplayContext type, PoseStack ps, MultiBufferSource buffer, int combinedLight, int combinedOverlay) {
        //Get the shield model
        ShieldModel shieldModel = mixinThis.getShieldModel();

        //copied from superclass
        ps.pushPose();
        ps.scale(1, -1, -1);

        boolean isBanner = (stackIn.getTagElement("BlockEntityTag") != null);
        Material rendermaterial = MaterialShieldRegistry.getMaterial(stackIn, isBanner);

        if (stackIn.is(ItemRegistry.SHIELD_LEATHER.get())) {
            rendermaterial = isBanner ? MaterialShieldRegistry.SHIELD_BASE_LEATHER : MaterialShieldRegistry.SHIELD_BASE_LEATHER_NOPATTERN;
        } else if (stackIn.is(ItemRegistry.SHIELD_BONE.get())) {
            rendermaterial = isBanner ? MaterialShieldRegistry.SHIELD_BASE_BONE : MaterialShieldRegistry.SHIELD_BASE_BONE_NOPATTERN;
        } else if (stackIn.is(ItemRegistry.SHIELD_OBSIDIAN.get())) {
            rendermaterial = isBanner ? MaterialShieldRegistry.SHIELD_BASE_OBSIDIAN : MaterialShieldRegistry.SHIELD_BASE_OBSIDIAN_NOPATTERN;
        } else if (stackIn.is(ItemRegistry.SHIELD_NETHERITE.get())) {
            rendermaterial = isBanner ? MaterialShieldRegistry.SHIELD_BASE_NETHERITE : MaterialShieldRegistry.SHIELD_BASE_NETHERITE_NOPATTERN;
        }
        VertexConsumer vertex = rendermaterial.sprite().wrap(ItemRenderer.getFoilBufferDirect(
                buffer,
                shieldModel.renderType(rendermaterial.atlasLocation()),
                true, stackIn.hasFoil()));

        shieldModel.handle().render(ps, vertex, combinedLight, combinedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);

        //Since we cant have shields with banners (no recipe, its pointless to use this code)
//        if (isBanner) {
//            List<Pair<Holder<BannerPattern>, DyeColor>> pattern = BannerBlockEntity.createPatterns(ShieldItem.getColor(stackIn), BannerBlockEntity.getItemPatterns(stackIn));
//            BannerRenderer.renderPatterns(ps, buffer, combinedLight, combinedOverlay, shieldModel.plate(), rendermaterial, false, pattern, stackIn.hasFoil());
//        } else
        shieldModel.plate().render(ps, vertex, combinedLight, combinedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);

        ps.popPose();
    }
}