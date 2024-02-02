package com.tripp1e.isleshelper.bossrush.frog;

import com.tripp1e.isleshelper.Utils;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.FallingBlockEntity;

import java.util.ArrayList;
import java.util.List;

public class EarthQuakeOutline {
    static List<Entity> entities = new ArrayList<>();

    public static void makeGlow() {
        ServerEntityEvents.ENTITY_LOAD.register((entity, state) -> {
            if (entity instanceof FallingBlockEntity) {
                entities.add(entity);
                Utils.renderBox(Utils.boxAroundEntity(entity), 1, 1, 1, 1);
                System.out.println("Entity summoned: " + entity);
            }
        });
    }
}
