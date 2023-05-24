package com.example.springbootgradle.dao;

import com.example.springbootgradle.domain.User;

import java.sql.*;

public class UserDao {

    SimpleConnectionMaker connectionMaker = new SimpleConnectionMaker();
    public void add(User user) throws ClassNotFoundException, SQLException {
        Connection conn = connectionMaker.makeNewConnection();

        PreparedStatement pst = conn.prepareStatement("insert into users(id, name, password) values(?, ?, ?)");
        pst.setString(1, user.getId());
        pst.setString(2, user.getName());
        pst.setString(3, user.getPassword());

        pst.executeUpdate();

        pst.close();
        conn.close();
    }

    public User get(String id) throws ClassNotFoundException, SQLException {
        Connection conn = connectionMaker.makeNewConnection();

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

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserDao userDao = new UserDao();
        User user = new User();
//        user.setId("2");
//        user.setName("kimss");
//        user.setPassword("12345678");
//        userDao.add(user);

        User selectedUser = userDao.get("2");
        System.out.println(selectedUser.getId());
        System.out.println(selectedUser.getName());
        System.out.println(selectedUser.getPassword());
    }
}
