package com.tripp1e.isleshelper.bossrush;

import com.tripp1e.isleshelper.Utils;
import net.fabricmc.fabric.api.client.message.v1.ClientReceiveMessageEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.minecraft.entity.decoration.ArmorStandEntity;

public class General {


    public static void teamDeathNotify() {
        ServerEntityEvents.ENTITY_LOAD.register((entity, world) -> {
            if (entity instanceof ArmorStandEntity && entity.getCustomName() != null
                && entity.getCustomName().toString().toLowerCase().contains("downed"))

                Utils.sendTitle("Teammate Died",0,60,0);
        });
    }

    public static void onlyPartyMessages() {
        ClientReceiveMessageEvents.ALLOW_CHAT.register((message, a, b, c, d) -> message.toString().toLowerCase().startsWith("party"));
    }

    private static double startTime = System.currentTimeMillis();
    public static String deltaTime = "0";
    public static void timer() {
        double currentTime = System.currentTimeMillis();
        if (!Utils.isInBoss()) startTime = currentTime;
        deltaTime = (currentTime -startTime)/1000D + "";
    }

}
