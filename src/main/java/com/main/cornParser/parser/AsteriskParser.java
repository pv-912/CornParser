package com.main.cornParser.parser;

import java.util.ArrayList;
import java.util.List;

import com.main.cornParser.exception.CronParsingException;

public class AsteriskParser implements Parser{

	@Override
	public List<String> parseField(String fieldString, int min, int max) throws Exception {
		
		if(!fieldString.equals("*")) {
			throw new CronParsingException("Unexpected value when expected *");
		}
		
		List<String> fieldList = new ArrayList<>();
		
		for(int i = min; i<=max; i++) {
			fieldList.add(String.valueOf(i));
		}

		return fieldList;
	}

}
