package net.myriantics.weve_got_beef;

import net.fabricmc.api.ModInitializer;

import net.minecraft.resources.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WeveGotBeef implements ModInitializer {
	public static final String MOD_ID = "weve-got-beef";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("We've Got Beef has loaded!");
	}

	public static Identifier locate(String name) {
		return Identifier.fromNamespaceAndPath(MOD_ID, name);
	}
}