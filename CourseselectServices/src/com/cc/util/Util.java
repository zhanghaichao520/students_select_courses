package com.cc.util;

public class Util {
	
	public static String ascllToString(String s) {// ASCII转换为字符串   
       // String s = "24352 28023 36229";// ASCII码   
		String result="";
        String[] chars = s.split(" ");   
        for (int i = 0; i < chars.length; i++) {   
            result += (char) Integer.parseInt(chars[i]);
        }  
        return result;
    }   
  
    public static String stringToAscll(String s) {// 字符串转换为ASCII码   
        //String s = "张海超";// 字符串   
        char[] chars = s.toCharArray(); // 把字符中转换为字符数组   
        String result = "";
        for (int i = 0; i < chars.length; i++) {// 输出结果   
            result += (int) chars[i]+" ";
        }  
         
		return (String) result.subSequence(0,result.length()-1);
    }   
}
