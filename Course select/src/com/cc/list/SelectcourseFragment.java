package com.cc.list;

import com.cc.R;
import com.cc.view.MyTableTextView;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class SelectcourseFragment extends Fragment {
	private LinearLayout mainLinerLayout;
	private RelativeLayout relativeLayout;
	private String[] name = { "课 程", "学 分", "教 师", "课 时", "年 级", "操 作" };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mainLinerLayout = (LinearLayout) getView().findViewById(R.id.MyTable);
		initData();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.frag_selectcourse, null);
		return view;
	}
	
	
	// 绑定数据
	private void initData() {
		// 初始化标题
		relativeLayout = (RelativeLayout) LayoutInflater.from(getActivity()).inflate(R.layout.table, null);
		MyTableTextView title = (MyTableTextView) relativeLayout.findViewById(R.id.list_1_1);
		title.setText(name[0]);
		title.setTextColor(Color.BLUE);
		
		title = (MyTableTextView) relativeLayout.findViewById(R.id.list_1_2);
		title.setText(name[1]);
		title.setTextColor(Color.BLUE);
		title = (MyTableTextView) relativeLayout.findViewById(R.id.list_1_3);
		title.setText(name[2]);
		title.setTextColor(Color.BLUE);
		title = (MyTableTextView) relativeLayout.findViewById(R.id.list_1_4);
		title.setText(name[3]);
		title.setTextColor(Color.BLUE);
		title = (MyTableTextView) relativeLayout.findViewById(R.id.list_1_5);
		title.setText(name[4]);
		title.setTextColor(Color.BLUE);
		title = (MyTableTextView) relativeLayout.findViewById(R.id.list_1_6);
		title.setText(name[5]);
		title.setTextColor(Color.BLUE);
		
		mainLinerLayout.addView(relativeLayout);

		// 初始化内容
		int number = 1;
		for (int i = 0; i < 50; i++) {
			final String main = i+"";
			relativeLayout = (RelativeLayout) LayoutInflater.from(getActivity()).inflate(R.layout.table, null);
			MyTableTextView txt = (MyTableTextView) relativeLayout.findViewById(R.id.list_1_1);
			txt.setText("数据库");

			txt = (MyTableTextView) relativeLayout.findViewById(R.id.list_1_2);
			txt.setText(String.valueOf(i));
			txt = (MyTableTextView) relativeLayout.findViewById(R.id.list_1_3);
			txt.setText("张三");
			txt = (MyTableTextView) relativeLayout.findViewById(R.id.list_1_4);
			txt.setText("90");
			txt = (MyTableTextView) relativeLayout.findViewById(R.id.list_1_5);
			txt.setText("大三");
			txt = (MyTableTextView) relativeLayout.findViewById(R.id.list_1_6);
			txt.setText("选  修");
			txt.setTextColor(Color.RED);
			txt.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					Toast.makeText(getActivity(), main, Toast.LENGTH_SHORT).show();
				}
			});
			mainLinerLayout.addView(relativeLayout);
			number++;
		}
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
	}
	
	
	@Override
	public void onDestroy() {
		super.onDestroy();
	}
}
