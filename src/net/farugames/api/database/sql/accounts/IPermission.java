package net.farugames.api.database.sql.accounts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import net.farugames.api.database.sql.SQLManager;

/**
 * Created by SweetKebab_ on 2018-07-26
 */
public class IPermission {

    private static String table = "permission";

    public static String getTable() {
        return table;
    }
    public static void removePermission(UUID uuid, String permission) {
        try {
            final Connection connection = SQLManager.getRessource();
            PreparedStatement preparedStatement = (PreparedStatement) connection
                    .prepareStatement("DELETE FROM `permission` WHERE `"+table+"`.`player_uuid` = ? AND permission = ?");
            preparedStatement.setString(1, uuid.toString());
            preparedStatement.setString(2, permission);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.print("");
            System.out.print("[IPermission] Connexion à la base de données par la table " + table + " impossible : ");
            e.printStackTrace();
            System.out.print("");
        }
    }

    public static void addPermission(UUID uuid, String permission) {
        try {
            final Connection connection = SQLManager.getRessource();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO " + table + " (player_uuid, permission) VALUES (?, ?)");
            preparedStatement.setString(1, uuid.toString());
            preparedStatement.setString(2, permission);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addPermissionWithTimer(UUID uuid, String permission, Integer time_in_minute) {
        try {
            final Connection connection = SQLManager.getRessource();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO " + table
                    + " (player_uuid, permission, permission_duration) VALUES (?, ?, DATE_ADD(NOW(), INTERVAL "
                    + time_in_minute + " MINUTE))");
            preparedStatement.setString(1, uuid.toString());
            preparedStatement.setString(2, permission);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<String> getPermissions(UUID uuid) {
        suppPermissionsTime();
        List<String> permissionList = new ArrayList<String>();
        try {
            final Connection connection = SQLManager.getRessource();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT permission FROM " + table + " WHERE player_uuid = ?");
            preparedStatement.setString(1, uuid.toString());
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                permissionList.add((rs.getString("permission")));
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return permissionList;
    }

    private static void suppPermissionsTime() {
        try {
            final Connection connection = SQLManager.getRessource();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM " + table + " WHERE permission_duration <= NOW()");
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

