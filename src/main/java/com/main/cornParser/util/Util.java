package com.main.cornParser.util;

import com.main.cornParser.exception.CronParsingException;
import com.main.cornParser.exception.InvalidCronStringException;

public class Util {

	public static boolean isCornStringValid(String cornString) {
		return cornString.split(" ").length == 6 ? true : false;
	}
	
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
	
	// output
		
}
