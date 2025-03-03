package dev.pandadorable.cutietech.datagen;

import dev.pandadorable.cutietech.CutieTech;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, CutieTech.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {

    }
}
