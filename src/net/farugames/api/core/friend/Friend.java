package net.farugames.api.core.friend;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Friend implements IFriend {

	private UUID uuid;
	private List<UUID> friends = new ArrayList<UUID>();
	
	public Friend(UUID playerUUID) {
		this.uuid = playerUUID;
	}
}
