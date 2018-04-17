package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StudentDAO {

    Connection ct;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String url = "jdbc:mysql://localhost:3306/studentmanagement";
    String user = "root";
    String password = "";
    String driver = "com.mysql.jdbc.Driver";


    public StudentDAO() throws Exception{
        Class.forName(driver);
        ct = DriverManager.getConnection(url,user,password);
    }
    public void close(){
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (ct != null) {
                ct.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean updateStudent(String sql,String []paras){
        boolean b = true;
        try{

            ps = ct.prepareStatement(sql);
            //给ps参数问号
            for (int i = 0; i < paras.length; i++) {
                ps.setString(i+1,paras[i]);
            }
            if(ps.executeUpdate()!=1){
                b = false;
            }
        }catch (Exception e){
            b = false;
            e.printStackTrace();
        }finally{
            this.close();
        }
        return b;
    }

    public ResultSet searchStudent(String sql,String []paras){
        try{
            ps = ct.prepareStatement(sql);
            for (int i = 0; i < paras.length; i++) {
                ps.setString(i+1,paras[i]);
            }
            rs = ps.executeQuery();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
//            this.close();
        }
        return rs;
    }
}
