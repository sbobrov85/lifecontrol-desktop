package com.jerait.lifecontrol.desktop.utils;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;
import com.jerait.lifecontrol.desktop.table.Account;
import java.io.File;

/**
 * Utility class for manage database connection.
 */
public final class Database {
    /**
     * Contain common database name with extension.
     */
    private static String databaseName = "lifecontrol.db";

    /**
     * Contain database connection.
     */
    private static ConnectionSource databaseConnection;

    /**
     * Close constructor for utility class.
     */
    private Database() {
    }

    /**
     * Get full name for database file.
     * @return {String}
     */
    protected static String getDatabaseFullName() {
        String userHome = System.getProperty("user.home");
        return userHome + File.separator + databaseName;
    }

    /**
     * Return database connection, with create if not connected.
     * @return {ConnectionSource}
     */
    public static ConnectionSource getDatabaseConnection() {
        if (!(databaseConnection instanceof ConnectionSource)) {
            try {
                databaseConnection = new JdbcConnectionSource(
                    "jdbc:sqlite:" + getDatabaseFullName()
                );
            } catch (SQLException e) {
                databaseConnection = null;
            }
        }

        return databaseConnection;
    }

    /**
     * Check if database exists.
     * @return {Boolean} true, if exists, false another.
     */
    public static Boolean exists() {
        Path databasePath = FileSystems
            .getDefault()
            .getPath(getDatabaseFullName());

        return Files.exists(databasePath);
    }

    /**
     * Create database file and tables structure.
     * @return true, if database created without errors, false another.
     */
    public static Boolean initDatabase() {
        Boolean result = true;

        try {
            ConnectionSource connection = Database.getDatabaseConnection();
            TableUtils.createTable(connection, Account.class);
        } catch (Exception e) {
            result = false;
        }

        return result;
    }
}
