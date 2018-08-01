package net.farugames.api.core.rank;

import net.md_5.bungee.api.ChatColor;

public enum Rank {

	OWNER(1000, "A", "OWNER", "Owner", ChatColor.BOLD + "OWNER ", ChatColor.DARK_RED, ChatColor.WHITE), 
	ADMIN(950, "B",	"ADMIN", "Admin", ChatColor.BOLD + "ADMIN ", ChatColor.RED, ChatColor.WHITE), 
	RESP(900, "C", "RESP", "Resp", ChatColor.BOLD + "RESP ", ChatColor.GOLD, ChatColor.WHITE),

	SUPERDEV(850, "D", "SUPERDEV", "SuperDev", ChatColor.BOLD + "DEV", ChatColor.DARK_GREEN, ChatColor.WHITE), 
	DEV(825, "E", "DEV", "Dev", ChatColor.BOLD + "DEV ", ChatColor.DARK_GREEN, ChatColor.WHITE), 
	MINIDEV(800, "F", "MINIDEV", "MiniDev", ChatColor.BOLD + "DEV", ChatColor.DARK_GREEN, ChatColor.WHITE),

	SUPERMOD(750, "G", "SUPERMOD", "SuperMod", ChatColor.BOLD + "MOD", ChatColor.BLUE, ChatColor.WHITE), 
	MOD(725, "H", "MOD", "Mod", ChatColor.BOLD + "MOD", ChatColor.BLUE, ChatColor.WHITE), 
	MINIMOD(700, "I", "MINIMOD", "MiniMod", ChatColor.BOLD + "MOD", ChatColor.BLUE, ChatColor.WHITE),

	SUPERHELPER(650, "J", "SUPERHELPER", "SuperHelper", ChatColor.BOLD + "HELPER", ChatColor.AQUA, ChatColor.WHITE), 
	HELPER(625, "K", "HELPER", "Helper", ChatColor.BOLD + "HELPER", ChatColor.AQUA, ChatColor.WHITE), 
	MINIHELPER(600, "L", "MINIHELPER", "MiniHelper", ChatColor.BOLD + "HELPER", ChatColor.AQUA, ChatColor.WHITE),

	SUPERBUILDER(550, "M", "SUPERBUILDER", "SuperBuilder ", ChatColor.BOLD + "BUILDER", ChatColor.GREEN, ChatColor.WHITE), 
	BUILDER(525, "N", "BUILDER", "Builder", ChatColor.BOLD + "BUILDER", ChatColor.GREEN, ChatColor.WHITE), 
	MINIBUILDER(500, "O", "MINIBUILDER", "MiniBuilder ", ChatColor.BOLD + "BUILDER", ChatColor.GREEN, ChatColor.WHITE),

	SUPERSTAFF(400, "P", "SUPERSTAFF", "SuperStaff", ChatColor.BOLD + "STAFF", ChatColor.DARK_AQUA, ChatColor.WHITE), 
	STAFF(400, "Q", "STAFF", "Staff", ChatColor.BOLD + "STAFF", ChatColor.DARK_AQUA, ChatColor.WHITE), 
	MINISTAFF(400, "R", "MINISTAFF", "MiniStaff", ChatColor.BOLD + "STAFF", ChatColor.DARK_AQUA, ChatColor.WHITE),

	FAMOUS(375, "S", "FAMOUS", "Famous", ChatColor.BOLD + "FAMOUS ", ChatColor.LIGHT_PURPLE, ChatColor.WHITE), 
	YOUTUBER(350, "T", "YOUTUBER", "Youtuber", ChatColor.BOLD + "YOUTUBER ", ChatColor.YELLOW, ChatColor.WHITE), 
	FRIEND(300, "U", "FRIEND", "Friend", ChatColor.BOLD + "§c❤ ", ChatColor.WHITE, ChatColor.WHITE),

	PLAYER(0, "Y", "PLAYER", "Player", "", ChatColor.GRAY, ChatColor.GRAY);

	private Integer power;
	private String order;
	private String idName;
	private String name;
	private String prefix;
	private ChatColor color;
	private ChatColor chatColor;

	private Rank(Integer power, String order, String idName, String name, String prefix, ChatColor color,
			ChatColor chatColor) {
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
		return this.prefix + getSuffix();
	}

	public ChatColor getColor() {
		return this.color;
	}

	public ChatColor getChatColor() {
		return this.chatColor;
	}

	public static Rank getRankByIdName(String idName) {
		for (Rank rank : Rank.values()) {
			if (rank.getIdName().equals(idName)) {
				return rank;
			}
		}
		return null;
	}

	public static Rank getRankByPower(Integer power) {
		for (Rank rank : Rank.values()) {
			if (rank.getPower().intValue() == power.intValue()) {
				return rank;
			}
		}
		return Rank.PLAYER;
	}

	public String getSuffix() {
		String suffix = "";
		if ((MINISTAFF.power <= this.power) && (this.power <= SUPERDEV.power)) {
			suffix = ChatColor.GREEN + "■ ";
			if (this.name.contains("Super"))
				suffix = ChatColor.LIGHT_PURPLE + "■ ";
			if (this.name.contains("Mini"))
				suffix = ChatColor.RED + "■ ";
		}
		return suffix + this.color;
	}
}
