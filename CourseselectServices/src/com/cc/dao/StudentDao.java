package com.cc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cc.model.Student;
import com.mysql.jdbc.Statement;

public class StudentDao {
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

	public void updateStudent(String sno, String password) throws SQLException {
		sql = " UPDATE student SET Spassword=? WHERE Sno=? ";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, password);
		ptmt.setString(2, sno);
		ptmt.execute();
	}

	public List<Student> querystudent() throws Exception {
		Statement stmt = (Statement) conn.createStatement();
		sql = " SELECT Sno,Sname,Ssex,Sage,student.Dno,Sgrade,Sphone,Spassword,Sphoto,Fname ,Dname"
				+ "FROM student,faculty,discipline"
				+ "WHERE student.Dno = discipline.Dno AND discipline.Fno = faculty.Fno ";
		ResultSet rs = stmt.executeQuery(sql);
		List<Student> studentList = new ArrayList<Student>();
		Student student = null;
		while (rs.next()) {
			student = new Student();
			student.setSno(rs.getString("Sno"));
			student.setSname(rs.getString("Sname"));
			student.setSsex(rs.getString("Ssex"));
			student.setSage(rs.getInt("Sage"));
			student.setDno(rs.getString("Dno"));
			student.setSgrade(rs.getString("Sgrade"));
			student.setSphone(rs.getString("Sphone"));
			student.setSpassword(rs.getString("Spassword"));
			student.setDname(rs.getString("Dname"));
			student.setFname(rs.getString("Fname"));
			student.setSphoto(rs.getString("Sphoto"));
			studentList.add(student);
		}
		return studentList;
	}

	public Student getstudent(String Sno) throws SQLException {
		Student student = new Student();
		sql = " select * from student ,discipline ,faculty "
				+ "where student.Dno = discipline.Dno AND discipline.Fno = faculty.Fno AND Sno = ?";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, Sno);
		ResultSet rs = ptmt.executeQuery();
		while (rs.next()) {
			student.setSno(rs.getString("Sno"));
			student.setSname(rs.getString("Sname"));
			student.setSsex(rs.getString("Ssex"));
			student.setSage(rs.getInt("Sage"));
			student.setDno(rs.getString("Dno"));
			student.setSgrade(rs.getString("Sgrade"));
			student.setSphone(rs.getString("Sphone"));
			student.setSpassword(rs.getString("Spassword"));
			student.setDname(rs.getString("Dname"));
			student.setFname(rs.getString("Fname"));
			student.setSphoto(rs.getString("Sphoto"));
		}
		return student;
	}
	public Student getSno(String Sname) throws SQLException {
		Student student = new Student();
		sql = " SELECT * FROM student WHERE Sname = ?";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, Sname);
		ResultSet rs = ptmt.executeQuery();
		while (rs.next()) {
			student.setSno(rs.getString("Sno"));
			student.setSname(rs.getString("Sname"));
			student.setSsex(rs.getString("Ssex"));
			student.setSage(rs.getInt("Sage"));
			student.setDno(rs.getString("Dno"));
			student.setSgrade(rs.getString("Sgrade"));
			student.setSphone(rs.getString("Sphone"));
			student.setSpassword(rs.getString("Spassword"));
			student.setSphoto(rs.getString("Sphoto"));
		}
		return student;
	}
}
