package Model;

import java.sql.*;

public class Database {
    private static final String USER = "UserAdmin";
    private static final String PASS = "UserAdmin123";
    private static final String URL = "jdbc:mysql://localhost:3306/supermarket";

    public Database() {

    }
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

}
