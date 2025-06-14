package tydino.everbloom;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tydino.everbloom.block.ModBlocks;
import tydino.everbloom.item.ModItemGroups;
import tydino.everbloom.item.ModItems;

public class EverbloomDandaloo implements ModInitializer {
	public static final String MOD_ID = "everbloom";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("applying everbloom dandaloo content to minecraft");

		ModBlocks.registerModBlocks();
		ModItems.registerModItems();

		ModItemGroups.registerItemGroups();
	}
}
//Caused by: java.lang.IllegalStateException: This registry can't create intrusive holders error comes from an illegal register, try and find origin