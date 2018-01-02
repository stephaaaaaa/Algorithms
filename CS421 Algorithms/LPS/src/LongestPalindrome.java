public class LongestPalindrome {

	int subsequenceMatrix[][];
	
	public int getSubsequenceMatrix(char[] originalString) {
		int stringLength = originalString.length;
		subsequenceMatrix = new int[stringLength][stringLength];
		/**
		 * create a subsequence matrix to hold all the lengths of each possible
		 * subsequence, for index i to index j
		 **/
		int subsequenceMatrix[][] = new int[stringLength][stringLength];
		for (int i = 0; i < stringLength; i++) {
			// for a subsequence of length 1, the only possible palindrome is
			// the character you are at. Equates to a length of 1
			subsequenceMatrix[i][i] = 1;
		}

		// for loop, for each subsequence length >= 2
		for (int l = 2; l <= stringLength; l++) {
			// create frames from 0 to whatever index j is at
			// proceed until you have accumulated frames that extend to
			// the end of your string
			for (int i = 0; i < stringLength - l + 1; i++) {
				// j will begin by being +1 than i, and increments until it
				// becomes the length of the string
				int j = i + l - 1;
				// if the characters are the same when size == 2, 
				if (l == 2 && originalString[i] == originalString[j]) {
					// max length of your subseq is 2
					subsequenceMatrix[i][j] = 2;
				} 
				// if the characters at i and j match
				else if (originalString[i] == originalString[j]) {
					// take the max of the individual preceding parts and add 2
					subsequenceMatrix[i][j] = subsequenceMatrix[i + 1][j - 1] + 2;
				} 
				// if the characters do not match
				else {
					// just take the max of the predecessors
					subsequenceMatrix[i][j] = Math.max(subsequenceMatrix[i + 1][j], subsequenceMatrix[i][j - 1]);
				}
			}
		}
		printMatrix(subsequenceMatrix, stringLength, stringLength, originalString);
		return subsequenceMatrix[0][stringLength - 1];
	}

	private static void printMatrix(int[][] matrix, int iLength, int jLength, char[] originalString){
		for(int i = 0; i < originalString.length; i++)
			System.out.print("\t" + originalString[i]);
		System.out.println("\n");
		for(int i = 0; i < iLength; i++){
			System.out.print(originalString[i] + "\t");
			for(int j = 0; j < jLength; j++){
				System.out.print(matrix[i][j] + "\t");
			}
			System.out.println("");
		}
		System.out.println("\n");
	}

	public static void main(String args[]) {
		LongestPalindrome lps = new LongestPalindrome();
		String input = "character";
		int palindromeLength = lps.getSubsequenceMatrix(input.toCharArray());
		System.out.print("The length of our longest palindrome subsequence is " + palindromeLength + "\n\n\n");
	}

}