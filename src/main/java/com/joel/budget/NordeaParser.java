package com.joel.budget;

import java.io.File;
import java.util.List;

public class NordeaParser {

	public NordeaParser(List<File> fileList) {
		for (File file : fileList) {
			System.out.println(file.getAbsolutePath());
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
