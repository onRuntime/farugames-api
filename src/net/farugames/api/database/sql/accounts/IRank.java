package net.farugames.api.database.sql.accounts;

import net.farugames.api.core.rank.Rank;
import net.farugames.api.database.sql.SQLManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Created by SweetKebab_ on 2018-07-26
 */
public class IRank {

    private static String table = "rank";

    public static String getTable() {
        return table;
    }

    public static void createAccount(UUID uuid) {
        try {
            final Connection connection = SQLManager.getRessource();

            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT rank_name FROM " + table + " WHERE player_uuid = ?");
            preparedStatement.setString(1, uuid.toString());
            ResultSet rs = preparedStatement.executeQuery();
            if(!rs.next()) {
                preparedStatement.close();
                preparedStatement = connection.prepareStatement(
                        "INSERT INTO " + table + " (player_uuid, rank_name, rank_power) VALUES(?, ?, ?)");
                preparedStatement.setString(1, uuid.toString());
                preparedStatement.setInt(2, 0);
                preparedStatement.setInt(3, 0);
                preparedStatement.executeUpdate();
                connection.close();
                rs.close();
                preparedStatement.close();
                return;
            }
            connection.close();
            rs.close();
            preparedStatement.close();
        } catch (SQLException e) {
            System.err.print("[IRank] Error trying to connect to database to the table " + table + " : ");
            e.printStackTrace();
        }
    }



    public static Rank getRank(UUID uuid) {
        Rank rank = null;
        try {
            final Connection connection = SQLManager.getRessource();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT rank_name FROM " + table + " WHERE player_uuid = ?");
            preparedStatement.setString(1, uuid.toString());
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()) {
                return Rank.getRankByIdName(rs.getString("rank_name"));
            }
            connection.close();
            rs.close();
            preparedStatement.close();
        } catch (SQLException e) {
            System.err.print("[IRank] Error trying to connect to database to the table " + table + " : ");
            e.printStackTrace();
        }
        return rank;
    }

    public static int getPermissionLevel(UUID uuid) {
        int power = 0;
        try {
            final Connection connection = SQLManager.getRessource();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT rank_power FROM " + table + " WHERE player_uuid = ?");
            preparedStatement.setString(1, uuid.toString());
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()) {
                return rs.getInt("rank_power");
            }
            preparedStatement.close();
        } catch (SQLException e) {
            System.err.print("[IRank] Error trying to connect to database to the table " + table + " : ");
            e.printStackTrace();
        }
        return power;
    }

    public static void setRank(UUID uuid, Rank rank) {
        try {
            final Connection connection = SQLManager.getRessource();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT rank_name FROM " + table + " WHERE player_uuid = ?");
            preparedStatement.setString(1, uuid.toString());
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()) {
                preparedStatement.close();
                preparedStatement = connection
                        .prepareStatement("UPDATE " + table + " SET rank_name = ? WHERE player_uuid = ?");
                preparedStatement.setString(1, rank.getIdName());
                preparedStatement.setString(2, uuid.toString());
                preparedStatement.executeUpdate();
            }
            preparedStatement.close();
        } catch (SQLException e) {
            System.err.print("[IRank] Error trying to connect to database to the table " + table + " : ");
            e.printStackTrace();
        }
    }
}
