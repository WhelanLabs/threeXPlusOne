package com.whelanlabs.threeXPlusOne;

import static org.junit.Assert.*;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class TailArrayTest {

	private final BigInteger zero = BigInteger.valueOf(0);
	private final BigInteger one = BigInteger.valueOf(1);
	private final BigInteger five = BigInteger.valueOf(5);
	
	@Test
	public void new_good_success() {
		List<Integer> array = Arrays.asList(1, 0, 1);
		new TailArray(array);
	}

	@Test(expected = RuntimeException.class)
	public void new_null_exception() {
		new TailArray(null);
	}
	
	@Test(expected = RuntimeException.class)
	public void new_even_exception() {
		List<Integer> array = Arrays.asList(1, 0, 0);
		new TailArray(array);
	}
	
	@Test(expected = RuntimeException.class)
	public void new_nonBoolean_exception() {
		List<Integer> array = Arrays.asList(1, 2, 1);
		new TailArray(array);
	}
	
	@Test
	public void getXAValue_good_success() {
		List<Integer> array = Arrays.asList(1, 0, 1);
		TailArray tailArray = new TailArray(array);
		assert(tailArray.getXAValue().compareTo(one) == 0);
	}
	
	@Test
	public void getXBValue_good_success() {
		List<Integer> array = Arrays.asList(1, 0, 1);
		TailArray tailArray = new TailArray(array);
		assert(tailArray.getXBValue().compareTo(zero) == 0);
	}
	
	@Test
	public void getShifts_good_success() {
		List<Integer> array = Arrays.asList(1, 0, 1);
		TailArray tailArray = new TailArray(array);
		assert(0 == tailArray.getShifts());
	}
	
	@Test
	public void getTail_good_success() {
		List<Integer> array = Arrays.asList(1, 0, 1);
		TailArray tailArray = new TailArray(array);
		String str = tailArray.getTail().toString();
		assert("[1, 0, 1]".equals(str)):str;
	}
	
	@Test
	public void getValue_is5_is5() {
		List<Integer> array = Arrays.asList(1, 0, 1);
		TailArray tailArray = new TailArray(array);
		BigInteger tailValue = tailArray.getTailValue();
		assert(five.compareTo(tailValue)==0 ):tailValue;
	}
	
	@Test
	public void toString_valid_getString() {
		List<Integer> array = Arrays.asList(1, 0, 1);
		TailArray tailArray = new TailArray(array);
		String tailArrayString = tailArray.toString();
		assert("{ base:\"(1X+0)[1, 0, 1]\", shifts:0 }".equals(tailArrayString)):tailArrayString;
	}
}







