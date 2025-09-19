package tydino.everbloom.entity.custom.dinosaurs.scarab;

import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.world.World;

public class BronzeScarabEntity extends ScarabBaseEntity{

    public BronzeScarabEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder createBronzeScarabAttributes(){
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.MAX_HEALTH, 10)
                .add(EntityAttributes.ATTACK_DAMAGE, 4)
                .add(EntityAttributes.MOVEMENT_SPEED, .15f);
    }
}
