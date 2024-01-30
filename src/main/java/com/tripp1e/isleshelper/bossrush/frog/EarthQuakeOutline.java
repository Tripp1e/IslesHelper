package com.tripp1e.isleshelper.bossrush.frog;

import com.tripp1e.isleshelper.Utils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.nbt.NbtCompound;

import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.util.List;

public class EarthQuakeOutline {

    public static void makeGlow(World world) {
        List<Entity> entities = world.getEntitiesByClass(Entity.class, new Box(20, 64, -19, 64, 80, 20), entity -> true);
        NbtCompound nbt = new NbtCompound();
        nbt.putBoolean("Glowing", true);
        entities.forEach(entity -> {
                if (entity instanceof FallingBlockEntity){
                    Utils.renderBox(Utils.boxAroundEntity(entity, 1), 1, 1, 1, 1);
                }
        });

    }
}
