package net.faru.api.sanctions;

import net.faru.api.player.rank.Rank;

public enum SanctionType {

	SERVER_BAN(Rank.MODERATOR.getPower(), Rank.STAFF.getPower()),
	GAMES_BAN(Rank.MODERATOR.getPower(), Rank.STAFF.getPower()),
	KICK(Rank.HELPER.getPower(), Rank.STAFF.getPower()),
	MUTE(Rank.HELPER.getPower(), Rank.STAFF.getPower());
	
	private Integer powerReq, targetMaxPower;
	
	SanctionType(Integer powerReq, Integer targetMaxPower) {
		this.powerReq = powerReq;
		this.targetMaxPower = targetMaxPower;
	}
	
	public Integer getRequiredPower() {
		return this.powerReq;
	}
	
	public Integer getMaxPowerUse() {
		return this.targetMaxPower;
	}
}
