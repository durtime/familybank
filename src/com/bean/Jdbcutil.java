package com.bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Jdbcutil {
	public static String db_url="jdbc:mysql://127.0.0.1:3306/familybank?serverTimezone=UTC";
    public static String db_user="root";//���ݵ��û���
    public static String db_password="1224";//���ݿ������
    public static Connection getConnection()//��ȡ���ӣ�����Connection���ͣ���������Ϊstatic������������������ʹ��
    {
        Connection conn=null;
        try
        {
        	Class.forName("com.mysql.cj.jdbc.Driver");//��������
            conn=DriverManager.getConnection(db_url,db_user,db_password);//�������ݿ�
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return conn;
    }
    public static void close(PreparedStatement state,Connection conn)//�رպ���
    {
        if(state!=null)//ֻ��״̬������ʱ���ȹر�״̬
        {
            try
            {
                state.close();
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }
        }
        if(conn!=null)
        {
            try
            {
                conn.close();
            }
            catch(SQLException e)
            {
                 e.printStackTrace();
            }
        }
    }
    public static void close(Statement state,Connection conn)//�رպ���
    {
        if(state!=null)//ֻ��״̬������ʱ���ȹر�״̬
        {
            try
            {
                state.close();
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }
        }
        if(conn!=null)
        {
            try
            {
                conn.close();
            }
            catch(SQLException e)
            {
                 e.printStackTrace();
            }
        }
    }
    public static void close(ResultSet rs,Statement state,Connection conn)
    {
        if(rs!=null)//�н������״̬������ʱ���ȹرս�������ڹر�״̬���ڹر�����
        {
            try
            {
                rs.close();
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }
        }
        if(state!=null)
             
        {
            try
            {
                state.close();
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }
        }
        if(conn!=null)
        {
            try
            {
                conn.close();
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }
        }
    }
}
