package lectures.chapter_08.code;

public class DelegationStack<T> {
    private LinkedListDeque<T> L = new LinkedListDeque<T>();
    
    public void push(T item) {
        L.addFirst(item);
    }
}
