package com.cc.activity;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.cc.R;
import com.cc.adapter.CustomAdapter;
import com.cc.adapter.CustomListCellDate;
import com.cc.data.Const;
import com.cc.list.LeftFragment;
import com.cc.list.MycourseFragment;
import com.cc.model.Course;
import com.cc.model.Student;
import com.cc.util.Util;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SearchActivity extends SlidingFragmentActivity implements OnClickListener {

	private ImageView topButton;
	private Fragment mContent;
	private TextView topTextView;
	Fragment newContent = null;
	String title = null;
	private ListView lv;
	Student student = Student.getInstance();
	private CustomAdapter cusAdapter = new CustomAdapter(this);
	HttpClient client;
	private EditText search_edt;
	private ImageButton search_btn;
	String text =""; 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE); // 无标题
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		initSlidingMenu(savedInstanceState);
		topButton = (ImageView) findViewById(R.id.topButton);
		topButton.setOnClickListener(this);
		topTextView = (TextView) findViewById(R.id.topTv);
		topTextView.setText("课程查询");
		client = new DefaultHttpClient();
		lv = (ListView) findViewById(R.id.lv);
	}
	public void search(View view) {
	
		cusAdapter.clear();

		lv.setAdapter(cusAdapter);
		search_edt = (EditText) findViewById(R.id.searchTest);
		client = new DefaultHttpClient();
		text = search_edt.getText().toString();
		readCourse(Const.URL_SEARCHCOURSE, text);

	}
	/**
	 * 初始化侧边栏
	 */
	private void initSlidingMenu(Bundle savedInstanceState) {
		// 如果保存的状态不为空则得到之前保存的Fragment，否则实例化MyFragment
		if (savedInstanceState != null) {
			mContent = getSupportFragmentManager().getFragment(savedInstanceState, "mContent");
		}

		if (mContent == null) {
			mContent = new MycourseFragment();
		}

		// 设置左侧滑动菜单
		setBehindContentView(R.layout.menu_frame_left);
		getSupportFragmentManager().beginTransaction().replace(R.id.menu_frame, new LeftFragment()).commit();

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
		getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).commit();
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
	public void readCourse(String url, String text) {

		new AsyncTask<String, Void, String>() {

			@Override
			protected String doInBackground(String... arg0) {
				String urlString = arg0[Const.INDEX_URL];
				HttpPost post = new HttpPost(urlString);
				List<BasicNameValuePair> list = new ArrayList<BasicNameValuePair>();
				list.add(new BasicNameValuePair("text", Util.stringToAscll(arg0[1])));
				try {
					post.setEntity(new UrlEncodedFormEntity(list));
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					Toast.makeText(SearchActivity.this, "服务器已断开", Toast.LENGTH_SHORT).show();
					e1.printStackTrace();
				}
				try {
					HttpResponse response = client.execute(post);
					String value = EntityUtils.toString(response.getEntity(), "utf-8");
					return new String(value.getBytes(), "utf-8");

				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					Toast.makeText(SearchActivity.this, "服务器已断开", Toast.LENGTH_SHORT).show();

					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					Toast.makeText(SearchActivity.this, "服务器已断开", Toast.LENGTH_SHORT).show();
					e.printStackTrace();
				}

				return null;
			}

			protected void onPostExecute(String result) {
				Gson gson = new Gson();
				List<Course> list;
				list = gson.fromJson(result, new TypeToken<List<Course>>() {
				}.getType());
				for (int i = 0; i < list.size(); i++) {
					Course course = list.get(i);
					list.get(i).setCname(Util.ascllToString(course.getCname()));
					list.get(i).setCgrade(Util.ascllToString(course.getCgrade()));
					list.get(i).setFname(Util.ascllToString(course.getFname()));
					list.get(i).setCteacher(Util.ascllToString(course.getCteacher()));
					/*
					 * 从数据库中读取已选课程，并设定属性，如下(最后一个属性必须为"退选")
					 */

					CustomListCellDate date = new CustomListCellDate(R.drawable.course, course.getCname(),
							course.getCnumber()+"/"+course.getCmaxnumber(), course.getFname(), course.getCcredit() + "学分", course.getCteacher(),
							"喜欢");
				
					cusAdapter.addDate(date);

					lv.setAdapter(cusAdapter);

				}
			};

		}.execute(url, text);
	}
}
