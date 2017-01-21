/**
 * 
 */
package com.joel.budget;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author joel
 *
 */
public class Entry {

	private Date date;
	private String transaction;
	private String category;
	private float amount;

	public Entry(String date, String transaction, String amount) {
		setDate(date, transaction);
		this.transaction = transaction;
		setCategory(transaction);
		this.amount = Float.parseFloat(amount.replace(".", "").replace(",", "."));
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
					// System.out.println(line);
					if (transaction.contains(line)) {
						this.category = files[i];
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

	private void setDate(String date, String transaction) {
		Pattern pat = Pattern.compile("Kortköp ([0-9]{6}) (.*)");
		Matcher matcher = pat.matcher(transaction);
		DateFormat format;
		String dateStr;

		if (matcher.matches()) {
			format = new SimpleDateFormat("yyMMdd", Locale.ENGLISH);
			dateStr = matcher.group(1);
		} else {
			format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
			dateStr = date;
		}

		try {
			this.date = format.parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void print() {
		System.out.format("%s: %s, %s, %.2f\n", category, date, transaction, amount);
	}

}
