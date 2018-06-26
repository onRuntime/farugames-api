package net.faru.api.spigot.player.currency;

public enum Currency {

	COINS("Coins", "money_coins", 0),
	CREDITS("Crédits", "money_credits", 0);
	
	private String name;
	private String column;
	private Integer defaultValue;
	
	private Currency(String name, String column, Integer defaultValue) {
		this.name = name;
		this.column = column;
		this.defaultValue = defaultValue;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getColumn() {
		return this.column;
	}
	
	public Integer getDefaultValue() {
		return this.defaultValue;
	}
}
