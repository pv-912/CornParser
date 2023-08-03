package com.main.cornParser.util;

import com.main.cornParser.exception.CronParsingException;
import com.main.cornParser.exception.InvalidCronStringException;

public class Util {

	/*
	 * Returns if Corn Expression has 6 input fields or not
	 * Output: True/False
	 */
	public static boolean isCornStringValid(String cornString) {
		return cornString.split(" ").length == 6 ? true : false;
	}
	
	/*
	 * validates if individual input value is parseable to Integer
	 * Output: True if can be parsed
	 * Else Exception
	 */
	public static boolean validateField(String value, int minValue, int maxValue) throws Exception {
		Integer intValue;
		try {
			intValue = Integer.parseInt(value);
		} catch (Exception e) {
			throw new CronParsingException(Constants.INVALID_INTEGER_INPUT_MSG);
		}
		
		if(intValue < minValue || intValue > maxValue) {
			throw new InvalidCronStringException(Constants.INVALID_INPUT_RANGE_MSG);
		}
		
		return true;
	}
			
}
