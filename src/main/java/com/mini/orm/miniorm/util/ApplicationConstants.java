package com.mini.orm.miniorm.util;

import static com.mini.orm.miniorm.MiniOrmApplication.retrieveDBName;

public class ApplicationConstants {

    //Database Configuration Constants
    public static final String DB_USERNAME = "root";
    public static final String DB_PASSWORD = "12345";
    public static final String DB_URL = "jdbc:mysql://localhost:3306/".concat(retrieveDBName() + "?autoReconnect=true");

}
