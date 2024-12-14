package com.example.web_programming.Connection;

import com.example.web_programming.config.ConfigLoader;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

public class DBConnection {

    public static final Jdbi jdbi;

    static {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = ConfigLoader.get("db.url");
            String username = ConfigLoader.get("db.username");
            String password = ConfigLoader.get("db.password");

            jdbi = Jdbi.create(url, username, password);
            jdbi.installPlugin(new SqlObjectPlugin());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Jdbi getJdbi() {

        return jdbi;
    }

}
