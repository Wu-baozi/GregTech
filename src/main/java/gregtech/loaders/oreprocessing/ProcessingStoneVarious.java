package gregtech.loaders.oreprocessing;

import gregtech.api.GT_Values;
import gregtech.api.unification.ore.IOreRegistrationHandler;
import gregtech.api.items.ItemList;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.material.Materials;
import gregtech.api.util.GT_Utility;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class ProcessingStoneVarious implements IOreRegistrationHandler {
    public ProcessingStoneVarious() {
        OrePrefix.stone.add(this);
        OrePrefix.stoneCobble.add(this);
        OrePrefix.stoneBricks.add(this);
        OrePrefix.stoneChiseled.add(this);
        OrePrefix.stoneCracked.add(this);
        OrePrefix.stoneMossy.add(this);
        OrePrefix.stoneMossyBricks.add(this);
        OrePrefix.stoneSmooth.add(this);
    }

    public void registerOre(OrePrefix aPrefix, Materials aMaterial, String aOreDictName, String aModName, ItemStack aStack) {
        if (aPrefix == OrePrefix.stoneSmooth) {
            GT_Values.RA.addAssemblerRecipe(GT_Utility.copyAmount(1L, aStack), ItemList.Circuit_Integrated.getWithDamage(0L, 1L), new ItemStack(Blocks.STONE_BUTTON, 1), 100, 4);
            GT_Values.RA.addAssemblerRecipe(GT_Utility.copyAmount(2L, aStack), ItemList.Circuit_Integrated.getWithDamage(0L, 2L), new ItemStack(Blocks.STONE_PRESSURE_PLATE, 1), 200, 4);
        }
    }
}
