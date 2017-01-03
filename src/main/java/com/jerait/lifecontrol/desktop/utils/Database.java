package com.jerait.lifecontrol.desktop.utils;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;
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
     * Contain base chunks for tables path.
     */
    private static String[] tablesLocationBaseChunks = {
        "src",
        "main",
        "java"
    };

    /**
     * Contain chunks for tables path.
     */
    private static String[] tablesLocationChunks = {
        "com",
        "jerait",
        "lifecontrol",
        "desktop",
        "table"
    };

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
     * Build relative path to tables classes directory.
     * @return path to tables classes directory.
     */
    protected static String getTablesLocation() {
        return String.join(File.separator, tablesLocationBaseChunks)
            + File.separator
            + String.join(File.separator, tablesLocationChunks);
    }

    /**
     * Build package name for database tables entities.
     * @return package name
     */
    protected static String getTablesPackageName() {
        return String.join(".", tablesLocationChunks);
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
     * All ORM classes must be stored into table folder!
     * @return true, if database created without errors, false another.
     */
    public static Boolean initDatabase() {
        Boolean result = true;

        String packageName = Database.getTablesPackageName();

        try {
            ConnectionSource connection = Database.getDatabaseConnection();

            File tableDir = new File(Database.getTablesLocation());
            File[] tablesList = tableDir.listFiles();

            for (File table: tablesList) {
                try {
                    String classFileName = table.getName().split("\\.")[0];
                    Class className = Class
                        .forName(packageName + "." + classFileName);
                    TableUtils.createTable(connection, className);
                } catch (Exception e) {
                    //todo add action.
                }
            }
        } catch (Exception e) {
            result = false;
        }

        return result;
    }
}
