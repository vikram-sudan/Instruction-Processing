package com.instruction.processing.InstructionQueueTest;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.Queue;

import org.apache.commons.lang.StringUtils;
import org.junit.Before;
import org.junit.Test;

import com.instruction.processing.InstructionMessage.InstructionMessage;
import com.instruction.processing.InstructionQueue.InstructionQueue;
import com.instruction.processing.api.InstructionProcessingAPI.IConstants;

public class InstructionQueueTest {
		
	InstructionQueue underTestQueue;

    @Before
    public void setUp() {
    	underTestQueue = new InstructionQueue();    	
    	setUpInstructionQueue();
    }
	
	@Test
	public void testDeQueueWithHighPriorityMessage(){
		int countBefore = underTestQueue.count();
		
		String messagePriority = underTestQueue.dequeue().getMessagePriority();
		
		assertEquals(messagePriority,IConstants.HIGH_PRIORITY);
		assertEquals(countBefore-1,underTestQueue.count());
	}
	
	@Test
	public void testDeQueueWithOutHighPriorityMessage(){
		
		testDeQueueWithHighPriorityMessage();
		String messagePriority = StringUtils.isEmpty(underTestQueue.dequeue().getMessagePriority()) ? "0" : underTestQueue.dequeue().getMessagePriority();		
		assertEquals(messagePriority,"0");		
	}
	
	
	@Test
	public void testPeekWithHighPriorityMessage(){
		int countBefore = underTestQueue.count();
		
		String messagePriority = underTestQueue.peek().getMessagePriority();
		
		assertEquals(messagePriority,IConstants.HIGH_PRIORITY);
		assertEquals(countBefore,underTestQueue.count());
	}
	
	@Test
	public void testPeekWithOutHighPriorityMessage(){
		testDeQueueWithHighPriorityMessage();		
		String messagePriority = StringUtils.isEmpty(underTestQueue.peek().getMessagePriority()) ? "0" : underTestQueue.peek().getMessagePriority();
		assertEquals(messagePriority,"0");		
	}
	
	@Test
	public void testIsEmpty(){
		
		assertEquals(underTestQueue.isEmpty(),IConstants.RETURN_FALSE);
		underTestQueue.instructionQueue = null;
		assertEquals(underTestQueue.isEmpty(),IConstants.RETURN_TRUE);	
	}
	
	private void setUpInstructionQueue(){
		Queue<InstructionMessage> queue = new LinkedList<InstructionMessage>();
		
		InstructionMessage message1 = new InstructionMessage();
		InstructionMessage message2 = new InstructionMessage();
		InstructionMessage message3 = new InstructionMessage();
		InstructionMessage message4 = new InstructionMessage();
		
		message1.setInstructionType('A');
		message1.setProductCode("BB12");
		message1.setQuantity(3456);
		message1.setUOM(65);
		message1.setTimeStamp(0);
		message1.setMessagePriority('A');
		
		message2.setInstructionType('B');
		message2.setProductCode("CB21");
		message2.setQuantity(1234);
		message2.setUOM(101);
		message2.setTimeStamp(0);
		message2.setMessagePriority('B');
		
		message3.setInstructionType('C');
		message3.setProductCode("DE32");
		message3.setQuantity(1234);
		message3.setUOM(101);
		message3.setTimeStamp(0);
		message3.setMessagePriority('C');
		
		message4.setInstructionType('D');
		message4.setProductCode("AE12");
		message4.setQuantity(1234);
		message4.setUOM(101);
		message4.setTimeStamp(0);
		message4.setMessagePriority('D');
		
		
		queue.add(message1);
		queue.add(message2);
		queue.add(message3);
		queue.add(message4);
		underTestQueue.instructionQueue = queue;		
	}
		
}
