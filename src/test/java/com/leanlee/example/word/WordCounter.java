package com.leanlee.example.word;

import org.junit.jupiter.api.Test;

import java.util.Date;


/**
 * @description: 统计字数
 * @author: Lean
 * @create: 2021-10-12 22:55
 **/
public class WordCounter {
	public static final String TIME_CONSUMING = "统计耗时：%s毫秒";

	@Test
	void countWord() {
		Date date = new Date();

		String path = "E:\\notes";
		Directory directory = new Directory();
		int count = directory.setPath(path).work();
		System.out.println(String.format(Directory.TOTAL, directory.getSize(), count));
		long time = new Date().getTime() - date.getTime();
		System.out.println(String.format(TIME_CONSUMING, time));
	}
}
