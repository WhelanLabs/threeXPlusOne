package com.whelanlabs.threeXPlusOne;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Utils {
	private static final Logger logger = LogManager.getLogger(Utils.class);

	private static final BigInteger two = BigInteger.valueOf(2);
	private static final BigInteger three = BigInteger.valueOf(3);

	private static Character CHAR_ZERO = (Character) '0';

	private Utils() {
		// static class
	}

	public static TailArray get3XPlusOne(TailArray input) {

		List<Integer> oneXList = input.getTail();
		List<Integer> twoXPlusOneList = input.getTail();
		twoXPlusOneList.add(1);

		Integer r = 0;

		BigInteger b = input.getXBValue().multiply(BigInteger.valueOf(3));

		Integer shifts = 0;

		List<Integer> temp = new ArrayList<>();
		for (int i = oneXList.size() - 1; i >= 0; i--) {
			Integer v1 = oneXList.get(i);
			Integer v2 = twoXPlusOneList.get(i + 1);
			Integer v3 = 0;

			if (0 == v1) {
				if (0 == v2) {
					if (0 == r) { // 000
						v3 = 0;
						r = 0;
					} else { // 001
						v3 = 1;
						r = 0;
					}
				} else { // v2 == 1
					if (0 == r) { // 010
						v3 = 1;
						r = 0;
					} else { // 011
						v3 = 0;
						r = 1;
					}
				}
			} else { // v1 == 1
				if (0 == v2) {
					if (0 == r) { // 100
						v3 = 1;
						r = 0;
					} else { // 101
						v3 = 0;
						r = 1;
					}
				} else { // v2 == 1
					if (0 == r) { // 110
						v3 = 0;
						r = 1;
					} else { // 111
						v3 = 1;
						r = 1;
					}
				}
			}

			temp.add(v3);
		}

		// if top digit addition has a carryover, add it to b-value.
		if (r == 1) {
			// b += 1;
			BigInteger bIncrement = two.pow(input.getTail().size());
			b = b.add(bIncrement);
		}
		// If the upshifted bit is a one, add it to b-value
		if (1 == oneXList.get(0)) {
			BigInteger bIncrement = two.pow(input.getTail().size());
			b = b.add(bIncrement);
		}

		List<Integer> reversedResultArray = new ArrayList<>();
		List<Integer> resultArray = new ArrayList<>();
		Boolean shift = true;

		// convert the temp array into left/right format and account for shifts
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
		// logger.debug("result = " + result);

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

		Boolean result = false;

		// do a more expensive check...
		BigInteger top = post.getXCValue().subtract(pre.getXCValue());
		BigInteger bottom = pre.getXAValue().subtract(post.getXAValue());

		if (top.compareTo(BigInteger.ZERO) < 0 && bottom.compareTo(BigInteger.ZERO) < 0) {
			top = top.multiply(BigInteger.valueOf(-1l));
			bottom = bottom.multiply(BigInteger.valueOf(-1l));
		}

		// case 1: closed loop
		int topCompare = top.compareTo(BigInteger.ZERO);
		
		// boolean case_1 = (bottom.compareTo(BigInteger.ZERO) == 0) && (top.compareTo(BigInteger.ZERO) == 0);
		boolean case_1 = ((topCompare) == 0);

		// case 2: future solution found
		boolean case_2 = ((topCompare) > 0 && (bottom.compareTo(BigInteger.ZERO) > 0)
				&& (BigInteger.ZERO.compareTo(top.mod(bottom)) == 0));

		if (case_1 || case_2) {
			System.out.println("### bingo ###");
			System.out.println("pre = " + pre);
			System.out.println("post = " + post);

			logger.info("### bingo ###");
			logger.info("pre = " + pre);
			logger.info("post = " + post);
			
			System.exit(0);
		}

		else if (pre.getXAValue().compareTo(post.getXAValue()) > 0
				&& pre.getXCValue().compareTo(post.getXCValue()) > 0) {
			// logger.info("### dead end (too small) - pre = " + pre + ", post = " + post);
			result = true;
		} else if (pre.getXAValue().compareTo(post.getXAValue()) > 0) {
			// System.out.println("### possible future dead end ###");
		} else {
			// System.out.println("### unknown ###");
		}

		return result;
	}

}
