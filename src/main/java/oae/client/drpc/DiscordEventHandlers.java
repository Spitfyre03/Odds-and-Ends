package oae.client.drpc;

import com.sun.jna.Callback;
import com.sun.jna.Structure;

/**
 * This class represents the events fired by Discord. When an event is fired, one of
 * the registered handler invokes the handler corresponding to the event. Use this to
 * handle reacting to updates from Discord, like generating a pop-up for a JoinRequest.
 *
 * @author Spitfyre03
 */
public class DiscordEventHandlers extends Structure {

	public ReadyHandler ready;

	public StatusHandler disconnected;

	public StatusHandler errored;

	public JoinHandler joinGame;

	public JoinHandler spectateGame;

	public JoinRequestHandler joinRequest;

	//*******************************************************************************//
	//                                                                               //
	//   Event handler interfaces.                                                   //
	//   All interfaces extend Callback, and contain a void method named callback,   //
	//   with parameters corresponding to the method definitions outlined in the     //
	//   Discord RPC library. The disconnected and errorred objects have been        //
	//   combined into a single Callback type, since they share the same parameter   //
	//   types. The joinGame and spectateGame objects have been consolidated for     //
	//   the same reason.                                                            //
	//                                                                               //
	//*******************************************************************************//

	/**
	 * The handler used when Discord calls if the client is ready
	 */
	public interface ReadyHandler extends Callback {
		public void callback(final DiscordUser user);
	}

	/**
	 * Handler used for both the disconnected and errorred events.
	 */
	public interface StatusHandler extends Callback {
		public void callback(int errorCode, final String message);
	}

	/**
	 * Handler used when a Discord user attempts to connect to a join or spectate invitation
	 */
	public interface JoinHandler extends Callback {
		public void callback(final String secret);
	}

	/**
	 * Handler used when a Discord user requests to join a game
	 */
	public interface JoinRequestHandler extends Callback {
		public void callback(final DiscordUser user);
	}
}
