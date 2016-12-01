package com.instruction.processing.api.InstructionProcessingAPI;

public class IConstants {
	public static boolean RETURN_TRUE = true;
	public static boolean RETURN_FALSE = false;
	public static char[] INSTRUCTIONTYPELIST = {'A','B','C','D'};
	public static int  PRODUCTCODELENGTH = 4;
	public static String PRODUCTCODEREGEX = "[A-Z]{2}[0-9]{2}";
	public static String IS_INTEGER_REGEX = "-?\\d+";
	public static int MIN_UOMCODE = 0;
	public static int MAX_UOMCODE = 256;
	public static int NEGATIVE_UOMCODE= -1;
	public static int ZEROQTY = 0;
	public static String HIGH_PRIORITY = "High";
	public static String MEDIUM_PRIORITY = "MEDIUM";
	public static String LOW_PRIORITY = "LOW";	
}
