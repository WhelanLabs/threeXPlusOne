package com.whelanlabs.threeXPlusOne;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class UtilsTest {

	
	@Test
	public void get3XPlusOne_X0011_partB_success() {
		List<Integer> startingBits = Arrays.asList(0, 0, 1, 1);
		TailArray startingTailArray = new TailArray(startingBits);
		TailArray partBTailArray = Utils.get3XPlusOne(startingTailArray);
		TailArray result = Utils.get3XPlusOne(partBTailArray);
		assert("{ formula:\"9.0X+1.0\", base:\"(9.0X+1.0)[]\", shifts:3 }".equals(result.toString())): result.toString();
	}
	
	@Test
	public void get3XPlusOne_X0101_success() {
		List<Integer> startingBits = Arrays.asList(0, 1, 0, 1);
		TailArray startingTailArray = new TailArray(startingBits);
		TailArray result = Utils.get3XPlusOne(startingTailArray);
		assert("{ formula:\"3.0X+1.0\", base:\"(3.0X+1.0)[]\", shifts:4 }".equals(result.toString())): result.toString();
	}
	
	@Test
	public void get3XPlusOne_X0111_success() {
		List<Integer> startingBits = Arrays.asList(0, 1, 1, 1);
		TailArray startingTailArray = new TailArray(startingBits);
		TailArray a = Utils.get3XPlusOne(startingTailArray);
		assert("{ formula:\"24.0X+11.0\", base:\"(24.0X+8.0)[0, 1, 1]\", shifts:1 }".equals(a.toString())): a.toString();

		TailArray b = Utils.get3XPlusOne(a);
		assert("{ formula:\"36.0X+17.0\", base:\"(36.0X+16.0)[0, 1]\", shifts:1 }".equals(b.toString())): b.toString();

		TailArray c = Utils.get3XPlusOne(b);
		assert("failure is always an option".equals(c.toString())): c.toString();
	}
}
