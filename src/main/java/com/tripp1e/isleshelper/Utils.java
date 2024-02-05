package com.tripp1e.isleshelper;

import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.controller.BooleanControllerBuilder;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.world.World;

public class Utils{

    public static PlayerEntity getPlayer() {
        return MinecraftClient.getInstance().player;
    }
    public static World getWorld() {
        return MinecraftClient.getInstance().world;
    }

    public static void sendTitle(String title, int fade, int stay, int leave) {
        MinecraftClient instance = MinecraftClient.getInstance();
        instance.inGameHud.setTitleTicks(fade, stay, leave);
        instance.inGameHud.setTitle(Text.of(title));
    }

    public static BooleanControllerBuilder createBooleanController(Option<Boolean> opt) {
        return BooleanControllerBuilder.create(opt).yesNoFormatter().coloured(true);
    }


}