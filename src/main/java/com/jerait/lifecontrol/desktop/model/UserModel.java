package com.jerait.lifecontrol.desktop.model;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.jerait.lifecontrol.desktop.table.User;
import com.jerait.lifecontrol.desktop.utils.Database;
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
     * Load default user into instance.
     * @return true, if load success, false another.
     */
    public Boolean loadUser() {
        return loadUser(0);
    }

    /**
     * Load selected user into instance.
     * @param userId user id for load or 0 for default user.
     * @return true if load success, false another.
     */
    public Boolean loadUser(final int userId) {
        Boolean loadResult = false;

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
}
