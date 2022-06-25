package net.web.fabric;

import net.fabricmc.api.ModInitializer;
import net.web.fabric.config.file;
//import net.web.fabric.http.website.htl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;



public class main implements ModInitializer {
	public static byte[] mainHtmlFile;
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("modid");

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		LOGGER.info("Hello Fabric world!");
		InputStream is = this.getClass().getClassLoader().getResourceAsStream("html/main.html");
		try {
			mainHtmlFile = is.readAllBytes();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		file.main();
	}
}
