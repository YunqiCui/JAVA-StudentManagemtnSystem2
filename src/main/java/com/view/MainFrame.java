package com.view;

import com.model.MyStudentModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


public class MainFrame extends JFrame implements ActionListener {

    JTable jt;
    JScrollPane jsp;
    JPanel jp1, jp2;
    JLabel jl1;
    JButton jb1, jb2, jb3, jb4;
    JTextField jtf;
    MyStudentModel ms = null;



    public static void main(String[] args) {
        MainFrame mf = new MainFrame();
    }

    public MainFrame() {

        jp1 = new JPanel();
        jtf = new JTextField(10);
        jb1 = new JButton("Search");
        jb1.addActionListener(this);

        jl1 = new JLabel("Student ID:");

        jp1.add(jl1);
        jp1.add(jtf);
        jp1.add(jb1);

        jp2 = new JPanel();
        jb2 = new JButton("Add");
        jb2.addActionListener(this);
        jb3 = new JButton("Edit");
        jb3.addActionListener(this);
        jb4 = new JButton("Delete");
        jb4.addActionListener(this);

        jp2.add(jb2);
        jp2.add(jb3);
        jp2.add(jb4);


        ms = new MyStudentModel();
        String []paras ={};
        ms.queryStudent("select * from student",paras);
        jt = new JTable(ms);
        jsp = new JScrollPane(jt);

        this.add(jp1, "North");
        this.add(jp2, "South");
        this.add(jsp);
        this.setTitle("Student Management System");
        this.setSize(900, 500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocation(200, 200);
        this.setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==jb1){
            String id = this.jtf.getText().trim();
            String searchSql = "select * from student where stuid = ?";
            String [] paras ={id};
            ms = new MyStudentModel();
            ms.queryStudent(searchSql,paras);
            jt.setModel(ms);

        }else if(e.getSource()==jb2){
            new AddDialog(this,"Add Student",true);
            ms = new MyStudentModel();
            String []emptyparas = {};
            ms.queryStudent("select * from student",emptyparas);
            jt.setModel(ms);


        }else if(e.getSource()==jb3){
            int rownumber = jt.getSelectedRow();
            if(rownumber==-1){
                JOptionPane.showMessageDialog(this,"Please select which record you want to update!");
                return ;
            }
            new UpdateDialog(this,"Update Student", true, ms, rownumber);
            ms = new MyStudentModel();
            String []emptyparas = {};
            ms.queryStudent("select * from student",emptyparas);
            jt.setModel(ms);


        }else if(e.getSource()==jb4){
            int rownumber = jt.getSelectedRow();
            if(rownumber==-1){
                JOptionPane.showMessageDialog(this,"Please select which record you want to delete!");
                return ;
            }
            String stuid = (String)ms.getValueAt(rownumber,0);
            String deletesql = "delete from student where stuid = ?";

            String []paras = {stuid};
            MyStudentModel temp = new MyStudentModel();
            try {
                temp.updateStudent(deletesql,paras);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            ms = new MyStudentModel();
            String []emptyparas = {};
            ms.queryStudent("select * from student",emptyparas);
            jt.setModel(ms);
            JOptionPane.showMessageDialog(this,"Record deleted!");
        }
    }
}