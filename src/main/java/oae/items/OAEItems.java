package oae.items;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import oae.OAEMain;

public class OAEItems {

	private static void initItems() {}

	public static void registerItems() {
		initItems();
		try {
			for (Field field : OAEItems.class.getFields()) {
				// If we a static Item
				if (Modifier.isStatic(field.getModifiers()) && Item.class.isAssignableFrom(field.getType())) {
					Item item = (Item)field.get(null);
					if (item != null) {
						if (item.getRegistryName() != null) {
							GameRegistry.registerItem(item);
						}
						// Fallback if regsitry name is missing. It really shouldn't be...
						else if (item.getUnlocalizedName() != null) {
							OAEMain.LOGGER.debug("Item %s missing registry name, Resorting to unlocalized name %s", item, item.getUnlocalizedName());
							GameRegistry.registerItem(item, item.getUnlocalizedName());
						}
						// If I somehow really really screw up O_o
						else {
							OAEMain.LOGGER.fatal("Failed to register declared item %s. No regsitry or unlocalized name found", item);
						}
					}
				}
			}
		}
		// TODO should we really be skipping items, or should we possibly throw an exception since missing items is a serious problem?
		catch (Exception e) {
			OAEMain.LOGGER.warn("Error while adding an item to the registry: ", e);
		}
	}

	@SideOnly(Side.CLIENT)
	public static void registerRenderers() {
		try {
			for (Field field : OAEItems.class.getFields()) {
				// If we a static Item
				if (Modifier.isStatic(field.getModifiers()) && Item.class.isAssignableFrom(field.getType())) {
					Item item = (Item)field.get(null);
					if (item instanceof IItem) {
						((IItem)item).registerResources();
					}
					else {
						OAEMain.LOGGER.debug("Item %s is not implementing interface IItem. Please report to Spitfyre03 on the GitHub repo. Thanks.", field.getName());
					}
				}
			}
		}
		catch (Exception e) {
			OAEMain.LOGGER.warn("Error while registering item model: ", e);
		}
	}
}
