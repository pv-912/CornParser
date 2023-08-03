package com.main.cornParser.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.main.cornParser.exception.CronParsingException;
import com.main.cornParser.exception.InvalidCronStringException;
import com.main.cornParser.util.Constants;

public class StepValueParserTest {

	private static Parser stepParser;
	
	@BeforeAll
	static void setup() {
		stepParser = new StepValueParser();
	}
	
	@Test
	public void stepValueOfMinutesPerTenMin() throws Exception {
		String actual = stepParser.parseField("*/10", Constants.MIN_FIELD_VALUES[0], Constants.MAX_FIELD_VALUES[0]).stream().collect(Collectors.joining(" "));
		assertEquals(actual, "0 10 20 30 40 50");
	}
	
	@Test
	public void stepValueStartingFromThirdMonthPerThreeMonth() throws Exception {
		String actual = stepParser.parseField("3/3", Constants.MIN_FIELD_VALUES[3], Constants.MAX_FIELD_VALUES[3]).stream().collect(Collectors.joining(" "));
		assertEquals(actual, "3 6 9 12");
	}
	
	@Test
	public void parseWrongString() throws Exception {
		assertThrows(CronParsingException.class, () -> {
			stepParser.parseField("*/*", Constants.MIN_FIELD_VALUES[3], Constants.MAX_FIELD_VALUES[3]);
		});
	}
	
	@Test
	public void parseWrongStringWithExceedingMonthMaxValue() throws Exception {
		assertThrows(InvalidCronStringException.class, () -> {
			stepParser.parseField("*/25", Constants.MIN_FIELD_VALUES[3], Constants.MAX_FIELD_VALUES[3]);
		});
	}
}
