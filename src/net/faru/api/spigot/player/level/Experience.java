package net.faru.api.spigot.player.level;

import net.md_5.bungee.api.ChatColor;

public class Experience {

	public static LevelExperience getLevel(Integer experience) {
		for(LevelExperience levelExperience : LevelExperience.values()) {
			if(experience >= levelExperience.getExperience()) {
				continue;
			}
			return levelExperience;
		}
		return null;
	}

	public static Integer getExperienceForNextLevel(Integer experience) {
		
		int exp = experience;
		for(LevelExperience levelExperience : LevelExperience.values()) {
			if(exp >= levelExperience.getExperience()) {
				exp = exp - levelExperience.getExperience();
			}
		}
		return exp;
	}
	
	public static String getPlayerProgressBarLevel(Integer experience, String characterType, ChatColor colorEnable,
			ChatColor colorDisable) {
		String baseLevel = "";
		String baseForget = "";
		int xpReal;
		int count = 0;
		if (getLevel(experience).getLevel() < 6) {
			xpReal = ((int) getExperienceForNextLevel(experience) / 100);
		} else {
			xpReal = ((int) getExperienceForNextLevel(experience) / 500);
		}
		for (int i = 0; i < xpReal; i++) {
			baseLevel = baseLevel + characterType;
			count++;
		}
		for (int i = count; i != 10; i++) {
			baseForget = baseForget + characterType;
		}
		return colorEnable + baseLevel + colorDisable + baseForget;
	}
	
	public static float getPlayerPourcentForNextLevel(Integer experience) {
		if (getLevel(experience).getLevel() < 6) {
			return (getExperienceForNextLevel(experience) / 100) * 10;
		} else {
			return (getExperienceForNextLevel(experience) / 500) * 10;
		}
	}
}
