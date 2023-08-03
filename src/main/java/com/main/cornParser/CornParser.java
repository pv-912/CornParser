package com.main.cornParser;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.main.cornParser.parser.SingleValueParser;
import com.main.cornParser.exception.InvalidCronStringException;
import com.main.cornParser.parser.AsteriskParser;
import com.main.cornParser.parser.Parser;
import com.main.cornParser.parser.RangeValueParser;
import com.main.cornParser.parser.StepValueParser;
import com.main.cornParser.parser.ValueListParser;
import com.main.cornParser.util.Constants;
import com.main.cornParser.util.Util;

public class CornParser {

	public static void parseCornExpression(String cornExpression) throws Exception {
		
		// Validate if cornString is splitable in 6 input fields
		if(!Util.isCornStringValid(cornExpression)) {
			throw new InvalidCronStringException(Constants.INVALID_INPUT_FORMAT_MSG);
		}
		
		List<String> parsedFieldList = parseCornExpressionFields(cornExpression);
		printOutput(parsedFieldList);
	}
	
	private static List<String> parseCornExpressionFields(String cornString) throws Exception {
		List<String> allParsedFields = new ArrayList<>();
		
		String[] fields = cornString.split(" ");
		
		for(int i = 0; i<5; i++) {
			
			Parser cornFieldParser;
			
			if ("*".equals(fields[i])) {
				cornFieldParser = new AsteriskParser();
			} 
			else if (fields[i].contains(",")) {
				cornFieldParser = new ValueListParser();
		    } 
			else if (fields[i].contains("/")) {
		    	cornFieldParser = new StepValueParser();
		    } 
			else if (fields[i].contains("-")) {
		    	cornFieldParser = new RangeValueParser();
		    } 
			else {
		    	cornFieldParser = new SingleValueParser();
		    }
			
			List<String> parseField = cornFieldParser.parseField(fields[i], Constants.MIN_FIELD_VALUES[i], Constants.MAX_FIELD_VALUES[i]);
			String parsedvalue 		= parseField.stream().collect(Collectors.joining(" "));
			allParsedFields.add(parsedvalue);
			
		}
		
		// add command field
		allParsedFields.add(fields[5]);
		
		return allParsedFields;
	}
	
	private static void printOutput(List<String> parsedFieldList) {

		System.out.println();

		for(int i =0; i< parsedFieldList.size(); i++) {
			System.out.printf("%-14s%s%n", Constants.FIELD_NAMES[i], parsedFieldList.get(i));
		}
		
		System.out.println();
		
	}
	
	/*
	 * Wrapper public function for parseCornExpressionFields for JUnit Test
	 */
	public static List<String> parseCornExpressionFieldsWrapper(String cornString) throws Exception {
		return parseCornExpressionFields(cornString);
	}
	
}
