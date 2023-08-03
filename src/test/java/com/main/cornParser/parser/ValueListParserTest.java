package com.main.cornParser.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.main.cornParser.exception.CronParsingException;
import com.main.cornParser.exception.InvalidCronStringException;
import com.main.cornParser.util.Constants;

public class ValueListParserTest {
	private static Parser valueListParser;
	
	@BeforeAll
	static void setup() {
		valueListParser = new ValueListParser();
	}
	
	@Test
	public void valueListForMinutes() throws Exception {
		String actual = valueListParser.parseField("10,20,30,40", Constants.MIN_FIELD_VALUES[0], Constants.MAX_FIELD_VALUES[0]).stream().collect(Collectors.joining(" "));
		assertEquals(actual, "10 20 30 40");
	}
	
	@Test
	public void valueListForyear() throws Exception {
		String actual = valueListParser.parseField("1,5,7", Constants.MIN_FIELD_VALUES[3], Constants.MAX_FIELD_VALUES[3]).stream().collect(Collectors.joining(" "));
		assertEquals(actual, "1 5 7");
	}
	
	@Test
	public void parseWrongString() throws Exception {
		assertThrows(CronParsingException.class, () -> {
			valueListParser.parseField(",1,2,3,", Constants.MIN_FIELD_VALUES[3], Constants.MAX_FIELD_VALUES[3]);
		});
	}
	
	@Test
	public void parseWrongStringWithExceedingMonthMaxValue() throws Exception {
		assertThrows(InvalidCronStringException.class, () -> {
			valueListParser.parseField("1,3,13,14", Constants.MIN_FIELD_VALUES[3], Constants.MAX_FIELD_VALUES[3]);
		});
	}
}
