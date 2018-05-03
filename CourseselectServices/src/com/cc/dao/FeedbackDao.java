package com.cc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.cc.model.Feedback;

public class FeedbackDao {
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

	public void addFeedback(Feedback feedback) throws Exception {
		sql=" insert into feedback (Sno,Ftext)"+
					" values(?,?)";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		ptmt.setString(1, feedback.getSno());
		ptmt.setString(2, feedback.getText());
		ptmt.execute();
	}
}
