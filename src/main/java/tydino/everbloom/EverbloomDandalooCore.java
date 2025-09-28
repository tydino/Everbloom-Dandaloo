package tydino.everbloom;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.server.network.ServerPlayerEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tydino.everbloom.block.ModBlocks;
import tydino.everbloom.entity.EDCEntities;
import tydino.everbloom.item.EDCItems;
import tydino.everbloom.recipe.EDCRecipes;
import tydino.everbloom.screen.EDCScreenHandler;
import tydino.everbloom.util.EDCLootTableModifiers;
import tydino.everbloom.world.gen.EDCWorldGeneration;

public class EverbloomDandalooCore implements ModInitializer {
	public static final String MOD_ID = "everbloom";
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
		/// player
		ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
			ServerPlayerEntity player = handler.player;

			player.getAttributeInstance(EntityAttributes.MAX_HEALTH).setBaseValue(80);
		});

	}
}