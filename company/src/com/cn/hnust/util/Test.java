package com.cn.hnust.util;

public class Test {
	public static void main(String[] args) {
		String fileName = "dsdsd.PDF" ;
		int index = fileName.indexOf(".") ;
		String hzName = fileName.substring(index+1) ;
		hzName = hzName.toLowerCase() ;
		System.out.println(hzName);
	}
}
