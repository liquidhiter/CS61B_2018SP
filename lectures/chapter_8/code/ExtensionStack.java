package lectures.chapter_8.code;

public class ExtensionStack<T> extends LinkedListDeque<T> {
    
    /**
     * Add the new item to the top of the stack
     * @param item
     */
    public void push(T item) {
        addFirst(item);
    }
}
