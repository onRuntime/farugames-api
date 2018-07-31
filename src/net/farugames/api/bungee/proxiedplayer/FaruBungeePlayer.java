package net.farugames.api.bungee.proxiedplayer;

import net.farugames.api.bungee.BungeeFaruAPI;
import net.farugames.api.spigot.player.data.DataType;
import net.farugames.api.spigot.player.languages.Lang;
import net.farugames.api.spigot.player.rank.Rank;
import net.farugames.api.sql.accounts.IData;
import net.farugames.api.sql.accounts.IRank;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class FaruBungeePlayer {

    private UUID uuid;
    private ProxiedPlayer player;

    private Rank rank;

    private Boolean online = false;

    private Boolean needDisconnect = false;
    private Lang disconnectReason = Lang.BAD_ACCOUNT;

    private Map<DataType, Object> mapData = new HashMap<DataType, Object>();

    private FaruBungeePlayer lastTalk;

    private int permissionLevel;

    private long cookie;
    private long candy;

    public FaruBungeePlayer(UUID uuid) {
        this.uuid = uuid;

//        this.loadData();
        IRank.createAccount(uuid);
        IData.createAccount(uuid);


        this.permissionLevel = IRank.getPermissionLevel(uuid);
        this.setRank(IRank.getRank(uuid));

        this.lastTalk = null;
    }

    private void createAccounts() {

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
//
//    public void pushData() {
//        for (DataType dataType : DataType.values()) {
//            BungeeFaruData.getInstance().getBungeeDatabase().setData(uuid, dataType, this.mapData.get(dataType));
//        }
//    }
//
//    public void loadData() {
//        for (DataType dataType : DataType.values()) {
//            this.mapData.put(dataType, BungeeFaruData.getInstance().getBungeeDatabase().getData(uuid, dataType));
//        }
//
//    }

    public void setData(DataType dataType, Object values) {
        this.mapData.put(dataType, values);
    }

    public Object getData(DataType dataType) {
        return this.mapData.get(dataType);
    }

    //TODO : Fully functionnal lang system
    public Lang getLanguage() {
        return Lang.ENGLISH;
//        return Lang.valueOf(String.valueOf(this.getData(DataType.LANGUAGE)));
    }

    public Boolean isLastTalked() {
        return this.lastTalk != null ? true : false;
    }

    public void setLastTalked(FaruBungeePlayer lastTalk) {
        this.lastTalk = lastTalk;
    }

    public FaruBungeePlayer getLastTalked() {
        return this.lastTalk;
    }

    public void disconnect() {
        this.online = false;
        this.needDisconnect = false;
        this.disconnectReason = Lang.BAD_ACCOUNT;
//        this.pushData();
        BungeeFaruAPI.iFaruPlayer.remove(uuid);
    }

    public static FaruBungeePlayer getPlayer(UUID uuid) {
        if (BungeeFaruAPI.iFaruPlayer.get(uuid) == null) {
            BungeeFaruAPI.iFaruPlayer.put(uuid, new FaruBungeePlayer(uuid));
        }
        return BungeeFaruAPI.iFaruPlayer.get(uuid);
    }

    public int getPermissionLevel() {
        return permissionLevel;
    }
}
