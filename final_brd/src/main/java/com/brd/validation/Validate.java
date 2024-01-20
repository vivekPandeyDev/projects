package com.brd.validation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Validate {

	public static <T> boolean dataTypeValidate(T input, String type) {
		if (type.equalsIgnoreCase("numeric")) {
			type = "Integer";
		}
		String checkType = input.getClass().getSimpleName();
		return checkType.equalsIgnoreCase(type);
	}

	public static <T> boolean dataLength(T type, int length) {
		return type.toString().length() <= length;
	}

	public static boolean specialCharacter(String checker, String specialCharacter) {
		char[] characterArray = specialCharacter.toCharArray();
		for (char character : characterArray) {
			if (checker.indexOf(character) != -1) {
				return false;
			}
		}
		return true;
	}

	public static <T> boolean domainValue(T value, T[] list) {
		for (T ele : list) {
			if (ele == value) {
				return true;
			}
		}
		return false;
	}

	public static boolean validateEmail(String mail) {
		char[] neededCharacter = { '@', '.' };
		if (mail.contains(" ")) {
			return false;
		}
		for (char ch : neededCharacter) {
			if (mail.indexOf(ch) == -1) {
				return false;
			}
		}
		return true;
	}

	public static boolean formatValidation(String date, String format) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		try {
			LocalDate ld = LocalDate.parse(date, formatter);
			String result = ld.format(formatter);
			return date.equals(result);
		} catch (DateTimeParseException e) {
			return false;
		}

	}

}