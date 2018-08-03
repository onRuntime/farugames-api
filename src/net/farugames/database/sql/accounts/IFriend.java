package net.farugames.database.sql.accounts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import net.farugames.api.core.currency.Currency;
import net.farugames.database.sql.SQLManager;

public class IFriend {
	
	private static String table = "friends";

    public static String getTable() {
        return table;
    }

    public static void createAccount(UUID playerUUID) {
        try {
            final Connection connection = SQLManager.getRessource();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT player_uuid FROM " + table + " WHERE player_uuid = ?");
            preparedStatement.setString(1, playerUUID.toString());
            ResultSet rs = preparedStatement.executeQuery();
            if (!rs.next()) {
                preparedStatement.close();
                preparedStatement = connection.prepareStatement("INSERT INTO " + table
                        + " (player_uuid, money_coins, money_credits) VALUES (?, ?, ?)");
                preparedStatement.setString(1, playerUUID.toString());
                preparedStatement.setInt(2, Currency.COINS.getDefaultValue());
                preparedStatement.setInt(3, Currency.CREDITS.getDefaultValue());
                preparedStatement.executeUpdate();
                preparedStatement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void setFriend(UUID playerUUID, List<UUID> friends) {
    	try(Connection connection = SQLManager.getRessource()) {
    		PreparedStatement preparedStatement = connection
    				.prepareStatement("SELECT player_uuid FROM " + table + " WHERE player_uuid = ?");
    		preparedStatement.setString(1, playerUUID.toString());
    		ResultSet rs = preparedStatement.executeQuery();
    		if(rs.next()) {
    			preparedStatement.close();
        		preparedStatement = connection.prepareStatement("UPDATE " + table + " SET "
                        + "friends_uuid = ? WHERE player_uuid = ?");
        		preparedStatement.setString(1, friends.toString().replaceAll("[", "").replaceAll("]", ""));
        		preparedStatement.setString(2, playerUUID.toString());
        		preparedStatement.executeUpdate();
    			preparedStatement.close();
    		}
    		createAccount(playerUUID);
    	} catch(SQLException exception) {
    		exception.printStackTrace();
    	}
    }
    
    public static void addFriend(UUID playerUUID, UUID targetUUID) {
    	try(Connection connection = SQLManager.getRessource()) {
    		PreparedStatement preparedStatement = connection
    				.prepareStatement("SELECT player_uuid FROM " + table + " WHERE player_uuid = ?");
    		preparedStatement.setString(1, playerUUID.toString());
    		ResultSet rs = preparedStatement.executeQuery();
    		if(!rs.next()) {
    			preparedStatement.close();
    			preparedStatement = connection.prepareStatement("INSERT INTO " + table
    					+ " (player_uuid, friends_uuid) VALUES (?, ?)");
    			preparedStatement.setString(1, playerUUID.toString());
    			preparedStatement.setString(2, targetUUID.toString());
    			preparedStatement.executeUpdate();
    		}
    		preparedStatement.close();
    		preparedStatement = connection.prepareStatement("UPDATE " + table + " SET "
                    + "friends_uuid = ? WHERE player_uuid = ?");
    		preparedStatement.setString(1, getFriends(playerUUID).toString().replaceAll("[", "").replaceAll("]", "") + ", " + targetUUID.toString());
    		preparedStatement.setString(2, playerUUID.toString());
    		preparedStatement.executeUpdate();
			preparedStatement.close();
    	} catch(SQLException exception) {
    		exception.printStackTrace();
    	}
    }
    
    public static void removeFriend(UUID playerUUID, UUID targetUUID) {
    	try(Connection connection = SQLManager.getRessource()) {
    		PreparedStatement preparedStatement = connection
    				.prepareStatement("SELECT player_uuid FROM " + table + " WHERE player_uuid = ?");
    		preparedStatement.setString(1, playerUUID.toString());
    		ResultSet rs = preparedStatement.executeQuery();
    		if(rs.next()) {
    			preparedStatement.close();
        		preparedStatement = connection.prepareStatement("UPDATE " + table + " SET "
                        + "friends_uuid = ? WHERE player_uuid = ?");
        		preparedStatement.setString(1, getFriends(playerUUID).contains(targetUUID) ? getFriends(playerUUID).toString().replaceAll(", " + targetUUID.toString(), "") : getFriends(playerUUID).toString().replaceAll("[", "").replaceAll("]", ""));
        		preparedStatement.setString(2, playerUUID.toString());
        		preparedStatement.executeUpdate();
    			preparedStatement.close();
    		}
    	} catch(SQLException exception) {
    		exception.printStackTrace();
    	}
    }
    
    public static List<UUID> getFriends(UUID playerUUID) {
    	List<UUID> friends = new ArrayList<UUID>();
    	try(Connection connection = SQLManager.getRessource()) {
    		PreparedStatement preparedStatement = connection
    				.prepareStatement("SELECT friends_uuid FROM " + table + " WHERE player_uuid = ?");
    		preparedStatement.setString(1, playerUUID.toString());
    		ResultSet rs = preparedStatement.executeQuery();
    		if(rs.next()) {
    			for(String friendUUID : split(rs.getString("friends_uuid"))) {
    				friends.add(UUID.fromString(friendUUID));
    			}
    		}
    	} catch(SQLException exception) {
    		exception.printStackTrace();
    	}
    	return friends;
    }
    
    public static String[] split(String text) {
    	return text.split(", ");
    }
}
