package com.cc.model;

public class Course {
	private String Cno;
	private String Cname;
	private String Cgrade;
	private int Ccredit;
	private String Cteacher;
	private int Cnumber;
	private int Score = -1;
	private String Fname;
	private int Cmaxnumber;


	public int getCmaxnumber() {
		return Cmaxnumber;
	}
	public void setCmaxnumber(int cmaxnumber) {
		Cmaxnumber = cmaxnumber;
	}
	public String getFname() {
		return Fname;
	}
	public void setFname(String fname) {
		Fname = fname;
	}
	public int getScore() {
		return Score;
	}
	public void setScore(int score) {
		Score = score;
	}
	public String getCno() {
		return Cno;
	}
	public void setCno(String cno) {
		Cno = cno;
	}
	public String getCname() {
		return Cname;
	}
	public void setCname(String cname) {
		Cname = cname;
	}
	public String getCgrade() {
		return Cgrade;
	}
	public void setCgrade(String cgrade) {
		Cgrade = cgrade;
	}
	public int getCcredit() {
		return Ccredit;
	}
	public void setCcredit(int ccredit) {
		Ccredit = ccredit;
	}
	public String getCteacher() {
		return Cteacher;
	}
	public void setCteacher(String cteacher) {
		Cteacher = cteacher;
	}
	public int getCnumber() {
		return Cnumber;
	}
	public void setCnumber(int cnumber) {
		Cnumber = cnumber;
	}
	
}
