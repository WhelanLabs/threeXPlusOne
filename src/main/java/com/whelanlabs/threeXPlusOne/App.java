package com.whelanlabs.threeXPlusOne;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App {
	private App() {
		// static class
	}

	public static void main() {
		System.out.println("Hello World!");
		Integer length = 4;

		System.out.println("length = " + length);

		List<List<Integer>> listOfListOfBits = Utils.getListOfBits(length);
		for (List<Integer> listOfBits : listOfListOfBits) {
			if (1 == listOfBits.get(listOfBits.size() - 1)) {
				Boolean isSmaller = false;
				List<Integer> currentBits = listOfBits;
				TailArray startingPoint = new TailArray(currentBits);
				TailArray startingTailArray = new TailArray(currentBits);

				System.out.println("################################");

				while (startingTailArray.getTail().size() > 0 && null != isSmaller && isSmaller == false) {
					System.out.println("currentBits = " + startingTailArray.getTail());
					//startingTailArray = 
					TailArray resultingTailArray = Utils.get3XPlusOne(startingTailArray);
					isSmaller = Utils.isSmaller(startingPoint, resultingTailArray);
					startingTailArray = resultingTailArray;
					System.out.println("");
				}
			}
		}

	}
}
