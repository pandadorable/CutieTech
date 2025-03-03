package dev.pandadorable.cutietech.block.entity.client.renderer.block;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import dev.pandadorable.cutietech.block.custom.SprinklerBlock;
import dev.pandadorable.cutietech.block.entity.SprinklerBlockEntity;
import dev.pandadorable.cutietech.block.entity.client.model.block.SprinklerBlockModel;
import net.minecraft.core.Direction;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class SprinklerBlockRenderer extends GeoBlockRenderer<SprinklerBlockEntity> {

    public SprinklerBlockRenderer() {
        super(new SprinklerBlockModel());
    }

    @Override
    protected void rotateBlock(Direction facing, PoseStack poseStack) {
        switch (facing) {
            case SOUTH -> {
                poseStack.mulPose(Axis.XP.rotationDegrees(90));
                poseStack.translate(0, -0.5, -0.5);
            }
            case NORTH -> {
                poseStack.mulPose(Axis.XP.rotationDegrees(270));
                poseStack.translate(0, -0.5, 0.5);
            }

            case WEST -> {
                poseStack.mulPose(Axis.ZP.rotationDegrees(90));
                poseStack.translate(0.5, -0.5, 0);
            }
            case EAST -> {
                poseStack.mulPose(Axis.ZP.rotationDegrees(270));
                poseStack.translate(-0.5, -0.5, 0);
            }

            case UP -> {
                poseStack.mulPose(Axis.XP.rotationDegrees(0));
            }
            case DOWN -> {
                poseStack.mulPose(Axis.XP.rotationDegrees(180));
                poseStack.translate(0, -1, 0);
            }
        }
    }
}
