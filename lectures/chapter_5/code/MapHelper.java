package Map61B;

import java.util.Comparator;
import java.util.List;

public class MapHelper {
    public static <K, V> V get(Map61B<K, V> map, K key) {
        if (map.containsKey(key)) {
            return map.get(key);
        }
        return null;
    }

    public static <K extends Comparable<K>, V> K maxKeys(Map61B<K, V> map, K key) {
        List<K> keys = map.keys();
        int numOfkeys = keys.size();
        int maxIndex = 0;
        for (int i = 0; i < numOfkeys; ++i) {
            if (keys.get(i).compareTo(keys.get(maxIndex)) > 0) {
                maxIndex = i;
            }
        }
        return keys.get(maxIndex);
    }
}
