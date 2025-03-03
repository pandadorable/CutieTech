package dev.pandadorable.cutietech.datagen;


import dev.pandadorable.cutietech.block.CTBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;

import java.util.List;
import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {

    public ModBlockLootTableProvider(HolderLookup.Provider pRegistries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), pRegistries);
    }

    @Override
    protected void generate() {
        dropSelf(CTBlocks.SPRINKLER_BLOCK.get());
        dropSelf(CTBlocks.EXAMPLE_BLOCK.get());
    }


    @Override
    protected Iterable<Block> getKnownBlocks() {
        return CTBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
