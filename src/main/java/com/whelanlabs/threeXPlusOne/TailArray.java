package com.whelanlabs.threeXPlusOne;

import java.util.ArrayList;
import java.util.List;

public class TailArray {

	private List<Integer> _tail;
	private Double _tailValue;
	private Integer _shifts;
	private Double _xaValue;
	private Double _xbValue;
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
		_xaValue = Math.pow(2, tail.size());
		_xbValue = 0d;

		_tailValue = getTailValue();
	}

	public TailArray(Double a, Double b, List<Integer> tail, Integer shifts) {
		_tail = tail;
		_shifts = shifts;
		_xaValue = a;
		_xbValue = b;
		_tailValue = getTailValue();
	}

	public Double getTailValue() {
		Double result = 0d;
		for (int i = 0; i < _tail.size(); i++) {
			int pos = _tail.size() - i - 1;
			if (_tail.get(i) == 1) {
				result += Math.pow(2, pos);
			}
		}
		return result;
	}

	public static Integer getTailValue(List<Integer> tail) {
		Integer result = 0;
		for (int i = tail.size() - 1; i >= 0; i--) {
			int pos = tail.size() - i - 1;
			result += 2 ^ pos;
		}
		return result;
	}

	public Double getXAValue() {
		return _xaValue;
	}

	public Double getXBValue() {
		return _xbValue;
	}

	public Double getXCValue() {
		return getXBValue() + getTailValue();
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
		return "{ formula:\"" + _xaValue + "X+" + getXCValue() + "\", base:\"(" + _xaValue + "X+" + _xbValue + ")"
				+ _tail + "\", shifts:" + _shifts + " }";
	}

}
