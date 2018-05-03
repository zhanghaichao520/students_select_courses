package com.cc.data;

import java.util.ArrayList;
import java.util.List;

import com.cc.model.Course;

public class Const {
	public static final int LOGIN_SUCCESS=1;
	public static final int QUERY_SUCCESS=1;
	public static final int LOGIN_FAILED=2;
	public static final int USER_NOEXIST=3;
	public static final int INDEX_URL=0;
	public static final int INDEX_ID=1;
	public static final int INDEX_PASSWORD=2;
	public static final int INDEX_NAME=3;
	public static final int INDEX_CONFIRM_PASSWORD=3;
	public static final String DEL_SUCCESS="1";
	public static final String DEL_FAILED="2";
	public static final String ADD_SUCCESS="1";
	public static final String ADD_FAILED="2";
	public static final String ADD_FAILEDS="3";
	public static final String FEEDBACK_SUCCESS="1";
	public static final String FEEDBACK_FAILED="2";
	public static final String CHANGEPASSWORD_SUCCESS="1";
	public static final String CHANGEPASSWORD_FAILED="2";
	public static final String URL_LOGIN =
			"http://192.168.1.106:8080/CourseselectServices/Login";
	public static final String URL_MYCOURSE =
			"http://192.168.1.106:8080/CourseselectServices/Mycoursesevers";
	public static final String URL_CHANGESC =
			"http://192.168.1.106:8080/CourseselectServices/ChangeSc";
	public static final String URL_FEEDBACK =
			"http://192.168.1.106:8080/CourseselectServices/Feedbacksevers";
	public static final String URL_CHANGEPASSWORD =
			"http://192.168.1.106:8080/CourseselectServices/ChangePassword";
	public static final String URL_ALLCOURSE =
			"http://192.168.1.106:8080/CourseselectServices/Allcoursesevers";
	public static final String URL_SEARCHCOURSE =
			"http://192.168.1.106:8080/CourseselectServices/Searchcoursesevers";
	public static List<Course> allCourselist = new ArrayList<Course>();
	public static List<Course> myCourselist = new ArrayList<Course>();

}
