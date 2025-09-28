package tydino.everbloom;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;
import tydino.everbloom.datagen.*;
import tydino.everbloom.world.EDCConfiguredFeatures;
import tydino.everbloom.world.EDCPlacedFeatures;

public class EverbloomDandalooDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(EDCBlockTagProvider::new);
		pack.addProvider(EDCItemTagProvider::new);
		pack.addProvider(EDCLootTableProvider::new);
		pack.addProvider(EDCModelProvider::new);
		pack.addProvider(EDCRecipeProvider::new);
		pack.addProvider(EDCRegistryDataGenerator::new);
	}

	@Override
	public void buildRegistry(RegistryBuilder registryBuilder) {

		registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, EDCConfiguredFeatures::bootstrap);
		registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, EDCPlacedFeatures::bootstrap);
	}
}