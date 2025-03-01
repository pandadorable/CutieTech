package dev.pandadorable.cutietech.block.custom;


import com.mojang.serialization.MapCodec;
import dev.pandadorable.cutietech.block.entity.CTBlockEntites;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class SprinklerBlock extends BaseEntityBlock implements EntityBlock {

    public static BooleanProperty TOGGLE_SPRINKLER = BooleanProperty.create("toggles_sprinkler");

    public SprinklerBlock() {
        super(Properties.of().noOcclusion());
        this.registerDefaultState(this.defaultBlockState().setValue(TOGGLE_SPRINKLER,false));
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return null;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return CTBlockEntites.SPRINKLER_BLOCK_ENTITY.get().create(pPos,pState);
    }

    @Override
    protected RenderShape getRenderShape(BlockState pState) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if(!level.isClientSide()){
            level.setBlock(pos,
                    state.setValue(TOGGLE_SPRINKLER, !state.getValue(TOGGLE_SPRINKLER)),
                    3);
        }
        return super.useWithoutItem(state,level,pos,player,hitResult);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(TOGGLE_SPRINKLER);
    }
}
