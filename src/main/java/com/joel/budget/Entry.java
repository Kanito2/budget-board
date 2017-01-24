/**
 * 
 */
package com.joel.budget;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
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
	private static int[] totalSums = new int[7];
	private static DateTime start;
	private static DateTime end;

	public Entry(String date, String transaction, String amount) {
		setDate(date, transaction);
		this.transaction = transaction;
		this.amount = Float.parseFloat(amount.replace(".", "").replace(",", "."));
		File dir = new File("categoryFiles");
		dir.mkdir();
		if (dir.exists() && dir.isDirectory()) {
			// do nothing
		} else {
			dir.mkdir();
		}
		setCategory(transaction);
	}

	public static int daysBetween() {
		int days = Days.daysBetween(start, end).getDays();
		return days;
	}

	public static int[] monthlyExpense() {
		int days = daysBetween();
		int[] monthlyExpense = Arrays.stream(totalSums).map(i -> Math.abs(30 * i / days)).toArray();
		monthlyExpense = Arrays.stream(monthlyExpense).map(i -> round(i, 500)).toArray();
		printArray(monthlyExpense);
		return monthlyExpense;
	}
	
	public static void printArray(int[] array) {
		for (int i = 0; i < array.length; i++) {
			System.out.format("%s ", array[i]);
		}
		System.out.println();
	}

	private static int round(double i, int v) {
		return (int) (Math.round(i / v) * v);
	}

	private void setCategory(String transaction) {
		String[] files = { "home", "food", "transport", "cloth", "saving", "other", "loan" };

		for (int i = 0; i < files.length; i++) {
			File file = new File("categoryFiles" + File.separator + files[i] + ".txt");

			if (file.exists() && !file.isDirectory()) {
				// do nothing
			} else {
				try {
					file.createNewFile();
					System.out.println("File created: " + file.getAbsolutePath());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			try (BufferedReader br = new BufferedReader(new FileReader(file))) {
				String line;
				while ((line = br.readLine()) != null) {
					if (transaction.contains(line)) {
						this.category = files[i];
						totalSums[i] += Math.round(amount);
						break;
					}
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void setDate(String date, String transaction) {
		Pattern pat = Pattern.compile("Kortköp ([0-9]{6}) (.*)");
		Matcher matcher = pat.matcher(transaction);
		DateTimeFormatter formatter;
		String dateStr;

		if (matcher.matches()) {
			formatter = DateTimeFormat.forPattern("yyMMdd");
			dateStr = matcher.group(1);
		} else {
			formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
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
