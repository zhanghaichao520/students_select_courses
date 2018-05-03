package com.cc.adapter;

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
import com.cc.activity.MainActivity;
import com.cc.data.Const;
import com.cc.model.Course;
import com.cc.model.Sc;
import com.cc.model.Student;
import com.cc.util.Util;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputBinding;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class CustomAdapter extends BaseAdapter {
	private ArrayList<CustomListCellDate> date = new ArrayList<CustomListCellDate>();
	private Context context;
	private Sc sc = new Sc();

	HttpClient client;
	Student student = Student.getInstance();
	public static String delResult = "";
	public static String addResult = "";

	public Context getContext() {
		return context;
	}

	public void clear()
	{
		date.clear();
	}
	public CustomAdapter(Context context) {
		this.context = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return date.size();
	}

	@Override
	public CustomListCellDate getItem(int arg0) {
		// TODO Auto-generated method stub
		return date.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(final int arg0, View arg1, ViewGroup arg2) {

		LinearLayout ll = null;
		if (arg1 != null) {
			ll = (LinearLayout) arg1;
		} else {
			ll = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.listviewadapter, null);
		}

		CustomListCellDate date = getItem(arg0);

		ImageView classImgView = (ImageView) ll.findViewById(R.id.class_img);
		TextView classNameView = (TextView) ll.findViewById(R.id.class_name);
		TextView gradeView = (TextView) ll.findViewById(R.id.grade);
		TextView scienceView = (TextView) ll.findViewById(R.id.science);
		TextView creditView = (TextView) ll.findViewById(R.id.credit);
		TextView teacherNameView = (TextView) ll.findViewById(R.id.teacher_name);
		Button chooseButtonView = (Button) ll.findViewById(R.id.choose_button);

		classImgView.setImageResource(date.getIconNum());
		classNameView.setText(date.getClassName());
		gradeView.setText(date.getGrade());
		scienceView.setText(date.getScience());
		creditView.setText(date.getCredit());
		teacherNameView.setText(date.getTeacherName());
		chooseButtonView.setText(date.getButtonName());

		chooseButtonView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (((TextView) v).getText() == "退选") {
					final AlertDialog dlg = new AlertDialog.Builder(context).create();
					dlg.show();

					WindowManager.LayoutParams params = dlg.getWindow().getAttributes();
					params.width = 1000;
					params.height = 1000;
					dlg.getWindow().setAttributes(params);

					Window window = dlg.getWindow();
					// *** 主要就是在这里实现这种效果的.
					// 设置窗口的内容页面,shrew_exit_dialog.xml文件中定义view内容
					window.setContentView(R.layout.tooltip);
					// 为确认按钮添加事件,执行退出应用操作
					Button ok = (Button) window.findViewById(R.id.sure);
					ok.setOnClickListener(new View.OnClickListener() {
						public void onClick(View v) {
							dlg.cancel();
							// 获取要删除的对象
							sc.setSno(student.getSno());
							
							for (int i = 0; i < Const.myCourselist.size(); i++) {
								Course myCourse = Const.myCourselist.get(i);
								if (getItem(arg0).getClassName().equals(myCourse.getCname())) {
									sc.setCno(myCourse.getCno());
									break;
								}
							}
							// 访问数据库
							client = new DefaultHttpClient();
							delMycourse(Const.URL_CHANGESC, sc.getSno(), sc.getCno());
							Toast.makeText(context, "成功退选" + getItem(arg0).getClassName(), 1).show();
							// 在当前列表中删除该条记录，同时从数据库里删除该条记录
							getDate().remove(arg0);
							notifyDataSetChanged();
							if (Const.myCourselist.size() == 1) {
								Course myCourse = Const.myCourselist.get(0);
								sc.setCno(myCourse.getCno());
								Const.myCourselist.remove(0);
								Const.allCourselist.add(myCourse);
							} else {
								for (int i = 0; i < Const.myCourselist.size(); i++) {
									Course myCourse = Const.myCourselist.get(i);
									if (getItem(arg0).getClassName().equals(myCourse.getCname())) {
										sc.setCno(myCourse.getCno());
										Const.myCourselist.remove(i-1);
										break;
									}
								}
							}
						}
					});

					// 关闭alert对话框架
					Button cancel = (Button) window.findViewById(R.id.cancel);
					cancel.setOnClickListener(new View.OnClickListener() {
						public void onClick(View v) {
							dlg.cancel();
						}
					});

				} else if (((TextView) v).getText() == "选修") {
					final AlertDialog dlg = new AlertDialog.Builder(context).create();
					dlg.show();

					WindowManager.LayoutParams params = dlg.getWindow().getAttributes();
					params.width = 1000;
					params.height = 1000;
					dlg.getWindow().setAttributes(params);

					Window window = dlg.getWindow();
					// *** 主要就是在这里实现这种效果的.
					// 设置窗口的内容页面,shrew_exit_dialog.xml文件中定义view内容

					window.setContentView(R.layout.tooltip);
					// 为确认按钮添加事件,执行退出应用操作
					Button ok = (Button) window.findViewById(R.id.sure);
					ok.setOnClickListener(new View.OnClickListener() {
						public void onClick(View v) {
							dlg.cancel();
							// 获取要添加的对象
							sc.setSno(student.getSno());
							for (int i = 0; i < Const.allCourselist.size(); i++) {
								Course myCourse = Const.allCourselist.get(i);
								if (getItem(arg0).getClassName().equals(myCourse.getCname())) {
									sc.setCno(myCourse.getCno());
									Const.myCourselist.add(myCourse);
									break;
								}
							}
							// 访问数据库
							client = new DefaultHttpClient();
							addMycourse(Const.URL_CHANGESC, sc.getSno(), sc.getCno());
							if (addResult.equals(Const.ADD_FAILEDS)) {
								Toast.makeText(context, "选修  " + getItem(arg0).getClassName() + " 失败，人数已满", 1).show();

							}else if (addResult.equals(Const.ADD_SUCCESS)) {
								Toast.makeText(context, "成功选修" + getItem(arg0).getClassName() + ",你可以在我的课程里查看", 1).show();

							}
							else {
								Toast.makeText(context, "产生冲突，请在我的课程确认是否选修成功", 1).show();

							}

							// 在当前列表中删除该条记录，同时往数据库里添加该条记录
							getDate().remove(arg0);
							notifyDataSetChanged();
							// 更新本地列表
							if (Const.myCourselist.size() == 1) {
								Course myCourse = Const.allCourselist.get(0);
								sc.setCno(myCourse.getCno());
								Const.allCourselist.remove(0);
							} else {
								for (int i = 0; i < Const.allCourselist.size(); i++) {
									Course myCourse = Const.allCourselist.get(i);
									if (getItem(arg0).getClassName().equals(myCourse.getCname())) {
										sc.setCno(myCourse.getCno());
										Const.allCourselist.remove(i-1);
										break;
									}
								}
							}

						}

					});

					// 关闭alert对话框架
					Button cancel = (Button) window.findViewById(R.id.cancel);
					cancel.setOnClickListener(new View.OnClickListener() {

						public void onClick(View v) {
							dlg.cancel();
						}

					});

				}else if (((TextView) v).getText() == "喜欢"){
					((TextView)v).setText("");
					Toast.makeText(context, "哦", Toast.LENGTH_SHORT).show();

				}
			}
		});

		return ll;
	}

	// public void delectDate(CustomListCellDate date)

	public ArrayList<CustomListCellDate> getDate() {
		return date;
	}

	public void addDate(CustomListCellDate date) {
		this.date.add(date);
	}

	static class ViewHolder {
		Button button;
	}

	public void addMycourse(String url, String Sno, String Cno) {

		new AsyncTask<String, Void, String>() {

			@Override
			protected String doInBackground(String... arg0) {
				String urlString = arg0[Const.INDEX_URL];
				HttpPost post = new HttpPost(urlString);
				List<BasicNameValuePair> list = new ArrayList<BasicNameValuePair>();
				list.add(new BasicNameValuePair("Sno", arg0[1]));
				list.add(new BasicNameValuePair("Cno", arg0[2]));
				list.add(new BasicNameValuePair("operation", "add"));
				try {
					post.setEntity(new UrlEncodedFormEntity(list));
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					Toast.makeText(context, "服务器已断开", Toast.LENGTH_SHORT).show();
					e1.printStackTrace();
				}
				try {
					HttpResponse response = client.execute(post);
					String value = EntityUtils.toString(response.getEntity(), "utf-8");
					return new String(value.getBytes(), "utf-8");

				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					Toast.makeText(context, "服务器已断开", Toast.LENGTH_SHORT).show();

					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					Toast.makeText(context, "服务器已断开", Toast.LENGTH_SHORT).show();
					e.printStackTrace();
				}
				return null;
			}

			protected void onPostExecute(String result) {
				addResult = result;
			};
		}.execute(url, Sno, Cno);
	}

	public void delMycourse(String url, String Sno, String Cno) {

		new AsyncTask<String, Void, String>() {

			@Override
			protected String doInBackground(String... arg0) {
				String urlString = arg0[Const.INDEX_URL];
				HttpPost post = new HttpPost(urlString);
				List<BasicNameValuePair> list = new ArrayList<BasicNameValuePair>();
				list.add(new BasicNameValuePair("Sno", arg0[1]));
				list.add(new BasicNameValuePair("Cno", arg0[2]));
				list.add(new BasicNameValuePair("operation", "del"));
				try {
					post.setEntity(new UrlEncodedFormEntity(list));
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					Toast.makeText(context, "服务器已断开", Toast.LENGTH_SHORT).show();
					e1.printStackTrace();
				}
				try {
					HttpResponse response = client.execute(post);
					String value = EntityUtils.toString(response.getEntity(), "utf-8");
					return new String(value.getBytes(), "utf-8");

				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					Toast.makeText(context, "服务器已断开", Toast.LENGTH_SHORT).show();

					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					Toast.makeText(context, "服务器已断开", Toast.LENGTH_SHORT).show();
					e.printStackTrace();
				}

				return null;
			}

			protected void onPostExecute(String result) {
				delResult = result;

			};

		}.execute(url, Sno, Cno);
	}
}