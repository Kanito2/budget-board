/**
 * 
 */
package com.joel.budget;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author joel
 *
 */
public class Entry {

	private Date date;
	private String transaction;
	private String category;
	private float amount;

	public Entry(String date, String transaction, String amount) throws ParseException {

		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		this.date = format.parse(date);
		System.out.format("%s, %s\n", date, transaction);

	}
}
