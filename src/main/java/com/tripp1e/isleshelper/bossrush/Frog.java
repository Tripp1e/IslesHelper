package com.tripp1e.isleshelper.bossrush;

import com.tripp1e.isleshelper.Utils;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

public class Frog {

    public static void stomachExplodeWarn() {

        Block blockUnderPlayer = Utils.getWorld().getBlockState(Utils.getPlayer().getBlockPos().down()).getBlock();

        if (blockUnderPlayer == Blocks.RED_CONCRETE ||
            blockUnderPlayer == Blocks.ORANGE_CONCRETE ||
            blockUnderPlayer == Blocks.YELLOW_CONCRETE) {

            String title =
                blockUnderPlayer.toString()
                .replace("_concrete}", "")
                .replace("Block{minecraft:", "")
                .toUpperCase();

            Utils.sendTitle(title, 0, 2, 0);
        }
    }


}
