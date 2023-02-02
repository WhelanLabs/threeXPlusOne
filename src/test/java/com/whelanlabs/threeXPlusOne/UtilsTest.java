package com.whelanlabs.threeXPlusOne;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class UtilsTest {

	@Test
	public void get3XPlusOne_X0001_success() {
		List<Integer> startingBits = Arrays.asList(0, 0, 0, 1);
		TailArray startingTailArray = new TailArray(startingBits);
		TailArray result = Utils.get3XPlusOne(startingTailArray);
		assert("{ formula:\"12.0X+1.0\", base:\"(12.0X+0.0)[0, 1]\", shifts:2 }".equals(result.toString())): result.toString();
	}
	
	@Test
	public void get3XPlusOne_X0011_success() {
		List<Integer> startingBits = Arrays.asList(0, 0, 1, 1);
		TailArray startingTailArray = new TailArray(startingBits);
		TailArray a = Utils.get3XPlusOne(startingTailArray);
		assert("{ formula:\"24.0X+5.0\", base:\"(24.0X+0.0)[1, 0, 1]\", shifts:1 }".equals(a.toString())): a.toString();

		TailArray b = Utils.get3XPlusOne(a);
		assert("{ formula:\"9.0X+2.0\", base:\"(9.0X+2.0)[]\", shifts:3 }".equals(b.toString())): b.toString();
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
		assert("{ formula:\"27.0X+13.0\", base:\"(27.0X+13.0)[]\", shifts:2 }".equals(c.toString())): c.toString();
	}
	
	@Test
	public void get3XPlusOne_X1001_success() {
		List<Integer> startingBits = Arrays.asList(1, 0, 0, 1);
		TailArray startingTailArray = new TailArray(startingBits);
		TailArray result = Utils.get3XPlusOne(startingTailArray);
		assert("{ formula:\"12.0X+7.0\", base:\"(12.0X+4.0)[1, 1]\", shifts:2 }".equals(result.toString())): result.toString();
	}
	
	@Test
	public void get3XPlusOne_X1011_success() {
		List<Integer> startingBits = Arrays.asList(1, 0, 1, 1);
		TailArray startingTailArray = new TailArray(startingBits);
		TailArray a = Utils.get3XPlusOne(startingTailArray);
		assert("{ formula:\"24.0X+17.0\", base:\"(24.0X+16.0)[0, 0, 1]\", shifts:1 }".equals(a.toString())): a.toString();

		TailArray b = Utils.get3XPlusOne(a);
		assert("{ formula:\"18.0X+13.0\", base:\"(18.0X+12.0)[1]\", shifts:2 }".equals(b.toString())): b.toString();

		TailArray c = Utils.get3XPlusOne(b);
		assert("{ formula:\"27.0X+20.0\", base:\"(27.0X+20.0)[]\", shifts:1 }".equals(c.toString())): c.toString();
	}
	
	@Test
	public void get3XPlusOne_X1101_success() {
		List<Integer> startingBits = Arrays.asList(1, 1, 0, 1);
		TailArray startingTailArray = new TailArray(startingBits);
		TailArray result = Utils.get3XPlusOne(startingTailArray);
		assert("{ formula:\"6.0X+5.0\", base:\"(6.0X+4.0)[1]\", shifts:3 }".equals(result.toString())): result.toString();
	}
	
	@Test
	public void get3XPlusOne_X1111_success() {
		List<Integer> startingBits = Arrays.asList(1, 1, 1, 1);
		TailArray startingTailArray = new TailArray(startingBits);
		TailArray a = Utils.get3XPlusOne(startingTailArray);
		assert("???".equals(a.toString())): a.toString();

		TailArray b = Utils.get3XPlusOne(a);
		assert("???".equals(b.toString())): b.toString();

		TailArray c = Utils.get3XPlusOne(b);
		assert("???".equals(c.toString())): c.toString();
		
		TailArray d = Utils.get3XPlusOne(c);
		assert("???".equals(d.toString())): d.toString();
	}
}
