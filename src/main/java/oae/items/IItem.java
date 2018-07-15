package oae.items;

import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Interface to improve encapsulation and allow automated registration of variants and renderers
 * 
 * @author coolAlias
 */
public interface IItem {

	/**
	 * Returns an array of variant names to be used in {@link #registerResources()} and possible {@link #registerRenderers() registerRenderer()}
	 * Variants typically use the format of the item registry name, which follows "domain:item_name"
	 * @return Return null if there are no variants (e.g. standard generic item)
	 */
	String[] getVariants();

	/**
	 * Register any item variant names here using e.g. {@link ModelLoader#registerItemVariants} or {@link ModelLoader#setCustomMeshDefinition}.
	 * This MUST be called during {@code FMLPreInitializationEvent}
	 * 
	 * Typical implementation taking advantage of {@link #getVariants()}:
	 * 
	 *	String[] variants = getVariants();
	 *	if (variants == null || variants.length < 1) {
	 *		variants = new String[]{getRegistryName()};
	 *	}
	 *	for (int i = 0; i < variants.length; ++i) {
	 *		ModelLoader.setCustomModelResourceLocation(this, i, new ModelResourceLocation(variants[i], "inventory"));
	 *	}
	 */
	@SideOnly(Side.CLIENT)
	void registerResources();

}
