package com.cc.model;

public class Student {
	private String Sno;
	private String Sname;
	private String Ssex;
	private int Sage;
	private String Dno; // 专业号
	private String Sgrade;
	private String Sphone;
	private String Spassword;
	private String Sphoto;
	private String Dname;//专业
	private String Fname;
	
	public String getDname() {
		return Dname;
	}

	public void setDname(String dname) {
		Dname = dname;
	}

	public String getFname() {
		return Fname;
	}

	public void setFname(String fname) {
		Fname = fname;
	}

	private Student() {
		// TODO Auto-generated constructor stub
	}

	private static Student student = null;

	public static Student getInstance() {

		if (student == null) {
			student = new Student();
		}

		return student;
	}

	public String getSno() {
		return Sno;
	}

	public void setSno(String sno) {
		Sno = sno;
	}

	public String getSname() {
		return Sname;
	}

	public void setSname(String sname) {
		Sname = sname;
	}

	public String getSsex() {
		return Ssex;
	}

	public void setSsex(String ssex) {
		Ssex = ssex;
	}

	public int getSage() {
		return Sage;
	}

	public void setSage(int sage) {
		Sage = sage;
	}

	public String getDno() {
		return Dno;
	}

	public void setDno(String dno) {
		Dno = dno;
	}

	public String getSgrade() {
		return Sgrade;
	}

	public void setSgrade(String sgrade) {
		Sgrade = sgrade;
	}

	public String getSphone() {
		return Sphone;
	}

	public void setSphone(String sphone) {
		Sphone = sphone;
	}

	public String getSpassword() {
		return Spassword;
	}

	public void setSpassword(String spassword) {
		Spassword = spassword;
	}

	public String getSphoto() {
		return Sphoto;
	}

	public void setSphoto(String sphoto) {
		Sphoto = sphoto;
	}

}
