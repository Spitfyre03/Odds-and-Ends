package oae.client.drpc;

import com.sun.jna.Structure;

public class DiscordUser extends Structure {

	public String userID;

	public String username;

	public String discriminator;

	public String avatar;
}
