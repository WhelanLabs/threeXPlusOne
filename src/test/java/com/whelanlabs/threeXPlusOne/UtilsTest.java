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
		assert("[]" == threeXPlusOne.getTail().toString()): threeXPlusOne;
	}

	@Test
	public void get3XPlusOne_good2_success() {
		List<Integer> array = Arrays.asList(0, 0, 0, 0, 1, 1);
		TailArray input = new TailArray(array);
		TailArray threeXPlusOne = Utils.get3XPlusOne(input);
		
		// TXPO(101) = (3X+1)000 = (3X+1)/8
		assert(3 == threeXPlusOne.getXAValue()): threeXPlusOne;
		assert("[]" == threeXPlusOne.getTail().toString()): threeXPlusOne;
		
		fail("Not yet implemented");
	}
}
