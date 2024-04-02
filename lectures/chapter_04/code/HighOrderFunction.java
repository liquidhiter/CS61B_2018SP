package lectures.chapter_04.code;
import java.util.function.Function;

public class HighOrderFunction {
    public static int tenX(int x) {
        return x * 10;
    }

    public static int doTwice(Function<Integer, Integer> f, int x) {
        return f.apply(f.apply(x));
    }

    public static void main(String[] args) {
        int result = doTwice(HighOrderFunction::tenX, 2);
        System.out.println(result);
    }
}
