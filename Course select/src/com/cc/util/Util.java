package com.cc.util;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class Util {
	/**
	 * ����ת��
	 * 
	 * @param context
	 * @param des
	 */
	public static void startActivity(Context context, Class des) {
		Intent intent = new Intent();
		intent.setClass(context, des);
		context.startActivity(intent);
		// �ر�ԴActivity
		((Activity) context).finish();
	}

	public static void startActivity(Context context, Class des, Bundle bundle) {
		Intent intent = new Intent();
		intent.setClass(context, des);
		intent.putExtras(bundle);
		context.startActivity(intent);

		((Activity) context).finish();

	}
	public static String ascllToString(String s) {// ASCII转换为字符串
		// String s = "24352 28023 36229";// ASCII码
		String result = "";
		String[] chars = s.split(" ");
		for (int i = 0; i < chars.length; i++) {
			result += (char) Integer.parseInt(chars[i]);
		}
		return result;
	}

	public static String stringToAscll(String s) {// 字符串转换为ASCII码
		// String s = "张海超";// 字符串
		char[] chars = s.toCharArray(); // 把字符中转换为字符数组
		String result = "";
		for (int i = 0; i < chars.length; i++) {// 输出结果
			result += (int) chars[i] + " ";
		}
		return result;
	}

}
