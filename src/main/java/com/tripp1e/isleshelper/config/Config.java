package com.tripp1e.isleshelper.config;

import dev.isxander.yacl3.config.v2.api.SerialEntry;

import java.util.ArrayList;
import java.util.List;

public class Config {

    @SerialEntry
    public General general = new General();
    @SerialEntry
    public BossRush bossRush = new BossRush();

    public static class General {
        //Hotbar Slot Locking
        @SerialEntry
        public boolean lockingSlotsEnabled = true;

        @SerialEntry
        public List<Integer> lockedSlots = new ArrayList<>();

    }

    public static class BossRush {
        //General
        @SerialEntry
        public boolean teammateDeathMessageEnabled = true;
        @SerialEntry
        public boolean onlyPartyChatsEnabled = true;

        //Timer
        @SerialEntry
        public boolean timerEnabled = true;
        @SerialEntry
        public int timerX = 50;
        @SerialEntry
        public int timerY = 100;

        //Frog
        @SerialEntry
        public boolean frogStomachWarningEnabled = true;

    }


}
