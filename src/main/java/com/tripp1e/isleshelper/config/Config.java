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

        @SerialEntry
        public boolean onlyPartyChatsEnabled = true;

    }

    public static class BossRush {
        //General
        @SerialEntry
        public boolean teammateDeathMessageEnabled = true;

        //Timer
        @SerialEntry
        public boolean timerEnabled = true;
        @SerialEntry
        public int timerX = 50;
        @SerialEntry
        public int timerY = 100;

        //Low Ammo
        @SerialEntry
        public boolean lowAmmoArrowEnabled = true;
        @SerialEntry
        public int lowAmmoArrowCount = 0;
        @SerialEntry
        public boolean lowAmmoStoneEnabled = true;
        @SerialEntry
        public int lowAmmoStoneCount = 0;
        @SerialEntry
        public boolean lowAmmoRuneEnabled = true;
        @SerialEntry
        public int lowAmmoRuneCount = 0;

        //Frog
        @SerialEntry
        public boolean frogStomachWarningEnabled = true;

    }


}
