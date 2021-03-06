package net.web.fabric.inv.view;

import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class Gui {

    public static LinkedHashMap<String, Gui> gui = new LinkedHashMap<>();
    public List<ItemStack> slots;

    public String name;

    public Gui(String name) {
        this.name = name;
        this.slots = new ArrayList<>();
    }

    public void addSlot(int i, ItemStack slot) {
        this.slots.add(i, slot);
    }

    public void register() {
        gui.put(this.name, this);
    }

    public static Gui getInv(String player) {
        return gui.get(player);
    }
}