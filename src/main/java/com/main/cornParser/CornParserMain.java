package com.main.cornParser;

import com.main.cornParser.exception.InvalidCronStringException;
import com.main.cornParser.util.Constants;

public class CornParserMain {

	public static void main(String[] args) throws Exception {
		
		if(args.length != 1) {
			throw new InvalidCronStringException(Constants.INVALID_INPUT_FORMAT_MSG);
		}
		
		String cornString = args[0];
		
		CornParser.parseCornExpression(cornString);
		
	}

}
