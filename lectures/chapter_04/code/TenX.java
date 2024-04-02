package lectures.chapter_04.code;
public class TenX implements IntUnaryFunction {
    public int apply(int x) {
        return 10 * x;
    }

    public static int do_twice(IntUnaryFunction f, int x) {
        return f.apply(f.apply(x));
    }

    public static void main(String[] args) {
        System.out.println(do_twice(new TenX(), 10));
    }
}