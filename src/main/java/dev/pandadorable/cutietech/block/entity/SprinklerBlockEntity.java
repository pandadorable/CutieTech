package dev.pandadorable.cutietech.block.entity;

import dev.pandadorable.cutietech.ClientUtils;
import dev.pandadorable.cutietech.block.custom.SprinklerBlock;
import dev.pandadorable.cutietech.particle.CTParticles;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.concurrent.TimeUnit;


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

        controller.setCustomInstructionKeyframeHandler(state -> {
            spawnParticles();
        });

        controllers.add(controller);
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    // 🎇 Génération des particules en fonction de l'animation
    public void spawnParticles() {
        if (level instanceof ClientLevel clientLevel) {
            switch(this.getBlockState().getValue(SprinklerBlock.FACING)) {
                case UP :
                    for(int i = 0 ; i < 21 ; i++){

                        double finalXpos = 0.5;
                        double finalYpos = 0.3;
                        double finalZpos = 0.5;

                        double finalZOffset = i/100f; // go from 0 to 0.2
                        double finalXOffset = Math.sqrt((20.1*20.1)-(i*i))/100f; // go from 0.2 to 0


                        ClientUtils.runLater(() -> {
                            clientLevel.addParticle(CTParticles.BUBBLE_PARTICLES.get(),
                                    worldPosition.getX() + finalXpos + finalXOffset,
                                    worldPosition.getY() + finalYpos,
                                    worldPosition.getZ() + finalZpos + finalZOffset,
                                    finalXOffset*1.25f,
                                    0.4f,
                                    finalZOffset*1.25f);

                            clientLevel.addParticle(CTParticles.BUBBLE_PARTICLES.get(),
                                    worldPosition.getX() + finalXpos - finalXOffset,
                                    worldPosition.getY() + finalYpos,
                                    worldPosition.getZ() + finalZpos - finalZOffset,
                                    -finalXOffset*1.25f,
                                    0.4f,
                                    -finalZOffset*1.25f);

                            clientLevel.addParticle(CTParticles.BUBBLE_PARTICLES.get(),
                                    worldPosition.getX() + finalXpos + finalZOffset,
                                    worldPosition.getY() + finalYpos,
                                    worldPosition.getZ() + finalZpos - finalXOffset,
                                    finalZOffset*1.25f,
                                    0.4f,
                                    -finalXOffset*1.25f);

                            clientLevel.addParticle(CTParticles.BUBBLE_PARTICLES.get(),
                                    worldPosition.getX() + finalXpos - finalZOffset,
                                    worldPosition.getY() + finalYpos,
                                    worldPosition.getZ() + finalZpos + finalXOffset,
                                    -finalZOffset*1.25f,
                                    0.4f,
                                    finalXOffset*1.25f);


                        }, 50*i, TimeUnit.MILLISECONDS);
                    }
                    break;
                case DOWN:
                    for(int i = 0 ; i < 21 ; i++){

                        double finalXpos = 0.5;
                        double finalYpos = 0.7;
                        double finalZpos = 0.5;

                        double finalZOffset = i/100f; // go from 0 to 0.2
                        double finalXOffset = Math.sqrt((20.1*20.1)-(i*i))/100f; // go from 0.2 to 0


                        ClientUtils.runLater(() -> {
                            clientLevel.addParticle(CTParticles.BUBBLE_PARTICLES.get(),
                                    worldPosition.getX() + finalXpos + finalZOffset,
                                    worldPosition.getY() + finalYpos,
                                    worldPosition.getZ() + finalZpos + finalXOffset,
                                    finalZOffset*1.25f,
                                    0,
                                    finalXOffset*1.25f);

                            clientLevel.addParticle(CTParticles.BUBBLE_PARTICLES.get(),
                                    worldPosition.getX() + finalXpos - finalZOffset,
                                    worldPosition.getY() + finalYpos,
                                    worldPosition.getZ() + finalZpos - finalXOffset,
                                    -finalZOffset*1.25f,
                                    0,
                                    -finalXOffset*1.25f);

                            clientLevel.addParticle(CTParticles.BUBBLE_PARTICLES.get(),
                                    worldPosition.getX() + finalXpos + finalXOffset,
                                    worldPosition.getY() + finalYpos,
                                    worldPosition.getZ() + finalZpos - finalZOffset,
                                    finalXOffset*1.25f,
                                    0,
                                    -finalZOffset*1.25f);

                            clientLevel.addParticle(CTParticles.BUBBLE_PARTICLES.get(),
                                    worldPosition.getX() + finalXpos - finalXOffset,
                                    worldPosition.getY() + finalYpos,
                                    worldPosition.getZ() + finalZpos + finalZOffset,
                                    -finalXOffset*1.25f,
                                    0,
                                    finalZOffset*1.25f);


                        }, 50*i, TimeUnit.MILLISECONDS);
                    }
                    break;
                case SOUTH:
                    for(int i = 0 ; i < 21 ; i++){

                        double finalXpos = 0.5;
                        double finalYpos = 0.5;
                        double finalZpos = 0.3;

                        double finalZOffset = i/100f; // go from 0 to 0.2
                        double finalXOffset = Math.sqrt((20.1*20.1)-(i*i))/100f; // go from 0.2 to 0


                        ClientUtils.runLater(() -> {
                            clientLevel.addParticle(CTParticles.BUBBLE_PARTICLES.get(),
                                    worldPosition.getX() + finalXpos + finalZOffset,
                                    worldPosition.getY() + finalYpos + finalXOffset,
                                    worldPosition.getZ() + finalZpos,
                                    finalZOffset*1.25f,
                                    finalXOffset*1.25f,
                                    0.4f);

                            clientLevel.addParticle(CTParticles.BUBBLE_PARTICLES.get(),
                                    worldPosition.getX() + finalXpos - finalZOffset,
                                    worldPosition.getY() + finalYpos - finalXOffset,
                                    worldPosition.getZ() + finalZpos,
                                    -finalZOffset*1.25f,
                                    -finalXOffset*1.25f,
                                    0.4f);

                            clientLevel.addParticle(CTParticles.BUBBLE_PARTICLES.get(),
                                    worldPosition.getX() + finalXpos + finalXOffset,
                                    worldPosition.getY() + finalYpos - finalZOffset,
                                    worldPosition.getZ() + finalZpos,
                                    finalXOffset*1.25f,
                                    -finalZOffset*1.25f,
                                    0.4f);

                            clientLevel.addParticle(CTParticles.BUBBLE_PARTICLES.get(),
                                    worldPosition.getX() + finalXpos - finalXOffset,
                                    worldPosition.getY() + finalYpos + finalZOffset,
                                    worldPosition.getZ() + finalZpos,
                                    -finalXOffset*1.25f,
                                    finalZOffset*1.25f,
                                    0.4f);


                        }, 50*i, TimeUnit.MILLISECONDS);
                    }
                    break;
                case NORTH:
                    for(int i = 0 ; i < 21 ; i++){

                        double finalXpos = 0.5;
                        double finalYpos = 0.5;
                        double finalZpos = 0.7;

                        double finalZOffset = i/100f; // go from 0 to 0.2
                        double finalXOffset = Math.sqrt((20.1*20.1)-(i*i))/100f; // go from 0.2 to 0


                        ClientUtils.runLater(() -> {
                            clientLevel.addParticle(CTParticles.BUBBLE_PARTICLES.get(),
                                    worldPosition.getX() + finalXpos + finalXOffset,
                                    worldPosition.getY() + finalYpos + finalZOffset,
                                    worldPosition.getZ() + finalZpos,
                                    finalXOffset*1.25f,
                                    finalZOffset*1.25f,
                                    -0.4f);

                            clientLevel.addParticle(CTParticles.BUBBLE_PARTICLES.get(),
                                    worldPosition.getX() + finalXpos - finalXOffset,
                                    worldPosition.getY() + finalYpos - finalZOffset,
                                    worldPosition.getZ() + finalZpos,
                                    -finalXOffset*1.25f,
                                    -finalZOffset*1.25f,
                                    -0.4f);

                            clientLevel.addParticle(CTParticles.BUBBLE_PARTICLES.get(),
                                    worldPosition.getX() + finalXpos + finalZOffset,
                                    worldPosition.getY() + finalYpos - finalXOffset,
                                    worldPosition.getZ() + finalZpos,
                                    finalZOffset*1.25f,
                                    -finalXOffset*1.25f,
                                    -0.4f);

                            clientLevel.addParticle(CTParticles.BUBBLE_PARTICLES.get(),
                                    worldPosition.getX() + finalXpos - finalZOffset,
                                    worldPosition.getY() + finalYpos + finalXOffset,
                                    worldPosition.getZ() + finalZpos,
                                    -finalZOffset*1.25f,
                                    finalXOffset*1.25f,
                                    -0.4f);


                        }, 50*i, TimeUnit.MILLISECONDS);
                    }
                    break;
                case EAST:
                    for(int i = 0 ; i < 21 ; i++){

                        double finalXpos = 0.3;
                        double finalYpos = 0.5;
                        double finalZpos = 0.5;

                        double finalZOffset = i/100f; // go from 0 to 0.2
                        double finalXOffset = Math.sqrt((20.1*20.1)-(i*i))/100f; // go from 0.2 to 0


                        ClientUtils.runLater(() -> {
                            clientLevel.addParticle(CTParticles.BUBBLE_PARTICLES.get(),
                                    worldPosition.getX() + finalXpos,
                                    worldPosition.getY() + finalYpos + finalZOffset,
                                    worldPosition.getZ() + finalZpos + finalXOffset,
                                    0.4f,
                                    finalZOffset*1.25f,
                                    finalXOffset*1.25f);

                            clientLevel.addParticle(CTParticles.BUBBLE_PARTICLES.get(),
                                    worldPosition.getX() + finalXpos,
                                    worldPosition.getY() + finalYpos - finalZOffset,
                                    worldPosition.getZ() + finalZpos - finalXOffset,
                                    0.4f,
                                    -finalZOffset*1.25f,
                                    -finalXOffset*1.25f);

                            clientLevel.addParticle(CTParticles.BUBBLE_PARTICLES.get(),
                                    worldPosition.getX() + finalXpos,
                                    worldPosition.getY() + finalYpos + finalXOffset,
                                    worldPosition.getZ() + finalZpos - finalZOffset,
                                    0.4f,
                                    finalXOffset*1.25f,
                                    -finalZOffset*1.25f);

                            clientLevel.addParticle(CTParticles.BUBBLE_PARTICLES.get(),
                                    worldPosition.getX() + finalXpos,
                                    worldPosition.getY() + finalYpos - finalXOffset,
                                    worldPosition.getZ() + finalZpos + finalZOffset,
                                    0.4f,
                                    -finalXOffset*1.25f,
                                    finalZOffset*1.25f);


                        }, 50*i, TimeUnit.MILLISECONDS);
                    }
                    break;
                case WEST:
                    for(int i = 0 ; i < 21 ; i++){

                        double finalXpos = 0.7;
                        double finalYpos = 0.5;
                        double finalZpos = 0.5;

                        double finalZOffset = i/100f; // go from 0 to 0.2
                        double finalXOffset = Math.sqrt((20.1*20.1)-(i*i))/100f; // go from 0.2 to 0


                        ClientUtils.runLater(() -> {
                            clientLevel.addParticle(CTParticles.BUBBLE_PARTICLES.get(),
                                    worldPosition.getX() + finalXpos,
                                    worldPosition.getY() + finalYpos + finalXOffset,
                                    worldPosition.getZ() + finalZpos + finalZOffset,
                                    -0.4f,
                                    finalXOffset*1.25f,
                                    finalZOffset*1.25f);

                            clientLevel.addParticle(CTParticles.BUBBLE_PARTICLES.get(),
                                    worldPosition.getX() + finalXpos,
                                    worldPosition.getY() + finalYpos - finalXOffset,
                                    worldPosition.getZ() + finalZpos - finalZOffset,
                                    -0.4f,
                                    -finalXOffset*1.25f,
                                    -finalZOffset*1.25f);

                            clientLevel.addParticle(CTParticles.BUBBLE_PARTICLES.get(),
                                    worldPosition.getX() + finalXpos,
                                    worldPosition.getY() + finalYpos + finalZOffset,
                                    worldPosition.getZ() + finalZpos - finalXOffset,
                                    -0.4f,
                                    finalZOffset*1.25f,
                                    -finalXOffset*1.25f);

                            clientLevel.addParticle(CTParticles.BUBBLE_PARTICLES.get(),
                                    worldPosition.getX() + finalXpos,
                                    worldPosition.getY() + finalYpos - finalZOffset,
                                    worldPosition.getZ() + finalZpos + finalXOffset,
                                    -0.4f,
                                    -finalZOffset*1.25f,
                                    finalXOffset*1.25f);


                        }, 50*i, TimeUnit.MILLISECONDS);
                    }
                    break;
            }
        }
    }
}
