public class HelloStatiType {
    public static void main(String[] args) {
        // This code below is buggy
        /*
         * Error Messages:
         * Exception in thread "main" java.lang.Error: Unresolved compilation problem: 
         * Type mismatch: cannot convert from String to int
         * at HelloStatiType.main(HelloStatiType.java:5)
        */
        int x = 0;
        x = "Hello";
    }
}
