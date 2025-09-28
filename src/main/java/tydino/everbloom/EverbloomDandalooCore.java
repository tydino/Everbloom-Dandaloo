package tydino.everbloom;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.server.network.ServerPlayerEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tydino.everbloom.block.ModBlocks;
import tydino.everbloom.entity.ModEntities;
import tydino.everbloom.item.ModItems;
import tydino.everbloom.recipe.ModRecipes;
import tydino.everbloom.screen.ModScreenHandler;
import tydino.everbloom.util.ModLootTableModifiers;
import tydino.everbloom.world.gen.ModWorldGeneration;

import java.util.UUID;

public class EverbloomDandalooCore implements ModInitializer {
	public static final String MOD_ID = "everbloom";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("applying everbloom dandaloo content to minecraft");

		ModBlocks.registerModBlocks();
		ModItems.registerModItems();

		ModWorldGeneration.generateWorldGen();

		ModEntities.registerModEntities();

		ModLootTableModifiers.modifyLootTables();

		ModScreenHandler.registerScreenHandlers();

		ModRecipes.registerRecipes();
		/// player
		ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
			ServerPlayerEntity player = handler.player;

			player.getAttributeInstance(EntityAttributes.MAX_HEALTH).setBaseValue(80);
		});

	}
}