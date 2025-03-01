package dev.pandadorable.cutietech.item;

import dev.pandadorable.cutietech.block.CTBlocks;
import dev.pandadorable.cutietech.item.custom.SprinklerItem;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

import static dev.pandadorable.cutietech.CutieTech.MODID;
import static dev.pandadorable.cutietech.block.CTBlocks.EXAMPLE_BLOCK;

public class CTItems {
    // Items
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);


    // Creates a new BlockItem with the id "cutietech:example_block", combining the namespace and path
    public static final DeferredItem<BlockItem> EXAMPLE_BLOCK_ITEM = ITEMS.registerSimpleBlockItem("example_block", EXAMPLE_BLOCK);

    // Creates a new food item with the id "cutietech:example_id", nutrition 1 and saturation 2
    public static final DeferredItem<Item> EXAMPLE_ITEM =
            ITEMS.registerSimpleItem("example_item", new Item.Properties().food(new FoodProperties.Builder().alwaysEdible().nutrition(1).saturationModifier(2f).build()));

    public static final Supplier<BlockItem> SPRINKLER =
            ITEMS.register("sprinkler", () -> new SprinklerItem(CTBlocks.SPRINKLER_BLOCK.get(), new Item.Properties()));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
