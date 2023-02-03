package com.whelanlabs.threeXPlusOne;

import java.util.ArrayList;
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
		// while(listOfListOfBits)
			if (1 == listOfBits.get(listOfBits.size() - 1)) {
				
				// skip cases where the starting value is 1
				if(1 != listOfBits.stream().mapToInt(Integer::intValue).sum()) {
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
						
						if(resultingTailArray.getTail().size()==1 && !isSmaller) {
							List<Integer> leadingOne = new ArrayList();
							leadingOne.add(1);
							leadingOne.addAll(listOfBits);
							listOfListOfBits.add(leadingOne);
							
							List<Integer> leadingZero = new ArrayList();
							leadingZero.add(0);
							leadingZero.addAll(listOfBits);
							listOfListOfBits.add(leadingZero);
						}
						
						startingTailArray = resultingTailArray;
						System.out.println("");
					}
				}

			}
		}

	}
}
