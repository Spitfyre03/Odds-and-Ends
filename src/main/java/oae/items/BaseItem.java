package oae.items;

import javax.annotation.Nonnull;

import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * For classes that would extend {@link net.minecraft.item.Item}, they should extend this
 * class instead, to implement automation and encapsulation of registry and localization
 * 
 * @author coolAlias
 */
public class BaseItem extends Item implements IItem {

	public BaseItem(@Nonnull final String name) {
		setRegistryName(name);
		setUnlocalizedName("oae." + name);
	}

	@Override
	public String[] getVariants() {
		return null;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerResources() {
		String[] variants = getVariants();
		if (variants == null || variants.length < 1) {
			variants = new String[]{getRegistryName()};// Registry name because this shouldn't change. Only thing volatile is lang
		}
		for (int i = 0; i < variants.length; ++i) {
			ModelLoader.setCustomModelResourceLocation(this, i, new ModelResourceLocation(variants[i], "inventory"));
		}
	}

}
