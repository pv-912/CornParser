package com.main.cornParser.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.main.cornParser.exception.InvalidCronStringException;
import com.main.cornParser.util.Constants;

public class RangeValueParsertest {
	private static Parser rangeParser;
	
	@BeforeAll
	static void setup() {
		rangeParser = new RangeValueParser();
	}
	
	@Test
	public void rangeValueForMinutes() throws Exception {
		String actual = rangeParser.parseField("1-5", Constants.MIN_FIELD_VALUES[0], Constants.MAX_FIELD_VALUES[0]).stream().collect(Collectors.joining(" "));
		assertEquals(actual, "1 2 3 4 5");
	}
	
	@Test
	public void rangeValueForMonth() throws Exception {
		String actual = rangeParser.parseField("10-12", Constants.MIN_FIELD_VALUES[3], Constants.MAX_FIELD_VALUES[3]).stream().collect(Collectors.joining(" "));
		assertEquals(actual, "10 11 12");
	}
	
	@Test
	public void parseWrongString() throws Exception {
		assertThrows(InvalidCronStringException.class, () -> {
			rangeParser.parseField("1-", Constants.MIN_FIELD_VALUES[3], Constants.MAX_FIELD_VALUES[3]);
		});
	}
	
	@Test
	public void parseWrongStringWithExceedingMonthMaxValue() throws Exception {
		assertThrows(InvalidCronStringException.class, () -> {
			rangeParser.parseField("1-25", Constants.MIN_FIELD_VALUES[3], Constants.MAX_FIELD_VALUES[3]);
		});
	}
}
