package com.tripp1e.isleshelper.features.bossrush;

import com.tripp1e.isleshelper.Utils;
import net.fabricmc.fabric.api.client.message.v1.ClientReceiveMessageEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.decoration.ArmorStandEntity;

public class General {


    public static void teamDeathNotify(Entity entity) {
        if (entity instanceof ArmorStandEntity && entity.getCustomName() != null) {
            if (entity.getCustomName().toString().toLowerCase().contains("downed")) {
                Utils.sendTitle("Teammate Died", 0, 60, 0);
            }
        }
    }

    public static void onlyPartyMessages() {
        ClientReceiveMessageEvents.ALLOW_CHAT.register((message, a, b, c, d) -> message.toString().toLowerCase().startsWith("party"));
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

}
