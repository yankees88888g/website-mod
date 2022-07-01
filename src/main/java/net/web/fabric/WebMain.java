package net.web.fabric;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.web.fabric.config.File;
import net.web.fabric.http.website.login.cred.Encryption;
import net.web.fabric.mod.menu.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.lang.String.valueOf;


public class WebMain implements ModInitializer {

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("website-mod");

	@Override
	public void onInitialize() {
		//Encryption.write("test","twe");
		LOGGER.info(strArrayToStr(List.name()));
		LOGGER.info(valueOf(List.count()));
		LOGGER.info("Hello Fabric world!");
		LOGGER.info(valueOf(List.name().length));
		LOGGER.info(valueOf(FabricLoader.getInstance().getAllMods()));
		File.main();
		//LOGGER.info(valueOf(Encryption.read("test","twe")));
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
