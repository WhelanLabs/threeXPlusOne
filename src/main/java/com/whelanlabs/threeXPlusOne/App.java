package com.whelanlabs.threeXPlusOne;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class App {

	private static final Logger logger = LogManager.getLogger(App.class);

	private App() {
		// static class
	}

	public static void main() throws IOException {
		Integer length = 4;

		System.out.println("length = " + length);

		List<List<Integer>> listOfListOfBits = Utils.getListOfBits(length);

		Integer i = 0;

		Feeder f = new Feeder(i);
		f.add(listOfListOfBits);
		f.closeWriter();

		process(i, f);
	}

	public static void process(Integer i, Feeder f) throws IOException {
		long startTime = 0l;

		Integer lastProcessesCount = 0;
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

			logger.info("##### batch " + i + " (batch size: ~" + f.size() + ")");

			startTime = System.currentTimeMillis();

			Feeder f2 = new Feeder(i);

			List<List<Integer>> canidates;
			Integer batchSize = 1000000;
			while ((canidates = f.get(batchSize)).size() > 0) {
				canidates.parallelStream().forEach((listOfBits) -> {
					f2.add(test(listOfBits));
				});

				Integer canidatesProcessed = canidates.size();
				lastProcessesCount += canidatesProcessed;
				logger.info("      Processed " + canidatesProcessed + " canidates.");
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

			// skip cases where the starting value is 1 - the trivial case
			if (1 != listOfBits.stream().mapToInt(Integer::intValue).sum()) {
				Boolean isDeadEnd = false;
				List<Integer> currentBits = listOfBits;
				TailArray startingPoint = new TailArray(currentBits);
				TailArray startingTailArray = new TailArray(currentBits);

				while (startingTailArray.getTail().size() > 0 && null != isDeadEnd && isDeadEnd == false) {

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
