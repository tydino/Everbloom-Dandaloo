package tydino.everbloom.entity.dinosaurs;

import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.control.FlightMoveControl;
import net.minecraft.entity.ai.pathing.BirdNavigation;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.passive.ParrotEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.item.Item;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class FlyingTamableDinosaurEntity extends TamableDinosaurEntity{
    public float flapSpeed;
    public final AnimationState flyAnimationState = new AnimationState();
    public int flyAnimationTimeout = 0;
    public int flyingTime;
    public int MAX_FLYING_TIME = 1200; //60 seconds for being in the air for reference
    public int tiredCooldown;
    public int TIRED_COOLDOWN_TIME;
    int minTired;
    int maxTired;

    protected FlyingTamableDinosaurEntity(EntityType<? extends TameableEntity> entityType, World world, Item scarab, int maxPitchChange, float flapSpeed, int maxTimeInAir, int minTiredCooldown, int maxTiredCooldown) {
        super(entityType, world, scarab);
        this.moveControl = new FlightMoveControl(this, maxPitchChange, true);
        this.flapSpeed = flapSpeed;
        this.flyingTime = 0;
        this.MAX_FLYING_TIME = maxTimeInAir;
        this.minTired = minTiredCooldown;
        this.maxTired = maxTiredCooldown;
        this.TIRED_COOLDOWN_TIME = RandomValueBetween(maxTired, minTired);
    }

    public int RandomValueBetween(int one, int two){
        return this.getRandom().nextInt(one-two+1)+two;
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
        this.flapWings();
        this.incrementFlyingTime();
        this.incrementTiredTime();
    }

    public void flapWings(){
        Vec3d vec3d = this.getVelocity();
        if (!this.isOnGround() && vec3d.y < (double)0.0F) {
            this.setVelocity(vec3d.multiply((double)1.0F, flapSpeed, (double)1.0F));
        }
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putInt("FlyingTime", this.flyingTime);
        nbt.putInt("TiredTime", this.tiredCooldown);
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.flyingTime = nbt.getInt("FlyingTime");
        this.tiredCooldown = nbt.getInt("TiredTime");
    }

    public void resetFlyingTime(){
        this.flyingTime = 0;
    }

    public void incrementFlyingTime(){
        if(!this.isOnGround() && !this.hasVehicle()){
            this.flyingTime++;
            resetTiredTime();
        }else{
            resetFlyingTime();
        }
    }

    public void resetTiredTime(){
        this.tiredCooldown = 0;
    }

    public void incrementTiredTime(){
        if(this.isOnGround()){
            tiredCooldown++;
        }
    }

    public int getFlyingTime(){
        return flyingTime;
    }
}
