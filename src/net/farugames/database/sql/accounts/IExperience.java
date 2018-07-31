package net.farugames.database.sql.accounts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import net.farugames.database.sql.SQLManager;

/**
 * Created by SweetKebab_ on 2018-07-26
 */
public class IExperience {

    private static String table = "experience";

    public static String getTable() {
        return table;
    }

    public static void createAccount(UUID uuid) {
        try {
            final Connection connection = SQLManager.getRessource();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT experience " + " FROM " + table + " WHERE player_uuid = ?");
            preparedStatement.setString(1, uuid.toString());
            ResultSet rs = preparedStatement.executeQuery();
            if (!rs.next()) {
                preparedStatement.close();
                preparedStatement = connection.prepareStatement(
                        "INSERT INTO " + table + " (player_uuid, experience) VALUES(?, ?)");
                preparedStatement.setString(1, uuid.toString());
                preparedStatement.setInt(2, 0);
                preparedStatement.executeUpdate();
                connection.close();
                rs.close();
                preparedStatement.close();
            }
        } catch (SQLException e) {
            System.out.print("");
            System.out.print("[IExperience] Connexion à la base de données par la table " + table + " impossible : ");
            e.printStackTrace();
            System.out.print("");
        }
    }

    public static void addExperience(UUID uuid, Integer value) {
        try {
            final Connection connection = SQLManager.getRessource();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE " + table + " SET experience = " + getExperience(uuid) + " + ? WHERE player_uuid = ?");
            preparedStatement.setInt(1, value);
            preparedStatement.setString(2, uuid.toString());
            preparedStatement.executeUpdate();
            connection.close();
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.print("");
            System.out.print("[IExperience] Connexion à la base de données par la table " + table + " impossible : ");
            e.printStackTrace();
            System.out.print("");
        }
    }

    public static void setExperience(UUID uuid, Integer values) {
        try {
            final Connection connection = SQLManager.getRessource();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE " + table + " SET experience = ? WHERE player_uuid = ?");
            preparedStatement.setInt(1, values);
            preparedStatement.setString(2, uuid.toString());
            preparedStatement.executeUpdate();
            connection.close();
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.print("");
            System.out.print("[IExperience] Connexion à la base de données par la table " + table + " impossible : ");
            e.printStackTrace();
            System.out.print("");
        }
    }

    public static Integer getExperience(UUID uuid) {
        Integer levelExperience = 0;
        try {
            final Connection connection = SQLManager.getRessource();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT experience FROM " + table + " WHERE player_uuid = ?");
            preparedStatement.setString(1, uuid.toString());
            ResultSet rs = preparedStatement.executeQuery();
            if (!rs.next()) {
                preparedStatement.close();
                return Integer.valueOf(levelExperience);
            }
            levelExperience = rs.getInt("experience");
            connection.close();
            rs.close();
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.print("");
            System.out.print("[IExperience] Connexion à la base de données par la table " + table + " impossible : ");
            e.printStackTrace();
            System.out.print("");
        }
        return Integer.valueOf(levelExperience);
    }
}

