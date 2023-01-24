package com.whelanlabs.threeXPlusOne;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Utils {
	
	private static Character CHAR_ZERO = (Character)'0';
	
	private Utils() {
		// static class
	}
	
	public static TailArray get3XPlusOne(TailArray input) {
		List<Integer> oneXList = input.getTail();
		List<Integer> twoXPlusOneList = input.getTail();
		twoXPlusOneList.add(1);

		
		Integer r = 0;
		Double a = input.getXAValue() *3;
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
		
		// System.out.println("temp = " + temp);

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
		
		
		Boolean smaller = false;
		
		System.out.println("str = " + resultArray);
		System.out.println("a = " + a);
		System.out.println("b = " + b);
		System.out.println("shifts = " + shifts);
		
		// pre = tail + x*2^size +b*2^(size)
		// post = remainder + ax*2^(size - shift)  +b*2^(size - shift)
		double pre_a = input.getXAValue();
		double post_a = input.getXAValue()*3 / Math.pow(2, shifts);
		System.out.println("pre_a = " + pre_a);
		System.out.println("post_a = " + post_a);
		
		Integer pre_b = input.getTailValue();
		Integer post_b = TailArray.getTailValue(resultArray);
		//System.out.println("pre_b = " + pre_b);
		//System.out.println("post_b = " + post_b);
		

				
		return new TailArray(post_a, b, resultArray, shifts);
	}

	public static List<List<Integer>> getListOfBits(Integer length) {
		List<List<Integer>> results = new ArrayList<>();
		Long maxNum = ((Double)Math.pow(2l, length)).longValue();
		for(Long i=1l; i<maxNum; i++) {
			String numString = Long.toString(i, 2);
			while(numString.length()<length) {
				numString = "0" + numString;
			}
			List<Integer> bitList = new ArrayList<>();
			for(int j=0; j<numString.length(); j++) {
				char bitString = numString.charAt(j);
				if(CHAR_ZERO.equals(bitString)) {
					bitList.add(0);
				}
				else {
					bitList.add(1);
				}
			}
			results.add(bitList);
		}
		return results;
	}

	public static Boolean isSmaller(TailArray a, TailArray b) {
		Boolean result = false;
		
		if(a.getXAValue()>b.getXAValue() & a.getXBValue()>b.getXBValue()) {
			System.out.println("### dead end ###");
			result = true;
		}
		else if(a.getXAValue()>b.getXAValue()) {
			System.out.println("### future dead end ###");
			result = null;
		}
		else {
			System.out.println("### not smaller ###");
		}
		
		return result;
	}

}
