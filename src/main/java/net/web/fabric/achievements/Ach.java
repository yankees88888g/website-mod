package net.web.fabric.achievements;

import net.minecraft.advancement.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class Ach {
    public static LinkedHashMap<String, Ach> ach = new LinkedHashMap<>();
    public List<AchData> data = new ArrayList<>();
    public String name;

    public Ach(String name) {
        this.name = name;
    }

    public void addAch(AchData achData) {
        this.data.add(achData);
    }

    public void register() {
        ach.put(this.name, this);
    }

    public static Ach getAch(String player) {
        return ach.get(player);
    }
}
