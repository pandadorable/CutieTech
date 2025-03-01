package dev.pandadorable.cutietech.block.entity;

import dev.pandadorable.cutietech.block.custom.SprinklerBlock;
import dev.pandadorable.cutietech.particle.CTParticles;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

public class SprinklerBlockEntity extends BlockEntity implements GeoBlockEntity {
    private AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    private static final RawAnimation OPEN_ANIM =
            RawAnimation.begin().thenPlay("sprinkler.open").thenLoop("sprinkler.work");
    private static final RawAnimation CLOSE_ANIM =
            RawAnimation.begin().thenPlayAndHold("sprinkler.close");


    public SprinklerBlockEntity(BlockPos pos, BlockState state) {
        super(CTBlockEntites.SPRINKLER_BLOCK_ENTITY.get(), pos, state);
    }


    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        AnimationController<SprinklerBlockEntity> controller = new AnimationController<>(this, state -> {
            BlockState blockState = level.getBlockState(worldPosition);
            if(blockState.hasProperty(SprinklerBlock.TOGGLE_SPRINKLER)) {
                if (!blockState.getValue(SprinklerBlock.TOGGLE_SPRINKLER)) {
                    return state.setAndContinue(CLOSE_ANIM);
                } else {
                    return state.setAndContinue(OPEN_ANIM);
                }
            }
            return PlayState.STOP;
        });

        controllers.add(controller);
    }

    @Override
    public void triggerAnim(@Nullable String controllerName, String animName) {
        GeoBlockEntity.super.triggerAnim(controllerName, animName);
        System.out.println("a "+controllerName+" b "+animName);
//        if("sprinkler_particles".equals(animName)){
//            this.spawnParticles();
//        }
    }

    @Override
    public boolean triggerEvent(int id, int type) {
        return super.triggerEvent(id, type);
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    // 🎇 Génération des particules en fonction de l'animation
    public void spawnParticles() {
        System.out.println("OwO");
        if (level instanceof ClientLevel clientLevel) {
            System.out.println("UwU");
            for (int i = 0; i < 10; i++) {
                double angle = Math.toRadians(i * 36); // Dispersion circulaire
                double xOffset = Math.cos(angle) * 0.5;
                double zOffset = Math.sin(angle) * 0.5;
                double yOffset = 0.2;

                clientLevel.addParticle(CTParticles.BUBBLE_PARTICLES.get(),
                        worldPosition.getX() + 0.5 + xOffset,
                        worldPosition.getY() + yOffset,
                        worldPosition.getZ() + 0.5 + zOffset,
                        0, 0, 0);
            }
        }
    }

}
