package net.farugames.api.proxy;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import net.farugames.api.core.data.DataType;
import net.farugames.api.core.lang.I18n;
import net.farugames.api.core.lang.Lang;
import net.farugames.api.core.rank.Rank;
import net.farugames.database.sql.accounts.IData;
import net.farugames.database.sql.accounts.IRank;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class ProxiedFaruPlayer {

    private UUID uuid;
    private ProxiedPlayer player;

    private Rank rank;

    private Boolean online = false;

    private Boolean needDisconnect = false;
    private String disconnectReason = I18n.tl(getLanguage(), "api_proxy_disconnect_reason_badaccount");

    private Map<DataType, Object> mapData = new HashMap<DataType, Object>();

    private ProxiedFaruPlayer lastTalk;

    private int permissionLevel;

    public ProxiedFaruPlayer(UUID uuid) {
        this.uuid = uuid;

//        this.loadData();arrête de faire le cheval
        IRank.createAccount(uuid);
        IData.createAccount(uuid);


        this.permissionLevel = IRank.getPermissionLevel(uuid);
        this.setRank(IRank.getRank(uuid));

        this.lastTalk = null;
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

    public String getDisconnectReason() {
        return this.disconnectReason;
    }

    public void setDisconnectReason(String disconnectReason) {
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
    }

    public Boolean isLastTalked() {
        return this.lastTalk != null ? true : false;
    }

    public void setLastTalked(ProxiedFaruPlayer lastTalk) {
        this.lastTalk = lastTalk;
    }

    public ProxiedFaruPlayer getLastTalked() {
        return this.lastTalk;
    }

    public void disconnect() {
        this.online = false;
        this.needDisconnect = false;
        this.disconnectReason = "";
//        this.pushData();
        ProxyFaruGamesAPI.iFaruPlayer.remove(uuid);
    }

    public static ProxiedFaruPlayer getPlayer(UUID uuid) {
        if (ProxyFaruGamesAPI.iFaruPlayer.get(uuid) == null) {
            ProxyFaruGamesAPI.iFaruPlayer.put(uuid, new ProxiedFaruPlayer(uuid));
        }
        return ProxyFaruGamesAPI.iFaruPlayer.get(uuid);
    }

    public int getPermissionLevel() {
        return permissionLevel;
    }
}
