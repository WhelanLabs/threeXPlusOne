package com.whelanlabs.threeXPlusOne;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Utils {
	private static final Logger logger = LogManager.getLogger(Utils.class);
	
	private static final BigInteger zero = BigInteger.valueOf(0);
	private static final BigInteger two = BigInteger.valueOf(2);
	private static final BigInteger three = BigInteger.valueOf(3);
	
	private static String _preRoot = "-";
	private static Set<String> currentTails = new HashSet<>();
	private static Set<String> deadTails = new HashSet<>();


	private static Character CHAR_ZERO = (Character) '0';

	private Utils() {
		// static class
	}

	public static TailArray get3XPlusOne(TailArray input) {
		
		//logger.debug("input = " + input);
		
		List<Integer> oneXList = input.getTail();
		List<Integer> twoXPlusOneList = input.getTail();
		twoXPlusOneList.add(1);

		Integer r = 0;
		
		BigInteger b =  input.getXBValue().multiply(BigInteger.valueOf(3));
		
		Integer shifts = 0;

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
		
		
		// if top digit addition has a carryover, add it to b-value.
		if (r == 1) {
			//b += 1;
			BigInteger bIncrement =  two.pow(input.getTail().size());
			b = b.add(bIncrement);
		}
		// If the upshifted bit is a one, add it to b-value
		if(1 == oneXList.get(0)) {
			BigInteger bIncrement =  two.pow(input.getTail().size());
			b = b.add(bIncrement);
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

		BigInteger post_a = three.multiply(input.getXAValue()).divide(two.pow(shifts));
		BigInteger post_b = b.divide(two.pow(shifts));
		

		TailArray result = new TailArray(post_a, post_b, resultArray, shifts);
		//logger.debug("result = " + result);

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

	public static Boolean isDeadEnd(TailArray pre, TailArray post) {
		
		if (_preRoot.equals(pre.getTail().toString())) {
			currentTails.add(post.getTail().toString().substring(1));
		}
		else {
			currentTails = new HashSet<>();
		}
		
		Boolean result = false;

		BigInteger before_b_plus_c = pre.getXBValue().add(pre.getTailValue());
		BigInteger after_b_plus_c = post.getXBValue().add(post.getTailValue());

		Double x = -0.5;
		if(pre.getXCValue() != post.getXCValue()) {
			Double top = (post.getXCValue().subtract(pre.getXCValue())).doubleValue();
			Double bottom = (pre.getXAValue().subtract(post.getXAValue())).doubleValue();
			x = top/bottom;
		}
		if( x%1==0 && x >= 0 ) {
			
			// do a more expensive check...
			BigDecimal top = new BigDecimal(post.getXCValue().subtract(pre.getXCValue()));
			BigDecimal bottom = new BigDecimal(pre.getXAValue().subtract(post.getXAValue()));
			BigDecimal xx = top.divide(bottom);
			
			if(xx.remainder(BigDecimal.ONE).compareTo(BigDecimal.ZERO) == 0) {
				System.out.println("### bingo ###");
				System.out.println("pre = " + pre);
				System.out.println("post = " + post);
				System.out.println("X = " + x);
				
				System.out.println("post.getXCValue() = " + post.getXCValue());
				System.out.println("pre.getXCValue() = " + pre.getXCValue());
				System.out.println("pre.getXAValue() = " + pre.getXAValue());
				System.out.println("post.getXAValue() = " + post.getXAValue());
				
				System.exit(0);
			}
		}
		
		
		if (deadTails.contains(post.getTail().toString().substring(1))) {
			logger.info("### dead end (dead tail) - pre = " + pre + ", post = " + post);
			deadTails.addAll(currentTails);
			deadTails.add(pre.getTail().toString().substring(1));
			result = true;
		}
		else if (pre.getXAValue().compareTo(post.getXAValue())>0  && pre.getXCValue().compareTo(post.getXCValue())>0 ) {
			logger.info("### dead end (too small) - pre = " + pre + ", post = " + post);
			deadTails.addAll(currentTails);
			deadTails.add(pre.getTail().toString().substring(1));
			result = true;
		} else if (pre.getXAValue().compareTo(post.getXAValue()) > 0 ) {
			//System.out.println("### possible future dead end ###");
		}
		else {
			//System.out.println("### unknown ###");
		}

		return result;
	}

}
