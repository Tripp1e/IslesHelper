package com.tripp1e.isleshelper;

import com.mojang.blaze3d.systems.RenderSystem;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.controller.BooleanControllerBuilder;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Box;
import org.joml.Matrix4f;

public class Utils{

    public static void sendTitle(String title, int fade, int stay, int leave) {
        MinecraftClient instance = MinecraftClient.getInstance();
        instance.inGameHud.setTitleTicks(fade, stay, leave);
        instance.inGameHud.setTitle(Text.of(title));
    }

    public static BooleanControllerBuilder createBooleanController(Option<Boolean> opt) {
        return BooleanControllerBuilder.create(opt).yesNoFormatter().coloured(true);
    }

    public static Box boxAroundEntity(Entity entity) {
        return new Box(entity.getBlockPos());
    }

    public static void renderBox(Box box, float red, float green, float blue, float alpha) {
        BufferBuilder bufferBuilder = new BufferBuilder(256);
        bufferBuilder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);
        WorldRenderer.renderFilledBox(new MatrixStack(), bufferBuilder, box.minX, box.minY, box.minZ, box.maxX, box.maxY, box.maxZ, red, green, blue, alpha);
    }


}