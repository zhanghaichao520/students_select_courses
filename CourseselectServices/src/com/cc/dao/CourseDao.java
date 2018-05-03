package com.cc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cc.model.Course;
import com.cc.model.Sc;
import com.mysql.jdbc.Statement;

public class CourseDao {
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/db_studio";
	private static final String USER = "root";
	private static final String PASSWORD = "123456";
	private static Connection conn = null;
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			conn = (Connection) DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	String sql = "";

	public void addSc(Sc sc) throws Exception {
		sql=" insert into sc (Sno,Cno,Score)"+
					" values(?,?,?)";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		ptmt.setString(1, sc.getSno());
		ptmt.setString(2, sc.getCno());
		ptmt.setInt(3, sc.getScore());
		ptmt.execute();
	}
	public void updateCourse(String cno,int cnumber) throws SQLException {
		sql=" UPDATE course SET Cnumber = ? WHERE Cno = ?";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		ptmt.setInt(1, cnumber);
		ptmt.setString(2, cno);
		ptmt.execute();
	}
	public void deluser(Sc sc) throws SQLException {
		sql=" delete from sc where Sno = ? AND Cno = ?";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		ptmt.setString(1, sc.getSno());
		ptmt.setString(2, sc.getCno());
		
		ptmt.execute();
	}
	public List<Course> queryCourse(String grade,String dno) throws Exception {
		Statement stmt=(Statement) conn.createStatement();
		sql=" SELECT DISTINCT Cno,Cname,Ccredit,Cgrade,Cteacher,Cnumber,Cmaxnumber,Fname FROM student,course,faculty "
				+"WHERE course.Cgrade = '"+grade+"'AND course.Fno = faculty.fno AND course.Fno =("
				+"SELECT Fno FROM discipline WHERE discipline.dno = '"+dno+"') ";
		ResultSet rs=stmt.executeQuery(sql);
		List<Course> CourseList=new ArrayList<Course>();
		Course Course = null;
		while (rs.next()) {
			Course = new Course();
			Course.setCno(rs.getString("Cno"));
			Course.setCname(rs.getString("Cname"));
			Course.setCcredit(rs.getInt("Ccredit"));
			Course.setCgrade(rs.getString("Cgrade"));
			Course.setCteacher(rs.getString("Cteacher"));
			Course.setFname(rs.getString("Fname"));
			Course.setCnumber(rs.getInt("Cnumber"));
			Course.setCmaxnumber(rs.getInt("Cmaxnumber"));
			CourseList.add(Course);
		}
		return CourseList;
	}
	public List<Course> queryCourse(String Sno) throws Exception {
		Statement stmt=(Statement) conn.createStatement();
		sql=" SELECT sc.Sno ,sc.Cno,sc.Score , course.Cname,Ccredit,Cgrade,Cteacher,Cnumber,Cmaxnumber,faculty.Fname FROM sc ,course,faculty WHERE sc.Sno= '"+Sno+"' AND sc.Cno = course.Cno AND course.Fno = faculty.Fno  ";
		ResultSet rs=stmt.executeQuery(sql);
		List<Course> CourseList=new ArrayList<Course>();
		Course Course = null;
		while (rs.next()) {
			Course = new Course();
			Course.setCno(rs.getString("Cno"));
			Course.setCname(rs.getString("Cname"));
			Course.setCcredit(rs.getInt("Ccredit"));
			Course.setCgrade(rs.getString("Cgrade"));
			Course.setCteacher(rs.getString("Cteacher"));
			Course.setFname(rs.getString("Fname"));
			Course.setScore(rs.getInt("Score"));
			Course.setCnumber(rs.getInt("Cnumber"));
			Course.setCmaxnumber(rs.getInt("Cmaxnumber"));
			CourseList.add(Course);
		}
		return CourseList;
	}
	public int getCnumber(String cno) throws SQLException {
		Course course = new Course();
		
		sql=" select Cnumber from course where Cno=?";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		ptmt.setString(1, cno);
		ResultSet rs = ptmt.executeQuery();
		while (rs.next()) {
			course.setCnumber(rs.getInt("Cnumber"));
		}
		return course.getCnumber();
	}

	public int getCflag(String cno) throws SQLException {
		Course course = new Course();
		
		sql=" select Cflag from course where Cno=?";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		ptmt.setString(1, cno);
		ResultSet rs = ptmt.executeQuery();
		while (rs.next()) {
			course.setCflag(rs.getInt("Cflag"));
		}
		return course.getCflag();
	}
	public void updateCflag(String cno,int cflag) throws SQLException {
		sql=" UPDATE course SET Cflag = ? WHERE Cno = ?";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		ptmt.setInt(1, cflag);
		ptmt.setString(2, cno);
		ptmt.execute();
	}
	public int getCmaxnumber(String cno) throws SQLException {
		Course course = new Course();
		
		sql=" select Cmaxnumber from course where Cno=?";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		ptmt.setString(1, cno);
		ResultSet rs = ptmt.executeQuery();
		while (rs.next()) {
			course.setCmaxnumber(rs.getInt("Cmaxnumber"));
		}
		return course.getCmaxnumber();
	}
}
