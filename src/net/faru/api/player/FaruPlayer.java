package net.faru.api.player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.faru.api.bungee.player.FaruBungeePlayer;
import net.faru.api.player.currency.Currency;
import net.faru.api.player.languages.Lang;
import net.faru.api.player.rank.Rank;
import net.faru.api.spigot.SpigotFaruAPI;
import net.faru.api.tools.player.UUIDManager;
import net.faru.data.database.currency.ICurrency;
import net.faru.data.database.player.IExperience;
import net.faru.data.database.player.IPermission;
import net.faru.data.database.rank.IRank;
import net.faru.data.spigot.SpigotFaruData;

public class FaruPlayer extends SpigotFaruAPI {

	private UUID uuid;
	private Player player;
	
	private Rank rank;
	
	private Boolean muted;
	private Boolean banned;
	
	private Integer experience;
	
	private Map<Currency, Integer> mapCurrency = new HashMap<Currency, Integer>();
	
	private List<String> permissionsList = new ArrayList<String>();
	
	public FaruPlayer(String playerName) {
		this.uuid = UUIDManager.getUUID(playerName);
		this.player = Bukkit.getPlayer(playerName);
		
		IRank.createAccount(uuid);
		IPermission.createAccount(uuid);
		ICurrency.createAccount(uuid);
		IExperience.createAccount(uuid);
		
		this.setRank(IRank.getRank(uuid));
		this.loadPermissions();
		this.loadCoins();
		this.loadExperience();
	}
	
	public UUID getUUID() {
		return this.uuid;
	}
	
	public Player getPlayer() {
		return this.player;
	}
	
	public Rank getRank() {
		return this.rank;
	}
	
	public void setRank(Rank rank) {
		this.rank = rank;
	}
	
	public Boolean isMuted() {
		return this.muted;
	}
	
	public void setMuted(Boolean muted) {
		this.muted = muted;
	}
	
	public Boolean isBanned() {
		return this.banned;
	}
	
	public void setBanned(Boolean banned) {
		this.banned = banned;
	}
	
	public void pushPermissions() {
		IPermission.setPermissions(uuid, permissionsList);
	}
	
	public void loadPermissions() {
		for(String permission : IPermission.getPermissions(uuid)) {
			this.permissionsList.add(permission);
		}
	}
	
	public boolean hasPermission(String permission) {
		return this.permissionsList.contains(permission);
	}
	
	public void addPermission(String permission) {
		this.permissionsList.add(permission);
	}
	
	public void removePermission(String permission) {
		this.permissionsList.remove(permission);
	}
	
	public void pushCoins() {
		for (Currency currency : Currency.values()) {
			ICurrency.addCoins(uuid, currency, getCoins(currency));
		}
	}
	
	public void loadCoins() {
		for (Currency currency : Currency.values()) {
			this.mapCurrency.put(currency, ICurrency.getCoins(uuid, currency));
		}
	}
	
	public Integer getCoins(Currency currency) {
		return this.mapCurrency.get(currency);
	}

	public void addCoins(Currency currency, Integer coins) {
		this.mapCurrency.put(currency, getCoins(currency) + coins);
	}
	
	public void removeCoins(Currency currency, Integer coins) {
		this.mapCurrency.put(currency, getCoins(currency) - coins);
	}
	
	public void pushExperience() {
		IExperience.setExperience(uuid, this.getExperience());
	}
	
	public void loadExperience() {
		this.experience = IExperience.getExperience(uuid);
	}
	
	public Integer getExperience() {
		return this.experience;
	}

	public void addExperience(Integer coins) {
		this.experience = experience + coins;
	}
	
	public void removeExperience(Integer coins) {
		this.experience = experience - coins;
	}
	
	public Lang getLanguage() {
		return this == null ?
				Lang.ENGLISH :
					FaruBungeePlayer.getPlayer(uuid).getLanguage();
	}
	
	public static FaruPlayer getPlayer(UUID uuid) {
		if(SpigotFaruData.iFaruPlayer.get(uuid) == null) {
			SpigotFaruData.iFaruPlayer.put(uuid, new FaruPlayer(UUIDManager.getName(uuid.toString())));
		}
		return SpigotFaruData.iFaruPlayer.get(uuid);
	}
}
