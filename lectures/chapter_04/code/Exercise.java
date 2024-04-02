package lectures.chapter_04.code;
import java.util.Map;
import java.util.Set;

public class Exercise {
    
    public static List<String> getWords(String inputFileName) {
        In in = new In();
        List<String> words = new ArrayList<String>();
        while (!in.isEmpty()) {
            words.add(in.readString());
        }

        return words;
    }

    public static int countUniqueWords(List<String> words) {
        Set<String> strings = new HashSet<String>();
        for (string s : words) {
            strings.add(s);
        }
        return strings.size();
    }

    public static Map<String, Integer> collectWordCount(List<String> words, List<String> target) {
        Map<String, Integer> counts = new HashMap<String, Integer>();
        for (String s : target) {
            counts.put(s, 0);
        }
        for (String s : words) {
            if (counts.containsKey(s)) {
                int oldCount = counts.get(s);
                counts.put(s, oldCount + 1);
            }
        }

        return counts;
    }
}
