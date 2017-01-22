/**
 * 
 */
package com.joel.budget;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * @author joel
 *
 */
public class Entry {

	private DateTime date;
	private String transaction;
	private String category;
	private float amount;
	private static float[] totalSums = new float[7];
	private static DateTime start;
	private static DateTime end;

	public Entry(String date, String transaction, String amount) {
		setDate(date, transaction);
		this.transaction = transaction;
		this.amount = Float.parseFloat(amount.replace(".", "").replace(",", "."));
		setCategory(transaction);
	}

	public static int daysBetween() {
		int days = Days.daysBetween(start, end).getDays();
		return days;
	}

	private void setCategory(String transaction) {
		String[] files = { "home", "food", "transport", "cloth", "saving", "other", "loan" };

		for (int i = 0; i < files.length; i++) {
			File file = new File("/home/joel/workspace_bkp2/NordeaParser/categories/" + files[i] + ".txt");

			if (file.exists() && !file.isDirectory()) {
				// do something
			} else {
				try {
					file.createNewFile();
					System.out.println("File created: " + file.getAbsolutePath());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			try (BufferedReader br = new BufferedReader(new FileReader(file))) {
				String line;
				while ((line = br.readLine()) != null) {
					if (transaction.contains(line)) {
						this.category = files[i];
						totalSums[i] += amount;
						break;
					}
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void printTotalSums() {
		for (int i = 0; i < totalSums.length; i++) {
			System.out.format("%.0f ", totalSums[i]);
		}
		System.out.println();
	}

	private void setDate(String date, String transaction) {
		Pattern pat = Pattern.compile("Kortköp ([0-9]{6}) (.*)");
		Matcher matcher = pat.matcher(transaction);
		DateTimeFormatter formatter;
		String dateStr;

		if (matcher.matches()) {
			formatter = DateTimeFormat.forPattern("yyMMdd");
			// format = new SimpleDateFormat("yyMMdd", Locale.ENGLISH);
			dateStr = matcher.group(1);
		} else {
			formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
			// format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
			dateStr = date;
		}

		this.date = formatter.parseDateTime(dateStr);

		if (end == null) {
			end = this.date;
		}
		if (start == null) {
			start = this.date;
		}

		if (this.date.isBefore(start)) {
			start = this.date;
		}
		if (this.date.isAfter(end)) {
			end = this.date;
		}
	}

	public void print() {
		DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
		System.out.format("%s: %s, %s, %.0f\n", category, formatter.print(date), transaction, amount);
	}

}
