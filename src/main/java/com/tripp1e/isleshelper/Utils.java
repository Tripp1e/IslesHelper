package com.tripp1e.isleshelper;

import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.controller.BooleanControllerBuilder;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.text.Text;
import net.minecraft.util.math.Box;

import static com.tripp1e.isleshelper.IslesHelperClient.LOGGER;


public class Utils{


    public static void sendTitle(String title, int fade, int stay, int leave) {
        MinecraftClient instance = MinecraftClient.getInstance();
        instance.inGameHud.setTitleTicks(fade, stay, leave);
        instance.inGameHud.setTitle(Text.of(title));
    }

    public static BooleanControllerBuilder createBooleanController(Option<Boolean> opt) {
        return BooleanControllerBuilder.create(opt).yesNoFormatter().coloured(true);
    }

    public static Box boxAroundEntity(Entity entity, double radius) {
        double x = entity.getX();
        double y = entity.getY();
        double z = entity.getZ();
        return new Box(x-radius, y-radius, z-radius, x+radius, y+radius, z+radius);
    }

    public static void renderBox(Box box, float red, float green, float blue, float alpha) {
        WorldRenderer.drawBox(new MatrixStack(), createVertexConsumer(box, red, green, blue, alpha), box, red, green, blue, alpha);
    }

    private static VertexConsumer createVertexConsumer(Box box, float red, float green, float blue, float alpha) {
        BufferBuilder bufferBuilder = new BufferBuilder(256);
        bufferBuilder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);

        float minX = (float) box.minX;
        float minY = (float) box.minY;
        float minZ = (float) box.minZ;
        float maxX = (float) box.maxX;
        float maxY = (float) box.maxY;
        float maxZ = (float) box.maxZ;

        // Vertices
        VertexBuilder(red, green, blue, alpha, minX, minY, minZ, maxX, maxY, maxZ, bufferBuilder);
        VertexBuilder(red, green, blue, alpha, maxX, minY, minZ, maxX, maxY, maxZ, bufferBuilder);

        return bufferBuilder;
    }
    private static void VertexBuilder(float red, float green, float blue, float alpha, float minX, float minY, float minZ, float maxX, float maxY, float maxZ, BufferBuilder bufferBuilder) {
        bufferBuilder.vertex(minX, minY, minZ).color(red, green, blue, alpha).next();
        bufferBuilder.vertex(minX, minY, maxZ).color(red, green, blue, alpha).next();
        bufferBuilder.vertex(minX, maxY, minZ).color(red, green, blue, alpha).next();
        bufferBuilder.vertex(maxX, maxY, maxZ).color(red, green, blue, alpha).next();
    }

}