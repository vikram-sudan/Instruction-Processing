package com.instruction.processing.InstructionMessageValidationServiceTest;

import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import com.instruction.processing.InstructionMessageValidationService.InstructionMessageValidationService;

public class InstructionMessageValidationServiceTest {
	InstructionMessageValidationService underTest;
	
	@Before
	public void setUp() {
		underTest = new InstructionMessageValidationService();
    }
	
	@Test
	public void validateInstructionTypeTest(){
		
		String instTypeA = "A";
		String instTypeB = "B";
		String instTypeZ = "Z";
		String instTypeD ="D";
		String instTypea = "a";
		String instType = "!";
		
		assertEquals(underTest.validateInstructionType(instTypeA),true);
		assertEquals(underTest.validateInstructionType(instTypeB),true);
		assertEquals(underTest.validateInstructionType(instTypeZ),false);
		assertEquals(underTest.validateInstructionType(instTypeD),true);
		assertEquals(underTest.validateInstructionType(instTypea),false);
		assertEquals(underTest.validateInstructionType(instType),false);
		
	}
	
	@Test
	public void validateProductCodeTest(){
		
		String validProductCode1 = "AB21";
		String validProductCode2 = "BC45";
		String validProductCode3 = "ZE78";
		String validProductCode4 ="EE11";
				
		String invalidProductCode1 = "aaaa";
		String invalidProductCode2 = "1122";
		String invalidProductCode3 = "1abe";
		String invalidProductCode4 = "$£%£||";
		String invalidProductCode5 = "Aa12";
		String invalidProductCode6 = "AAT1";
		
		assertEquals(underTest.validateProductCode(validProductCode1),true);
		assertEquals(underTest.validateProductCode(validProductCode2),true);
		assertEquals(underTest.validateProductCode(validProductCode3),true);
		assertEquals(underTest.validateProductCode(validProductCode4),true);
		
		assertEquals(underTest.validateProductCode(invalidProductCode1),false);
		assertEquals(underTest.validateProductCode(invalidProductCode2),false);
		assertEquals(underTest.validateProductCode(invalidProductCode3),false);
		assertEquals(underTest.validateProductCode(invalidProductCode4),false);
		assertEquals(underTest.validateProductCode(invalidProductCode5),false);
		assertEquals(underTest.validateProductCode(invalidProductCode6),false);
		
	}
	
	@Test
	public void validateUomCodeTest(){
		
		String validUomCode1 = "121";
		String validUomCode2 = "12";
		String validUomCode3 = "255";
		String validUomCode4 ="0";
				
		String invalidUomCode1 = "256";
		String invalidUomCode2 = "-1";
		String invalidUomCode3 = "1a2";
		String invalidUomCode4= "aa";
		String invalidUomCode5 = "1031";
			
		assertEquals(underTest.validateUomCode(validUomCode1),true);
		assertEquals(underTest.validateUomCode(validUomCode2),true);
		assertEquals(underTest.validateUomCode(validUomCode3),true);
		assertEquals(underTest.validateUomCode(validUomCode4),true);
		
		assertEquals(underTest.validateUomCode(invalidUomCode1),false);
		assertEquals(underTest.validateUomCode(invalidUomCode2),false);
		assertEquals(underTest.validateUomCode(invalidUomCode3),false);
		assertEquals(underTest.validateUomCode(invalidUomCode4),false);
		assertEquals(underTest.validateUomCode(invalidUomCode5),false);
		
		
	}
	
	@Test
	public void validateQuantityTest(){
		
		String validQuantityCode1 = "121";
		String validQuantityCode2 = "12";
		String validQuantityCode3 = "255";
		String validQuantityCode4 ="19870";
				
		String invalidQuantityCode1 = "0";
		String invalidQuantityCode2 = "-1";
		String invalidQuantityCode3 = "1a2";
		String invalidQuantityCode4 = "aa";
		String invalidQuantityCode5 = "1031L";
			
		assertEquals(underTest.validateQuantity(validQuantityCode1),true);
		assertEquals(underTest.validateQuantity(validQuantityCode2),true);
		assertEquals(underTest.validateQuantity(validQuantityCode3),true);
		assertEquals(underTest.validateQuantity(validQuantityCode4),true);
		
		assertEquals(underTest.validateQuantity(invalidQuantityCode1),false);
		assertEquals(underTest.validateQuantity(invalidQuantityCode2),false);
		assertEquals(underTest.validateQuantity(invalidQuantityCode3),false);
		assertEquals(underTest.validateQuantity(invalidQuantityCode4),false);
		assertEquals(underTest.validateQuantity(invalidQuantityCode5),false);		
		
	}
	
	@Test
	public void validateTimeStampTest(){
		
		String validTimeStamp1 = "2016-11-29T18:46:19.012Z";
		String validTimeStamp2 = "2016-11-30T18:46:19.012Z";
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
		String tomorrow = sdf.format(cal.getTime());
		
		cal.add(Calendar.MONTH,1 );
		String nextMonth = sdf.format(cal.getTime());
				
		assertEquals(underTest.validateTimeStamp(validTimeStamp1),true);
		assertEquals(underTest.validateTimeStamp(validTimeStamp2),true);

		assertEquals(underTest.validateTimeStamp(tomorrow),false);
		assertEquals(underTest.validateTimeStamp(nextMonth),false);			
		
	}
	
	@Test
	public void validateInstructionMessageTest(){
		
		String validMessage1 = "InstructionMessage A MZ89 5678 50 2016­08­05T10:04:56.012Z";
		String validMessage2 = "InstructionMessage B MZ90 5679 60 2016­07­05T10:04:56.012Z";
		String validMessage3 = "InstructionMessage C AG91 5680 70 2016­05­05T10:04:56.012Z";
		String validMessage4 = "InstructionMessage D MZ81 5878 90 2016­03­05T10:04:56.012Z";
				
		String invalidMessage1 = "InstructionMessage E EF12 5681 80 2016­04­05T10:04:56.012Z";
		String invalidMessage2 = "InstructionMessage A aa12 5681 80 2016­04­05T10:04:56.012Z";
		String invalidMessage3 = "InstructionMessage E EF12 5681 256 2016­04­05T10:04:56.012Z";
		String invalidMessage4 = "InstructionMessage E EF12 5681 282 2017­04­05T10:04:56.012Z";
		String invalidMessage5 = "InstructionMessage E EF12 -100 -9 2017­04­05T10:04:56.012Z";
		String invalidMessage6 = "InstructionMessage E EF12 100 -9 2017­04­05T10:04:56.012Z";
		String invalidMessage7 = "InstructionMessage E EF12 aa 23 2017­04­05T10:04:56.012Z";
			
		assertEquals(underTest.validateInstructionMessage(validMessage1),true);
		assertEquals(underTest.validateInstructionMessage(validMessage2),true);		
		assertEquals(underTest.validateInstructionMessage(validMessage3),true);
		assertEquals(underTest.validateInstructionMessage(validMessage4),true);
		
		assertEquals(underTest.validateInstructionMessage(invalidMessage1),false);
		assertEquals(underTest.validateInstructionMessage(invalidMessage2),false);	
		assertEquals(underTest.validateInstructionMessage(invalidMessage3),false);	
		assertEquals(underTest.validateInstructionMessage(invalidMessage4),false);
		assertEquals(underTest.validateInstructionMessage(invalidMessage5),false);
		assertEquals(underTest.validateInstructionMessage(invalidMessage6),false);
		assertEquals(underTest.validateInstructionMessage(invalidMessage7),false);
		
	}
}
