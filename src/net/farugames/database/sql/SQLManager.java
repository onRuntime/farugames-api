package net.farugames.database.sql;

import java.sql.Connection;

/**
 * Created by SweetKebab_ on 2018-07-26
 */
public class SQLManager {

    private final String host, database, username, password;
    private final int port;

    private static SQLDatabase sqlDatabase;

    public SQLManager(String host, String database, String username, String password, int port) {
        this.host = host;
        this.database = database;
        this.username = username;
        this.password = password;
        this.port = port;
    }

    public void connect() {
        sqlDatabase = new SQLDatabase(host, database, username, password, port);
        sqlDatabase.connect();
    }

    public void disconnect() {
        sqlDatabase.disconnect();
    }

    public static Connection getRessource() {
        return sqlDatabase.getRessource();
    }

    public static SQLDatabase getSQLDatabase() {
        return sqlDatabase;
    }
}
