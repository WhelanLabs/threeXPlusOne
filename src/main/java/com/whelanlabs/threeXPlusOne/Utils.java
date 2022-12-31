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
			if(v1==0 & v2==0 & r==0) {
				v3 = 0;
				r = 0;
			}
			else if(v1==0 & v2==0 & r==1) {
				v3 = 1;
				r = 0;
			}
			else if(v1==0 & v2==1 & r==0) {
				v3 = 1;
				r = 0;
			}
			else if(v1==0 & v2==1 & r==1) {
				v3 = 0;
				r = 1;
			}
			else if(v1==1 & v2==0 & r==0) {
				v3 = 1;
				r = 0;
			}
			else if(v1==1 & v2==0 & r==1) {
				v3 = 0;
				r = 1;
			}
			else if(v1==1 & v2==1 & r==0) {
				v3 = 0;
				r = 1;
			}
			else if(v1==1 & v2==1 & r==1) {
				v3 = 1;
				r = 1;
			}
			temp.add(v3);
		}
		if(r==1) {
			b+=1;
		}
		if(twoXPlusOneList.get(0)==1) {
			b+=1;
		}
		
		List<Integer> resultArray = new ArrayList<>();
		Boolean shift = true;
		
		System.out.println("temp = " + temp);

		for(int i=temp.size()-1; i>=0; i--) {
			Integer value = temp.get(i);
			if(value==0 & shift) {
				shifts += 1;
			}
			else {
				shift = false;
				resultArray.add(value);
			}
		}
		
		
		
		
		System.out.println("str = " + resultArray);
		System.out.println("a = " + a);
		System.out.println("b = " + b);
		System.out.println("shifts = " + shifts);

		return new TailArray(a, b, resultArray, shifts);
	}

}
