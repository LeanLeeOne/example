package com.leanlee.example.word;

import java.io.File;

/**
 * @description: 遍历文件
 * @author: Lean
 * @create: 2021-10-12 22:56
 **/
public class Directory {

	public static final String TITLE = "《%s》";
	public static final String SUB = "%s篇，%s字";
	public static final String TOTAL = "总计：%s篇，%s字";

	private String path;
	private int size = 0;
	private Counter counter = new Counter();

	public Directory setPath(String path) {
		this.path = path;
		return this;
	}

	public int getSize() {
		return size;
	}

	public int work() {
		File directory = new File(path);
		if (!directory.isDirectory()) {
			return 0;
		}

		File[] files = directory.listFiles(); // 列出所有文件和子目录
		if (files.length == 0) {
			return 0;
		}

		int count = 0;
		for (File file : files) {
			String path = file.getAbsolutePath();
			if (file.isDirectory()) {
				String name = file.getName();
				if (!name.startsWith("0") && !name.startsWith("1")) {
					continue;
				}
				int c = new Directory().setPath(path).work();
				int s = file.listFiles().length;
				System.out.println(String.format(TITLE, name));
				System.out.println(String.format(SUB, s, c));
				size += s;
				count += c;
				continue;
			}

			if (!path.endsWith(".md")) {
				continue;
			}
			count += counter.setPath(path).count();
		}
		return count;
	}
}
