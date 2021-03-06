package gregtech.common.items.behaviors;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import gregtech.api.GTValues;
import gregtech.api.items.metaitem.MetaItem.MetaValueItem;
import gregtech.api.items.metaitem.stats.IEnchantabilityHelper;
import gregtech.api.items.toolitem.ToolMetaItem;
import gregtech.api.unification.material.Materials;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.util.UUID;

public class NanoSaberBehavior extends ToggleEnergyConsumerBehavior implements IEnchantabilityHelper {

    private static final ResourceLocation OVERRIDE_KEY_LOCATION = new ResourceLocation(GTValues.MODID, "nano_saber_active");
    protected static final UUID ATTACK_DAMAGE_MODIFIER = UUID.fromString("CB3F55D3-645C-4F38-A288-9C13A33DB5CF");
    protected static final UUID ATTACK_SPEED_MODIFIER = UUID.fromString("FA233E1C-4180-4288-B01B-BCCE9785ACA3");

    private final float baseAttackDamage;
    private final float additionalAttackDamage;

    public NanoSaberBehavior(float baseAttackDamage, float additionalAttackDamage, int energyUsagePerTick) {
        super(energyUsagePerTick);
        this.baseAttackDamage = baseAttackDamage;
        this.additionalAttackDamage = additionalAttackDamage;
    }

    @Override
    public void onAddedToItem(MetaValueItem metaValueItem) {
        metaValueItem.getMetaItem().addPropertyOverride(OVERRIDE_KEY_LOCATION, (stack, worldIn, entityIn) -> isItemActive(stack) ? 1.0f : 0.0f);
    }

    @Override
    public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot slot, ItemStack stack) {
        HashMultimap<String, AttributeModifier> modifiers = HashMultimap.create();
        if(slot == EntityEquipmentSlot.MAINHAND) {
            float attackDamage = baseAttackDamage + (isItemActive(stack) ? additionalAttackDamage : 0.0f);
            modifiers.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", -2.0, 0));
            modifiers.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon Modifier", attackDamage, 0));
        }
        return modifiers;
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return true;
    }

    @Override
    public int getItemEnchantability(ItemStack stack) {
        return ToolMetaItem.getMaterialEnchantability(Materials.Platinum);
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        return enchantment != Enchantments.UNBREAKING &&
            enchantment != Enchantments.MENDING &&
            enchantment.type.canEnchantItem(Items.IRON_SWORD);
    }
}
