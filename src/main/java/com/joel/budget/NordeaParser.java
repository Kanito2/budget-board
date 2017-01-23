package com.joel.budget;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;

public class NordeaParser {

	public NordeaParser(List<File> fileList) {
		for (File file : fileList) {

			CSVReader reader = null;
			try {
				reader = new CSVReader(new FileReader(file));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			List<String[]> myEntries = null;
			try {
				myEntries = reader.readAll();
			} catch (IOException e) {
				e.printStackTrace();
			}

			for (String[] row : myEntries) {
				if (!row[0].equals("Datum") && !row[0].equals("")) {
					Entry entry = new Entry(row[0], row[1], row[3]);
					entry.print();
				}
			}
		}
		Entry.printTotalSums();
		System.out.println("days=" + Entry.daysBetween());
	}

	public static void main(String[] args) {
		List<File> fileList = new ArrayList<File>();
		File file = new File("C:\\Users\\Marcela\\Downloads\\Joel\\export (1).csv");
		File file2 = new File("C:\\Users\\Marcela\\Downloads\\Joel\\export (2).csv");
		// File file = new
		// File("/home/joel/workspace_bkp2/NordeaParser/exports/export
		// (1).csv");
		// File file2 = new
		// File("/home/joel/workspace_bkp2/NordeaParser/exports/export
		// (2).csv");
		fileList.add(file);
		fileList.add(file2);

		new NordeaParser(fileList);

	}

}
