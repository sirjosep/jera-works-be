package com.lawencon.admin.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {

	public static String dateTimeFormat(LocalDateTime localDateTime) {
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a");

		return localDateTime.format(formatter);
	}

	public static String dateFormat(LocalDate date) {
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		return date.format(formatter);
	}

	public static LocalDateTime dateTimeParse(String dateStr) {
		final LocalDateTime date = LocalDateTime.parse(dateStr, DateTimeFormatter.ISO_DATE_TIME);

		return date;
	}

	public static LocalDate dateParse(String dateStr) {
		final LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ISO_DATE_TIME);

		return date;
	}
	
	public static LocalDate dateParseTest(String dateStr) {
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss.S");
		final LocalDate date = LocalDate.parse(dateStr, formatter);

		return date;
	}
	
	public static String yearFormat(LocalDate date) {
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");

		return date.format(formatter);
	}

}
