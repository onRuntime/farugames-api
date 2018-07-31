package net.farugames.api.bungee.sanctions;

import net.farugames.api.bungee.BungeeFaruAPI;
import net.farugames.api.spigot.player.FaruPlayer;

import java.util.Date;
import java.util.List;


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
				//new SanctionMainMenu(this);
				break;
		}
		addSanction(this);
	}
	
	public void create() {
//		create(this);
		removeSanction(this.target);
	}
	
	public FaruPlayer getAuthor() {
		return this.author;
	}
	
	public FaruPlayer getTarget() {
		return this.target;
	}
	
	public void setSanction(SanctionType sanctionType) {
		this.sanction = sanctionType;
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
	
	public static void addSanction(Sanction sanction) {
		BungeeFaruAPI.sanctions.add(sanction);
	}
	
	public static void removeSanction(FaruPlayer faruTarget) {
		for(Sanction sanction : getSanctions()) {
			if(sanction.getTarget() == faruTarget) {
				BungeeFaruAPI.sanctions.remove(sanction);
			}
		}
	}
	
	public static List<Sanction> getSanctions() {
		return BungeeFaruAPI.sanctions;
	}
	
	public static Sanction getSanction(FaruPlayer faruTarget) {
		for(Sanction sanction : getSanctions()) {
			if(sanction.getTarget() == faruTarget) {
				return sanction;
			}
		}
		return null;
	}
	
	public static Boolean isSanctionned(FaruPlayer faruTarget) {
		for(Sanction sanction : getSanctions()) {
			if(sanction.getTarget() == faruTarget) {
				return true;
			}
		}
		return false;
	}
}
