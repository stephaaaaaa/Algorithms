import java.util.HashMap;
import java.util.LinkedList;

public class BruteForce {

	@SuppressWarnings({ "rawtypes" })
	public static void main(String[] args) {
		LinkedList<Integer> initialSet = new LinkedList<Integer>();
		initialSet.add(1);
		initialSet.add(2);
		initialSet.add(3);
		initialSet.add(4);
		// begin by finding the power set. I.e, all possible subsets.
		LinkedList<LinkedList<Integer>> powerSet = powerSet(initialSet);
		System.out.println("Our power set is " + powerSet + "\nIts size is " + powerSet.size());
		System.out.println("\nWe will begin by removing the fullest set. We cannot sum the contents together,"
				+ " and generate a sum \nthat we can add in a pair set. Even if we could, "
				+ "adding another variation of the contents would be nondisjoint set.\n");
		if (powerSet.contains(initialSet))
			powerSet.remove(initialSet);
		System.out.println("Our new power set is: " + powerSet + "\nIts new size is " + powerSet.size());
		LinkedList<HashMap> aggregateSums = computeSetSums(powerSet);
		System.out.println("\nHere we have all the sets, with their sums:\n" + aggregateSums);
		// XXX Begin making your pairs
		// Begin by generating a list of the sums you need to check for
		LinkedList<Integer> sumsList = createSumsList(aggregateSums);
		System.out.println("Sums: " + sumsList);
		generatePairs(sumsList, aggregateSums);
	}

	private static LinkedList<LinkedList<Integer>> generatePairs(LinkedList<Integer> sumsList,
			@SuppressWarnings("rawtypes") LinkedList<HashMap> aggregateSums) {
		LinkedList<LinkedList<Integer>> pairs = null;
		for (int sum : sumsList) { // iterate through the sums list
			pairs = new LinkedList<LinkedList<Integer>>();
			System.out.println("\nFor the sum: " + sum);
			int i = 0;
			// check if the value is the same as the sum
			while (i < aggregateSums.size()) {
				@SuppressWarnings("rawtypes")
				HashMap current = aggregateSums.get(i);
				if (current.values().contains(sum)) {
					pairs.addAll(current.keySet());
					aggregateSums.remove(current);
					i--;
				}
				i++;
			}
			if (pairs.size() != 2)
				System.err.println("No pairs.");

			// check for duplicate elements
			for (int j = 0; j < pairs.size() - 1; j++) {
				LinkedList<Integer> currentPair = pairs.get(j);
				LinkedList<Integer> nextPair = pairs.get(j + 1);
				for (Integer set1Int : currentPair) {
					for (Integer set2Int : nextPair) {
						if (set1Int.equals(set2Int)) {
							System.err.println("Pairs are not made up of disjoint sets.");
						}
					}
				}
			}
			System.out.println(pairs);
		}
		return pairs;
	}

	@SuppressWarnings("hiding")
	private static <Integer> LinkedList<LinkedList<Integer>> powerSet(LinkedList<Integer> originalSet) {
		LinkedList<LinkedList<Integer>> sets = new LinkedList<LinkedList<Integer>>();
		if (originalSet.isEmpty()) {
			// return the empty set
			sets.add(new LinkedList<Integer>());
			return sets;
		}
		Integer beginning = originalSet.get(0);
		LinkedList<Integer> remaining = new LinkedList<Integer>(originalSet.subList(1, originalSet.size()));
		for (LinkedList<Integer> set : powerSet(remaining)) {
			LinkedList<Integer> newSet = new LinkedList<Integer>();
			newSet.add(beginning);
			newSet.addAll(set);
			sets.add(newSet);
			sets.add(set);
		}
		return sets;
	}

	@SuppressWarnings("rawtypes")
	private static LinkedList<HashMap> computeSetSums(LinkedList<LinkedList<Integer>> powerSet) {
		LinkedList<HashMap> setsAndSumsList = new LinkedList<HashMap>();
		for (int i = 0; i < powerSet.size(); i++) {
			HashMap<LinkedList<Integer>, Integer> setAndSum = new HashMap<LinkedList<Integer>, Integer>();
			LinkedList<Integer> currentSet = powerSet.get(i);
			int sum = 0;
			for (int j = 0; j < currentSet.size(); j++) {
				sum += currentSet.get(j);
			}
			setAndSum.put(powerSet.get(i), sum);
			setsAndSumsList.add(setAndSum);
		}
		return setsAndSumsList;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static LinkedList<Integer> createSumsList(LinkedList<HashMap> aggregateSums) {
		LinkedList<Integer> sums = new LinkedList<Integer>();
		for (int i = 0; i < aggregateSums.size(); i++) { // create a list of
															// just the sums
			sums.addAll(aggregateSums.get(i).values());
		}
		// remove duplicates
		for (int i = 0; i < sums.size(); i++) {
			int currentSumVal = sums.get(i);
			for (int j = i + 1; j < sums.size(); j++) {
				if (sums.get(j) == currentSumVal) {
					sums.remove(j);
				}
			}
		}
		return sums;
	}
}