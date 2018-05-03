package com.cc.services;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cc.dao.CourseDao;
import com.cc.data.Const;
import com.cc.model.Sc;

/**
 * Servlet implementation class ChangeSc
 */
@WebServlet("/ChangeSc")
public class ChangeSc extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ChangeSc() {
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
		PrintWriter out = response.getWriter();
		String operation = request.getParameter("operation");
		String Sno = request.getParameter("Sno");
		String Cno = request.getParameter("Cno");
		String result = "";
		int cflag = 0;
		int cnumber = 0;
		int cmaxnumber = 0;
		CourseDao op = new CourseDao();
		try {
			cnumber = op.getCnumber(Cno);
			cmaxnumber = op.getCmaxnumber(Cno);
			cflag = op.getCflag(Cno);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// 拼装要操作的数据对象
		Sc sc = new Sc();
		sc.setCno(Cno);
		sc.setSno(Sno);
		sc.setScore(-1);
		if (operation.equals("del")) {
			try {
				op.deluser(sc);
				cnumber--;
				op.updateCourse(Cno, cnumber);
				result = Const.DEL_SUCCESS;
				op.updateCflag(Cno, Const.EXECUTE_SUCCESS);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				result = Const.DEL_FAILED;
			}
		}
		
		if (operation.equals("add")) {
			if (cflag == Const.EXECUTE_SUCCESS) {
				try {
					op.updateCflag(Cno, Const.EXECUTE_FAILED);
					op.addSc(sc);
					cnumber++;
					op.updateCourse(Cno, cnumber);
					result = Const.ADD_SUCCESS;
					if (cnumber < cmaxnumber) {
						try {
							op.updateCflag(Cno, Const.EXECUTE_SUCCESS);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					result = Const.ADD_FAILED;
				}
			}else{
				result = Const.ADD_FAILEDS;
			}
		}
		out.print(result + "");
		out.flush();
		out.close();

		System.out.println(operation + ": " + result + " " + cnumber);
	}

}
