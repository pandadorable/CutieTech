package dev.pandadorable.cutietech.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import org.jetbrains.annotations.Nullable;

public class BubbleParticles extends TextureSheetParticle {
    protected BubbleParticles(ClientLevel level, double x, double y, double z, SpriteSet spriteSet,
                              double xSpeed, double ySpeed, double zSpeed) {
        super(level, x, y, z, xSpeed, ySpeed, zSpeed);

        //this.gravity = 9.81f;

        this.lifetime = 40;
        this.setSpriteFromAge(spriteSet);
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.CUSTOM;
    }

    public static class Provider implements ParticleProvider<SimpleParticleType>{
        private final SpriteSet spriteSet;

        public Provider(SpriteSet spriteSet) {
            this.spriteSet = spriteSet;
        }

        @Override
        public @Nullable Particle createParticle(SimpleParticleType simpleParticleType, ClientLevel clientLevel,
                                                 double pX, double pY, double pZ, double sX, double sY, double sZ) {
            return new BubbleParticles(clientLevel, pX, pY, pZ, this.spriteSet, sX, sY, sZ);
        }
    }
}
