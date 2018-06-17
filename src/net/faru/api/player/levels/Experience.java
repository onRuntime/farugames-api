package net.faru.api.player.levels;

import net.md_5.bungee.api.ChatColor;

public class Experience {

	public static Integer getPlayerLevel(Integer experience) {
		int leveling = 1;
		if (experience >= 0 && experience < 1000)
			leveling = 1;
		if (experience >= 1000 && experience < 2000)
			leveling = 2;
		if (experience >= 2000 && experience < 3000)
			leveling = 3;
		if (experience >= 3000 && experience < 4000)
			leveling = 4;
		if (experience >= 4000 && experience < 5000)
			leveling = 5;
		if (experience >= 5000) {
			leveling = 6;
			int levelUp = (experience - 5000);
			levelUp = ((int) levelUp / 5000);
			leveling = levelUp + 6;

		}
		return leveling;
	}

	public static Integer getPlayerExperienceLevel(Integer experience) {
		int realXP = experience;
		int realLevel = getPlayerLevel(experience);
		if (experience >= 0 && experience < 1000)
			realXP = experience;
		if (experience >= 1000 && experience < 2000)
			realXP = realXP - 1000;
		if (experience >= 2000 && experience < 3000)
			realXP = realXP - 2000;
		if (experience >= 3000 && experience < 4000)
			realXP = realXP - 3000;
		if (experience >= 4000 && experience < 5000)
			realXP = realXP - 4000;
		if (experience >= 5000) {
			realLevel = realLevel - 5;
			realXP = experience;
			for (int i = 1; i != realLevel; i++) {
				realXP = realXP - 5000;
			}
			realXP = realXP - 5000;
		}
		return realXP;
	}
	
	public static String getPlayerProgressBarLevel(Integer experience, String characterType, ChatColor colorEnable,
			ChatColor colorDisable) {
		String baseLevel = "";
		String baseForget = "";
		int xpReal;
		int count = 0;
		if (getPlayerLevel(experience) < 6) {
			xpReal = ((int) getPlayerExperienceLevel(experience) / 100);
		} else {
			xpReal = ((int) getPlayerExperienceLevel(experience) / 500);
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
		if (getPlayerLevel(experience) < 6) {
			return (getPlayerExperienceLevel(experience) / 100) * 10;
		} else {
			return (getPlayerExperienceLevel(experience) / 500) * 10;
		}
	}

	public static Integer getPointForLevelNext(Integer experience) {
		int pointNeed = 1000;
		if (experience >= 0 && experience < 5000) {
			pointNeed = 1000;
		} else {
			pointNeed = 5000;
		}
		return pointNeed;
	}

	public static Integer getPlayerNextLevel(Integer experience) {
		return getPlayerLevel(experience) + 1;
	}
}
