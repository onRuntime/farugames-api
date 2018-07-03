package net.farugames.api.spigot.player.data;

public enum DataType {

	FIRST_LOGIN(1, "First connection's date", "first_login"),
	FIRST_IP(2, "First connection's IP", "first_ip"),
	LAST_LOGIN(3, "Last connecion's date", "last_login"),
	LAST_IP(4, "Last connection's IP", "last_ip"),
	LANGUAGE(5, "Used language", "language"),
	AFK_STATUT(6, "AFK state", "afk_statut"),
	
	ALLOW_PRIVATE_MESSAGES(7, "Private messages", "allow_private_messages"),
	ALLOW_PARTY_REQUESTS(8, "Party requests", "allow_party"),
	ALLOW_PARTY_FOLLOW(9, "Party's leader follow", "allow_party_follow"),
	ALLOW_FRIEND_REQUESTS(10, "Friend requests", "allow_friend"),
	ALLOW_GUILDS_REQUESTS(11, "Guilds requests", "allow_guilds"),
	ALLOW_CHAT(12, "Chat usage and visibility", "allow_chat"),
	ALLOW_PLAYER_VISIBLITY(13, "Players visibility", "allow_player_visibility"),
	ALLOW_LOBBY_DOUBLE_PROCESS_COMMAND(14, "Lobby command protection", "allow_lobby_double_process_command"),
	ALLOW_CHAT_MENTIONS(15, "Player's mentions", "allow_chat_mention");

	private Integer id;
	private String name;
	private String column;

	private DataType(Integer id, String name, String column) {
		this.id = id;
		this.name = name;
		this.column = column;
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
}
