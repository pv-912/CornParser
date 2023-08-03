package com.main.cornParser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.main.cornParser.exception.CronParsingException;
import com.main.cornParser.exception.InvalidCronStringException;
import com.main.cornParser.util.Constants;
import com.main.cornParser.util.Util;



class CornParserMainTests {

	@Test
	public void testIfCornStringValid() {
		boolean ifvalid = Util.isCornStringValid("* * * * * /usr/find");
		assertEquals(ifvalid, true);
		
		ifvalid = Util.isCornStringValid("* 1-2 3,4 * 2/4 /usr/find");
		assertEquals(ifvalid, true);
		
		ifvalid = Util.isCornStringValid("* * * * *");
		assertEquals(ifvalid, false);
		
		ifvalid = Util.isCornStringValid("1-2 3,4 * 2/4 /usr/find");
		assertEquals(ifvalid, false);
		
		ifvalid = Util.isCornStringValid("* 1-2 3,4 * 2/4 /usr/find *");
		assertEquals(ifvalid, false);

	}
	
	@Test
	public void testValidateFieldMethod() throws Exception {
		boolean ifvalid = Util.validateField("4", 1, 5);
		assertEquals(ifvalid, true);
		String expectedMessage = Constants.INVALID_INPUT_RANGE_MSG;
		Exception exception = assertThrows(InvalidCronStringException.class, () -> {
			Util.validateField("4", 1, 2);
		});
		
		String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
		
		exception = assertThrows(InvalidCronStringException.class, () -> {
			Util.validateField("-1", 1, 2);
		});
		actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);

		
		exception = assertThrows(CronParsingException.class, () -> {
			Util.validateField("abcd", 1, 2);
		});
		expectedMessage = Constants.INVALID_INTEGER_INPUT_MSG;
		actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);

	}
	
	@Test
	public void testInvalidCronString() {
		String cronString = "30 0 1 1 *"; // Missing the command field

        String expectedMessage = Constants.INVALID_INPUT_FORMAT_MSG;
        Exception exception = assertThrows(InvalidCronStringException.class, () -> CornParser.parseCornExpression(cronString));

        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
	}

	@Test
	public void testInvalidCronStringExceedingMaxValue() {
		String cronString = "*/15 0,3,99 1-5 7 1-5 /usr/bin/find"; // Missing the command field

        String expectedMessage = Constants.INVALID_INPUT_RANGE_MSG;
        Exception exception = assertThrows(InvalidCronStringException.class, () -> CornParser.parseCornExpression(cronString));

        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
	}
	
	@Test
	public void testInvalidCronStringStepValueIntervalAsZero() {
		String cronString = "*/0 0,3,99 1-5 7 1-5 /usr/bin/find"; // Missing the command field

        String expectedMessage = Constants.INVALID_STEP_INTEVAL_MSG;
        Exception exception = assertThrows(InvalidCronStringException.class, () -> CornParser.parseCornExpression(cronString));

        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
	}
	
	@Test
	public void testInvalidCronStringAsUnknownChar() {
		String cronString = "*/15 0,3 1.5 7 1-5 /usr/bin/find"; // Missing the command field

        String expectedMessage = Constants.INVALID_INDIVIDUAL_INPUT_MSG;
        Exception exception = assertThrows(CronParsingException.class, () -> CornParser.parseCornExpression(cronString));

        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
	}
	
	@Test
	public void testInvalidCronStringAsUnknownCharInValueList() {
		String cronString = "*/15 0,3,4,5,a 1-5 7 1-5 /usr/bin/find"; // Missing the command field

        String expectedMessage = Constants.INVALID_INTEGER_INPUT_MSG;
        Exception exception = assertThrows(CronParsingException.class, () -> CornParser.parseCornExpression(cronString));

        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
	}

	@Test
	public void testInvalidCronStringForMultipleRangeValue() {
		String cronString = "*/15 0,3,4,5 1-5-9 7 1-5 /usr/bin/find"; // Missing the command field

        String expectedMessage = Constants.INVALID_RANGE_FORMAT_MSG;
        Exception exception = assertThrows(InvalidCronStringException.class, () -> CornParser.parseCornExpression(cronString));

        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
	}
	
	@Test
	public void testValidCronStringOutput() throws Exception {
		String cronString = "*/15 0,3 1-5 7 1-5 /usr/bin/find";
        List<String> expandedFields = CornParser.parseCornExpressionFieldsWrapper(cronString);

        assertEquals(6, expandedFields.size());
        assertEquals("0 15 30 45", expandedFields.get(0));
        assertEquals("0 3", expandedFields.get(1));
        assertEquals("1 2 3 4 5", expandedFields.get(2));
        assertEquals("7", expandedFields.get(3));
        assertEquals("1 2 3 4 5", expandedFields.get(4));
        assertEquals("/usr/bin/find", expandedFields.get(5));
	}
	
	@Test
	public void testValidCronStringOutput2() throws Exception {
		String cronString = "10-15 1-3 1,5 */4 2 /cd/Document";
        List<String> expandedFields = CornParser.parseCornExpressionFieldsWrapper(cronString);

        assertEquals(6, expandedFields.size());
        assertEquals("10 11 12 13 14 15", expandedFields.get(0));
        assertEquals("1 2 3", expandedFields.get(1));
        assertEquals("1 5", expandedFields.get(2));
        assertEquals("1 5 9", expandedFields.get(3));
        assertEquals("2", expandedFields.get(4));
        assertEquals("/cd/Document", expandedFields.get(5));
	}
	
	@Test
	public void testValidCronStringOutput3() throws Exception {
		String cronString = "2,5,10,13,16,19 1-9 3/2 5/1 2/2 /usr/bin/find";
        List<String> expandedFields = CornParser.parseCornExpressionFieldsWrapper(cronString);

        assertEquals(6, expandedFields.size());
        assertEquals("2 5 10 13 16 19", expandedFields.get(0));
        assertEquals("1 2 3 4 5 6 7 8 9", expandedFields.get(1));
        assertEquals("3 5 7 9 11 13 15 17 19 21 23 25 27 29 31", expandedFields.get(2));
        assertEquals("5 6 7 8 9 10 11 12", expandedFields.get(3));
        assertEquals("2 4 6", expandedFields.get(4));
        assertEquals("/usr/bin/find", expandedFields.get(5));
	}

}
