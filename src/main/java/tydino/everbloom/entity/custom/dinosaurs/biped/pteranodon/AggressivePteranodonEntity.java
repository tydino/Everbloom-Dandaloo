package tydino.everbloom.entity.custom.dinosaurs.biped.pteranodon;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import tydino.everbloom.block.ModBlocks;
import tydino.everbloom.entity.custom.dinosaurs.biped.compsognathus.CompsognathusEntity;
import tydino.everbloom.entity.custom.dinosaurs.goals.*;

public class AggressivePteranodonEntity extends PteranodonEntity{
    public AggressivePteranodonEntity(EntityType<? extends PteranodonEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder createPteradonAttributes()
    {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.MAX_HEALTH, 20)
                .add(EntityAttributes.MOVEMENT_SPEED, 0.5f)
                .add(EntityAttributes.FLYING_SPEED, .25f)
                .add(EntityAttributes.ATTACK_DAMAGE, 10)
                .add(EntityAttributes.TEMPT_RANGE, 15);

    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(2, new EscapeDangerGoal(this, 1.1f));
        this.goalSelector.add(3, new MeleeAttackGoal(this, 1.25f, true));
        this.goalSelector.add(4, new FollowParentGoal(this, 1.25F));
        this.goalSelector.add(5, new WanderAroundFarGoal(this, (double)1.0F));
        this.goalSelector.add(6, new LookAtEntityGoal(this, PlayerEntity.class, 10.0F));
        this.goalSelector.add(7, new LookAtEntityGoal(this, CompsognathusEntity.class, 15.0F));
        this.goalSelector.add(8, new LookAroundGoal(this));

        this.targetSelector.add(3, new ActiveTargetGoal(this, PlayerEntity.class, true));
        this.targetSelector.add(4, new ActiveTargetGoal(this, CompsognathusEntity.class, true));

    }

    @Override
    public @Nullable PteranodonEntity createChild(ServerWorld world, PassiveEntity passiveEntity) {return null;}

    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        return ActionResult.FAIL;
    }
}
