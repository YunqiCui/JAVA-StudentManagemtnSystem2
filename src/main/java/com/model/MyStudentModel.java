package com.model;

import com.dao.StudentDAO;

import javax.swing.table.AbstractTableModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class MyStudentModel extends AbstractTableModel{

    Vector rowdata, colnames;

//    public MyStudentModel(String sql){
//
//
//    }
    public void queryStudent(String sql,String [] paras){

        colnames = new Vector();
        rowdata = new Vector();


        colnames.add("stuid");
        colnames.add("firstname");
        colnames.add("lastname");
        colnames.add("gender");
        colnames.add("age");
        colnames.add("city");
        colnames.add("faculty");

        StudentDAO sd = null;
        try {
             sd = new StudentDAO();
            ResultSet rs = sd.searchStudent(sql,paras);

            while (rs.next()) {
                Vector row = new Vector();
                row.add(rs.getString(1));
                row.add(rs.getString(2));
                row.add(rs.getString(3));
                row.add(rs.getString(4));
                row.add(rs.getInt(5));
                row.add(rs.getString(6));
                row.add(rs.getString(7));
                rowdata.add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sd.close();
        }
    }
    public boolean updateStudent(String sql,String []paras) throws Exception{

        StudentDAO sd = new StudentDAO();
        return sd.updateStudent(sql,paras);
    }

    public int getRowCount() {
        return this.rowdata.size();
    }

    public int getColumnCount() {
        return this.colnames.size();
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        return ((Vector)this.rowdata.get(rowIndex)).get(columnIndex);
    }

    @Override
    public String getColumnName(int col){
        return (String)this.colnames.get(col);
    }
}
