package com.yz.dao;

import com.yz.entity.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class dao {
    public static Connection connection;

    public static Connection getConnection()  throws Exception{
         String userName = "root";
         String passWord = "123456";
         String Driver = "com.mysql.jdbc.Driver";
         String url = "jdbc:mysql://localhost:3306/wallpaper?useSSL=false";
         Class.forName(Driver);
         connection = DriverManager.getConnection(url,userName,passWord);
         return connection;
    }
    public static List<Custom> selectBack(int i) throws Exception{
        String sql = "SELECT wallpaper.id,wallpaper.img_url,category.`name` FROM `wallpaper` " +
                "LEFT JOIN wallpaper_category " +
                "ON wallpaper.id = wallpaper_category.wallpaper_id " +
                "LEFT JOIN category " +
                "ON wallpaper_category.category_id = category.id " +
                "LIMIT "+i+",200";
        if(connection==null || connection.isClosed()){
            connection = getConnection();
        }
        PreparedStatement pre = connection.prepareStatement(sql);
        ResultSet resultSet = pre.executeQuery();
        List<Custom> list = new ArrayList<Custom>();

        while (resultSet.next()){
            Custom custom = new Custom();
            custom.setId(resultSet.getString("id"));
            custom.setImgUrl(resultSet.getString("img_url"));
            custom.setName(resultSet.getString("name"));
            list.add(custom);
        }
        return list;
    }

    public static void main(String[] args) throws Exception{
        List<Custom> list = selectBack(0);
        Iterator it = list.iterator();
        while (it.hasNext()){
            Custom custom =(Custom) it.next();
            System.out.println("id: "+custom.getId());
            System.out.println("img_url: "+custom.getImgUrl());
            System.out.println("name: "+custom.getName());
        }
    }
}
