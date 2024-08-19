package org.agmas.prisongamefabric.util;

import net.fabricmc.loader.impl.util.log.Log;
import net.fabricmc.loader.impl.util.log.LogCategory;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.boss.BossBarManager;

import java.util.ArrayList;
import java.util.HashMap;

public class Schedule {

    public static ArrayList<ScheduleEntry> entries = new ArrayList<ScheduleEntry>();
    public static int time;
    public static int previousEndTime=0;
    public static BossBar scheduleBar;

    public static void createSchedule() {
        entries.add(new ScheduleEntry(60, "Roll Call", 0, 1000, true,true));
        entries.add(new ScheduleEntry(60, "Breakfast", 1000, 2000, true));
        entries.add(new ScheduleEntry(100, "Free Time", 2000, 4000, true));
        entries.add(new ScheduleEntry(140, "Job Time", 4000, 11000, true));
        entries.add(new ScheduleEntry(60, "Evening Roll Call", 11000, 13000, true));
        entries.add(new ScheduleEntry(60, "Cell Time", 13000, 14000, true));
        entries.add(new ScheduleEntry(80, "Lockdown", 14000, 23999, true));

    }

    public static boolean advanceSchedule() {
        ScheduleEntry currentPeriod = entries.getFirst();
        if (time > currentPeriod.duration) {
            previousEndTime = currentPeriod.worldEndTime;
            time = 0;
            entries.removeFirst();
            entries.addLast(currentPeriod);
            return true;
        }
        return false;
    }

    public static ScheduleEntry getCurrentPeriod() {
        return entries.getFirst();
    }

    public static class ScheduleEntry {
            public int duration = 0;
            public String name = "";
            public int worldStartTime = 0;
            public int worldEndTime = 0;
            boolean resetDay = false;

            public ScheduleEntry(int duration, String name, int start, int end, boolean inSeconds) {
                this.duration = duration * (inSeconds ? 20 : 1);
                this.name = name;
                worldStartTime = start;
                worldEndTime = end;
            }
            public ScheduleEntry(int duration, String name, int start, int end, boolean inSeconds, boolean resetDay) {
                this.duration = duration * (inSeconds ? 20 : 1);
                this.name = name;
                this.resetDay = resetDay;
                worldStartTime = start;
                worldEndTime = end;
            }
    }
}
