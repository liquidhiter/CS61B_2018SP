package lectures.chapter_03.code;
public interface List61B<T> {
    public void addFirst(T x);
    public void addLast(T x);
    public T getFirst();
    public T getLast();
    public T removeLast();
    public T get(int i);
    public void insert(T x, int position);
    public int size();

    default public void print() {
        for (int i = 0; i < size(); i += 1) {
            System.out.print(get(i) + " ");
        }
        System.out.println();
    }
}
