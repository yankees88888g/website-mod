package net.web.fabric;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.web.fabric.config.File;
import net.web.fabric.mod.menu.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class WebMain implements ModInitializer {

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("modid");

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		LOGGER.info(strArrayToStr(List.name()));
		LOGGER.info(String.valueOf(List.count()));
		LOGGER.info("Hello Fabric world!");
		LOGGER.info(String.valueOf(List.name().length));
		LOGGER.info(String.valueOf(FabricLoader.getInstance().getAllMods()));
		File.main();
	}

	private String strArrayToStr(String[] array) {
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < array.length; i++) {
			sb.append(array[i]);
		}
		String str = sb.toString();
		return str;
	}
}
