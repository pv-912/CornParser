package com.main.cornParser.parser;

import java.util.ArrayList;
import java.util.List;

import com.main.cornParser.exception.CronParsingException;
import com.main.cornParser.util.Constants;
import com.main.cornParser.util.Util;

public class SingleValueParser implements Parser{

	@Override
	public List<String> parseField(String fieldString, int min, int max) throws Exception {
		List<String> fieldList = new ArrayList<>();
		
		try {
			if(Util.validateField(fieldString, min, max)) {
				fieldList.add(fieldString);
			};
		} catch (Exception e) {
			throw new CronParsingException(Constants.INVALID_INDIVIDUAL_INPUT_MSG);
		}
		
		return fieldList;
	}

}
