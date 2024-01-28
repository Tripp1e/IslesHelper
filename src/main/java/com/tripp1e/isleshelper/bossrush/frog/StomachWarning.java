package com.tripp1e.isleshelper.bossrush.frog;

import com.tripp1e.isleshelper.Utils;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class StomachWarning {

    public static void checkPhase(PlayerEntity player) {
        BlockPos playerPos = player.getBlockPos();
        World world = player.getWorld();

        Block blockUnder = world.getBlockState(playerPos.down()).getBlock();

        // Check if Platform is going to explode, then show Title
        if (blockUnder == Blocks.RED_CONCRETE ||
            blockUnder == Blocks.ORANGE_CONCRETE ||
            blockUnder == Blocks.YELLOW_CONCRETE) {

            String title =
                    blockUnder.toString()
                    .replace("_concrete}", "")
                    .replace("Block{minecraft:", "")
                    .toUpperCase();

            Utils.sendTitle(title, 0, 2, 0);
        }
    }

}
