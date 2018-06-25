package net.faru.api.bungee.player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import net.faru.api.player.data.DataType;
import net.faru.api.player.languages.Lang;
import net.faru.api.player.rank.Rank;
import net.faru.data.bungee.BungeeFaruData;
import net.faru.data.database.player.IData;
import net.faru.data.database.rank.IRank;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class FaruBungeePlayer {

	private UUID uuid;
	private ProxiedPlayer player;
	
	private Rank rank;
	
	private Boolean online = false;
	
	private Boolean needDisconnect = false;
	private Lang disconnectReason = Lang.BAD_ACCOUNT;
	
	private Map<DataType, Object> mapData = new HashMap<DataType, Object>();
	
	public FaruBungeePlayer(UUID uuid) {
		this.uuid = uuid;
		
		IRank.createAccount(uuid);
		IData.createAccount(uuid, this.player);
		
		this.loadData();
		this.setRank(IRank.getRank(uuid));
	}
	
	public UUID getUUID() {
		return this.uuid;
	}
	
	public void setPlayer(ProxiedPlayer player) {
		this.player = player;
	}
	
	public ProxiedPlayer getPlayer() {
		return this.player;
	}
	
	public Rank getRank() {
		return this.rank;
	}
	
	public void setRank(Rank rank) {
		this.rank = rank;
	}
	
	public Boolean isOnline() {
		return this.online;
	}
	
	public void isOnline(Boolean online) {
		this.online = online;
	}
	
	public Lang getDisconnectReason() {
		return this.disconnectReason;
	}
	
	public void setDisconnectReason(Lang disconnectReason) {
		this.disconnectReason = disconnectReason;
	}
	
	public Boolean needDisconnect() {
		return this.needDisconnect;
	}
	
	public void needDisconnect(Boolean needDisconnect) {
		this.needDisconnect = needDisconnect;
	}
	
	public void pushData() {
		for (DataType dataType : DataType.values()) {
			IData.setData(uuid, dataType, this.mapData.get(dataType));
		}
	}
	
	public void pushData(DataType dataType) {
		IData.setData(uuid, dataType, this.mapData.get(dataType));
	}
	
	public void loadData() {
		for (DataType dataType : DataType.values()) {
			this.mapData.put(dataType, IData.getData(uuid, dataType));
		}
	}
	
	public void setData(DataType dataType, boolean values) {
		this.mapData.put(dataType, values);
	}

	public Object getData(DataType dataType) {
		return this.mapData.get(dataType);
	}
	
	public Lang getLanguage() {
		return Lang.valueOf(String.valueOf(this.getData(DataType.LANGUAGE)));
	}
	
	public void disconnect() {
		this.online = false;
		this.needDisconnect = false;
		this.disconnectReason = Lang.BAD_ACCOUNT;
		this.pushData();
		IRank.setRank(this.uuid, this.rank);
	}
	
	public static FaruBungeePlayer getPlayer(UUID uuid) {
		if(BungeeFaruData.iFaruPlayer.get(uuid) == null) {
			BungeeFaruData.iFaruPlayer.put(uuid, new FaruBungeePlayer(uuid));
		}
		return BungeeFaruData.iFaruPlayer.get(uuid);
	}
}
