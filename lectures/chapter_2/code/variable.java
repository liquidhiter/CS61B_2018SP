public class variable {
    public static void main(String[] args) {
        int x;
        
        // What if the variable is used before being defined?
        //! NOTE: Java prevents using a variable until after it has been initialized properly
        /* error: variable x might not have been initialized
        System.out.println("Variable x = " + x); */
        System.out.println("Variable x = " + x);
    }
}
