package dev.pandadorable.cutietech.block.entity.client.renderer.block;

import dev.pandadorable.cutietech.block.entity.SprinklerBlockEntity;
import dev.pandadorable.cutietech.block.entity.client.model.block.SprinklerBlockModel;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class SprinklerBlockRenderer extends GeoBlockRenderer<SprinklerBlockEntity> {

    public SprinklerBlockRenderer() {
        super(new SprinklerBlockModel());
    }

}
