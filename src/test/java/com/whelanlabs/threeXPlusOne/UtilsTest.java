package com.whelanlabs.threeXPlusOne;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class UtilsTest {

	@Test
	public void get3XPlusOne_good_success() {
		List<Integer> array = Arrays.asList(1, 0, 1);
		TailArray input = new TailArray(array);
		TailArray threeXPlusOne = Utils.get3XPlusOne(input);
		
		// TXPO(101) = (3X+1)000 = (3X+1)/8
		assert(3 == threeXPlusOne.getXAValue()): threeXPlusOne;
		assert(2 == threeXPlusOne.getXBValue()): threeXPlusOne;
		assert(3 == threeXPlusOne.getShifts()): threeXPlusOne;
		assert("[]".equals(threeXPlusOne.getTail().toString())): threeXPlusOne;
	}

	@Test
	public void get3XPlusOne_good2_success() {
		List<Integer> array = Arrays.asList(0, 0, 0, 0, 1, 1);
		TailArray input = new TailArray(array);
		TailArray threeXPlusOne = Utils.get3XPlusOne(input);
		
		// TXPO(101) = (3X+1)000 = (3X+1)/8
		assert(3 == threeXPlusOne.getXAValue()): threeXPlusOne;
		String tail = threeXPlusOne.getTail().toString();
		assert("[0, 0, 1, 0, 1]".equals(tail)): tail;
	}
	
	@Test
	public void get3XPlusOne_good3_success() {
		List<Integer> array = Arrays.asList(0, 0, 1, 0, 0, 1);
		TailArray input = new TailArray(array);
		TailArray threeXPlusOne = Utils.get3XPlusOne(input);
		
		// TXPO(101) = (3X+1)000 = (3X+1)/8
		assert(3 == threeXPlusOne.getXAValue()): threeXPlusOne;
		String tail = threeXPlusOne.getTail().toString();
		assert("[0, 1, 1, 1]".equals(tail)): tail;
	}
	
	//
	@Test
	public void getListOfBits_good_success() {
		List<List<Integer>> results = Utils.getListOfBits(4);
		
		System.out.println(results);
		assert(15 == results.size()): results.size();
		List<Integer> n10 = results.get(10);
		String nTenString = n10.toString();
		assert("[1, 0, 1, 1]".equals(nTenString)): nTenString;

	}
}
