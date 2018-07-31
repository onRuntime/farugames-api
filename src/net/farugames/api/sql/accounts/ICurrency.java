package net.farugames.api.sql.accounts;

import net.farugames.api.spigot.player.currency.Currency;
import net.farugames.api.sql.SQLManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Created by SweetKebab_ on 2018-07-26
 */
public class ICurrency {
    private static String table = "currency";

    public static String getTable() {
        return table;
    }

    public static void createAccount(UUID uuid) {
        try {
            final Connection connection = SQLManager.getRessource();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT money_coins FROM " + table + " WHERE player_uuid = ?");
            preparedStatement.setString(1, uuid.toString());
            ResultSet rs = preparedStatement.executeQuery();
            if (!rs.next()) {
                preparedStatement.close();
                preparedStatement = connection.prepareStatement("INSERT INTO " + table
                        + " (player_uuid, money_coins, money_credits) VALUES (?, ?, ?)");
                preparedStatement.setString(1, uuid.toString());
                preparedStatement.setInt(2, Currency.COINS.getDefaultValue());
                preparedStatement.setInt(3, Currency.CREDITS.getDefaultValue());
                preparedStatement.executeUpdate();
                preparedStatement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addCoins(UUID uuid, Currency currency, Integer money) {
        try {
            final Connection connection = SQLManager.getRessource();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE " + table + " SET "
                    + currency.getColumn() + " = " + currency.getColumn() + " + ? WHERE player_uuid = ?");
            preparedStatement.setInt(1, money);
            preparedStatement.setString(2, uuid.toString());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void setCoins(UUID uuid, Currency currency, Integer money) {
        try {
            final Connection connection = SQLManager.getRessource();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE " + table + " SET " + currency.getColumn() + " = ? WHERE player_uuid = ?");
            preparedStatement.setInt(1, money);
            preparedStatement.setString(2, uuid.toString());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void removeCoins(UUID uuid, Currency currency, Integer money) {
        try {
            final Connection connection = SQLManager.getRessource();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE " + table + " SET "
                    + currency.getColumn() + " = " + currency.getColumn() + " - ? WHERE player_uuid = ?");
            preparedStatement.setInt(1, money);
            preparedStatement.setString(2, uuid.toString());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Integer getCoins(UUID uuid, Currency currency) {
        Integer coins = 0;
        try {
            final Connection connection = SQLManager.getRessource();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT " + currency.getColumn() + " FROM " + table + " WHERE player_uuid = ?");
            preparedStatement.setString(1, uuid.toString());
            ResultSet rs = preparedStatement.executeQuery();
            if (!rs.next()) {
                return Integer.valueOf(coins);
            }
            coins = rs.getInt(currency.getColumn());
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Integer.valueOf(coins);
    }
}

