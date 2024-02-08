package com.tripp1e.isleshelper.features.general;

import net.minecraft.client.MinecraftClient;

public class GeneralIsles {

    public static boolean onlyPartyMessages(String message) {
        if (message.contains(MinecraftClient.getInstance().player.getName().toString())) return true;
        else return !message.contains("\uE045");
    }
}