package net.web.fabric.achievements;

import net.minecraft.advancement.Advancement;
import net.minecraft.item.ItemStack;

public class AchData {

    public String title;
    public String des;
    public ItemStack icon;
    public String id;
    public boolean done;

    public AchData(Advancement advancement, boolean done){
        this.title = advancement.display().get().getTitle().getString();
        this.des = advancement.display().get().getDescription().getString();
        this.icon = advancement.display().get().getIcon();
        this.id = advancement.display().toString();
        this.done = done;
    }
}
