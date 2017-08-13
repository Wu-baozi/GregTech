package gregtech.loaders.oreprocessing;

import gregtech.api.unification.ore.IOreRegistrationHandler;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;
import net.minecraft.item.ItemStack;

public class ProcessingAll implements IOreRegistrationHandler {
    public ProcessingAll() {
        for (OrePrefix tPrefix : OrePrefix.values()) tPrefix.add(this);
    }

    public void registerOre(OrePrefix aPrefix, Materials aMaterial, String aOreDictName, String aModName, ItemStack aStack) {
        if (((aStack.getItem() instanceof net.minecraft.item.ItemBlock)) && (aPrefix.mDefaultStackSize < aStack.getItem().getItemStackLimit(aStack)))
            aStack.getItem().setMaxStackSize(aPrefix.mDefaultStackSize);
    }
}
