package net.faru.api.player.levels;

public enum LevelExperience {

	LEVEL_1(1, 250),
	LEVEL_100(100, 45000);
	
	private Integer level;
	private Integer experience;
	
	LevelExperience(Integer level, Integer experience) {
		this.level = level;
		this.experience = experience;
	}
	
	public Integer getLevel() {
		return this.level;
	}
	
	public Integer getExperience() {
		return this.experience;
	}
}
