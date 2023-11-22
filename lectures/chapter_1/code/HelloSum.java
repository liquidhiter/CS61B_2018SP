public class HelloSum {
	public static void main(String[] args) {
		int sum = 0;
		int x = 0;
		while (x < 10) {
			sum += x;
			x += 1;
		}

		System.out.println("Sum of integers from 0 to 9 is " + sum);
	}
}
