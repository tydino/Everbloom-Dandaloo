package tydino.everbloom;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import tydino.everbloom.datagen.*;

public class EverbloomDandalooDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(ModBlockTagProvider::new);
		pack.addProvider(ModItemTagProvider::new);
		pack.addProvider(ModLootTableProvider::new);
		pack.addProvider(ModModelProvider::new);
		pack.addProvider(ModRecipeProvider::new);
	}
}
//file incomplete use https://github.com/Tutorials-By-Kaupenjoe/Fabric-Tutorial-1.21.X/blob/11-datagen/src/main/java/net/kaupenjoe/tutorialmod/TutorialModDataGenerator.java