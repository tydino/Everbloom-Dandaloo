package tydino.everbloom.block.power.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import team.reborn.energy.api.base.SimpleEnergyStorage;
import tydino.everbloom.block.entity.ModBlockEntities;

public class PowerStorageTierOneEntity extends BlockEntity {
    public PowerStorageTierOneEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.POWER_STORAGE_TIER_ONE_BE, pos, state);
    }

    //power
    public final SimpleEnergyStorage energyStorage = new SimpleEnergyStorage(100_000, 100, 100) {
        @Override
        protected void onFinalCommit() {
            super.onFinalCommit();

            markDirty();
        }
    };

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);
        if (nbt.contains("Energy", NbtElement.LONG_TYPE)) {
            this.energyStorage.amount = nbt.getLong("Energy");
        }
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        nbt.putLong("Energy", this.energyStorage.amount);
    }

    public SimpleEnergyStorage getEnergyStorage() {
        return this.energyStorage;
    }

    public SimpleEnergyStorage getEnergyProvider(Direction direction) {
        return this.energyStorage;
    }
}
