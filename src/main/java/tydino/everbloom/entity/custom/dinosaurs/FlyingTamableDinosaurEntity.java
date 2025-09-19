package tydino.everbloom.entity.custom.dinosaurs;

import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.control.FlightMoveControl;
import net.minecraft.entity.ai.pathing.BirdNavigation;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.item.Item;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class FlyingTamableDinosaurEntity extends TamableDinosaurEntity{

    public float flapProgress;
    public float maxWingDeviation;
    public float prevMaxWingDeviation;
    public float prevFlapProgress;
    private float flapSpeed;
    public final AnimationState flyAnimationState = new AnimationState();
    public int flyAnimationTimeout = 0;

    protected FlyingTamableDinosaurEntity(EntityType<? extends TameableEntity> entityType, World world, Item scarab, int maxPitchChange, float flapSpeed) {
        super(entityType, world, scarab);
        this.moveControl = new FlightMoveControl(this, maxPitchChange, false);
        this.flapSpeed = flapSpeed;
    }

    protected EntityNavigation createNavigation(World world) {
        BirdNavigation birdNavigation = new BirdNavigation(this, world);
        birdNavigation.setCanPathThroughDoors(false);
        birdNavigation.setCanSwim(true);
        return birdNavigation;
    }

    @Override
    public void tickMovement() {
        super.tickMovement();
        if(!isSittingDownNow()) {
            this.flapWings();
        }
    }

    public void flapWings(){
        this.prevFlapProgress = this.flapProgress;
        this.prevMaxWingDeviation = this.maxWingDeviation;
        this.maxWingDeviation += (float)(!this.isOnGround() && !this.hasVehicle() ? 4 : -1) * 0.3F;
        this.maxWingDeviation = MathHelper.clamp(this.maxWingDeviation, 0.0F, 1.0F);
        if (!this.isOnGround() && this.flapSpeed < 1.0F) {
            this.flapSpeed = 1.0F;
            if(flyAnimationTimeout<=0) {
                flyAnimationState.start(this.age);
                flyAnimationTimeout = 20;
            }else{
                flyAnimationTimeout--;
            }
        }

        this.flapSpeed *= 0.9F;
        Vec3d vec3d = this.getVelocity();
        if (!this.isOnGround() && vec3d.y < (double)0.0F) {
            this.setVelocity(vec3d.multiply((double)1.0F, 0.6, (double)1.0F));
        }

        this.flapProgress += this.flapSpeed * 2.0F;
    }
}
