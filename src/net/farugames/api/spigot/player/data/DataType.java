package net.farugames.api.spigot.player.data;

public enum DataType {

	FIRST_LOGIN(1, "First connection's date", "first_login", System.currentTimeMillis()),
	FIRST_IP(2, "First connection's IP", "first_ip", ""),
	LAST_LOGIN(3, "Last connecion's date", "last_login", System.currentTimeMillis()),
	LAST_IP(4, "Last connection's IP", "last_ip", ""),
	LANGUAGE(5, "Used language", "language", "ENGLISH"),
	AFK_STATUT(6, "AFK state", "afk_statut", false),
	
	ALLOW_PRIVATE_MESSAGES(7, "Private messages", "allow_private_messages", true),
	ALLOW_PARTY_REQUESTS(8, "Party requests", "allow_party", true),
	ALLOW_PARTY_FOLLOW(9, "Party's leader follow", "allow_party_follow", true),
	ALLOW_FRIEND_REQUESTS(10, "Friend requests", "allow_friend", true),
	ALLOW_GUILDS_REQUESTS(11, "Guilds requests", "allow_guilds", true),
	ALLOW_CHAT(12, "Chat usage and visibility", "allow_chat", true),
	ALLOW_PLAYER_VISIBLITY(13, "Players visibility", "allow_player_visibility", true),
	ALLOW_LOBBY_DOUBLE_PROCESS_COMMAND(14, "Lobby command protection", "allow_lobby_double_process_command", false),
	ALLOW_CHAT_MENTIONS(15, "Player's mentions", "allow_chat_mention", true);

	private Integer id;
	private String name;
	private String column;
	private Object defaultValue;

	private DataType(Integer id, String name, String column, Object defaultValue) {
		this.id = id;
		this.name = name;
		this.column = column;
		this.defaultValue = defaultValue;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getColumn() {
		return column;
	}

	public Object getDefaultValue() { return defaultValue;
	}
}
