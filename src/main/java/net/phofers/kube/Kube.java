package net.phofers.kube;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Kube implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final String MOD_ID = "kube";
    public static final Logger LOGGER = LoggerFactory.getLogger("kube");

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		registerModItems();
		registerModBlocks();
	}

	// Mod items go here:
	public static final Item KERBAL_ITEM = registerItem("kerbal",
			new Item(new Item.Settings().maxCount(16)));

	public static final Block KUBE_BLOCK = registerBlock("kube",
			new Block(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK)
					.mapColor(MapColor.DARK_GREEN)
					.hardness(2.5f)
					.sounds(BlockSoundGroup.FUNGUS
					)));

	public static Item registerItem(String name, Item item) {
		return Registry.register(Registries.ITEM, new Identifier(MOD_ID, name), item);
	}
	public static void registerModItems() {
		LOGGER.info("Registering whatever hellish Kerbal items "+MOD_ID+" added");

		ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(Kube::addItemsToIngredientsItemGroup);
	}
	public static void addItemsToIngredientsItemGroup(FabricItemGroupEntries entries) {
		entries.add(KERBAL_ITEM);
	}
	public static void addItemsToDecorationBlocksItemGroup(FabricItemGroupEntries entries) {
		entries.add(KUBE_BLOCK);
	}
	private static Block registerBlock(String name, Block block) {
		registerBlockItem(name, block);
		return Registry.register(Registries.BLOCK, new Identifier(MOD_ID, name), block);
	}
	private static Item registerBlockItem(String name, Block block) {
		return Registry.register(Registries.ITEM, new Identifier(MOD_ID, name),
				new BlockItem(block, new Item.Settings()));
	}
	public static void registerModBlocks() {
		LOGGER.info("Registering the even more hellish blocks that "+MOD_ID+" added");
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(Kube::addItemsToDecorationBlocksItemGroup);
	}

}