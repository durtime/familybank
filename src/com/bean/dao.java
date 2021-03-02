package com.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;



public class dao {
	public boolean changepass(String username,String newpassword) {
		Connection conn=null;
		PreparedStatement psts=null;
		try {
			conn=Jdbcutil.getConnection();
			String sql="UPDATE user SET password = ? WHERE username = ?";
			psts = conn.prepareStatement(sql);
			psts.setString(1, newpassword);
			psts.setString(2, username);
			psts.executeUpdate();
			Jdbcutil.close(psts, conn);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public User userInfo(String username) {
		System.out.println("userinfo");
		User userinfo = null;
		Connection con = Jdbcutil.getConnection();
		try {
			String sql = "select * from user where username=?";
			PreparedStatement psts = con.prepareStatement(sql);
			psts.setString(1, username);
            ResultSet rs = psts.executeQuery();
			while(rs!=null && rs.next())
			{
				String tusername = rs.getString("username");
				String tphone = rs.getString("phone");
				String tmail = rs.getString("mail");
				double tmoney = rs.getDouble("money");
				userinfo=new User();
				userinfo.setPhone(tphone);
				userinfo.setMail(tmail);
				userinfo.setUsername(tusername);
				userinfo.setMoney(tmoney);
			}
			rs.close();
			psts.close();
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return userinfo;
	}
	//搜索
	public ArrayList<Bill> search(double billnum,String billtype,String date) {
		System.out.println("dao");
		Connection con = Jdbcutil.getConnection();
		ArrayList<Bill> billlist=new ArrayList<Bill>();
		if(billnum!=0||billtype!=""||date!="") {
			try {
				String sql = "select * from bill where billtype=? or billnum=? or date=?";
				PreparedStatement psts = con.prepareStatement(sql);
				psts.setString(1, billtype);
				psts.setDouble(2, billnum);
				psts.setString(3, date);
	            ResultSet rs = psts.executeQuery();
				while(rs!=null && rs.next())
				{
					double tbillnum = rs.getDouble("billnum");
					String tdate = rs.getString("date");
					String tbilltype = rs.getString("billtype");
					String type = rs.getString("type");
					String beizhu = rs.getString("beizhu");
					int billid = rs.getInt("billid");
		            Bill bill = new Bill();
		            bill.setBillnum(tbillnum);
		            bill.setType(type);
		            bill.setDate(tdate);
		            bill.setBilltype(tbilltype);
		            bill.setBeizhu(beizhu);
		            bill.setBillid(billid);
					billlist.add(bill);
				}

				if(rs==null) {
					rs.close();
					psts.close();
					con.close();
					return null;
				}else {
					rs.close();
					psts.close();
					con.close();
					return billlist;
				}
				

			}catch(Exception e) {
				e.printStackTrace();
				return null;
			}
		}else {
			return null;
		}
	}
	//修改行数据
	public boolean change(String id,String billnum,String type,String beizhu) {
		Connection conn=null;
		PreparedStatement psts=null;
		try {
			conn=Jdbcutil.getConnection();
			String sql="UPDATE bill SET billnum = ?,type = ?,beizhu=? WHERE billid = ?";
			psts = conn.prepareStatement(sql);
			psts.setString(1, billnum);
			psts.setString(2, type);
			psts.setString(3, beizhu);
			psts.setString(4, id);
			psts.executeUpdate();

			Jdbcutil.close(psts, conn);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	//删除行数据
	public boolean delete(String id) {
		Connection conn=null;
		PreparedStatement psts=null;
		try {
			conn=Jdbcutil.getConnection();
            String sql = "delete from bill where billid=?";
			psts=conn.prepareStatement(sql);
			psts.setString(1, id);
			psts.executeUpdate();
			Jdbcutil.close(psts, conn);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	//验证用户
	public boolean userTest(String username,String password) {
		Connection conn=null;
		PreparedStatement psts=null;
		try {
			conn=Jdbcutil.getConnection();
            String sql = "select * from user";
			psts=conn.prepareStatement(sql);
            ResultSet rs = psts.executeQuery();
			while(rs!=null && rs.next()) {
				if(rs.getString("username").equals(username)&&rs.getString("password").equals(password)) {
					return true;
				}
			}
			Jdbcutil.close(psts, conn);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		System.out.println("no");
		return false;
	}
	public boolean addBill(Bill bill,double money,String username) {
		Connection conn=null;
		PreparedStatement psts=null;
		try {
			conn=Jdbcutil.getConnection();
            String sql = "insert into bill (billid,billnum,type,date,billtype,beizhu) values (DEFAULT,?,?,?,?,?)";
			psts=conn.prepareStatement(sql);
			psts.setDouble(1, bill.getBillnum());
			psts.setString(2, bill.getType());
			psts.setString(3, bill.getDate());
			psts.setString(4, bill.getBilltype());
			psts.setString(5, bill.getBeizhu());
			
			psts.executeUpdate();
			sql = "update user set money=? where username=?";
			psts=conn.prepareStatement(sql);
			psts.setDouble(1, money);
			psts.setString(2, username);
			psts.executeUpdate();

			Jdbcutil.close(psts, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	public double getMoney(String username) {
		Connection conn=null;
		PreparedStatement psts=null;
		double nowmoney = 0;

		try {
			conn=Jdbcutil.getConnection();
			String sql="select * from user where username=?";
			psts=conn.prepareStatement(sql);
			psts.setString(1, username);
			ResultSet rs = psts.executeQuery();
			if(rs.next()){
				nowmoney = rs.getDouble("money");
			}
			Jdbcutil.close(psts, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nowmoney;
	}
	public int getCount(ArrayList<Bill> list) {
		return list.size();
	}
	//全部查询
	public  ArrayList<Bill> select(){
		Connection con = Jdbcutil.getConnection();
		ArrayList<Bill> billlist=new ArrayList<Bill>();
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery("select * from bill");
			while(rs!=null && rs.next())
			{
				int billid = rs.getInt("billid");
				double billnum = rs.getDouble("billnum");
				String type = rs.getString("type");
				String date = rs.getString("date");
				String billtype = rs.getString("billtype");
				String beizhu = rs.getString("beizhu");
	            Bill bill = new Bill();
	            bill.setBillid(billid);
	            bill.setBillnum(billnum);
	            bill.setType(type);
	            bill.setDate(date);
	            bill.setBilltype(billtype);
	            bill.setBeizhu(beizhu);
				billlist.add(bill);
			}
			rs.close();
			stm.close();
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return billlist;
	}
	
//支出收入表ArrayList<Bill>
		public ArrayList<Bill> selectBilltype(String key) {
			Connection con = Jdbcutil.getConnection();
			ArrayList<Bill> billlist=new ArrayList<Bill>();
			try {
				Statement stm = con.createStatement();
				ResultSet rs = stm.executeQuery("select * from bill where billtype='" + key + "'");
				while(rs!=null && rs.next())
				{
					double billnum = rs.getDouble("billnum");
					String date = rs.getString("date");
					String billtype = rs.getString("billtype");
					String type = rs.getString("type");
					String beizhu = rs.getString("beizhu");
					int billid = rs.getInt("billid");
		            Bill bill = new Bill();
		            bill.setBillnum(billnum);
		            bill.setType(type);
		            bill.setDate(date);
		            bill.setBilltype(billtype);
		            bill.setBeizhu(beizhu);
		            bill.setBillid(billid);
					billlist.add(bill);
				}
				rs.close();
				stm.close();
				con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
			return billlist;
		}
	}
