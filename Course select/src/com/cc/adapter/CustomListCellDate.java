package com.cc.adapter;



public class CustomListCellDate {

	private String className;
	private String grade;
	private String science;
	private String credit;
	private String teacherName;
	private String buttonName;
	private int iconNum;

	public String getButtonName() {
		return buttonName;
	}


	public void setButtonName(String buttonName) {
		this.buttonName = buttonName;
	}


	public int getIconNum() {
		return iconNum;
	}


	public void setIconNum(int iconNum) {
		this.iconNum = iconNum;
	}


	public String getClassName() {
		return className;
	}


	public void setClassName(String className) {
		this.className = className;
	}


	public String getGrade() {
		return grade;
	}


	public void setGrade(String grade) {
		this.grade = grade;
	}


	public String getScience() {
		return science;
	}


	public void setScience(String science) {
		this.science = science;
	}


	public String getCredit() {
		return credit;
	}


	public void setCredit(String credit) {
		this.credit = credit;
	}


	public String getTeacherName() {
		return teacherName;
	}


	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}


	public CustomListCellDate(int iconNum,
			String className,
			String grade,
			String science,
			String credit,
			String teacherName,
			String buttonName
			) {
		this.iconNum = iconNum;
		this.className = className;
		this.grade = grade;
		this.science = science;
		this.credit = credit;
		this.teacherName = teacherName;
		this.buttonName = buttonName;
	}

}
