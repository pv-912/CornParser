package com.main.cornParser.parser;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.main.cornParser.exception.CronParsingException;
import com.main.cornParser.util.Constants;

public class AsteriskParserTest {
	
	private static Parser asteriskParser;
	
	@BeforeAll
	static void setup() {
		asteriskParser = new AsteriskParser();
	}
	
	@Test
	public void parseAllValuesForWeek() throws Exception {
		String actual = asteriskParser.parseField("*", Constants.MIN_FIELD_VALUES[4], Constants.MAX_FIELD_VALUES[4]).stream().collect(Collectors.joining(" "));
		assertEquals(actual, "1 2 3 4 5 6 7");
	}
	
	@Test
	public void parseAllValuesForMonth() throws Exception {
		String actual = asteriskParser.parseField("*", Constants.MIN_FIELD_VALUES[3], Constants.MAX_FIELD_VALUES[3]).stream().collect(Collectors.joining(" "));
		assertEquals(actual, "1 2 3 4 5 6 7 8 9 10 11 12");
	}
	
	@Test
	public void parseWrongString() throws Exception {
		assertThrows(CronParsingException.class, () -> {
			asteriskParser.parseField("**", Constants.MIN_FIELD_VALUES[3], Constants.MAX_FIELD_VALUES[3]);
		});
	}
	
	
}
