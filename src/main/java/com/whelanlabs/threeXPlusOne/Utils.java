package com.whelanlabs.threeXPlusOne;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Utils {

	private static Character CHAR_ZERO = (Character) '0';

	private Utils() {
		// static class
	}

	public static TailArray get3XPlusOne(TailArray input) {
		
		System.out.println("input = " + input);
		
		List<Integer> oneXList = input.getTail();
		List<Integer> twoXPlusOneList = input.getTail();
		twoXPlusOneList.add(1);

		Integer r = 0;
		
		//Double b = input.getXBValue();
		Double b = 3*input.getXBValue();
		
		Integer shifts = 0;  //input.getShifts();

		List<Integer> temp = new ArrayList<>();
		for (int i = oneXList.size() - 1; i >= 0; i--) {
			Integer v1 = oneXList.get(i);
			Integer v2 = twoXPlusOneList.get(i + 1);
			Integer v3 = 0;
			String valuesString = "[" + v1 + "," + v2 + "," + r + "]";

			// single digit addition of two bits including the previous carry
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
		
		
		// if (twoXPlusOneList.get(0) == 1) {
		if (r == 1) {
			//b += 1;
			double bIncrement = Math.pow(2, input.getTail().size());
			b+= bIncrement;
		}

		List<Integer> reversedResultArray = new ArrayList<>();
		List<Integer> resultArray = new ArrayList<>();
		Boolean shift = true;

		// convert the temp array into left/right format
		for (int i = 0; i < temp.size(); i++) {
			Integer value = temp.get(i);
			if (value == 0 & shift) {
				shifts += 1;
			} else {
				shift = false;
				reversedResultArray.add(value);
			}
		}

		for (int i = reversedResultArray.size() - 1; i >= 0; i--) {
			Integer value = reversedResultArray.get(i);
			resultArray.add(value);
		}

		double post_a = input.getXAValue() * 3 / Math.pow(2, shifts);
		double post_b = b / Math.pow(2, shifts);
		

		TailArray result = new TailArray(post_a, post_b, resultArray, shifts);
		System.out.println("result = " + result);

		return result;
	}

	public static List<List<Integer>> getListOfBits(Integer length) {
		List<List<Integer>> results = new ArrayList<>();
		Long maxNum = ((Double) Math.pow(2l, length)).longValue();
		for (Long i = 1l; i < maxNum; i++) {
			String numString = Long.toString(i, 2);
			while (numString.length() < length) {
				numString = "0" + numString;
			}
			List<Integer> bitList = new ArrayList<>();
			for (int j = 0; j < numString.length(); j++) {
				char bitString = numString.charAt(j);
				if (CHAR_ZERO.equals(bitString)) {
					bitList.add(0);
				} else {
					bitList.add(1);
				}
			}
			results.add(bitList);
		}
		return results;
	}

	public static Boolean isSmaller(TailArray pre, TailArray post) {
		Boolean result = false;

		double before_b_plus_c = pre.getXBValue() + pre.getTailValue();
		double after_b_plus_c = post.getXBValue() + post.getTailValue();

		
		if(pre.getXAValue() == post.getXAValue() && before_b_plus_c == after_b_plus_c ) {
			System.out.println("### bingo ###");
		}
		else if (pre.getXAValue() >= post.getXAValue() && pre.getXCValue() >= post.getXCValue()) {
			System.out.println("### dead end ###");
			result = true;
		} else if (pre.getXAValue() > post.getXAValue()) {
			System.out.println("### future dead end ###");
			// result = null;
		} else {
			System.out.println("### not smaller ###");
		}

		return result;
	}

}
