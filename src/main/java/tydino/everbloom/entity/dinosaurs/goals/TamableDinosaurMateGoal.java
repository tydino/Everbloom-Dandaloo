package tydino.everbloom.entity.dinosaurs.goals;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.ai.goal.AnimalMateGoal;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.GameRules;
import tydino.everbloom.entity.dinosaurs.TamableDinosaurEntity;

public class TamableDinosaurMateGoal extends AnimalMateGoal {
    private final TamableDinosaurEntity entity;

    public TamableDinosaurMateGoal(TamableDinosaurEntity entity, double speed) {
        super(entity, speed);
        this.entity = entity;
    }

    public boolean canStart() {
        return super.canStart() && !this.entity.hasEgg() && entity.isTamed() && this.entity.whatAge() >= this.entity.MaxAge - 1;
    }

    protected void breed() {
        ServerPlayerEntity serverPlayerEntity = this.animal.getLovingPlayer();
        if (serverPlayerEntity == null && this.mate.getLovingPlayer() != null) {
            serverPlayerEntity = this.mate.getLovingPlayer();
        }

        if (serverPlayerEntity != null) {
            serverPlayerEntity.incrementStat(Stats.ANIMALS_BRED);
            Criteria.BRED_ANIMALS.trigger(serverPlayerEntity, this.animal, this.mate, (PassiveEntity)null);
        }

        this.entity.setHasEgg(true);
        this.animal.setBreedingAge(6000);
        this.mate.setBreedingAge(6000);
        this.animal.resetLoveTicks();
        this.mate.resetLoveTicks();
        Random random = this.animal.getRandom();
        if (castToServerWorld(this.world).getGameRules().getBoolean(GameRules.DO_MOB_LOOT)) {
            this.world.spawnEntity(new ExperienceOrbEntity(this.world, this.animal.getX(), this.animal.getY(), this.animal.getZ(), random.nextInt(10) + 1));
        }

    }
}
