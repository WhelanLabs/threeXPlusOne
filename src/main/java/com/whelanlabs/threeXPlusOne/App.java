package com.whelanlabs.threeXPlusOne;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
	private App() {
		//static class
	}
	
	
    public static void main()
    {
        System.out.println( "Hello World!" );
        Integer length = 6;
        
		System.out.println("length = " + length);


        List<List<Integer>> listOfListOfBits = Utils.getListOfBits(length);
        for(List<Integer> listOfBits: listOfListOfBits) {
        	if(1 == listOfBits.get(listOfBits.size()-1)) {
        		System.out.println("");
        		System.out.println("listOfBits = " + listOfBits);
            	TailArray tailArray = new TailArray(listOfBits);
            	TailArray threeXPlusOne = Utils.get3XPlusOne(tailArray);
        	}

        }
        
        
    }
}
