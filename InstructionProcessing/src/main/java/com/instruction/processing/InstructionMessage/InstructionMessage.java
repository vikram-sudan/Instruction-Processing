package com.instruction.processing.InstructionMessage;

import org.apache.commons.lang.StringUtils;

import com.instruction.processing.InstructionMessageValidationService.InstructionMessageValidationService;
import com.instruction.processing.InstructionQueue.InstructionQueue;
import com.instruction.processing.api.InstructionProcessingAPI.IConstants;
import com.instruction.processing.api.InstructionProcessingAPI.IMessageReceiver;

public class InstructionMessage implements IMessageReceiver {
	private char instructionType;
	private String productCode;
	private int quantity;
	private int UOM;
	private long timeStamp;
	private String messagePriority;
	InstructionQueue instructionQueue = new InstructionQueue();

	public char getInstructionType() {
		return instructionType;
	}

	public void setInstructionType(char instructionType) {
		this.instructionType = instructionType;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getUOM() {
		return UOM;
	}

	public void setUOM(int uOM) {
		UOM = uOM;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public void receive(String instructionMessage) {
		InstructionMessageValidationService validationService = new InstructionMessageValidationService();

		if (validationService.validateInstructionMessage(instructionMessage)) {
			instructionQueue.enqueue(setInstructionMessage(instructionMessage));
		}
	}
	
	public boolean isEmpty(){
		return instructionQueue.isEmpty();
	}
	
	public int getCount(){
		return instructionQueue.count();
	}

	public InstructionMessage peekQueue(){
		return instructionQueue.peek();
	}
	
	public InstructionMessage deQueue(){
		return instructionQueue.dequeue();
	}
	
	public void printQueue(){
		instructionQueue.printQueue();
	}
	
	protected InstructionMessage setInstructionMessage(String messageReceived) {
		InstructionMessage instructionMessage = new InstructionMessage();
		String[] instructionArray = StringUtils.split(messageReceived);

		instructionMessage.setInstructionType(instructionArray[1].charAt(0));
		instructionMessage.setProductCode(instructionArray[2]);
		instructionMessage.setUOM(Integer.valueOf(instructionArray[3]));
		instructionMessage.setQuantity(Integer.valueOf(instructionArray[4]));
		//instructionMessage.setTimeStamp(Long.valueOf(instructionArray[5]));
		instructionMessage.setMessagePriority(instructionArray[1].charAt(0));

		return instructionMessage;
	}

	public String getMessagePriority() {
		return messagePriority;
	}

	public void setMessagePriority(char instructionType) {
		if (IConstants.INSTRUCTIONTYPELIST[0] == instructionType) {
			this.messagePriority = IConstants.HIGH_PRIORITY;
		} else if (IConstants.INSTRUCTIONTYPELIST[1] == instructionType) {
			this.messagePriority = IConstants.MEDIUM_PRIORITY;
		} else if (IConstants.INSTRUCTIONTYPELIST[2] == instructionType ||
				   IConstants.INSTRUCTIONTYPELIST[3] == instructionType) {
			this.messagePriority = IConstants.LOW_PRIORITY;
		}

	}
	
	public String toString(){
		return "Instruction Type: "+this.getInstructionType()+" Product Code: "+this.getProductCode()+" Quantity: "+this.getQuantity()+" UOM: "+this.getUOM()+" Timestamp: "+this.getTimeStamp()+" Priority: "+this.getMessagePriority();
	}

}
