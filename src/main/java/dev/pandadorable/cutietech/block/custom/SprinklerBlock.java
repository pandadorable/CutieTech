package dev.pandadorable.cutietech.block.custom;


import com.mojang.serialization.MapCodec;
import dev.pandadorable.cutietech.block.entity.CTBlockEntites;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class SprinklerBlock extends DirectionalBlock implements EntityBlock {

    public static BooleanProperty TOGGLE_SPRINKLER = BooleanProperty.create("toggles_sprinkler");

    public SprinklerBlock() {
        super(Properties.of().noOcclusion());
        this.registerDefaultState(this.getStateDefinition().any()
                .setValue(TOGGLE_SPRINKLER,false)
                .setValue(FACING, Direction.UP)
        );
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING,context.getClickedFace());
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        switch(state.getValue(FACING)){
            case EAST:
                return Block.box(0, 6, 6, 5, 10, 10);
            case WEST:
                return Block.box(11, 6, 6, 16, 10, 10);

            case NORTH:
                return Block.box(6, 6, 11, 10, 10, 16);
            case SOUTH:
                return Block.box(6, 6, 0, 10, 10, 5);

            case DOWN:
                return Block.box(6, 11, 6, 10, 16, 10);
            default:
                return Block.box(6, 0, 6, 10, 5, 10);
        }
    }

    @Override
    protected MapCodec<? extends DirectionalBlock> codec() {
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
        builder.add(TOGGLE_SPRINKLER)
                .add(new Property[]{FACING});
    }
}
