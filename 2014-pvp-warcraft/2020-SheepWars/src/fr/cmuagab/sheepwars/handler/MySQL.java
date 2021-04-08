package fr.cmuagab.sheepwars.handler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.bukkit.plugin.Plugin;

/**
 * Connects to and uses a MySQL database
 * 
 * @author -_Husky_-
 * @author tips48
 */
public class MySQL extends Database {
    private final String user;
    private final String database;
    private final String password;
    private final String port;
    private final String hostname;

    /**
     * Creates a new MySQL instance
     * 
     * @param plugin
     *            Plugin instance
     * @param hostname
     *            Name of the host
     * @param port
     *            Port number
     * @param database
     *            Database name
     * @param username
     *            Username
     * @param password
     *            Password
     */
    public MySQL(final Plugin plugin, final String hostname, final String port, final String database, final String username, final String password) {
        super(plugin);
        this.hostname = hostname;
        this.port = port;
        this.database = database;
        this.user = username;
        this.password = password;
    }

    @Override
    public Connection openConnection() throws SQLException, ClassNotFoundException {
        if (this.checkConnection()) { return this.connection; }
        Class.forName("com.mysql.jdbc.Driver");
        this.connection = DriverManager.getConnection("jdbc:mysql://" + this.hostname + ":" + this.port + "/" + this.database, this.user, this.password);
        return this.connection;
    }
}
