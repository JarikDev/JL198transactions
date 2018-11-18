package com;

import java.io.IOException;
import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
        String userName = "root";
        String password = "sql123";
        String connectionUrl = "jdbc:mysql://localhost:3306/test?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(connectionUrl, userName, password);
            Statement stat = conn.createStatement();

            conn.setAutoCommit(false);
            stat.execute("drop table IF EXISTS Books");
            stat.executeUpdate("CREATE TABLE Books(id MEDIUMINT NOT NULL AUTO_INCREMENT,name CHAR(30) NOT NULL,dt DATE, PRIMARY KEY(id))");
            stat.executeUpdate("INSERT INTO Books (name) VALUES ('Inferno')");
            Savepoint savepoint = conn.setSavepoint();
            stat.executeUpdate("INSERT INTO Books (name) VALUES ('Davinci code')");
            stat.executeUpdate("INSERT INTO Books (name) VALUES ('Solomon keyn')");

            conn.commit();

        } catch (SQLException e) {
            conn.rollback();
        }
    }
}
























