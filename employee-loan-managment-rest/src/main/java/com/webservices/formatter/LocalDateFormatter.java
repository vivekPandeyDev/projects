package com.webservices.formatter;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.springframework.format.Formatter;

public class LocalDateFormatter implements Formatter<LocalDate> {

	@Override
	public String print(LocalDate object, Locale locale) {
		return object.toString();
	}

	@Override
	public LocalDate parse(String text, Locale locale) throws ParseException {
		return LocalDate.parse(text, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}

}
