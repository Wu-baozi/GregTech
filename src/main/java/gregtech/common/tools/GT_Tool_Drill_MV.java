package gregtech.common.tools;

import gregtech.GT_Mod;
import gregtech.api.enums.Textures;
import gregtech.api.items.toolitem.ToolMetaItem;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.items.IIconContainer;
import net.minecraft.item.ItemStack;

public class GT_Tool_Drill_MV extends GT_Tool_Drill_LV {

    @Override
    public int getToolDamagePerBlockBreak(ItemStack stack) {
        return GT_Mod.gregtechproxy.mHardRock ? 100 : 200;
    }

    @Override
    public int getToolDamagePerDropConversion(ItemStack stack) {
        return 400;
    }

    @Override
    public int getToolDamagePerContainerCraft(ItemStack stack) {
        return 3200;
    }

    @Override
    public int getToolDamagePerEntityAttack(ItemStack stack) {
        return 800;
    }

    @Override
    public int getBaseQuality(ItemStack stack) {
        return 1;
    }

    @Override
    public float getBaseDamage(ItemStack stack) {
        return 2.5F;
    }

    @Override
    public float getSpeedMultiplier(ItemStack stack) {
        return 6.0F;
    }

    @Override
    public float getMaxDurabilityMultiplier(ItemStack stack) {
        return 2.0F;
    }

    @Override
    public IIconContainer getIcon(boolean aIsToolHead, ItemStack aStack) {
        return aIsToolHead ? ToolMetaItem.getPrimaryMaterial(aStack).mIconSet.mTextures[OrePrefix.toolHeadDrill.mTextureIndex] : Textures.ItemIcons.POWER_UNIT_MV;
    }
}
