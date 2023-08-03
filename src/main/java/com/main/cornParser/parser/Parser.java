package com.main.cornParser.parser;

import java.util.List;

public interface Parser {

	List<String> parseField(String fieldString, int min, int max) throws Exception;
	
}
