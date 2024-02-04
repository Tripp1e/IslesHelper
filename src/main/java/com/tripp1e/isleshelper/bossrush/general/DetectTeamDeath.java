package com.tripp1e.isleshelper.bossrush.general;

import com.tripp1e.isleshelper.Utils;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.minecraft.entity.decoration.ArmorStandEntity;

public class DetectTeamDeath {

    public static void detectDeath (){

        ServerEntityEvents.ENTITY_LOAD.register((entity, world) -> {
            if (entity instanceof ArmorStandEntity && entity.getCustomName() != null
                && entity.getCustomName().toString().equalsIgnoreCase("downed"))
                Utils.sendTitle("Teammate Died",0,60,0);
        });

    }

}
