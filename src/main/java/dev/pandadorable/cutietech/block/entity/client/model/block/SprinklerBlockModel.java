package dev.pandadorable.cutietech.block.entity.client.model.block;



import dev.pandadorable.cutietech.block.entity.SprinklerBlockEntity;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.model.DefaultedBlockGeoModel;

import static dev.pandadorable.cutietech.CutieTech.MODID;

public class SprinklerBlockModel extends DefaultedBlockGeoModel<SprinklerBlockEntity> {

    public SprinklerBlockModel(){
        super(ResourceLocation.fromNamespaceAndPath(MODID,"sprinkler"));
    }

    @Override
    public @Nullable RenderType getRenderType(SprinklerBlockEntity animatable, ResourceLocation texture) {
        return RenderType.entityCutout(getTextureResource(animatable));
    }
}
