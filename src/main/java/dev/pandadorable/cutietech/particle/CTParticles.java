package dev.pandadorable.cutietech.particle;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

import static dev.pandadorable.cutietech.CutieTech.MODID;

public class CTParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES =
            DeferredRegister.create(BuiltInRegistries.PARTICLE_TYPE, MODID);

    public static final Supplier<SimpleParticleType> BUBBLE_PARTICLES =
            PARTICLE_TYPES.register("bubble_particles", () -> new SimpleParticleType(false));

    public static void register(IEventBus modBusEvent){
        PARTICLE_TYPES.register(modBusEvent);
    }
}
