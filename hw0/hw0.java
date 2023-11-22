package hw0;
public class hw0 {
	
	/**
	 * 
	 */
	private static final String symbol = "*";
	private static final String  placeholder = " ";

	/**
	 * 
	 * @param lineNum
	 * @param lineWidth
	 */
	public static void printLine(int lineNum, int lineWidth) {
		// Sanity check for the line number
		assert (lineNum >= 0) : "Line number must be non-negative";
		assert (lineWidth > 0) : "Line width must be larger than zero";
		// assert (lineWidth <= lineNum) : "Line width must be small than line number";

		for (int i = 0; i < lineWidth; i++) {
			if (i <= lineNum) {
				System.out.print(symbol);
			} else {
				System.out.print(placeholder);
			}
		}
	}

	/**
	 * 
	 * @param arrayOfInts
	 * @return
	 */
	public static int max(int[] seqOfInts) {
		assert (seqOfInts != null) : "seqOfInts must not be null";
		int lenOfSeq = seqOfInts.length;        
		int maxOfSeq = seqOfInts[0];
		for (int idx = 1; idx < lenOfSeq; ++idx) {
			if (seqOfInts[idx] > maxOfSeq)
				maxOfSeq = seqOfInts[idx];
		}

		return maxOfSeq;
	}

	/**
	 * 
	 * @param seqOfInts
	 * @param from
	 * @param to
	 * @return
	 */
	private static int sumPartialSeq(int[] seqOfInts, int from, int to) {
		int sum = 0;
		for (int idx = from; idx <= to; ++idx) {
			sum += seqOfInts[idx];
		}

		return sum;
	}


	/**
	 * 
	 * @param seqOfInt
	 * @param n
	 */
	public static void windowPosSum(int[] seqOfInt, int n) {
		assert (seqOfInt != null) : "seqOfInt must not be null";
		assert (n >= 0);
		
		// No need to do anything if n equals 0
		if (n == 0) return;
		
		int lenOfSeq = seqOfInt.length;
		// No need to do anything if there is only one element
		if (lenOfSeq == 1)
			return;

		for (int i = 0; i < lenOfSeq; ++i) {
			if (seqOfInt[i] < 0)
				continue;
			
			// Calculate the sum of the partial sequences
			int sum = 0;
			for (int j = i; j <= n + i; ++j) {
				if (j >= lenOfSeq) break;
				sum += seqOfInt[j];
			}

			seqOfInt[i] = sum;
		}
	}

	
	public static void main(String[] args) {
		/* Test Solution to Exercise 1 */
		printTriangle(5, 5);

		/* Test Solution to Exercise 2 */
		int[] numbers = new int[]{9, 2, 15, 2, 22, 10, 6}; 
		System.out.println("Max integer of the sequence is " + max(numbers));

		/* Test solution to Exercise 4 */
		int[] a = {1, 2, -3, 4, 5, 4};
		int n = 3;
		windowPosSum(a, n);
		// Should print 4, 8, -3, 13, 9, 4
		System.out.println(java.util.Arrays.toString(a));

		int[] b = {1, -1, -1, 10, 5, -1};
		int m = 2;
		windowPosSum(b, m);
		// Should print {-1, -1, -1, 14, 4, -1}
		System.out.println(java.util.Arrays.toString(b));
	}
}