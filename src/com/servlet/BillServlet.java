package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;

import com.bean.Bill;
import com.bean.dao;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class addBillServlet
 */
@WebServlet("/BillServlet")
public class BillServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/text;charset=UTF-8");
        String method = request.getParameter("method");
        HttpSession session = request.getSession();
        String username =(String) session.getAttribute("username");
        System.out.println("BillServlet");
        if(method.equals("addshouru")) {
        	double billnum = Double.parseDouble(request.getParameter("billnum"));
            String type = request.getParameter("type");
            String date = request.getParameter("date");
            String billtype = request.getParameter("billtype");
            String beizhu = request.getParameter("beizhu");
            Bill bill = new Bill();
            bill.setBillnum(billnum);
            bill.setType(type);
            bill.setDate(date);
            bill.setBilltype(billtype);
            bill.setBeizhu(beizhu);
			try {
				dao dao = new dao();
				double money = dao.getMoney(username)+billnum;
				dao.addBill(bill,money,username);
				request.setAttribute(method, "shouru");
				request.getRequestDispatcher("addsucss.jsp").forward(request,response);
			} catch (Exception e) {
				e.printStackTrace();
			}
        }else if(method.equals("addzhichu")) {
        	double billnum = Double.parseDouble(request.getParameter("billnum"));
            String type = request.getParameter("type");
            String date = request.getParameter("date");
            String billtype = request.getParameter("billtype");
            String beizhu = request.getParameter("beizhu");
            Bill bill = new Bill();
            bill.setBillnum(billnum);
            bill.setType(type);
            bill.setDate(date);
            bill.setBilltype(billtype);
            bill.setBeizhu(beizhu);
			try {
				dao dao = new dao();
				double money = dao.getMoney(username)-billnum;
				dao.addBill(bill,money,username);
				request.setAttribute(method, "zhichu");
				request.getRequestDispatcher("addsucss.jsp").forward(request,response);
			} catch (Exception e) {
				e.printStackTrace();
			}
        }else if(method.equals("search")) {
        	try {
				dao dao = new dao();
				System.out.println("search");
				double fbillnum=0;
				if(request.getParameter("fbillnum")!="")
				{
		        	fbillnum = Double.parseDouble(request.getParameter("fbillnum"));
				}
		        String fbilltype = request.getParameter("fbilltype");
		        String fdate = request.getParameter("fdate");

		        ArrayList<Bill> flag = dao.search(fbillnum,fbilltype,fdate);
		        if(flag!=null) {
					int count = dao.getCount(flag);
					JsonAction billAction = new JsonAction();
					JSONObject json = billAction.findAll2(count, flag);
					response.getWriter().write(json.toString());
				}else {
					response.getWriter().write("false");
				}

        	} catch (Exception e) {
				e.printStackTrace();
			}
        }else if(method.equals("delete")) {
        	try {
				dao dao = new dao();
				System.out.println("delete");
		        String id = request.getParameter("id");
				boolean flag = dao.delete(id);
				String myflag = flag ? "true" : "false";
				response.getWriter().write(myflag);
        	} catch (Exception e) {
				e.printStackTrace();
			}
        }else if(method.equals("select")) {
			try {
				System.out.println("select");
				dao dao = new dao();
				ArrayList<Bill> billlist=dao.select();
				int count = dao.getCount(billlist);
				JsonAction billAction = new JsonAction();
				JSONObject json = billAction.findAll2(count, billlist);
				response.getWriter().write(json.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(method.equals("zhichulist")) {
			try {
				System.out.println("支出");
				dao dao = new dao();
				ArrayList<Bill> billlist=dao.selectBilltype("支出");
				double allzhinum=0;
				for(int i=0;i<billlist.size();i++) {
					allzhinum += billlist.get(i).getBillnum();
				}
				int count = dao.getCount(billlist);
				JsonAction billAction = new JsonAction();
				JSONObject json = billAction.findAll2(count, billlist);
				response.getWriter().write(json.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
        }else if(method.equals("shourulist")) {
			try {
				System.out.println("收入");
				dao dao = new dao();
				ArrayList<Bill> billlist=dao.selectBilltype("收入");
				double allshounum=0;
				for(int i=0;i<billlist.size();i++) {
					allshounum += billlist.get(i).getBillnum();
				}
				int count = dao.getCount(billlist);
				JsonAction billAction = new JsonAction();
				JSONObject json = billAction.findAll2(count, billlist);
				response.getWriter().write(json.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
        }else if(method.equals("change")) {
			try {
				System.out.println("修改");
				dao dao = new dao();
		        String id = request.getParameter("id");
		        String billnum = request.getParameter("billnum");
		        String stype = request.getParameter("stype");
		        String sbeizhu = request.getParameter("sbeizhu");
		        boolean flag = dao.change(id,billnum,stype,sbeizhu);
				String myflag = flag ? "true" : "false";
		        //request.setAttribute("thisbill", flag);
				response.getWriter().write(myflag);
				System.out.println("ok");

				//request.getRequestDispatcher("editbill.html").forward(request,response);
			} catch (Exception e) {
				e.printStackTrace();
			}
        }

    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
