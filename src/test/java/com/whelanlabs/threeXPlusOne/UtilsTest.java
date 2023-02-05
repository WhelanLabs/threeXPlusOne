package com.whelanlabs.threeXPlusOne;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

public class UtilsTest {
	private static final Logger logger = LogManager.getLogger(UtilsTest.class);

	@Test
	public void get3XPlusOne_X0001_success() {
		List<Integer> startingBits = Arrays.asList(0, 0, 0, 1);
		TailArray startingTailArray = new TailArray(startingBits);
		TailArray result = Utils.get3XPlusOne(startingTailArray);
		assert("{ formula:\"12X+1\", base:\"(12X+0)[0, 1]\", shifts:2 }".equals(result.toString())): result.toString();
	}
	
	@Test
	public void get3XPlusOne_X0011_success() {
		List<Integer> startingBits = Arrays.asList(0, 0, 1, 1);
		TailArray startingTailArray = new TailArray(startingBits);
		TailArray a = Utils.get3XPlusOne(startingTailArray);
		assert("{ formula:\"24X+5\", base:\"(24X+0)[1, 0, 1]\", shifts:1 }".equals(a.toString())): a.toString();

		TailArray b = Utils.get3XPlusOne(a);
		assert("{ formula:\"9X+2\", base:\"(9X+2)[]\", shifts:3 }".equals(b.toString())): b.toString();
	}
	
	@Test
	public void get3XPlusOne_X0101_success() {
		List<Integer> startingBits = Arrays.asList(0, 1, 0, 1);
		TailArray startingTailArray = new TailArray(startingBits);
		TailArray result = Utils.get3XPlusOne(startingTailArray);
		assert("{ formula:\"3X+1\", base:\"(3X+1)[]\", shifts:4 }".equals(result.toString())): result.toString();
	}
	
	@Test
	public void get3XPlusOne_X0111_success() {
		List<Integer> startingBits = Arrays.asList(0, 1, 1, 1);
		TailArray startingTailArray = new TailArray(startingBits);
		TailArray a = Utils.get3XPlusOne(startingTailArray);
		assert("{ formula:\"24X+11\", base:\"(24X+8)[0, 1, 1]\", shifts:1 }".equals(a.toString())): a.toString();

		TailArray b = Utils.get3XPlusOne(a);
		assert("{ formula:\"36X+17\", base:\"(36X+16)[0, 1]\", shifts:1 }".equals(b.toString())): b.toString();

		TailArray c = Utils.get3XPlusOne(b);
		assert("{ formula:\"27X+13\", base:\"(27X+13)[]\", shifts:2 }".equals(c.toString())): c.toString();
	}
	
	@Test
	public void get3XPlusOne_X1001_success() {
		List<Integer> startingBits = Arrays.asList(1, 0, 0, 1);
		TailArray startingTailArray = new TailArray(startingBits);
		TailArray result = Utils.get3XPlusOne(startingTailArray);
		assert("{ formula:\"12X+7\", base:\"(12X+4)[1, 1]\", shifts:2 }".equals(result.toString())): result.toString();
	}
	
	@Test
	public void get3XPlusOne_X1011_success() {
		List<Integer> startingBits = Arrays.asList(1, 0, 1, 1);
		TailArray startingTailArray = new TailArray(startingBits);
		TailArray a = Utils.get3XPlusOne(startingTailArray);
		assert("{ formula:\"24X+17\", base:\"(24X+16)[0, 0, 1]\", shifts:1 }".equals(a.toString())): a.toString();

		TailArray b = Utils.get3XPlusOne(a);
		assert("{ formula:\"18X+13\", base:\"(18X+12)[1]\", shifts:2 }".equals(b.toString())): b.toString();

		TailArray c = Utils.get3XPlusOne(b);
		assert("{ formula:\"27X+20\", base:\"(27X+20)[]\", shifts:1 }".equals(c.toString())): c.toString();
	}
	
	@Test
	public void get3XPlusOne_X1101_success() {
		List<Integer> startingBits = Arrays.asList(1, 1, 0, 1);
		TailArray startingTailArray = new TailArray(startingBits);
		TailArray result = Utils.get3XPlusOne(startingTailArray);
		assert("{ formula:\"6X+5\", base:\"(6X+4)[1]\", shifts:3 }".equals(result.toString())): result.toString();
	}
	
	@Test
	public void get3XPlusOne_X1111_success() {
		List<Integer> startingBits = Arrays.asList(1, 1, 1, 1);
		TailArray startingTailArray = new TailArray(startingBits);
		TailArray a = Utils.get3XPlusOne(startingTailArray);
		assert("{ formula:\"24X+23\", base:\"(24X+16)[1, 1, 1]\", shifts:1 }".equals(a.toString())): a.toString();

		TailArray b = Utils.get3XPlusOne(a);
		assert("{ formula:\"36X+35\", base:\"(36X+32)[1, 1]\", shifts:1 }".equals(b.toString())): b.toString();

		TailArray c = Utils.get3XPlusOne(b);
		assert("{ formula:\"54X+53\", base:\"(54X+52)[1]\", shifts:1 }".equals(c.toString())): c.toString();
		
		TailArray d = Utils.get3XPlusOne(c);
		assert("{ formula:\"81X+80\", base:\"(81X+80)[]\", shifts:1 }".equals(d.toString())): d.toString();
	}
}
