package lectures.chapter_08.code;

public class StackAdapter<T> {
    private MyDeque L;
    public StackAdapter(MyDeque<T> worker) {
        L = worker;
    }

    public void push(T item) {
        L.addFirst(item);
    }
}
