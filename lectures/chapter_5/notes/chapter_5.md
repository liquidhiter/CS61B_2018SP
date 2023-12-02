# Topic: More Java launguage stuff
## Autoboxing and unboxing
- primitive types and generic type arguments
- implicit convertion between the primitive types and wrapper

dis-advantages of using wrapper type for primitive ones
- auto-boxing results in worse performance
- arrays are never auto-boxed or auto-unboxed
- wrapper types require much more memory than primitive ones

## Immutabulity
- similar concept as in Python: be immutable as it should be, e.x. tuple rather than list
- final reference only guarantees that the reference itself is not changable, not the object


## Generic methods
```java
public static <K, V> V get(Map61B<K, V> map, K key) {
    /* */
}
```

```java
public static <K extends Comparable<K>, V> K maxKeys(Map61B<K, V> map, K key) {
    /* */
}
```
- `<K extends Comparable<K>, V>` all classes of K needs to be comparable

## Iterable and iterator
```java
import java.util.Iterator;

public class ArraySet<T> implements Iterable<T> {

    private T[] items;
    private int size;

    /*======= Omit some code here =======*/

    /** returns an iterator (a.k.a. seer) */
    public Iterator<T> iterator() {
        return new ArraySetIterator();
    }

    private class ArraySetIterator implements Iterator<T> {
        private int nextIdx;

        public ArraySetIterator() {
            nextIdx = 0;
        }

        @Override
        public boolean hasNext() {
            return nextIdx < size;
        }

        @Override
        public T next() {
            return items[nextIdx++];
        }
    }

    public static void main(String[] args) {
        ArraySet<String> s = new ArraySet<>();
        s.add(null);
        s.add("horse");
        s.add("fish");
        s.add("house");
        s.add("fish");
        System.out.println(s.contains("horse"));
        System.out.println(s.size());

        Iterator<String> iter = s.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }

        for (String item : s) {
            System.out.println(item);
        }
    }
}
```
- `Iterable` requires the class to define the method of `iterator()` which returns an iterator
- `Iterator` implements how the iteration is working, specifically defining methods of `hasNext()` and `next()`
  - `next()` is called when the `hasNext()` returns false: common convention is to throw the `NoSuchElementException`
  - `next()` can be called without calling the `hasNext()` method


## `toString()` and `equals()`
```java
@Override
public boolean equals(Object obj) {
    /* self-equals */
    if (this == obj) {
        return true;
    }

    if (obj == null) {
        return false;
    }

    /* comparable for same class */
    if (obj.getClass() != this.getClass()) {
        return false;
    }

    ArraySet<T> other = (ArraySet<T>) obj;
    if (other.size() != this.size()) {
        return false;
    }

    for (T item : this) {
        if (!other.contains(item)) {
            return false;
        }
    }
    
    return true;
}
```
- understand the difference between `==` and `equals`


## Generic static methods
```java
public static <type> ArraySet<type> of(type... args) {
    ArraySet<type> result = new ArraySet<>();
    for (type t : args) {
        result.add(t);
    }

    return result;
}
```