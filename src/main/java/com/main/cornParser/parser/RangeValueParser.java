package com.main.cornParser.parser;

import java.util.ArrayList;
import java.util.List;

import com.main.cornParser.exception.InvalidCronStringException;
import com.main.cornParser.util.Constants;
import com.main.cornParser.util.Util;

public class RangeValueParser implements Parser{

	@Override
	public List<String> parseField(String fieldString, int min, int max) throws Exception {
		
		String[] inputs = fieldString.split("-");
		
		if(inputs.length != 2) {
			throw new InvalidCronStringException(Constants.INVALID_RANGE_FORMAT_MSG);
		}
		
		// Validate if inputs are integer values
		Util.validateField(inputs[0], min, max);
		Util.validateField(inputs[0], min, max);
		
		Integer start = Integer.parseInt(inputs[0]);
		Integer end = Integer.parseInt(inputs[1]);
		
		// Start and end range should be in valid range
		validateInputs(start, end, min, max);
		
		List<String> valueList = new ArrayList<>();
		for(int i = start ; i <= end; i++) {
			valueList.add(String.valueOf(i));
		}
		
		return valueList;
	}
	
	private boolean validateInputs(int start, int end, int min, int max) throws InvalidCronStringException {
		
		// start should not be greater than end
		if(start < min || end > max || start > end) {
			throw new InvalidCronStringException(Constants.INVALID_INPUT_RANGE_MSG + min + " - " + max);
		}
		
		return true;
	}

	
}
