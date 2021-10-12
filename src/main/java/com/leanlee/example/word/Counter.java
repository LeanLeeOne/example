package com.leanlee.example.word;

import org.apache.commons.lang3.StringUtils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/**
 * 字数统计器
 *
 * @author: Lean
 * @create: 2021/10/12 22:18
 **/
public class Counter {

	private String path; // 文件路径

	/**
	 * 设置文件路径
	 **/
	public Counter setPath(String path) {
		this.path = path;
		return this;
	}

	public int count() {
		if (StringUtils.isBlank(path)) {
			return 0;
		}

		String content = read();

		String englishString = content.replaceAll("[\u4e00-\u9fa5]", "");
		String[] englishWords = englishString.split("[\\p{P}\\p{S}\\p{Z}\\s]+");
		int chineseWordCount = content.length() - englishString.length();
		int otherWordCount = englishWords.length;
		if (englishWords.length > 0 && englishWords[0].length() < 1) {
			otherWordCount--;
		}
		if (englishWords.length > 1 && englishWords[englishWords.length - 1].length() < 1) {
			otherWordCount--;
		}
		return chineseWordCount + otherWordCount;
	}

	public String read() {
		StringBuilder content = new StringBuilder();
		try {
			Reader reader = new FileReader(path);
			for (; ; ) {
				int n = reader.read();
				if (n == -1) {
					break;
				}
				char c = (char) n;
				if (filter(c)) {
					continue;
				}
				content.append(c);
			}
			reader.close(); // 关闭流
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return content.toString();
	}

	public boolean filter(char word) {
		if ("#".equals(String.valueOf(word))
				|| "\\".equals(String.valueOf(word))
				|| "`".equals(String.valueOf(word))
				|| "*".equals(String.valueOf(word))
				|| "_".equals(String.valueOf(word))
				|| "{}".equals(String.valueOf(word))
				|| "[]".equals(String.valueOf(word))
				|| "()".equals(String.valueOf(word))
				|| "#".equals(String.valueOf(word))
				|| "+".equals(String.valueOf(word))
				|| "-".equals(String.valueOf(word))
				|| ".".equals(String.valueOf(word))
				|| "!".equals(String.valueOf(word))
		) {
			return true;
		}
		return false;
	}
}
