package oae;

import java.util.concurrent.TimeUnit;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import oae.client.drpc.RPCHandler;
import oae.client.drpc.RPCWrapper;
import oae.items.OAEItems;

/**
 * This proxy is the instance assigned to the mod proxy if it is loaded on the client side, i.e.
 * a player loads an instance of the playable version of the game. All client side registry, such
 * as model and client event handler registrations, should be done from this proxy.
 * 
 * @author Spitfyre03
 */
public class ClientProxy extends CommonProxy {

	private static RPCHandler richPresence;

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);
		OAEMain.LOGGER.info("Initializing Discord RPC.");
		richPresence = new RPCHandler();
		MinecraftForge.EVENT_BUS.register(richPresence);
		new Thread("RPC-Thread") {
			@Override
			public void run() {
				while (!Thread.currentThread().isInterrupted()) {
					RPCWrapper.updatePresence(richPresence.presence);
					RPCWrapper.runCallbacks();
					try {
						TimeUnit.SECONDS.sleep(2);
					}
					catch (InterruptedException e) {
						OAEMain.LOGGER.error("Exception while running RPC-Thread", e);
					}
				}
			}
		}.start();
		OAEItems.registerRenderers();
	}

	@Override
	public void init(FMLInitializationEvent event) {
		super.init(event);
		richPresence.presence.state = "Init";
	}

	@Override
	public void postInit(FMLPostInitializationEvent event) {
		super.postInit(event);
		richPresence.presence.state = "Post-init";
	}
}
