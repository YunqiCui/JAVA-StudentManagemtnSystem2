package com.view;

import com.model.MyStudentModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddDialog extends JDialog implements ActionListener {
    JLabel jl1, jl2, jl3, jl4, jl5, jl6, jl7;
    JButton jb1, jb2;
    JTextField jtf1, jtf2, jtf3, jtf4, jtf5, jtf6, jtf7;
    JPanel jp1, jp2, jp3;
    Connection ct = null;
    PreparedStatement ps = null;

    //owner它的父窗口;title窗口名;model指定是模态窗口，还是非模态
    public AddDialog(Frame owner, String title, boolean modal) {
        super(owner, title, modal);//调用父类构造方法，达到模式对话框效果
        jl1 = new JLabel("Student Id");
        jl2 = new JLabel("Firstname");
        jl3 = new JLabel("Lastname");
        jl4 = new JLabel("Gender");
        jl5 = new JLabel("Age");
        jl6 = new JLabel("City");
        jl7 = new JLabel("Faculty");

        jtf1 = new JTextField();
        jtf2 = new JTextField();
        jtf3 = new JTextField();
        jtf4 = new JTextField();
        jtf5 = new JTextField();
        jtf6 = new JTextField();
        jtf7 = new JTextField();

        jb1 = new JButton("Add");
        jb2 = new JButton("Cancle");

        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();

        //设置布局
        jp1.setLayout(new GridLayout(7, 1));
        jp2.setLayout(new GridLayout(7, 1));

        //添加组件
        jp1.add(jl1);
        jp1.add(jl2);
        jp1.add(jl3);
        jp1.add(jl4);
        jp1.add(jl5);
        jp1.add(jl6);
        jp1.add(jl7);

        jp2.add(jtf1);
        jp2.add(jtf2);
        jp2.add(jtf3);
        jp2.add(jtf4);
        jp2.add(jtf5);
        jp2.add(jtf6);
        jp2.add(jtf7);

        jp3.add(jb1);
        jp3.add(jb2);

        this.add(jp1, BorderLayout.WEST);
        this.add(jp2, BorderLayout.CENTER);
        this.add(jp3, BorderLayout.SOUTH);

        jb1.addActionListener(this);
        jb2.addActionListener(this);

        //
        this.setSize(400, 300);
        this.setLocation(300, 300);
        this.setVisible(true);
    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jb1) {
            MyStudentModel tempst = new MyStudentModel();
            String sql = "insert into student values(?,?,?,?,?,?,?)";
            String []paras = {jtf1.getText(),jtf2.getText(),jtf3.getText(),jtf4.getText(),jtf5.getText().toString(),jtf6.getText(),jtf7.getText()};
            boolean a = false;
            try {
                a = tempst.updateStudent(sql,paras);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            if(!a){
                JOptionPane.showMessageDialog(this,"Not able to add record!");
            }else{
                this.dispose();
            }
        } else if (e.getSource() == jb2) {
            this.dispose();
        }

    }
}
