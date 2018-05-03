package com.cc.services;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cc.dao.FeedbackDao;
import com.cc.data.Const;
import com.cc.model.Feedback;
import com.cc.util.Util;

/**
 * Servlet implementation class Feedbacksevers
 */
@WebServlet("/Feedbacksevers")
public class Feedbacksevers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Feedbacksevers() {
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
		String text = request.getParameter("text");
		String result = "";
		Feedback feedback = new Feedback();
		FeedbackDao op = new FeedbackDao();
		//Æ´×°Êý¾Ý
		feedback.setSno(sno);
		feedback.setText(Util.ascllToString(text));
		
		try {
			op.addFeedback(feedback);
			result = Const.FEEDBACK_SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = Const.FEEDBACK_FAILED;
		}
		out.print(result+"");
		out.flush();
		out.close();
		
		System.out.println("result: "+result);
	}

}
