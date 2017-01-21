/**
 * 
 */
package com.joel.budget;

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

		this.transaction = transaction;

		this.amount = Float.parseFloat(amount.replace(".", "").replace(",", "."));

		System.out.format("%s, %s, %.2f\n", this.date, this.transaction, this.amount);

	}
}
