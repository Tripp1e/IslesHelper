package com.tripp1e.isleshelper.features.bossrush;

import com.tripp1e.isleshelper.config.ConfigManager;
import com.tripp1e.isleshelper.util.Utils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class GeneralBossRush {

    public static void teamDeathNotify(Entity entity) {
        if (entity instanceof ArmorStandEntity && entity.getCustomName() != null) {
            if (entity.getCustomName().toString().toLowerCase().contains("downed")) {
                Utils.sendTitle("Teammate Died", 0, 60, 0);
            }
        }
    }

    private static double startTime = System.currentTimeMillis();
    public static String deltaTime = "0.0";
    public static void timer() {
        double currentTime = System.currentTimeMillis();
        startTime = Utils.isInBoss() ? startTime : currentTime;
        if(Utils.isInBoss()) deltaTime = (currentTime - startTime)/1000D+"";

        if (!Utils.isInBoss() && !deltaTime.equals("0.0")){
            Utils.sendTitle("Your Time was: " + deltaTime, 5, 100, 5);
            deltaTime = "0.0";
        }
    }


    public static void warnOnLowAmmo() {

        int copperArrows = 0;
        int honingStones = 0;
        int magicRunes = 0;

        ItemStack currentStack = null;

        for (int i = 0; i < Utils.getPlayer().getInventory().size(); i++) {
            currentStack = Utils.getPlayer().getInventory().getStack(i);
            if (currentStack != null && currentStack.isOf(Items.SPECTRAL_ARROW))
                copperArrows += currentStack.getCount();

            if (currentStack != null && currentStack.isOf(Items.NETHERITE_SCRAP))
                honingStones += currentStack.getCount();

            if (currentStack != null && currentStack.isOf(Items.PHANTOM_MEMBRANE))
                magicRunes += currentStack.getCount();
        }

        if (    Utils.isInBoss() &&
                ConfigManager.get().bossRush.lowAmmoArrowEnabled &&
                copperArrows < ConfigManager.get().bossRush.lowAmmoRuneCount)
                Utils.sendTitle("Low Arrows", 0, 2, 0);

        if (    Utils.isInBoss() &&
                ConfigManager.get().bossRush.lowAmmoStoneEnabled &&
                honingStones < ConfigManager.get().bossRush.lowAmmoStoneCount)
                Utils.sendTitle("Low Stones", 0, 2, 0);

        if (    Utils.isInBoss() &&
                ConfigManager.get().bossRush.lowAmmoRuneEnabled &&
                magicRunes < ConfigManager.get().bossRush.lowAmmoRuneCount)
                Utils.sendTitle("Low Runes", 0, 2, 0);

    }



}
