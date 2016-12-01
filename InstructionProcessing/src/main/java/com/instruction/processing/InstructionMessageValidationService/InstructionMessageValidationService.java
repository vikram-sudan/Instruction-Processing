package com.instruction.processing.InstructionMessageValidationService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.instruction.processing.api.InstructionProcessingAPI.IConstants;

public class InstructionMessageValidationService {

	public boolean validateInstructionType(String instructionType) {
		// char instType = instructionType.charAt(0);
		if ((new String(IConstants.INSTRUCTIONTYPELIST)
				.contains(instructionType))) {
			return IConstants.RETURN_TRUE;
		}
		return IConstants.RETURN_FALSE;
	}

	public boolean validateProductCode(String productCode) {
		if (IConstants.PRODUCTCODELENGTH == productCode.length()
				&& productCode.matches(IConstants.PRODUCTCODEREGEX)) {
			return IConstants.RETURN_TRUE;
		}
		return IConstants.RETURN_FALSE;
	}

	public boolean validateUomCode(String uomCode) {
		if (uomCode.matches(IConstants.IS_INTEGER_REGEX)) {
			int uomCodeValue = Integer.valueOf(uomCode);
			if (IConstants.MIN_UOMCODE <= uomCodeValue
					&& IConstants.MAX_UOMCODE > uomCodeValue) {
				return IConstants.RETURN_TRUE;
			}
		}
		return IConstants.RETURN_FALSE;
	}

	public boolean validateQuantity(String quantity) {
		if (quantity.matches(IConstants.IS_INTEGER_REGEX)) {
			if (IConstants.ZEROQTY < Integer.valueOf(quantity)) {
				return IConstants.RETURN_TRUE;
			}
		}
		return IConstants.RETURN_FALSE;
	}

	public boolean validateTimeStamp(String timeStamp) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
		Date messageDate;
		Date currentDate = new Date();
		long epochMessageDate = 0;
		long epochCurrentDate = 0;
		try {
			messageDate = sdf.parse(timeStamp);
			String today = sdf.format(currentDate);
			currentDate = sdf.parse(today);
			epochMessageDate = messageDate.getTime();
			epochCurrentDate = currentDate.getTime();
		} catch (ParseException e) {
			e.printStackTrace();			
		}

		if (epochMessageDate <= epochCurrentDate) {
			return IConstants.RETURN_TRUE;
		}
		return IConstants.RETURN_FALSE;
	}

	public boolean validateInstructionMessage(String instructionMsg) {
		String[] instructionArray = StringUtils.split(instructionMsg);
		boolean isValid = IConstants.RETURN_TRUE;

		if (instructionArray != null) {

			if (StringUtils.isEmpty(instructionArray[1])
					|| !validateInstructionType(instructionArray[1])) {
				return IConstants.RETURN_FALSE;
			}

			if (StringUtils.isEmpty(instructionArray[2])
					|| !validateProductCode(instructionArray[2])) {
				return IConstants.RETURN_FALSE;
			}

			if (StringUtils.isEmpty(instructionArray[4])
					|| !validateUomCode(instructionArray[4])) {
				return IConstants.RETURN_FALSE;
			}

			if (StringUtils.isEmpty(instructionArray[3])
					|| !validateQuantity(instructionArray[3])) {
				return IConstants.RETURN_FALSE;
			}

			 if (StringUtils.isEmpty(instructionArray[5]) ||
					 !validateTimeStamp(instructionArray[5])) {
			 return IConstants.RETURN_FALSE;
			 }
		}
		return isValid;
	}
}
