package com.whelanlabs.threeXPlusOne;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TailArray {

	private static final BigInteger two = BigInteger.valueOf(2);
	private static final BigInteger zero = BigInteger.valueOf(0);

	private List<Integer> _tail;
	private Integer _shifts;
	private BigInteger _xaValue;
	private BigInteger _xbValue;

	public TailArray(List<Integer> tail) {
		if (null == tail) {
			throw new RuntimeException("nulls not allowed.");
		}

		if (tail.get(tail.size() - 1) != 1) {
			throw new RuntimeException("tail must be odd. (" + tail + ")");
		}

		for (Integer value : tail) {
			if (!(value == 0 || value == 1)) {
				throw new RuntimeException("only ones and zeros allowed. (" + tail + ")");
			}
		}

		_tail = tail;
		_shifts = 0;
		// The initial leading X value is: AX+B = 1X+0
		_xaValue = two.pow(tail.size());
		_xbValue = zero;

	}

	public TailArray(BigInteger a, BigInteger b, List<Integer> tail, Integer shifts) {
		_tail = tail;
		_shifts = shifts;
		_xaValue = a;
		_xbValue = b;
	}

	public BigInteger getTailValue() {
		return getTailValue(_tail);
	}

	public static BigInteger getTailValue(List<Integer> tail) {
		BigInteger result = zero;

		if (tail.size() > 0) {
			String str = getNumberString(tail);
			result = new BigInteger(str, 2);
		}

		return result;
	}

	public static String getNumberString(List<Integer> list) {
		String numberString = list.stream().map(String::valueOf).collect(Collectors.joining());
		return numberString;
	}

	public BigInteger getXAValue() {
		return _xaValue;
	}

	public BigInteger getXBValue() {
		return _xbValue;
	}

	public BigInteger getXCValue() {
		BigInteger b = getXBValue();
		BigInteger t = getTailValue();
		BigInteger result = b.add(t);
		return result;
	}

	public Integer getShifts() {
		return _shifts;
	}

	public List<Integer> getTail() {
		List<Integer> result = new ArrayList<>();
		result.addAll(_tail);
		return result;
	}

	public String toString() {
		return "{ formula:\"" + _xaValue + "X+" + getXCValue().toString() + "\", base:\"(" + _xaValue + "X+" + _xbValue
				+ ")" + _tail + "\", shifts:" + _shifts + " }";
	}

}
