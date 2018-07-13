package oae;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import oae.ref.ModInfo;

/**
 * The face of Odds and Ends. This only serves as the mod object and FML event handler. Most
 * FML related processes with be carried out here. All other actions are deferred to the
 * proxies for execution.
 * 
 * @author Spitfyre03
 */
@Mod(modid=ModInfo.ID, version=ModInfo.VERSION, name=ModInfo.NAME)
public class OAEMain {

	@Instance
	public static OAEMain instance;

	// TODO implement sectioning? What does that look like
	public static final Logger LOGGER = LogManager.getLogger(ModInfo.ID);

	@SidedProxy(serverSide = ModInfo.SERVER_PROXY, clientSide = ModInfo.CLIENT_PROXY)
	public static CommonProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		proxy.preInit(event);
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init(event);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		proxy.postInit(event);
	}
}
