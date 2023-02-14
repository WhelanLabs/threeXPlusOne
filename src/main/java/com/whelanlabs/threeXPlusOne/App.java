package com.whelanlabs.threeXPlusOne;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class App {

	private static final Logger logger = LogManager.getLogger(App.class);

	private App() {
		// static class
	}

	public static void main() throws IOException {
		logger.info("Hello World!");
		Integer length = 4;
		Integer i = 0;

		System.out.println("length = " + length);

		long startTime = 0l;
		Integer lastProcessesCount = 0;
		List<List<Integer>> listOfListOfBits = Utils.getListOfBits(length);
		
		Feeder f = new Feeder(i.toString());
		f.add(listOfListOfBits);
		f.closeWriter();
		
		do {

			i++;

			long endTime = System.currentTimeMillis();
			if (0 != startTime) {
				long duration = endTime - startTime;
				long durationSeconds = duration / 1000;
				if (durationSeconds > 0) {
					long processingRate = lastProcessesCount / durationSeconds;
					logger.info("      processed " + processingRate + " tails per second. (total milliseconds: "
							+ duration + ")");
				}
			}

			logger.info("##### batch " + i + " (batch size: " + f.size() + ")");

			lastProcessesCount = f.size();
			startTime = System.currentTimeMillis();

			// List<List<Integer>> nextBatch = Collections.synchronizedList(new ArrayList<>());
			
			Feeder f2 = new Feeder(i.toString());

			List<List<Integer>> canidates;
			while(( canidates = f.get(1000000)).size() > 0) {
				canidates.parallelStream().forEach((listOfBits) -> {
						f2.add(test(listOfBits));
				});
			}

			f2.closeWriter();
			f = f2;

		} while (f.size() > 0);

	}

	private static List<List<Integer>> test(List<Integer> listOfBits) {
		List<List<Integer>> nextBatch = new ArrayList<>();
		if (null == listOfBits) {
			return nextBatch;
		}

		if (1 == listOfBits.get(listOfBits.size() - 1)) {

			// skip cases where the starting value is 1
			if (1 != listOfBits.stream().mapToInt(Integer::intValue).sum()) {
				Boolean isDeadEnd = false;
				List<Integer> currentBits = listOfBits;
				TailArray startingPoint = new TailArray(currentBits);
				TailArray startingTailArray = new TailArray(currentBits);

				while (startingTailArray.getTail().size() > 0 && null != isDeadEnd && isDeadEnd == false) {
					// System.out.println("currentBits = " + startingTailArray.getTail());

					TailArray resultingTailArray = Utils.get3XPlusOne(startingTailArray);
					isDeadEnd = Utils.isDeadEnd(startingPoint, resultingTailArray);

					if (resultingTailArray.getTail().size() <= 1 && !isDeadEnd) {
						logger.debug("Adding new child canidates for: " + listOfBits);

						List<Integer> leadingOne = new ArrayList<>();
						leadingOne.add(1);
						leadingOne.addAll(listOfBits);
						nextBatch.add(leadingOne);

						List<Integer> leadingZero = new ArrayList<>();
						leadingZero.add(0);
						leadingZero.addAll(listOfBits);
						nextBatch.add(leadingZero);

						break;
					}

					startingTailArray = resultingTailArray;
				}
			}

		}
		return nextBatch;
	}
}
