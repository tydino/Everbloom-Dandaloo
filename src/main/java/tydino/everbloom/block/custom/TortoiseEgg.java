package tydino.everbloom.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.tag.BlockTags;
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
import tydino.everbloom.entity.ModEntities;
import tydino.everbloom.entity.custom.unsorted.TortoiseEntity;

public class TortoiseEgg extends Block {
    public static final MapCodec<TortoiseEgg> CODEC = createCodec(TortoiseEgg::new);
    public static final IntProperty HATCH;
    private static final int HATCHING_TIME = 6000;
    private static final int BOOSTED_HATCHING_TIME = 3000;
    private static final VoxelShape SHAPE = TortoiseEgg.createCuboidShape(6, 0, 6, 10, 7, 10);

    public MapCodec<TortoiseEgg> getCodec() {
        return CODEC;
    }

    public TortoiseEgg(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState((BlockState)((BlockState)this.stateManager.getDefaultState()).with(HATCH, 0));
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
            TortoiseEntity tortoiseEntity = (TortoiseEntity) ModEntities.TORTOISE.create(world, SpawnReason.BREEDING);
            if (tortoiseEntity != null) {
                Vec3d vec3d = pos.toCenterPos();
                tortoiseEntity.setBaby(true);
                tortoiseEntity.refreshPositionAndAngles(vec3d.getX(), vec3d.getY(), vec3d.getZ(), MathHelper.wrapDegrees(world.random.nextFloat() * 360.0F), 0.0F);
                world.spawnEntity(tortoiseEntity);
            }

        }
    }

    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        boolean bl = isAboveHatchBooster(world, pos);
        if (!world.isClient() && bl) {
            world.syncWorldEvent(3009, pos, 0);
        }

        int i = bl ? BOOSTED_HATCHING_TIME : HATCHING_TIME;
        int j = i / 3;
        world.emitGameEvent(GameEvent.BLOCK_PLACE, pos, GameEvent.Emitter.of(state));
        world.scheduleBlockTick(pos, this, j + world.random.nextInt(300));
    }

    public boolean canPathfindThrough(BlockState state, NavigationType type) {
        return true;
    }

    public static boolean isAboveHatchBooster(BlockView world, BlockPos pos) {
        return world.getBlockState(pos.down()).isIn(BlockTags.SNIFFER_EGG_HATCH_BOOST);
    }

    static {
        HATCH = Properties.HATCH;
    }
}
