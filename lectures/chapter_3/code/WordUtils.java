public class WordUtils {
    
    /**
     * 
     * @param list
     * @return
     */
    public static String longest(SLList<String> list) {
        int indexOfLongest = 0;
        int lenOfList = list.size();
        for (int i = 1; i < lenOfList; ++i) {
            String maxString = list.get(indexOfLongest);
            String currString = list.get(i);
            if (currString.length() > maxString.length()) {
                indexOfLongest = i;
            }
        }

        return list.get(indexOfLongest);
    }

    public static void main(String[] args) {
        
    }
}
