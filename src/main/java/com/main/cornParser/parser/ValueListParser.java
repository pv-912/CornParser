package com.main.cornParser.parser;

import java.util.ArrayList;
import java.util.List;

import com.main.cornParser.util.Util;

public class ValueListParser implements Parser{

	@Override
	public List<String> parseField(String fieldString, int min, int max) throws Exception {
		String[] values = fieldString.split(",");
		List<String> fieldList = new ArrayList<>();

		for(String val: values) {
			
			// Validate each value before adding
			Util.validateField(val, min, max);
			
			fieldList.add(val);
			
		}
		
		return fieldList;
	}

}
