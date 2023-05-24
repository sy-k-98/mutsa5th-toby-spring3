package com.example.springbootgradle.dao;

import com.example.springbootgradle.domain.User;

import java.sql.*;

public class UserDao {

    ConnectionMaker connectionMaker;

    public UserDao(ConnectionMaker connectionMaker) {
        this.connectionMaker = new DConnectionMaker();
    }

    public void add(User user) throws ClassNotFoundException, SQLException {
        Connection conn = connectionMaker.makeConnection();

        PreparedStatement pst = conn.prepareStatement("insert into users(id, name, password) values(?, ?, ?)");
        pst.setString(1, user.getId());
        pst.setString(2, user.getName());
        pst.setString(3, user.getPassword());

        pst.executeUpdate();

        pst.close();
        conn.close();
    }

    public User get(String id) throws ClassNotFoundException, SQLException {
        Connection conn = connectionMaker.makeConnection();

        PreparedStatement pst = conn.prepareStatement("select id, name, password from users where id = ?");
        pst.setString(1, id);
        ResultSet rs = pst.executeQuery();
        rs.next(); //

        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));

        rs.close();
        pst.close();
        conn.close();

        return user;
    }
}
