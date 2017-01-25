package com.jerait.lifecontrol.desktop.model;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.table.DatabaseTableConfig;
import com.jerait.lifecontrol.desktop.table.User;
import com.jerait.lifecontrol.desktop.table.UserPrivilege;
import com.jerait.lifecontrol.desktop.table.UserRoleLink;
import com.jerait.lifecontrol.desktop.table.UserRolePrivilegeLink;
import com.jerait.lifecontrol.desktop.utils.Database;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Model class (Singleton) for manage users.
 */
public final class UserModel {
    /**
     * Contain User instance for Singleton.
     */
    private static UserModel instance;

    /**
     * Contain dao for work with users tables.
     */
    private Dao<User, ?> dao;

    /**
     * Contain current user instance.
     */
    private User user;

    /**
     * Contain user privileges list.
     */
    private List<UserPrivilege> privileges;

    /**
     * Close constructor for Singleton.
     */
    private UserModel() {
        try {
            dao = DaoManager.createDao(
                Database.getDatabaseConnection(),
                User.class
            );
        } catch (SQLException ex) {
            Logger.getLogger(UserModel.class.getName())
                .log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Get instance of User model.
     * @return User instance.
     */
    public static UserModel getInstance() {
        if (!(instance instanceof UserModel)) {
            instance = new UserModel();
        }

        return instance;
    }

    /**
     * load user privileges list into instance property.
     * @param userId user id from db.
     * @throws SQLException on dao or load errors.
     * @throws IOException on raw results errors.
     */
    private void loadUserPrivileges(final int userId)
        throws SQLException, IOException {
        Dao<UserPrivilege, ?> privilegesDao = DaoManager.createDao(
                Database.getDatabaseConnection(),
                UserPrivilege.class
            );

        String query = "SELECT up.* FROM "
            + DatabaseTableConfig.extractTableName(UserRoleLink.class) + " url"
            + " INNER JOIN "
            + DatabaseTableConfig.extractTableName(UserRolePrivilegeLink.class)
            + " urpr ON urpr.user_role_id = url.user_role_id"
            + " INNER JOIN "
            + DatabaseTableConfig.extractTableName(UserPrivilege.class)
            + " up ON up.user_privilege_id = urpr.user_privilege_id"
            + " WHERE url.user_id = " + userId;

        GenericRawResults<UserPrivilege> rawResults= privilegesDao.queryRaw(
            query,
            privilegesDao.getRawRowMapper()
        );

        privileges = rawResults.getResults();

        rawResults.close();
     }

    /**
     * Load selected user into instance.
     * @param userId user id for load properties.
     * @return true if load success, false another.
     */
    public Boolean loadUser(final int userId) {
        Boolean loadResult = false;

        QueryBuilder<User, ?> queryBuilder = dao.queryBuilder();

        try {
            queryBuilder.where().eq("user_id", userId);
            user = queryBuilder.queryForFirst();
            if (user.getUserId() > 0) {
                loadUserPrivileges(user.getUserId());
            }
            loadResult = true;
        } catch (Exception ex) {
            Logger.getLogger(UserModel.class.getName())
                .log(Level.SEVERE, null, ex);
        }

        return loadResult;
    }

    /**
     * Get default user id.
     * @return default user id or 0 if not exist.
     */
    public int getDefaultUserId() {
        int defaultUserId = 0;

        QueryBuilder queryBuilder = dao.queryBuilder();

        try {
            queryBuilder.where()
                .eq(User.IS_DEFAULT_COLUMN_NAME, true);
            List<User> users = queryBuilder.query();
            if (!users.isEmpty()) {
                defaultUserId = users.get(0).getUserId();
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserModel.class.getName())
                .log(Level.SEVERE, null, ex);
        }

        return defaultUserId;
    }

    /**
     * Load user data into instance from db by username.
     * @param username selected user
     * @return true if success, false another.
     */
    public Boolean loadUserByUsername(final String username) {
        Boolean loadResult = false;

        QueryBuilder queryBuilder = dao.queryBuilder();

        try {
            queryBuilder.where()
                .eq(User.USERNAME_COLUMN_NAME, username);
            User user = (User) queryBuilder.queryForFirst();
            int userId = user.getUserId();
            if (userId != 0) {
                loadResult = loadUser(userId);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserModel.class.getName())
                .log(Level.SEVERE, null, ex);
        }

        return loadResult;
    }

    /**
     * Get all usernames for active users list from database.
     * @return Usernames list.
     */
    public String[] getUsernameList() {
        String[] usernames = null;
        QueryBuilder queryBuilder = dao.queryBuilder();

        try {
            queryBuilder.orderBy(User.IS_DEFAULT_COLUMN_NAME, false);
            List<User> users = queryBuilder.query();
            if (!(users.isEmpty())) {
                usernames = new String[users.size()];
                int index = 0;
                for (User user: users) {
                    usernames[index] = user.getUsername();
                    index++;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserModel.class.getName())
                .log(Level.SEVERE, null, ex);
        }

        return usernames;
    }

    /**
     * Check user password for loaded user.
     * @param password raw user password.
     * @return true if correct, false another.
     */
    public Boolean checkPassword(String password) {
        Boolean checkResult = false;

        if (user instanceof User) {
          //todo add md5 prepare from settings solt.
          String passwordHash = password;
          checkResult = passwordHash.equals(user.getPassword());
        }

        return checkResult;
    }
}
