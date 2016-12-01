package com.instruction.processing.InstructionQueue;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import com.instruction.processing.InstructionMessage.InstructionMessage;
import com.instruction.processing.api.InstructionProcessingAPI.IConstants;

public class InstructionQueue {
	
	public Queue<InstructionMessage> instructionQueue = new LinkedList<InstructionMessage>();
		
	// Add a message to the queue
	public void enqueue(InstructionMessage message) {
		instructionQueue.add(message);		

	}

	// Removed the highest priority message from the queue and returns it
	public InstructionMessage dequeue() {
		InstructionMessage retHighPriorityMessage = new InstructionMessage();
		Iterator<InstructionMessage> it = instructionQueue.iterator();
		while(it.hasNext()){
			InstructionMessage im = it.next();
			if(IConstants.HIGH_PRIORITY.equals(im.getMessagePriority())){
				retHighPriorityMessage = im;
				instructionQueue.remove();
				return retHighPriorityMessage;
			}
		}
		return retHighPriorityMessage;

	}

	// Returns the highest­priority message from the queue, without returning it
	public InstructionMessage peek() {
		Iterator<InstructionMessage> it = instructionQueue.iterator();
		while(it.hasNext()){
			InstructionMessage im = it.next();
			if(IConstants.HIGH_PRIORITY.equals(im.getMessagePriority())){
				return im;
			}
		}
		return new InstructionMessage();

	}

	// Returns the number of messages currently on the queue
	public int count() {
		return instructionQueue.size();

	}
	
	public boolean isEmpty(){
		if(instructionQueue == null || instructionQueue.size() == 0){
			return IConstants.RETURN_TRUE;
		}
		return IConstants.RETURN_FALSE;		
	}
	
	public void printQueue(){
		Iterator<InstructionMessage> it = instructionQueue.iterator();
		
		while(it.hasNext()){
			InstructionMessage im = it.next();
			System.out.println( "Print queue messages " + "- " + im.toString());
		
		}
	}

}
