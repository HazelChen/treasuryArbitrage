package edu.nju.treasuryArbitrage.logic.fileIO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class FileOperater {
	
	public String read(String fileName) {
		File file = new File(fileName);
		BufferedReader reader = null;
		StringBuffer sb = new StringBuffer();

		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				sb.append(tempString + "\n");
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		return sb.toString();
	}
	
	public void write(String filename, String content) {
		try {
			PrintWriter printWriter = new PrintWriter(filename);
			printWriter.print(content);
			printWriter.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void write2ln(String filename, String firstln, String secondln) {
		try {
			PrintWriter printWriter = new PrintWriter(filename);
			printWriter.println(firstln);
			printWriter.println(secondln);
			printWriter.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
