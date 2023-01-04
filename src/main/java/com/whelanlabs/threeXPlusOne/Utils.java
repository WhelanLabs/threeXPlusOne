package com.whelanlabs.threeXPlusOne;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Utils {
	
	private Utils() {
		// static class
	}
	
	public static TailArray get3XPlusOne(TailArray input) {
		List<Integer> oneXList = input.getTail();
		List<Integer> twoXPlusOneList = input.getTail();
		twoXPlusOneList.add(1);

		
		Integer r = 0;
		Integer a = input.getXAValue() *3;
		Integer b = 0;
		Integer shifts = input.getShifts();

		
		List<Integer> temp = new ArrayList<>();
		for(int i = oneXList.size()-1; i>=0; i--) {
			Integer v1 = oneXList.get(i);
			Integer v2 = twoXPlusOneList.get(i+1);
			Integer v3 = 0;
			String valuesString = "[" + v1 + "," + v2 + "," + r + "]";
		     switch (valuesString) {
	         case "[0,0,0]":
				v3 = 0;
				r = 0;
				break;
	         case "[0,0,1]":
				v3 = 1;
				r = 0;
				break;
	         case "[0,1,0]":
				v3 = 1;
				r = 0;
				break;
	         case "[0,1,1]":
				v3 = 0;
				r = 1;
				break;
	         case "[1,0,0]":
				v3 = 1;
				r = 0;
				break;
	         case "[1,0,1]":
				v3 = 0;
				r = 1;
				break;
	         case "[1,1,0]":
				v3 = 0;
				r = 1;
				break;
	         case "[1,1,1]":
				v3 = 1;
				r = 1;
				break;
	     }
		     
			temp.add(v3);
		}
		if(r==1) {
			b+=1;
		}
		if(twoXPlusOneList.get(0)==1) {
			b+=1;
		}
		
		List<Integer> reversedResultArray = new ArrayList<>();
		List<Integer> resultArray = new ArrayList<>();
		Boolean shift = true;
		
		System.out.println("temp = " + temp);

		for(int i=0; i<temp.size(); i++) {
			Integer value = temp.get(i);
			if(value==0 & shift) {
				shifts += 1;
			}
			else {
				shift = false;
				reversedResultArray.add(value);
			}
		}
		
		for(int i=reversedResultArray.size()-1; i>=0; i--) {
			Integer value = reversedResultArray.get(i);
			resultArray.add(value);
		}
		
		
		
		
		System.out.println("str = " + resultArray);
		System.out.println("a = " + a);
		System.out.println("b = " + b);
		System.out.println("shifts = " + shifts);

		return new TailArray(a, b, resultArray, shifts);
	}

}
