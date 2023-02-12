package com.whelanlabs.threeXPlusOne;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TailArray {

	private static final BigInteger two = BigInteger.valueOf(2);
	private static final BigInteger zero = BigInteger.valueOf(0);
	
	private List<Integer> _tail;
	private BigInteger _tailValue;
	private Integer _shifts;
	private BigInteger _xaValue;
	private BigInteger _xbValue;
	private Boolean _smaller = false;

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

		_tailValue = getTailValue();
	}

	public TailArray(BigInteger a, BigInteger b, List<Integer> tail, Integer shifts) {
		_tail = tail;
		_shifts = shifts;
		_xaValue = a;
		_xbValue = b;
		_tailValue = getTailValue(tail);
	}

	public BigInteger getTailValue() {
//		BigInteger result = zero;
//		for (int i = 0; i < _tail.size(); i++) {
//			int pos = _tail.size() - i - 1;
//			if (_tail.get(i) == 1) {
//				result.add(two.pow(pos));
//			}
//		}
		return getTailValue(_tail);
	}

	public static BigInteger getTailValue(List<Integer> tail) {
		BigInteger result = zero;
		
//		for (int i = tail.size() - 1; i >= 0; i--) {
//			int pos = tail.size() - i - 1;
//			if(tail.get(pos) == 1) {
//				result = result.add(two.pow(i));
//			}
//		}
		
		if(tail.size() >0) {
			String str = getNumberString(tail);
			result = BigInteger.valueOf(Integer.parseInt(str, 2));
		}

		// int foo = Integer.parseInt("1001", 2);
		return result;
	}
	
    public static String getNumberString(List<Integer> list)
    {
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
		return "{ formula:\"" + _xaValue + "X+" + getXCValue().toString() + "\", base:\"(" + _xaValue + "X+" + _xbValue + ")"
				+ _tail + "\", shifts:" + _shifts + " }";
	}

}
