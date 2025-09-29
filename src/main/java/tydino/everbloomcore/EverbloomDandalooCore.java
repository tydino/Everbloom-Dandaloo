package tydino.everbloomcore;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.server.network.ServerPlayerEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tydino.everbloomcore.block.ModBlocks;
import tydino.everbloomcore.entity.EDCEntities;
import tydino.everbloomcore.item.EDCItemGroup;
import tydino.everbloomcore.item.EDCItems;
import tydino.everbloomcore.recipe.EDCRecipes;
import tydino.everbloomcore.screen.EDCScreenHandler;
import tydino.everbloomcore.util.EDCLootTableModifiers;
import tydino.everbloomcore.world.gen.EDCWorldGeneration;

//use if using reborn https://github.com/TechReborn/TechReborn

public class EverbloomDandalooCore implements ModInitializer {
	public static final String MOD_ID = "everbloom_core";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("applying everbloom dandaloo content to minecraft");

		ModBlocks.registerModBlocks();
		EDCItems.registerModItems();

		EDCWorldGeneration.generateWorldGen();

		EDCEntities.registerModEntities();

		EDCLootTableModifiers.modifyLootTables();

		EDCScreenHandler.registerScreenHandlers();

		EDCRecipes.registerRecipes();

		EDCItemGroup.registerItemGroup();
		/// player
		ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
			ServerPlayerEntity player = handler.player;

			player.getAttributeInstance(EntityAttributes.MAX_HEALTH).setBaseValue(80);
		});

	}
}