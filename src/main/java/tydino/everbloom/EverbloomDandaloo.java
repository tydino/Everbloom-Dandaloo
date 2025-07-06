package tydino.everbloom;

import net.fabricmc.api.ModInitializer;

import net.kyrptonaught.customportalapi.api.CustomPortalBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tydino.everbloom.block.ModBlocks;
import tydino.everbloom.block.entity.ModBlockEntities;
import tydino.everbloom.entity.ModEntities;
import tydino.everbloom.item.ModItemGroups;
import tydino.everbloom.item.ModItems;
import tydino.everbloom.recipe.ModRecipes;
import tydino.everbloom.screen.ModScreenHandler;
import tydino.everbloom.util.ModLootTableModifiers;
import tydino.everbloom.world.gen.ModWorldGeneration;

//https://github.com/Tutorials-By-Kaupenjoe/Fabric-Tutorial-1.21.X/tree/62-Update1.21.4
public class EverbloomDandaloo implements ModInitializer {
	public static final String MOD_ID = "everbloom";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("applying everbloom dandaloo content to minecraft");

		ModItemGroups.registerItemGroups();

		ModBlocks.registerModBlocks();
		ModItems.registerModItems();

		ModWorldGeneration.generateWorldGen();

		ModEntities.registerModEntities();

		ModLootTableModifiers.modifyLootTables();

		ModBlockEntities.registerBlockEntities();
		ModScreenHandler.registerScreenHandlers();

		ModRecipes.registerRecipes();

		//portals https://github.com/kyrptonaught/customportalapi?tab=readme-ov-file
		//remember to use misode's dimension and biomejson builders!
		LOGGER.info("registering everbloom's dimensions");
		//aether /execute in everbloom:aether run tp @s 0 100 0
		CustomPortalBuilder.beginPortal()
				.frameBlock(Blocks.GLOWSTONE)
				.lightWithFluid(Fluids.WATER)
				.destDimID(Identifier.of("everbloom", "aether"))
				.tintColor(0x02b3ff)
				.registerPortal();
	}
}