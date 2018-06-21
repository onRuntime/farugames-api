package net.faru.api.sanctions;

import java.util.Date;

import net.faru.api.menus.sanctions.SanctionMainMenu;
import net.faru.api.player.FaruPlayer;

public class Sanction {
	
	private FaruPlayer author;
	private FaruPlayer target;
	
	private SanctionType sanction;
	
	private String reason;
	private Date date;
	private Integer time;
	
	public Sanction(FaruPlayer faruPlayer, FaruPlayer faruTarget, SanctionType sanctionType) {
		this.author = faruPlayer;
		this.target = faruTarget;
		
		this.sanction = sanctionType;
		
		switch(sanctionType) {
			case SERVER_BAN:
				// new SanctionServerBan(this);
				break;
			case GAMES_BAN:
				// new SanctionGamesBan(this);
				break;
			case KICK:
				// new SanctionKick(this);
				break;
			case MUTE:
				// new SanctionMute(this);
				break;
			default:
				new SanctionMainMenu(this);
				break;
		}
	}
	
	public void create() {
		// ISanction.create(this);
	}
	
	public FaruPlayer getAuthor() {
		return this.author;
	}
	
	public FaruPlayer getTarget() {
		return this.target;
	}
	
	public SanctionType getSanction() {
		return this.sanction;
	}
	
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public void setTime(Integer hours) {
		this.time = hours;
	}
	
	public String getReason() {
		return this.reason;
	}
	
	public Date getDate() {
		return this.date;
	}
	
	public Integer getTime() {
		return this.time;
	}
}
