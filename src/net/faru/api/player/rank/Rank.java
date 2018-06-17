package net.faru.api.player.rank;

import org.bukkit.ChatColor;

public enum Rank {

	OWNER(1000, "A", "OWNER", "Owner", "Owner", ChatColor.DARK_RED, ChatColor.WHITE),
	ADMINISTRATOR(950, "B", "ADMINISTRATOR", "Administrator", "Administrator", ChatColor.RED, ChatColor.WHITE),
	
	DEVELOPER(900, "C", "DEVELOPER", "Developer", "Developer", ChatColor.DARK_GREEN, ChatColor.WHITE),
	BUILDER(800, "D", "BUILDER", "Builder", "Builder", ChatColor.DARK_GREEN, ChatColor.WHITE),
	
	MODERATOR(700, "E", "MODERATOR", "Moderator", "Moderator", ChatColor.BLUE, ChatColor.WHITE),
	HELPER(600, "F", "HELPER", "Helper", "Helper", ChatColor.AQUA, ChatColor.WHITE),
	
	REDACTOR(520, "G", "REDACTOR", "Redactor", "Redactor", ChatColor.DARK_AQUA, ChatColor.WHITE),
	GRAPHIST(510, "H", "GRAPHIST", "Graphist", "Graphist", ChatColor.DARK_AQUA, ChatColor.WHITE),
	STAFF(500, "I", "STAFF", "Staff", "Staff", ChatColor.DARK_AQUA, ChatColor.WHITE),
	
	YOUTUBER(400, "J", "YOUTUBER", "Youtuber", "Youtuber", ChatColor.GOLD, ChatColor.WHITE),
	FRIEND(300, "K", "FRIEND", "Friend", "Friend", ChatColor.GREEN, ChatColor.WHITE),
	
	PLAYER(0, "Y", "PLAYER", "Player", "", ChatColor.GRAY, ChatColor.GRAY);
	
	private Integer power;
	private String order;
	private String idName;
	private String name;
	private String prefix;
	private ChatColor color;
	private ChatColor chatColor;
	
	Rank(Integer power, String order, String idName, String name, String prefix, ChatColor color, ChatColor chatColor) {
		this.power = power;
		this.order = order;
		this.idName = idName;
		this.name = name;
		this.prefix = prefix;
		this.color = color;
		this.chatColor = chatColor;
	}
	
	public Integer getPower() {
		return this.power;
	}
	
	public String getOrder() {
		return this.order;
	}
	
	public String getIdName() {
		return this.idName;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getPrefix() {
		return this.prefix;
	}
	
	public ChatColor getColor() {
		return this.color;
	}
	
	public ChatColor getChatColor() {
		return this.chatColor;
	}
	
	public static Rank getRankByIdName(String idName) {
		for(Rank rank : Rank.values()) {
			if(rank.getIdName().equals(idName)) {
				return rank;
			}
		}
		return null;
	}
	
	public static Rank getRankByPower(Integer power) {
		for(Rank rank : Rank.values()) {
			if(rank.getPower() == power) {
				return rank;
			}
		}
		return null;
	}
}
