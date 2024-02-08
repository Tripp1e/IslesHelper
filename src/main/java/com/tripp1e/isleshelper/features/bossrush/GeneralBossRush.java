package com.tripp1e.isleshelper.features.bossrush;

import com.tripp1e.isleshelper.Utils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.decoration.ArmorStandEntity;

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
            //ASD
        }
    }

}
