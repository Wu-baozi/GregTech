package gregtech.api.unification.ore;

import gregtech.api.GregTech_API;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.type.Material;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.IStringSerializable;

/**
 * For ore generation
 */
public enum StoneTypes implements IStringSerializable {

    //vanilla ones
    STONE("stone", OrePrefix.ore, Materials.Stone),
    NETHERRACK("netherrack", OrePrefix.oreNetherrack, Materials.Netherrack),
    ENDSTONE("endstone", OrePrefix.oreEndstone, Materials.Endstone),

    //gt ones
    BLACK_GRANITE("black_granite", OrePrefix.oreBlackgranite, Materials.GraniteBlack),
    RED_GRANITE("red_granite", OrePrefix.oreRedgranite, Materials.GraniteRed),
    MARBLE("marble", OrePrefix.oreMarble, Materials.Marble),
    BASALT("basalt", OrePrefix.oreBasalt, Materials.Basalt);


    public final int id = ordinal();
    public final String name;
    public final OrePrefix processingPrefix;
    public final Material stoneMaterial;

    public static final StoneTypes[] TYPES = values();

    StoneTypes(String name, OrePrefix processingPrefix, Material stoneMaterial) {
        this.name = name;
        this.processingPrefix = processingPrefix;
        this.stoneMaterial = stoneMaterial;
    }

    public static StoneTypes computeStoneType(IBlockState blockState) {
        Block block = blockState.getBlock();
        int metadata = block.getMetaFromState(blockState);

        StoneTypes variantId = StoneTypes.STONE;

        if (block == Blocks.STONE) {
            variantId = StoneTypes.STONE;
        } else if (block == GregTech_API.sBlockGranites) {
            if (metadata == 0)
                variantId = StoneTypes.BLACK_GRANITE;
            else if (metadata == 8)
                variantId = StoneTypes.RED_GRANITE;
        } else if (block == GregTech_API.sBlockStones) {
            if (metadata == 0)
                variantId = StoneTypes.MARBLE;
            else if (metadata == 8)
                variantId = StoneTypes.BASALT;
        } else if (block == Blocks.NETHERRACK) {
            variantId = StoneTypes.NETHERRACK;
        } else if (block == Blocks.END_STONE) {
            variantId = StoneTypes.ENDSTONE;
        }
        return variantId;
    }

    @Override
    public String getName() {
        return name;
    }
}
