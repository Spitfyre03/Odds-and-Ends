package oae;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import oae.items.OAEItems;

/**
 * This class is main proxy holder and object type of the OAEMain proxy. When the mod starts
 * up, the proxy object is assigned to a subtype instance of this class, depending on the
 * Side. This subtype will execute code that only needs carried out its side; actions that
 * need to be performed regardless of the side are executed from this proxy. The subtype
 * must call its parent method in order to execute it.
 *
 * @author Spitfyre03
 */
public class CommonProxy {

	public void preInit(FMLPreInitializationEvent event) {
		OAEItems.registerItems();
	}

	public void init(FMLInitializationEvent event) {
		
	}

	public void postInit(FMLPostInitializationEvent event) {
		
	}

	// TODO add methods for getting working directory. That way client proxy can override and we have access from any scope
}
