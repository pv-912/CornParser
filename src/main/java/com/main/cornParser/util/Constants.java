package com.main.cornParser.util;

public class Constants {
	
	// min max values of the fields
	public static final int[] MIN_FIELD_VALUES = {0, 0, 1, 1, 1};
    public static final int[] MAX_FIELD_VALUES = {59, 23, 31, 12, 7};
    public static final String[] FIELD_NAMES = {"minute", "hour", "day of month", "month", "day of week", "command"};
	
	// Error Messages
	public static final String INVALID_INPUT_FORMAT_MSG = "Invalid input format. Please try in the format: '*/15 0 1,15 * 1-5 /usr/bin/find'";
	public static final String INVALID_RANGE_FORMAT_MSG = "Invalid input format for range. Please try in the format: '1-5'";
	public static final String INVALID_STEP_FORMAT_MSG = "Invalid input format for step values. Please try in the format: '*/5 or 2/3'";
	public static final String INVALID_STEP_FIRST_INPUT_MSG = "Invalid input format for step values. First value should be * or integer.";
	public static final String INVALID_INDIVIDUAL_INPUT_MSG = "Invalid input format for individual value. Single value should be integer.";
	public static final String INVALID_INPUT_RANGE_MSG = "Invalid input range. Please enter the values in the range valid range. ";
	public static final String INVALID_INTEGER_INPUT_MSG = "Invalid integer value. Please enter integer values in correct format.";
	public static final String INVALID_STEP_INTEVAL_MSG = "Invalid interval value for step. Please enter integer values greater than zero.";

}
