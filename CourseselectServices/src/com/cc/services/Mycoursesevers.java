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

import com.cc.dao.CourseDao;
import com.cc.model.Course;
import com.cc.util.Util;
import com.google.gson.Gson;

/**
 * Servlet implementation class Mycoursesevers
 */
@WebServlet("/Mycoursesevers")
public class Mycoursesevers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Mycoursesevers() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		String sno = request.getParameter("sno");
		List<Course> result = new ArrayList<Course>();
		CourseDao op = new CourseDao();

		try {
			result = op.queryCourse(sno);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for(int i = 0; i < result.size() ; i++)  
		{  
		     Course course = result.get(i);
		     course.setCname(Util.stringToAscll(course.getCname()));
		     course.setCgrade(Util.stringToAscll(course.getCgrade()));
		     course.setFname(Util.stringToAscll(course.getFname()));
		     course.setCteacher(Util.stringToAscll(course.getCteacher()));
		}
		Gson gson = new Gson();
		String json = gson.toJson(result);
		out.print(json+"");
		out.flush();
		out.close();

		// 控制台显示信息
		System.out.println(json);  
		System.out.println(" mycourse:post----:" +sno);
	}


}
