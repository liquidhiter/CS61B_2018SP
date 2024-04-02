package lectures.chapter_04.code;
public class VengefulSLList<T> extends SLList<T> {
    SLList<T> deletedItems;

    public VengefulSLList(T x) {
        super(x);
        deletedItems = new SLList<>();
    }

    public VengefulSLList() {
        super();
        deletedItems = new SLList<>();
    }

    public void printLostItems() {
        deletedItems.print();
    }

    @Override
    public T removeLast() {
        T x = super.removeLast();
        deletedItems.addLast(x);
        return x;
    }

    public static void main(String[] args) {
        VengefulSLList<Integer> list = new VengefulSLList<>();
        for (int i = 0; i < 10; ++i) {
            list.addFirst(i);
        }
        list.print();
        System.out.println();
        int last = list.removeLast();
        list.print();
    }
}
