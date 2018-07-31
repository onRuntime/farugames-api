package net.farugames.api.database.sql;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;

/**
 * Created by SweetKebab_ on 2018-07-26
 */
public class SQLDatabase {

    private final String host, database, username, password;
    private final int port;
    private HikariDataSource hikariDataSource;

    public SQLDatabase(String host, String database, String username, String password, int port) {
        this.host = host;
        this.database = database;
        this.username = username;
        this.password = password;
        this.port = port;
    }

    public void connect() {
        try {
            if (!(isConnected())) {
                final HikariConfig hikariConfig = new HikariConfig();
                hikariConfig
                        .setJdbcUrl("jdbc:mysql://" + this.host + ":" + port + "/" + this.database + "?useUnicode=yes");
                hikariConfig.setUsername(username);
                hikariConfig.setPassword(password);
                hikariConfig.setMaxLifetime(600000L);
                hikariConfig.setIdleTimeout(300000L);
                hikariConfig.setLeakDetectionThreshold(0L);
                hikariConfig.setMaximumPoolSize(1024);
                hikariConfig.setConnectionTimeout(10000L);
                hikariConfig.addDataSourceProperty("useSSL", false);
                this.hikariDataSource = new HikariDataSource(hikariConfig);
                System.out.println("[SQLManager] FaruGames vient de se connecter à la base de données.");
            }
        } catch (Exception e) {
            System.out.println("[DatabaseSQL] Erreur: impossible de se connecter à la base de données.");
            return;
        }
    }

    public void disconnect() {
        try {
            if (isConnected()) {
                this.hikariDataSource.close();
                System.out.println("[SQLManager] FaruGames vient de se deconnecter à la base de données.");
            }
        } catch (Exception e) {
            System.out.println("[DatabaseSQL] Erreur: impossible de se déconnecter à la base de données.");
            return;
        }
    }

    public boolean isConnected() {
        return hikariDataSource != null && !hikariDataSource.isClosed();
    }

    public Connection getRessource() {
        try {
            return hikariDataSource.getConnection();
        } catch (Exception e) {
            System.out.println("[DatabaseSQL] Erreur: impossible de récupérer la connection à la base de données.");
            disconnect();
            connect();
            return null;
        }
    }

    public HikariDataSource getHikariDataSource() {
        return hikariDataSource;
    }
}
