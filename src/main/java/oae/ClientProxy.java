package oae;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import oae.items.OAEItems;

/**
 * This proxy is the instance assigned to the mod proxy if it is loaded on the client side, i.e.
 * a player loads an instance of the playable version of the game. All client side registry, such
 * as model and client event handler registrations, should be done from this proxy.
 * 
 * @author Spitfyre03
 */
public class ClientProxy extends CommonProxy {

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);
		OAEItems.registerRenderers();
	}

	@Override
	public void init(FMLInitializationEvent event) {
		super.init(event);
	}

	@Override
	public void postInit(FMLPostInitializationEvent event) {
		super.postInit(event);
	}
}
