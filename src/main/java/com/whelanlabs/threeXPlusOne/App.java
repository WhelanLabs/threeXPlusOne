package com.whelanlabs.threeXPlusOne;

import java.util.ArrayList;
import java.util.List;


public class App {
	private App() {
		// static class
	}

	public static void main() {
		System.out.println("Hello World!");
		Integer length = 4;

		System.out.println("length = " + length);

		List<List<Integer>> listOfListOfBits = Utils.getListOfBits(length);
		do {
			List<List<Integer>> nextBatch = new ArrayList<>();
			
			System.out.println("################################");
			
			for (List<Integer> listOfBits : listOfListOfBits) {
				if (1 == listOfBits.get(listOfBits.size() - 1)) {
					
					// skip cases where the starting value is 1
					if(1 != listOfBits.stream().mapToInt(Integer::intValue).sum()) {
						Boolean isSmaller = false;
						List<Integer> currentBits = listOfBits;
						TailArray startingPoint = new TailArray(currentBits);
						TailArray startingTailArray = new TailArray(currentBits);

						while (startingTailArray.getTail().size() > 0 && null != isSmaller && isSmaller == false) {
							// System.out.println("currentBits = " + startingTailArray.getTail());

							TailArray resultingTailArray = Utils.get3XPlusOne(startingTailArray);
							isSmaller = Utils.isSmaller(startingPoint, resultingTailArray);
							
							if(resultingTailArray.getTail().size()==1 && !isSmaller) {
								System.out.println("Adding new canidate for : " + listOfBits);
								
								List<Integer> leadingOne = new ArrayList<>();
								leadingOne.add(1);
								leadingOne.addAll(listOfBits);
								nextBatch.add(leadingOne);
								
								List<Integer> leadingZero = new ArrayList<>();
								leadingZero.add(0);
								leadingZero.addAll(listOfBits);
								nextBatch.add(leadingZero);
							}
							
							startingTailArray = resultingTailArray;
							// System.out.println("");
						}
					}

				}
			}
			
			listOfListOfBits = nextBatch;
			
		} while (listOfListOfBits.size() > 0);
		


	}
}
