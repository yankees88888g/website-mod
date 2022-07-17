package net.web.fabric.achievements;

import net.minecraft.advancement.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class Ach {
    public static LinkedHashMap<String, Ach> ach = new LinkedHashMap<>();
    public List<Advancement> achs;
    public List<String> ids;
    public List<Boolean> done;
    public String name;

    public Ach(String name) {
        this.name = name;
        this.achs = new ArrayList<>();
    }

    public void addAch(int i, Advancement achi, boolean done, String id) {
        this.achs.add(i, achi);
        this.done.add(i, done);
        this.ids.add(i, id);
    }

    public void register() {
        ach.put(this.name, this);
    }

    public static Ach getAch(String player) {
        return ach.get(player);
    }
}
