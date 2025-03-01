package dev.pandadorable.cutietech.block.entity;

import dev.pandadorable.cutietech.block.CTBlocks;
import dev.pandadorable.cutietech.block.entity.client.renderer.block.SprinklerBlockRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static dev.pandadorable.cutietech.CutieTech.MODID;

public class CTBlockEntites {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, MODID);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<SprinklerBlockEntity>> SPRINKLER_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("sprinkler", () ->
                    BlockEntityType.Builder.of(SprinklerBlockEntity::new,
                            CTBlocks.SPRINKLER_BLOCK.get())
                            .build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
