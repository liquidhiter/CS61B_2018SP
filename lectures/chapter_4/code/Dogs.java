import java.util.Comparator;

public class Dogs implements Comparable<Dogs> {
    private String name;
    private int size;

    /**
     * 
     * @param n
     * @param s
     */
    public Dogs(String n, int s) {
        name = n;
        size = s;
    }

    /**
     * 
     */
    public int compareTo(Dogs other) {
        return this.size - other.size;
    }

    private static class NameComparator implements Comparator<Dogs> {
        public int compare(Dogs o1, Dogs o2) {
            return o1.name.compareTo(o2.name);
        }
    }

    public static Comparator<Dogs> getNameComparator() {
        return new NameComparator();
    }

    public static void main(String[] args) {
        Comparator<Dogs> comparator = Dogs.getNameComparator();
        Dogs d1 = new Dogs("HENK", 12);
        Dogs d2 = new Dogs("PETER", 3);
        if (comparator.compare(d1, d2) > 0) {
            System.out.println("LARGER: " + d1.name);
        } else {
            System.out.println("SMALLER: " + d1.name);
        }
    }
}
