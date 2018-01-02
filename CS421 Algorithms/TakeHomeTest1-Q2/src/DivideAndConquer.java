import java.util.Arrays;

public class DivideAndConquer {

	public static void main(String[] args) {
		int[] sortedNumbers = { -6, -5, 2, 4, 5 };
		System.out.println("Our input array is " + Arrays.toString(sortedNumbers));
		int originalKValue = 5; // k value for which we find absolute value occurrences
		int kValue = Math.abs(originalKValue);
		System.out.println("Find the number of values that are equal to our K value. K = " + kValue);
		// Find the number of occurrences in which both k and -k are present
		System.out.println("The number of occurences for the k value is " + findKOccurrences(sortedNumbers, kValue));
	}

	private static int findKOccurrences(int[] sortedNumbers, int kValue) {
		// split the array in half, to navigate through it there
		int length = sortedNumbers.length;
		int occurenceCount = 0;
		int midpoint = 0;
		if (length % 2 != 0)
			midpoint = (length + 1) / 2;
		else
			midpoint = length / 2;
		int[] right = splitArray(sortedNumbers, midpoint, 0, midpoint);
		int[] left = splitArray(sortedNumbers, midpoint, midpoint, length);

		for (int i = 0; i < right.length; i++) {
			if (right[i] == kValue)
				occurenceCount++;
		}
		for (int i = 0; i < left.length; i++) {
			if (left[i] == kValue)
				occurenceCount++;
		}
		return occurenceCount;
	}

	static int[] splitArray(int[] original, int size, int beginning, int endPoint) {
		int[] splitArray = new int[size];
		int count = 0;
		for (int i = beginning; i < endPoint; i++) {
			// add values up to the end, while applying the absolute value
			splitArray[count] = Math.abs(original[i]);
			count++;
		}
		return splitArray;
	}
}
