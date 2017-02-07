package com.jerait.lifecontrol.desktop.utils;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.jerait.lifecontrol.desktop.table.AccountGroup;
import com.jerait.lifecontrol.desktop.table.Category;
import com.jerait.lifecontrol.desktop.table.Option;
import com.jerait.lifecontrol.desktop.table.User;
import com.jerait.lifecontrol.desktop.table.UserPrivilege;
import com.jerait.lifecontrol.desktop.table.UserRole;
import com.jerait.lifecontrol.desktop.table.UserRoleLink;
import com.jerait.lifecontrol.desktop.table.UserRolePrivilegeLink;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;
import java.io.File;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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

            fillDatabaseDefault();
        } catch (Exception e) {
            result = false;
        }

        return result;
    }

    /**
     * Fill database with default data.
     */
    private static void fillDatabaseDefault() {
        fillDatabaseDefaultAccountGroup();
        fillDatabaseDefaultUserPrivilege();

        try {
            int userRoleId = fillDatabaseDefaultUserRole();
            int userId = fillDatabaseDefaultUser();
            linkUserRole(userId, userRoleId);
            fillDefaultOptions();
            fillDefaultCategories();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName())
                .log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Adding default accounts group.
     */
    private static void fillDatabaseDefaultAccountGroup() {
        try {
            AccountGroup generalAccountGroup = new AccountGroup();
            generalAccountGroup.setLabel("%general_account");
            Dao<AccountGroup, ?> accountGroupDao = DaoManager.createDao(
                getDatabaseConnection(),
                AccountGroup.class
            );
            accountGroupDao.create(generalAccountGroup);
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName())
                .log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Adding default privileges list.
     */
    private static void fillDatabaseDefaultUserPrivilege() {
        Map<String, String> privileges = new HashMap<>();

        privileges.put("ADMIN_USERS", "%privilege_admin_users");
        privileges.put(
            "ACCOUNT_VIEW_PROTECTED",
            "%privilege_account_view_protected"
        );
        privileges.put("ACCOUNT_ADD", "%privilege_account_add");
        privileges.put("ACCOUNT_EDIT", "%privilege_account_edit");
        privileges.put("ACCOUNT_DELETE", "%privilege_account_delete");

        try {
            Dao<UserPrivilege, ?> dao = DaoManager.createDao(
                getDatabaseConnection(),
                UserPrivilege.class
            );
            for (Map.Entry<String, String> privilege: privileges.entrySet()) {
                UserPrivilege userPrivilege = new UserPrivilege();

                userPrivilege.setSyskey(privilege.getKey());
                userPrivilege.setLabel(privilege.getValue());

                dao.create(userPrivilege);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName())
                .log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Adding default user role to database.
     * @return user role link id, 0 - if error.
     * @throws SQLException dao errors.
     */
    private static int fillDatabaseDefaultUserRole() throws SQLException {
        UserRole userRole = new UserRole();
        userRole.setName("%Administrator");
        Dao<UserRole, ?> dao = DaoManager.createDao(
                getDatabaseConnection(),
                UserRole.class
        );
        dao.create(userRole);

        int userRoleId = userRole.getUserRoleId();
        linkUserRolePrivilege(userRoleId);

        return userRoleId;
    }

    /**
     * Link all user privileges to default admin role.
     * @param userRoleId default admin user role id.
     * @throws SQLException dao errors.
     */
    private static void linkUserRolePrivilege(final int userRoleId)
        throws SQLException {
        Dao<UserPrivilege, ?> dao = DaoManager.createDao(
            getDatabaseConnection(),
            UserPrivilege.class
        );
        List<UserPrivilege> privileges = dao.queryForAll();

        if (!privileges.isEmpty()) {
            Dao<UserRolePrivilegeLink, ?> daoLink = DaoManager.createDao(
                getDatabaseConnection(),
                UserRolePrivilegeLink.class
            );

            for (UserPrivilege privilege : privileges) {
                UserRolePrivilegeLink roleLink = new UserRolePrivilegeLink();
                roleLink.setUserPrivilegeId(privilege.getUserPrivilegeId());
                roleLink.setUserRoleId(userRoleId);
                daoLink.create(roleLink);
            }
        }
    }

    /**
     * Adding default user and link it to roles.
     * @return user id.
     * @throws SQLException dao errors.
     */
    private static int fillDatabaseDefaultUser() throws SQLException {
        User user = new User();
        user.setUsername("Default user");
        user.setPassword("");
        user.setIsDefault(true);
        user.setInsertDate(new Date());
        user.setIsActive(true);
        Dao<User, ?> dao = DaoManager.createDao(
            getDatabaseConnection(),
            User.class
        );
        dao.create(user);
        return user.getUserId();
    }

    /**
     * Adding link user to user role.
     * @param userId user id for default user.
     * @param userRoleId role id for default user.
     * @throws SQLException dao errors.
     */
    private static void linkUserRole(final int userId, final int userRoleId)
        throws SQLException {
        UserRoleLink link = new UserRoleLink();
        link.setUserId(userId);
        link.setUserRoleId(userRoleId);
        Dao<UserRoleLink, ?> dao = DaoManager.createDao(
            getDatabaseConnection(),
            UserRoleLink.class
        );
        dao.create(link);
    }

    /**
     * Create default options.
     * @throws SQLException on dao errors.
     */
    private static void fillDefaultOptions() throws SQLException {
        // random salt
        Option saltOption = new Option();
        saltOption.setOptionId("salt");
        saltOption.setValue(Tools.saltGenerate());
        Dao<Option, ?> optionDao = DaoManager.createDao(
            getDatabaseConnection(),
            Option.class
        );
        optionDao.create(saltOption);
    }

    /**
     * Create two root categories: income and expense.
     * @throws SQLException on dao errors.
     */
    private static void fillDefaultCategories() throws SQLException {
        Dao<Category, ?> dao = DaoManager.createDao(
            getDatabaseConnection(),
            Category.class
        );

        Category category;

        //income
        category = new Category();
        category.setLabel("%Income");
        category.setIsProtected(Boolean.TRUE);
        category.setCategoryType(Category.CATEGORY_TYPE_INCOME);
        dao.create(category);

        //expense
        category = new Category();
        category.setLabel("%Expense");
        category.setIsProtected(Boolean.TRUE);
        category.setCategoryType(Category.CATEGORY_TYPE_EXPENSE);
        dao.create(category);
    }
}
