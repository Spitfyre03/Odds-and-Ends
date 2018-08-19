package oae.client.drpc;

import com.sun.jna.Structure;

/**
 * This class represents the Rich Presence object that is displayed on Discord. The
 * RPCWrapper class is responsible for packaging DRP objects and sending them to
 * Discord. The documentation for these fields reflects that of the official Discord
 * documentation on Rich Presence.
 *
 * @author Spitfyre03
 */
public class DiscordRichPresence extends Structure {

	/** The user's current party status. Max 128 bytes. */
	public String state;

	/** What the player is currently doing. Max 128 bytes. */
	public String details;

	/** Epoch seconds since game start - will show time as "elapsed". */
	public long startTimestamp;

	/** Epoch seconds until game end - will show time as "remaining". */
	public long endTimestamp;

	/** Name of the uploaded image for the large profile artwork. Max 32 bytes. */
	public String largeImageKey;

	/** Tooltip for {@code largeImageKey}. Max 128 bytes. */
	public String largeImageText;

	/** Name of the uploaded image for the small profile artwork. Max 32 bytes. */
	public String smallImageKey;

	/** Tooltip for the {@code smallImageKey}. Max 128 bytes. */
	public String smallImageText;

	/** ID of the player's party, lobby, or group. Max 128 bytes. */
	public String partyID;

	/** Current size of the player's party, lobby, or group. */
	public int partySize;

	/** Maximum size of the player;s party, lobby, or group. */
	public int partyMax;

	/** Currently unused by the RPC framework. */
	@Deprecated
	public String matchSecret;

	/** Unique hashed String for chat invitations and Ask to Join. */
	public String joinSecret;

	/** Unique hashed String for Spectate button. */
	public String spectateSecret;

	/** Currently unused by the RPC framework. */
	@Deprecated
	public int instance;
}
