package tydino.everbloom;

import net.fabricmc.api.ModInitializer;

import net.kyrptonaught.customportalapi.api.CustomPortalBuilder;
import net.minecraft.block.BeehiveBlock;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BeehiveBlockEntity;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import team.reborn.energy.api.EnergyStorage;
import tydino.everbloom.block.ModBlocks;
import tydino.everbloom.block.entity.ModBlockEntities;
import tydino.everbloom.block.power.entity.ItemCompressorTierOneEntity;
import tydino.everbloom.block.power.entity.PowerHectogonTierOneEntity;
import tydino.everbloom.block.power.entity.PowerStorageTierOneEntity;
import tydino.everbloom.block.power.entity.SolarPanelTierOneEntity;
import tydino.everbloom.entity.ModEntities;
import tydino.everbloom.item.ModItemGroups;
import tydino.everbloom.item.ModItems;
import tydino.everbloom.recipe.ModRecipes;
import tydino.everbloom.screen.ModScreenHandler;
import tydino.everbloom.util.ModLootTableModifiers;
import tydino.everbloom.world.gen.ModWorldGeneration;

//todo:
//add a burrowing mob that works like a bees (toad) that drops slime
//add a craftable book(set) that can allow for easier implementation of how to get most things.
//make the power storage block actually store power when broken so it can be moved from one place to another while keeping its energy.

//C:\Users\tydin\Downloads\1.21.7\assets\minecraft\textures\item
//https://github.com/TechReborn/Energy use for energistic reference
//use if stumped on energy https://youtu.be/N3Abbn9rV6M
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

		//power block initialization
		LOGGER.info("registering powered blocks");

		//power hectogons
		EnergyStorage.SIDED.registerForBlockEntity(PowerHectogonTierOneEntity::getEnergyProvider, ModBlockEntities.POWER_HECTOGON_TIER_ONE_BE);

		//solar panels
		EnergyStorage.SIDED.registerForBlockEntity(SolarPanelTierOneEntity::getEnergyProvider, ModBlockEntities.SOLAR_PANEL_TIER_ONE_BE);

		//power storages
		EnergyStorage.SIDED.registerForBlockEntity(PowerStorageTierOneEntity::getEnergyProvider, ModBlockEntities.POWER_STORAGE_TIER_ONE_BE);

		//power users
		EnergyStorage.SIDED.registerForBlockEntity(ItemCompressorTierOneEntity::getEnergyProvider, ModBlockEntities.ITEM_COMPRESSOR_TIER_ONE_BE);
	}
}
//https://www.youtube.com/watch?v=5a4DAkWW3JQ try

//https://www.google.com/search?client=opera-gx&q=in+fabricmc+how+to+do+a+nonspedable+in+a+crafting+table+like+that+of+a+bucket+or+glass+bottle&sourceid=opera&ie=UTF-8&oe=UTF-8

//https://www.google.com/search?q=how+doi+implement+the+bigger+crafting+tables+mod+into+my+fabric+mod+to+make+bigger+crafting&client=opera-gx&hs=3Va&sca_esv=8fe472cf0c0bad30&sxsrf=AE3TifNXq7OMM0FVsirq0LfBw6n0B_HrMg%3A1751912179084&ei=8w5saOH0BIHEp84P8Iy-uAs&ved=0ahUKEwihuvK4rauOAxUB4skDHXCGD7cQ4dUDCBE&uact=5&oq=how+doi+implement+the+bigger+crafting+tables+mod+into+my+fabric+mod+to+make+bigger+crafting&gs_lp=Egxnd3Mtd2l6LXNlcnAiW2hvdyBkb2kgaW1wbGVtZW50IHRoZSBiaWdnZXIgY3JhZnRpbmcgdGFibGVzIG1vZCBpbnRvIG15IGZhYnJpYyBtb2QgdG8gbWFrZSBiaWdnZXIgY3JhZnRpbmdIpRNQigpY7w9wAngBkAEAmAGLAaAB7AOqAQMwLjS4AQPIAQD4AQGYAgKgAg7CAgoQABiwAxjWBBhHmAMAiAYBkAYIkgcBMqAHogiyBwC4BwDCBwMyLTLIBws&sclient=gws-wiz-serp