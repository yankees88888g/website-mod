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
        this.title = advancement.getDisplay().getTitle().getString();
        this.des = advancement.getDisplay().getDescription().getString();
        this.icon = advancement.getDisplay().getIcon();
        this.id = advancement.getId().toString();
        this.done = done;
    }
}
