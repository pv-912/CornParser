package com.main.cornParser.parser;

import java.util.ArrayList;
import java.util.List;

import com.main.cornParser.exception.InvalidCronStringException;
import com.main.cornParser.util.Constants;
import com.main.cornParser.util.Util;

public class StepValueParser implements Parser{

	@Override
	public List<String> parseField(String fieldString, int min, int max) throws Exception {
		String[] inputStrings = fieldString.split("/");
		
		if(inputStrings.length != 2) {
			throw new InvalidCronStringException(Constants.INVALID_STEP_FORMAT_MSG);
		}
		int start = min;
		
		// If start is not * then set to input value
		if(!"*".equals(inputStrings[0])){
			
			try {
				Util.validateField(inputStrings[0], min, max);
			} catch (Exception e) {
				throw new InvalidCronStringException(Constants.INVALID_STEP_FIRST_INPUT_MSG);
			}
			
			start = Integer.parseInt(inputStrings[0]);
        }
		
		Util.validateField(inputStrings[1], min, max);
		
		int interval = Integer.parseInt(inputStrings[1]);
		
		// Interval should not be equal to zero to avoid out of memory
		if(interval == 0) {
			throw new InvalidCronStringException(Constants.INVALID_STEP_INTEVAL_MSG);
		}
		
		List<String> fieldList = new ArrayList<>();
		for(int i = start; i<= max; i+=interval) {
			fieldList.add(String.valueOf(i));
		}
		
 		return fieldList;
	}

}
