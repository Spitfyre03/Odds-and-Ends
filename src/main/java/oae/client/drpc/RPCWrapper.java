package oae.client.drpc;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.SystemUtils;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;

import oae.OAEMain;

/**
 * The wrapper class for the Discord Rich Presence native library. Use this class to handle Discord-
 * related operations.
 *
 * When the class is first initialized, the appropriate dynamic library is extracted
 * from the resource directory and copied into a temporary folder. From this folder, the DLL is
 * loaded into runtime, and then it is mapped to the interface RPC.
 *
 * @author Spitfyre03
 */
public class RPCWrapper {

	static {
		String discord = "/discord-rpc";
		String dll = System.mapLibraryName(discord);// Turns the libname into a system-formatted version (e.g. linux DLLs end in .so)
		String resPath = "/drpc";// Represents the asset. Path will be modified to match the needed asset for the running system
		String tempPath = "/" + (SystemUtils.IS_OS_WINDOWS ? System.getenv("TEMP") : System.getProperty("user.home"));// TEMP variable for windows, user.home for Unix

		if (SystemUtils.IS_OS_LINUX || SystemUtils.IS_OS_MAC_OSX) {}
		else if (SystemUtils.IS_OS_WINDOWS) {
			resPath += (Platform.is64Bit() ? "/win64" : "/win32") + dll;
			tempPath += discord + "/" + dll;
		}
		File file = new File(tempPath);
		try (InputStream in = RPCWrapper.class.getResourceAsStream(resPath);OutputStream out = FileUtils.openOutputStream(file)) {
			IOUtils.copy(in, out);
			FileUtils.forceDeleteOnExit(file);
		}
		catch (Exception e) {
			OAEMain.LOGGER.error(e);
		}
		System.load(file.getAbsolutePath());
	}

	// No instances for you!!
	private RPCWrapper() {}

	public static void initDiscord(String appID, DiscordEventHandlers handlers, int autoRegister, String optionalSteamID) {
		RPC.INSTANCE.Discord_Initialize(appID, handlers, autoRegister, optionalSteamID);
	}

	public static void registerToDiscord(String appID, String command) {
		RPC.INSTANCE.Discord_Register(appID, command);
	}

	@Deprecated
	public static void registerSteamGame(String appID, String steamID) {
		// Minecraft is not a Steam game. This should be unused
		//RPC.INSTANCE.Discord_RegisterSteamGame(appID, steamID);
	}

	public static void updateHandlers(DiscordEventHandlers handlers) {
		RPC.INSTANCE.Discord_UpdateHandlers(handlers);
	}

	public static void shutdown() {
		RPC.INSTANCE.Discord_Shutdown();
	}

	public static void runCallbacks() {
		RPC.INSTANCE.Discord_RunCallbacks();
	}

	public static void updatePresence(DiscordRichPresence presence) {
		RPC.INSTANCE.Discord_UpdatePresence(presence);
	}

	public static void clearPresence() {
		RPC.INSTANCE.Discord_ClearPresence();
	}

	public static void respond(String userID, DiscordReply reply) {
		RPC.INSTANCE.Discord_Respond(userID, reply.ordinal());
	}

	private static interface RPC extends Library {

		/*********************************************************************************
		 * Create a singleton of this interface mapped to and representing the native
		 * library for Discord RPC. All methods defined here will call the methods from
		 * the native library.
		 *********************************************************************************/
		static final RPC INSTANCE = (RPC) Native.loadLibrary("discord-rpc", RPC.class);

		void Discord_Initialize(String applicationId, DiscordEventHandlers handlers, int autoRegister, String optionalSteamId);
        void Discord_Register(String applicationId, String command);
        void Discord_RegisterSteamGame(String applicationId, String steamId);
        void Discord_UpdateHandlers(DiscordEventHandlers handlers);
        void Discord_Shutdown();
        void Discord_RunCallbacks();
        void Discord_UpdatePresence(DiscordRichPresence presence);
        void Discord_ClearPresence();
        void Discord_Respond(String userId, int reply);
	}
}
