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
				System.out.println("################################");

				while (currentBits.size() > 0 && null != isSmaller && isSmaller == false) {
					TailArray startingTailArray = new TailArray(currentBits);
					System.out.println("currentBits = " + currentBits);

					TailArray resultingTailArray = Utils.get3XPlusOne(startingTailArray);
					isSmaller = Utils.isSmaller(startingTailArray, resultingTailArray);
					currentBits = resultingTailArray.getTail();
					System.out.println("#######");
				}
			}
		}

	}
}
