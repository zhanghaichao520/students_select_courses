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
import com.cc.data.Const;
import com.cc.model.Student;
import com.cc.util.Util;
import com.google.gson.Gson;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {

	private Button btn_login;
	private EditText Sno_edt;
	private EditText Spassword_edt;
	HttpClient client;
	Student student = Student.getInstance();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initevent();
	}

	public void initevent() {
		// TODO Auto-generated method stub
		client = new DefaultHttpClient();
		Sno_edt = (EditText) findViewById(R.id.editText1);
		Spassword_edt = (EditText) findViewById(R.id.editText2);
		Sno_edt.requestFocus();
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					if (Sno_edt.getText().toString().equals("") || Spassword_edt.getText().toString().equals("")) {
						Toast.makeText(LoginActivity.this, "用户名和密码不能为空", Toast.LENGTH_SHORT).show();
					} else
						readNet(Const.URL_LOGIN, Sno_edt.getText().toString(), Spassword_edt.getText().toString());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					Toast.makeText(LoginActivity.this, "服务器未开启", Toast.LENGTH_SHORT).show();
					e.printStackTrace();
				}
			}
		});
	}

	public void readNet(String url, String Sno, String Spassword) {

		new AsyncTask<String, Void, String>() {

			@Override
			protected String doInBackground(String... arg0) {
				String urlString = arg0[Const.INDEX_URL];
				HttpPost post = new HttpPost(urlString);
				List<BasicNameValuePair> list = new ArrayList<BasicNameValuePair>();
				list.add(new BasicNameValuePair("Sno", arg0[Const.INDEX_ID]));
				list.add(new BasicNameValuePair("Spassword", arg0[Const.INDEX_PASSWORD]));
				try {
					post.setEntity(new UrlEncodedFormEntity(list));
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					Toast.makeText(LoginActivity.this, "服务器已断开", Toast.LENGTH_SHORT).show();
					e1.printStackTrace();
				}
				try {
					HttpResponse response = client.execute(post);
					String value = EntityUtils.toString(response.getEntity(), "utf-8");
					return new String(value.getBytes(), "utf-8");

				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					Toast.makeText(LoginActivity.this, "服务器已断开", Toast.LENGTH_SHORT).show();

					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					Toast.makeText(LoginActivity.this, "服务器已断开", Toast.LENGTH_SHORT).show();
					e.printStackTrace();
				}

				return null;
			}

			protected void onPostExecute(String result) {
				result = (String) result.subSequence(1, result.length() - 1);
				Gson gson = new Gson();

				student = gson.fromJson(result, Student.class);
				student.setSname(Util.ascllToString(student.getSname()));
				student.setSsex(Util.ascllToString(student.getSsex()));
				student.setSgrade(Util.ascllToString(student.getSgrade()));
				student.setDname(Util.ascllToString(student.getDname()));
				student.setFname(Util.ascllToString(student.getFname()));
				if (student.getSno().equals(Sno_edt.getText().toString())
						&& student.getSpassword().equals(Spassword_edt.getText().toString())) { // Student
					Bundle bundle = new Bundle();
					bundle.putString("Sno", student.getSno());
					bundle.putString("Sname", student.getSname());
					bundle.putString("Ssex", student.getSsex());
					bundle.putInt("Sage", student.getSage());
					bundle.putString("Sgrade", student.getSgrade());
					bundle.putString("Dno", student.getDno());
					bundle.putString("Sphone", student.getSphone());
					bundle.putString("Spassword", student.getSpassword());
					bundle.putString("Dname", student.getDname());
					bundle.putString("Fname", student.getFname());
					bundle.putString("Sphoto", student.getSphoto());
					Util.startActivity(LoginActivity.this, MainActivity.class,bundle);
					Toast.makeText(LoginActivity.this, "欢迎你：" + student.getSname(), Toast.LENGTH_SHORT).show();
				} else if (student.getSno() == null) {
					Toast.makeText(LoginActivity.this, "用户不存在", Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(LoginActivity.this, "密码错误", Toast.LENGTH_SHORT).show();

				}

			};

		}.execute(url, Sno, Spassword);
	}
}
