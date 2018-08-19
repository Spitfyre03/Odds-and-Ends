package oae.client.drpc;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oae.OAEMain;
import oae.client.drpc.DiscordEventHandlers.ReadyHandler;
import oae.client.drpc.DiscordEventHandlers.StatusHandler;

/**
 * Responsible for connecting Minecraft and Forge to the Discord RPC framework. This class is registered as an
 * event handler to the MinecraftForge.EVENT_BUS. It is instantiated and run from the client proxy; therefore, all
 * RPC behavior is contained and handled within this class.
 *
 * @author Spitfyre03
 */
public class RPCHandler {

	private static final String APP_ID = "463118357042954240";

	public DiscordRichPresence presence;

	public RPCHandler() {
		DiscordEventHandlers handlers = buildHandlers();
		RPCWrapper.initDiscord(APP_ID, handlers, 1, "");
		presence = initPresence();
		RPCWrapper.updatePresence(presence);
	}

	/**
	 * @return a standard DiscordEventHandlers object to be registered
	 */
	private DiscordEventHandlers buildHandlers() {
		DiscordEventHandlers handlers = new DiscordEventHandlers();
		handlers.ready = new ReadyHandler() {
			@Override
			public void callback(final DiscordUser user) {
				OAEMain.LOGGER.info("%s logging in.", user.username);
			}
		};
		handlers.disconnected = new StatusHandler() {
			@Override
			public void callback(int errorCode, String message) {
				OAEMain.LOGGER.info(message);
			}
		};
		return handlers;
	}

	/**
	 * @return a presence representing the state of the current game at startup
	 */
	private DiscordRichPresence initPresence() {
		DiscordRichPresence presence = new DiscordRichPresence();

		presence.startTimestamp = System.currentTimeMillis() / 1000;// Epoch second
		presence.details = "Loading";
		presence.state = "Pre-init";
		//presence.joinSecret = UUID.randomUUID().toString();
		//presence.spectateSecret = UUID.randomUUID().toString();
		presence.largeImageKey = "mc-logo";
		presence.largeImageText = "Minecraft " + Minecraft.getMinecraft().getVersion();

		return presence;
	}

	@SubscribeEvent
	public void onScreenOpened(GuiOpenEvent event) {
		if (event.gui instanceof GuiMainMenu) {
			presence.details = "In Main Menu";
			presence.state = "";
		}
	}
}
