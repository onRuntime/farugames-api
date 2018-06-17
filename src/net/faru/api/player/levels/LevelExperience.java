package net.faru.api.player.levels;

public enum LevelExperience {

	LEVEL_1("1", 250),
	LEVEL_100("MAX", 45000);
	
	private String level;
	private Integer experience;
	
	LevelExperience(String level, Integer experience) {
		this.level = level;
		this.experience = experience;
	}
	
	public String getLevel() {
		return this.level;
	}
	
	public Integer getExperience() {
		return this.experience;
	}
}
