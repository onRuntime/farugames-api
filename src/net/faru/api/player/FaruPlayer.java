package net.faru.api.player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.faru.api.player.currency.Currency;
import net.faru.api.player.rank.Rank;
import net.faru.api.tools.player.UUIDManager;
import net.faru.data.database.currency.ICurrency;
import net.faru.data.database.player.IPermission;
import net.faru.data.database.rank.IRank;

public class FaruPlayer {

	private UUID uuid;
	private Player player;
	
	private Rank rank;
	
	private Map<Currency, Integer> mapCurrency = new HashMap<Currency, Integer>();
	
	private List<String> permissionsList = new ArrayList<String>();
	
	public FaruPlayer(String playerName) {
		this.uuid = UUIDManager.getUUID(playerName);
		this.player = Bukkit.getPlayer(playerName);
		
		IRank.createAccount(uuid);
		IPermission.createAccount(uuid);
		ICurrency.createAccount(uuid);
		
		this.setRank(IRank.getRank(uuid));
		this.loadPermissions();
		this.loadCoins();
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
}
