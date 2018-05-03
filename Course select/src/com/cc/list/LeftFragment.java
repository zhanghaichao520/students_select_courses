package com.cc.list;

import com.cc.R;
import com.cc.activity.AboutActivity;
import com.cc.activity.ChangepassworActivity;
import com.cc.activity.FeedbackActivity;
import com.cc.activity.LoginActivity;
import com.cc.activity.MainActivity;
import com.cc.activity.MycourseActivity;
import com.cc.activity.PersonalActivity;
import com.cc.activity.SearchActivity;
import com.cc.activity.SelectcourseActivity;
import com.cc.model.Student;
import com.cc.activity.MycourseActivity;
import com.cc.util.ImageLoading;
import com.cc.util.Util;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.ViewGroup;

public class LeftFragment extends Fragment implements OnClickListener{
	private View todayView;
	private View lastListView;
	private View discussView;
	private View favoritesView;
	private View commentsView;
	private View changepasswordView;
	private View settingsView;
	private View reloginView;
	private View exitView;
	private TextView name;
	private ImageView photo;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.layout_menu, null);
		findViews(view);
		
		return view;
	}
	
	
	public void findViews(View view) {
		todayView = view.findViewById(R.id.tvMycourse);
		lastListView = view.findViewById(R.id.tvSelectcourse);
		discussView = view.findViewById(R.id.tvQuerycourse);
		favoritesView = view.findViewById(R.id.tvperson);
		commentsView = view.findViewById(R.id.tvabout);
		changepasswordView = view.findViewById(R.id.tvChangepassword);
		settingsView = view.findViewById(R.id.tvMySettings);
		reloginView = view.findViewById(R.id.relogin);
		exitView = view.findViewById(R.id.exit);
		name =(TextView) view.findViewById(R.id.name);
		photo = (ImageView) view.findViewById(R.id.profile_image);
		Student student =Student.getInstance();
		name.setText(student.getSname());
		new ImageLoading().showImageThread(photo, student.getSphoto());
		todayView.setOnClickListener(this);
		lastListView.setOnClickListener(this);
		discussView.setOnClickListener(this);
		favoritesView.setOnClickListener(this);
		commentsView.setOnClickListener(this);
		settingsView.setOnClickListener(this);
		reloginView.setOnClickListener(this);
		exitView.setOnClickListener(this);
		changepasswordView.setOnClickListener(this);
	}
	
	@Override
	public void onDestroyView() {
		super.onDestroyView();
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	@Override
	public void onClick(View v) {
		Fragment newContent = null;
		String title = null;
		Intent intent ;
		switch (v.getId()) {
		case R.id.tvMycourse: // 我的课程
			intent = new Intent();
			intent.setClass(getActivity(), MycourseActivity.class);
			getActivity().startActivity(intent);
			((Activity) getActivity()).finish();
			break;
		case R.id.tvSelectcourse:// 选修课程
			intent = new Intent();
			intent.setClass(getActivity(), SelectcourseActivity.class);
			getActivity().startActivity(intent);
			((Activity) getActivity()).finish();
			break;
		case R.id.tvQuerycourse: // 课程查询
			intent = new Intent();
			intent.setClass(getActivity(), SearchActivity.class);
			getActivity().startActivity(intent);
			((Activity) getActivity()).finish();
			break;
		case R.id.tvperson: // 个人中心
			intent = new Intent();
			intent.setClass(getActivity(), PersonalActivity.class);
			getActivity().startActivity(intent);
			((Activity) getActivity()).finish();
			break;
		case R.id.tvabout: // 关于
			intent = new Intent();
			intent.setClass(getActivity(), AboutActivity.class);
			getActivity().startActivity(intent);
			((Activity) getActivity()).finish();
			break;
		case R.id.tvMySettings: // 反馈
			intent = new Intent();
			intent.setClass(getActivity(), FeedbackActivity.class);
			getActivity().startActivity(intent);
			((Activity) getActivity()).finish();
			break;
		case R.id.tvChangepassword: //修改密码
			intent = new Intent();
			intent.setClass(getActivity(), ChangepassworActivity.class);
			getActivity().startActivity(intent);
			((Activity) getActivity()).finish();
			break;
		case R.id.relogin:
			intent = new Intent();
			intent.setClass(getActivity(), LoginActivity.class);
			getActivity().startActivity(intent);
			((Activity) getActivity()).finish();
			break;
		case R.id.exit:
			((Activity) getActivity()).finish();
			break;	
		default:
			break;
		}
		if (newContent != null) {
			switchFragment(newContent, title);
		}
	}
	
	/**
	 * 切换fragment
	 * @param fragment
	 */
	private void switchFragment(Fragment fragment, String title) {
		if (getActivity() == null) {
			return;
		}
		if (getActivity() instanceof MainActivity) {
			MainActivity fca = (MainActivity) getActivity();
			fca.switchConent(fragment, title);
		}
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	}
}
