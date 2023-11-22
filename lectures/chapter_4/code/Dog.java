public class Dog implements OurComparable {
    private String name;
    private int size;

    public Dog(String n, int s) {
        name = n;
        size = s;
    }

    public void bark() {
        System.out.println(name + " says bark");
    }

    public int compareTo(Object o) {
        Dog dog = (Dog) o;
        if (this.size < dog.size) {
            return -1;
        } else if (this.size == dog.size) {
            return 0;
        }

        return 1;
    }

    public static OurComparable max(OurComparable[] items) {
        int maxIndex = 0;
        for (int i = 0; i < items.length; i++) {
            int cmp = items[i].compareTo(items[maxIndex]);
            if (cmp > 0) {
                maxIndex = i;
            }
        }

        return items[maxIndex];
    }
}