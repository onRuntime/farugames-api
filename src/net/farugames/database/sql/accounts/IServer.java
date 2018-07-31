package net.farugames.database.sql.accounts;

import net.farugames.api.core.server.FaruServer;
import net.farugames.api.core.server.ServerStatut;
import net.farugames.database.sql.SQLManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SweetKebab_ on 2018-07-26
 */
public class IServer {

    private static String table = "servers";

    public static String getTable() {
        return table;
    }

    public static void request(String serverType) {
        try {
            final Connection connection = SQLManager.getRessource();
            PreparedStatement preparedStatement = (PreparedStatement) connection
                    .prepareStatement("INSERT INTO requested_servers (server_type) VALUES (?)");
            preparedStatement.setString(1, serverType.toUpperCase());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            System.err.print("[IServer] Error trying to connect to database : ");
            e.printStackTrace();
        }
    }

    public static void update(FaruServer server) {
        try {
            final Connection connection = SQLManager.getRessource();
            PreparedStatement preparedStatement = (PreparedStatement) connection
                    .prepareStatement("SELECT name FROM " + table + " WHERE name = ?");
            preparedStatement.setString(1, server.getName());
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()) {
                preparedStatement.close();
                preparedStatement = (PreparedStatement) connection
                        .prepareStatement("UPDATE " + table + " SET mode = ?, statut = ?, onlineplayers = ?, onlineplayersname = ? WHERE name = ?");
                preparedStatement.setString(1, server.getMode().toString());
                preparedStatement.setString(2, server.getStatut().toString());
                preparedStatement.setInt(3, server.getOnlinePlayers());
                preparedStatement.setString(4, server.getPlayers() != null ? server.getPlayers().toString() : "NULL");
                preparedStatement.setString(5, server.getName());
                preparedStatement.executeUpdate();
            }
            preparedStatement.close();
        } catch (SQLException e) {
            System.err.print("[IServer] Error trying to connect to database : ");
            e.printStackTrace();
        }
    }

    public static void create(String name, String host, Integer port, String mode, String statut) {
        try {
            final Connection connection = SQLManager.getRessource();
            PreparedStatement preparedStatement = (PreparedStatement) connection
                    .prepareStatement("SELECT name FROM " + table + " WHERE name = ?");
            preparedStatement.setString(1, name);
            ResultSet rs = preparedStatement.executeQuery();
            if(!rs.next()) {
                preparedStatement.close();
                preparedStatement = (PreparedStatement) connection
                        .prepareStatement("INSERT INTO " + table + " (name, host, port, mode, statut, onlineplayers, onlineplayersname) VALUES (?, ?, ?, ?, ?, ?, ?)");
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, host);
                preparedStatement.setInt(3, port);
                preparedStatement.setString(4, mode);
                preparedStatement.setString(5, statut);
                preparedStatement.setInt(6, 0);
                preparedStatement.setString(7, new ArrayList<String>().toString());
                preparedStatement.executeUpdate();
            }
            preparedStatement.close();
        } catch (SQLException e) {
            System.err.print("[IServer] Error trying to connect to database : ");
            e.printStackTrace();
        }
    }

    public static void setStatut(String name, String statut) {
        try {
            final Connection connection = SQLManager.getRessource();
            PreparedStatement preparedStatement = (PreparedStatement) connection
                    .prepareStatement("SELECT name FROM " + table + " WHERE name = ?");
            preparedStatement.setString(1, name);
            ResultSet rs = preparedStatement.executeQuery();
            if(!rs.next()) {
                preparedStatement.close();
                preparedStatement = (PreparedStatement) connection
                        .prepareStatement("UPDATE " + table + " SET statut = ? WHERE name = ?");
                preparedStatement.setString(1, statut);
                preparedStatement.setString(2, name);
                preparedStatement.executeUpdate();
            }
            preparedStatement.close();
        } catch (SQLException e) {
            System.err.print("[IServer] Error trying to connect to database : ");
            e.printStackTrace();
        }
    }

    public static void remove(String name, ServerStatut statut) {
        try {
            final Connection connection = SQLManager.getRessource();
            PreparedStatement preparedStatement = (PreparedStatement) connection
                    .prepareStatement("SELECT name FROM " + table + " WHERE name = ?");
            preparedStatement.setString(1, name);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()) {
                preparedStatement.close();
                preparedStatement = connection
                        .prepareStatement("UPDATE " + table + " SET statut = '" + statut + "' WHERE name = ?");
                preparedStatement.setString(1, name);
                preparedStatement.executeUpdate();
            }
            preparedStatement.close();
        } catch (SQLException e) {
            System.err.print("[IServer] Error trying to connect to database : ");
            e.printStackTrace();
        }
    }

    public static boolean exists(String name) {
        boolean exists = false;
        try {
            final Connection connection = SQLManager.getRessource();
            PreparedStatement preparedStatement = (PreparedStatement) connection
                    .prepareStatement("SELECT name FROM " + table + " WHERE name = ?");
            preparedStatement.setString(1, name);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()) exists = true;
            preparedStatement.close();
        } catch (SQLException e) {
            System.err.print("[IServer] Error trying to connect to database : ");
            e.printStackTrace();
        }
        return exists;
    }

    public static List<FaruServer> getServers() {
        List<FaruServer> list = new ArrayList<FaruServer>();
        try {
            final Connection connection = SQLManager.getRessource();
            PreparedStatement preparedStatement = (PreparedStatement) connection
                    .prepareStatement("SELECT * FROM " + table);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                list.add(FaruServer.getServer(rs.getString("name"), rs.getString("host"), rs.getInt("port")));
            }
        } catch (SQLException e) {
            System.err.print("[IServer] Error trying to connect to database : ");
            e.printStackTrace();
        }
        return list;
    }

    public static int getGlobalOnlinePlayers() {
        int result = 0;
        try {
            final Connection connection = SQLManager.getRessource();
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement("SELECT SUM(onlineplayers) AS total FROM " + table);

            ResultSet rs = preparedStatement.executeQuery();
            if (!rs.next()) {
                return Integer.valueOf(result).intValue();
            }
            result = rs.getInt("total");
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Integer.valueOf(result).intValue();
    }
}

