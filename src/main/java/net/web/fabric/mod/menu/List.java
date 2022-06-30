package net.web.fabric.mod.menu;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.metadata.ModMetadata;

import java.util.ArrayList;

public class List {
    public static String[] main() {

        String list = String.valueOf(FabricLoader.getInstance().getAllMods().stream().toList());
        String[] array = list.split(",");
        return array;
    }

    public static int count() {
        int counter = 0;
        String list = String.valueOf(FabricLoader.getInstance().getAllMods().stream().toList());
        String[] array = list.split(",");
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null)
                counter++;
        }
        return counter-33;
    }

    public static String[] desc(){
        ArrayList<String> des = new ArrayList<String>();
        for(int i =0; i < FabricLoader.getInstance().getAllMods().stream().toList().size(); i++) {
            if (FabricLoader.getInstance().getAllMods().stream().toList().get(i) != null)
                des.add(FabricLoader.getInstance().getAllMods().stream().toList().get(i).getMetadata().getDescription());
        }
        StringBuilder sb = new StringBuilder();
        for (String s : des)
        {
            sb.append(s);
            sb.append("⣆⭐⦩≽");//random unicodes are used to prevent cutting of mods using regular spacers from getting cut off.
        }
        return sb.toString().split("⣆⭐⦩≽");
    }
    public static String[] name(){
        ArrayList<String> des = new ArrayList<String>();
        for(int i =0; i < FabricLoader.getInstance().getAllMods().stream().toList().size(); i++) {
            if (FabricLoader.getInstance().getAllMods().stream().toList().get(i) != null)
                des.add(FabricLoader.getInstance().getAllMods().stream().toList().get(i).getMetadata().getName());
        }
        StringBuilder sb = new StringBuilder();
        for (String s : des)
        {
            sb.append(s);
            sb.append("⣆⭐⦩≽");//random unicodes are used to prevent cutting of mods using regular spacers from getting cut off.
        }
        return sb.toString().split("⣆⭐⦩≽");
    }
}
