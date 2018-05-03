package com.cc.activity;

import com.cc.R;
import com.cc.adapter.CustomAdapter;
import com.cc.list.LeftFragment;
import com.cc.list.MycourseFragment;
import com.cc.model.Student;
import com.cc.util.ImageLoading;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;


public class PersonalActivity extends SlidingFragmentActivity implements
		OnClickListener {
	private TextView s_name;
	private TextView s_sno;
	private TextView s_age;
	private TextView s_sex;
	private TextView s_phone;
	private TextView s_grade;
	private TextView s_fault;
	private TextView s_profession;
	private ImageView topButton;
	private Fragment mContent;
	private TextView topTextView;
	private ImageView photo_img;
	Fragment newContent = null;
	String title = null;
	private CustomAdapter cusAdapter = new CustomAdapter(this);
	@Override
	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE); // 无标题
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_personal);
		initSlidingMenu(savedInstanceState);
		topButton = (ImageView) findViewById(R.id.topButton);
		topButton.setOnClickListener(this);
		topTextView = (TextView) findViewById(R.id.topTv);
		topTextView.setText("个人中心");
		initevent();
	}

	public void initevent() {
		// TODO Auto-generated method stub
		s_name = (TextView) findViewById(R.id.s_name);
		s_sno = (TextView) findViewById(R.id.s_sno);
		s_age = (TextView) findViewById(R.id.s_age);
		s_sex = (TextView) findViewById(R.id.s_sex);
		s_phone = (TextView) findViewById(R.id.s_phone);
		s_grade = (TextView) findViewById(R.id.s_grade);
		s_fault = (TextView) findViewById(R.id.s_fault);
		s_profession = (TextView) findViewById(R.id.s_profession);
		photo_img = (ImageView) findViewById(R.id.avatar);
		Student student = Student.getInstance();
		new ImageLoading().showImageThread(photo_img, student.getSphoto());
		s_name.setText(student.getSname());
		s_sno.setText(student.getSno());
		s_age.setText(student.getSage()+"");
		s_sex.setText(student.getSsex());
		s_phone.setText(student.getSphone());
		s_grade.setText(student.getSgrade());
		s_fault.setText(student.getFname());
		s_profession.setText(student.getDname());
	}

	/**
	 * 初始化侧边栏
	 */
	private void initSlidingMenu(Bundle savedInstanceState) {
		// 如果保存的状态不为空则得到之前保存的Fragment，否则实例化MyFragment
		if (savedInstanceState != null) {
			mContent = getSupportFragmentManager().getFragment(
					savedInstanceState, "mContent");
		}

		if (mContent == null) {
			mContent = new MycourseFragment();
		}

		// 设置左侧滑动菜单
		setBehindContentView(R.layout.menu_frame_left);
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.menu_frame, new LeftFragment()).commit();

		// 实例化滑动菜单对象
		SlidingMenu sm = getSlidingMenu();
		// 设置可以左右滑动的菜单
		sm.setMode(SlidingMenu.LEFT);
		// 设置滑动阴影的宽度
		sm.setShadowWidthRes(R.dimen.shadow_width);
		// 设置滑动菜单阴影的图像资源
		sm.setShadowDrawable(null);
		// 设置滑动菜单视图的宽度
		sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		// 设置渐入渐出效果的值
		sm.setFadeDegree(0.35f);
		// 设置触摸屏幕的模式,这里设置为全屏
		sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		// 设置下方视图的在滚动时的缩放比例
		sm.setBehindScrollScale(0.0f);

	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		getSupportFragmentManager().putFragment(outState, "mContent", mContent);
	}

	/**
	 * 切换Fragment
	 * 
	 * @param fragment
	 */
	public void switchConent(Fragment fragment, String title) {
		mContent = fragment;
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.content_frame, fragment).commit();
		getSlidingMenu().showContent();
		topTextView.setText(title);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.topButton:
			toggle();
			break;
		default:
			break;
		}
	}

}
