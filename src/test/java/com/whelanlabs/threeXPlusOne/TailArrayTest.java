package com.whelanlabs.threeXPlusOne;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class TailArrayTest {

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
		assert(1 == tailArray.getXAValue());
	}
	
	@Test
	public void getXBValue_good_success() {
		List<Integer> array = Arrays.asList(1, 0, 1);
		TailArray tailArray = new TailArray(array);
		assert(0 == tailArray.getXBValue());
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
	
}
