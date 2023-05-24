package com.example.springbootgradle.db;

import java.sql.*;
import java.util.Map;

import static java.lang.System.getenv;

public class ConnectChecker {
    public void check() throws ClassNotFoundException, SQLException {
        Map<String, String> env = getenv();
        String dbHost = env.get("DB_HOST");
        String dbUser = env.get("DB_USER");
        String dbPassword = env.get("DB_PASSWORD");

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(
                dbHost, dbUser, dbPassword
        );
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SHOW DATABASES");
        rs = st.getResultSet();

        while(rs.next()) {
            String str = rs.getString(1);
            System.out.println(str);
        }
    }

    public void add() throws SQLException, ClassNotFoundException {
        Map<String, String> env = getenv();
        String dbHost = env.get("DB_HOST");
        String dbUser = env.get("DB_USER");
        String dbPassword = env.get("DB_PASSWORD");

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(
                dbHost, dbUser, dbPassword
        );
        PreparedStatement pst = con.prepareStatement("insert into users(id, name, password) values (?, ?, ?)");
        pst.setInt(1, 1);
        pst.setString(2, "lion");
        pst.setString(3, "1234");
        pst.executeUpdate();
    }

    public void select() throws SQLException, ClassNotFoundException {
        Map<String, String> env = getenv();
        String dbHost = env.get("DB_HOST");
        String dbUser = env.get("DB_USER");
        String dbPassword = env.get("DB_PASSWORD");

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(
                dbHost, dbUser, dbPassword
        );
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM users");
        rs = st.getResultSet();
        while(rs.next()) {
            Integer col1 = rs.getInt(1);
            String col2 = rs.getString(2);
            String col3 = rs.getString(3);
            System.out.println(col1 + col2 + col3);
        }
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ConnectChecker cc = new ConnectChecker();
        cc.select();
    }
}
