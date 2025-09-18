package tydino.everbloom.block.dinosaurs;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import tydino.everbloom.entity.custom.dinosaurs.TamableDinosaurEntity;

public class DinosaurEgg extends Block {
    public MapCodec<?> CODEC;
    public static final IntProperty HATCH = Properties.HATCH;
    private int HATCHING_TIME;
    public VoxelShape SHAPE;
    public EntityType<? extends TamableDinosaurEntity> tde;

    public MapCodec<? extends DinosaurEgg> getCodec() {
        return (MapCodec<? extends DinosaurEgg>) CODEC;
    }

    public DinosaurEgg(AbstractBlock.Settings settings, int minX, int minY, int minZ, int maxX, int maxY, int maxZ, int hatchingTime, EntityType<? extends TamableDinosaurEntity> TDE, MapCodec<?> codec) {
        super(settings);
        this.setDefaultState((BlockState)((BlockState)this.stateManager.getDefaultState()).with(HATCH, 0));
        this.SHAPE = DinosaurEgg.createCuboidShape(minX, minY, minZ, maxX, maxY, maxZ);
        this.HATCHING_TIME = hatchingTime;// /3 then *20 for full time in seconds
        this.CODEC = codec;
        this.tde = TDE;
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{HATCH});
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    public int getHatchStage(BlockState state) {
        return (Integer)state.get(HATCH);
    }

    private boolean isReadyToHatch(BlockState state) {
        return this.getHatchStage(state) == 1;
    }

    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!this.isReadyToHatch(state)) {
            world.playSound((PlayerEntity)null, pos, SoundEvents.BLOCK_SNIFFER_EGG_CRACK, SoundCategory.BLOCKS, 0.7F, 0.9F + random.nextFloat() * 0.2F);
            world.setBlockState(pos, (BlockState)state.with(HATCH, this.getHatchStage(state) + 1), 1);
        } else {
            world.playSound((PlayerEntity)null, pos, SoundEvents.BLOCK_SNIFFER_EGG_HATCH, SoundCategory.BLOCKS, 0.7F, 0.9F + random.nextFloat() * 0.2F);
            world.breakBlock(pos, false);
            TamableDinosaurEntity tortoiseEntity =  tde.create(world, SpawnReason.NATURAL);
            if (tortoiseEntity != null) {
                Vec3d vec3d = pos.toCenterPos();
                tortoiseEntity.setBaby(true);
                tortoiseEntity.refreshPositionAndAngles(vec3d.getX(), vec3d.getY(), vec3d.getZ(), MathHelper.wrapDegrees(world.random.nextFloat() * 360.0F), 0.0F);
                world.spawnEntity(tortoiseEntity);
            }

        }
    }

    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        int j = HATCHING_TIME / 3;
        world.emitGameEvent(GameEvent.BLOCK_PLACE, pos, GameEvent.Emitter.of(state));
        world.scheduleBlockTick(pos, this, j + world.random.nextInt(300));
    }

    public boolean canPathfindThrough(BlockState state, NavigationType type) {
        return true;
    }
}
