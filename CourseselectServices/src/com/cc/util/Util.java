package com.cc.util;

public class Util {
	
	public static String ascllToString(String s) {// ASCIIת��Ϊ�ַ���   
       // String s = "24352 28023 36229";// ASCII��   
		String result="";
        String[] chars = s.split(" ");   
        for (int i = 0; i < chars.length; i++) {   
            result += (char) Integer.parseInt(chars[i]);
        }  
        return result;
    }   
  
    public static String stringToAscll(String s) {// �ַ���ת��ΪASCII��   
        //String s = "�ź���";// �ַ���   
        char[] chars = s.toCharArray(); // ���ַ���ת��Ϊ�ַ�����   
        String result = "";
        for (int i = 0; i < chars.length; i++) {// ������   
            result += (int) chars[i]+" ";
        }  
         
		return (String) result.subSequence(0,result.length()-1);
    }   
}
