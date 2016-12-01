package com.instruction.processing.InstructionProcessing;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.instruction.processing.InstructionMessage.InstructionMessage;


public class InstructionProcessor 
{
    public static void main( String[] args )
    {
        System.out.println( "Initiliazing");
        String messageReceived =  "InstructionMessage A MZ89 5678 50 2016-11-29T18:46:19.012Z";
        String messageReceived1 = "InstructionMessage B MZ90 5679 60 2016-06-29T18:46:19.012Z";
        String messageReceived2 = "InstructionMessage C AG91 5680 70 2016-08-29T18:46:19.012Z";
        String messageReceived3 = "InstructionMessage D EF12 5681 80 2016-12-1T18:46:19.012Z";
        String messageReceived4 = "InstructionMessage E MZ81 5878 90 2016-11-30T18:46:19.012Z";
        	
        InstructionMessage instMessage = new InstructionMessage();
        instMessage.receive(messageReceived);
        instMessage.receive(messageReceived1);
        instMessage.receive(messageReceived2);
        instMessage.receive(messageReceived3);
        instMessage.receive(messageReceived4);
        
        System.out.println("Is Queue empty " + "- " + instMessage.isEmpty());
        System.out.println( "Queue Count is " + "- " + instMessage.getCount());
        
        if(StringUtils.isEmpty(instMessage.peekQueue().getMessagePriority())){
        	System.out.println( "No highest priority message found in the queue");
        }else {
        	 System.out.println( "Returning highest priority message without removing from queue " + "- " + instMessage.peekQueue().toString());
        }        
       InstructionMessage highPriorityMessage = instMessage.deQueue();
        if(StringUtils.isEmpty(highPriorityMessage.getMessagePriority())){
        	System.out.println( "No highest priority message found in the queue");
        } else{
        	System.out.println( "Removing highest priority message and fetching from queue " + "- " + highPriorityMessage);
        }
       
        
        instMessage.printQueue();
        
        System.out.println("epoch in seconds "+System.currentTimeMillis()/1000);
        System.out.println("current time in seconds "+new Date().getTime()/1000);
        
        
        
        
    }
}
