package com.mini.orm.miniorm.repo;

import java.sql.*;
import static com.mini.orm.miniorm.util.ApplicationConstants.*;

public class OrmRepository {

    private static OrmRepository ormRepositoryInstance = null;
    private static final Object threadLock = new Object();

    Connection connection;

    /**
     * Create object and opens a connection to the database
     */
    public OrmRepository() {
        openConnection();
    }

    public void openConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            if (connection == null) {
                System.err.println("DBConnect: getConnection: connection null ");
            }
        } catch (Exception e) {
            System.out.println("DBConnect: getConnection: exception: " + e.getMessage());
        }
    }

    /**
     * Return an instance of OrmRepository class
     *
     * @return ormRepository instance
     */
    public static OrmRepository getInstance() {
        synchronized (threadLock) {
            return ormRepositoryInstance == null ? ormRepositoryInstance = new OrmRepository() : ormRepositoryInstance;
        }
    }

    /**
     * Closes a database connection
     */
    public void closeConnection() {
        try {
            connection.close();
        } catch (Exception e) {
            System.err.println("DBConnect::closeConnection: Exception: " + e.getMessage());
        }
    }

    /**
     * Cleans up system resources after database connection closes
     * @throws Throwable
     */
    protected void finalize() throws Throwable {
        closeConnection();
        super.finalize();
    }

}
