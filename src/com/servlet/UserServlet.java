package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.User;
import com.bean.dao;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class loginServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/text;charset=UTF-8");
		String method = request.getParameter("method");
		
		if(method.equals("login")) {
			System.out.println("login");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String rememberMe = request.getParameter("rememberMe");
			dao dao = new dao();
			boolean userTest = dao.userTest(username, password);
			String flag = userTest? "true" : "false";
			response.getWriter().write(flag);
			if(userTest) {
		            HttpSession session = request.getSession();
		            session.setAttribute("username", username);
			}
		}else if(method.equals("info")) {
            HttpSession session = request.getSession();
            String attribute = (String)session.getAttribute("username");
			dao dao = new dao();
			User userInfo = dao.userInfo(attribute);
			JsonAction userAction = new JsonAction();
			JSONObject json = userAction.find3(1, userInfo);
			response.getWriter().write(json.toString());
		}else if(method.equals("changepass")) {
			System.out.println("changepass");
            HttpSession session = request.getSession();
            String username = (String)session.getAttribute("username");
			String old_password = request.getParameter("old_password");
			String new_password = request.getParameter("new_password");
			String again_password = request.getParameter("again_password");
			dao dao = new dao();
			String flag="false";
			boolean userTest = dao.userTest(username, old_password);
			if(userTest) {
				boolean changepass = dao.changepass(username, again_password);
				if(changepass) {
					flag = changepass? "true" : "false";
					response.getWriter().write(flag);
				}else {
					response.getWriter().write(flag);
				}
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
