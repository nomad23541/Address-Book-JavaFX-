package com.chrisreading.address.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Helper class for handling dates.
 */
public class DateUtil {
	
	/** date pattern used for formatting */
	private static final String DATE_PATTERN = "dd.MM.yyyy";
	
	private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);
	
	/**
	 * Returns the given date and the formatted Strings
	 * 
	 * @param date the date to be returned as a string
	 * @return formatted string
	 */
	public static String format(LocalDate date) {
		if(date == null)
			return null;
		return DATE_FORMATTER.format(date);
	}
	
	/**
	 * Converts a string in the date format to a LocalDate Object
	 * 
	 * returns null if can't be converted
	 * 
	 * @param dateString date as a string
	 * @return the date object
	 */
	public static LocalDate parse(String dateString) {
		try {
			return DATE_FORMATTER.parse(dateString, LocalDate::from);
		} catch (DateTimeParseException e) {
			return null;
		}
	}
	
	/**
	 * Checks the String to see if it is valid.
	 * 
	 * @param dateString
	 * @return
	 */
	public static boolean validDate(String dateString) {
		// try to parse
		return DateUtil.parse(dateString) != null;
	}

}
