package com.cc.services;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cc.dao.StudentDao;
import com.cc.data.Const;
import com.cc.model.Student;
import com.cc.util.Util;
import com.google.gson.Gson;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		StudentDao op = new StudentDao();
		Student student = new Student();
		PrintWriter out = response.getWriter();
		String Sno = request.getParameter("Sno");
		String Spassword = request.getParameter("Spassword");
		int result = 0;
		try {
			student = op.getstudent(Sno);
			if (student.getSno() == null) {
				System.out.println("用户不存在");
				result = Const.USER_NOEXIST;
			} else if (student.getSpassword().equals(Spassword))
				result = Const.LOGIN_SUCCESS;
			else
				result = Const.LOGIN_FAILED;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			result = Const.LOGIN_FAILED;
			e.printStackTrace();
		}
		List<Student> list = new ArrayList<Student>();
		student.setSname(Util.stringToAscll(student.getSname()));
		student.setSsex(Util.stringToAscll(student.getSsex()));
		student.setSgrade(Util.stringToAscll(student.getSgrade()));
		student.setDname(Util.stringToAscll(student.getDname()));
		student.setFname(Util.stringToAscll(student.getFname()));
		list.add(student);
		Gson gson = new Gson();
		String json = gson.toJson(list);
		out.print(json+"");
		out.flush();
		out.close();

		// 控制台显示信息
		System.out.println(json);  
		System.out.println(" login:post----ID:" + Sno + ",password:" + Spassword);
		System.out.println(" login:post----result:" + result);
	}

}
