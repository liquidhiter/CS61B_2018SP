package hw3.hash;

import java.util.List;

public class OomageTestUtility {

    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        /*
         * Write a utility function that returns true if the given oomages
         * have hashCodes that would distribute them fairly evenly across
         * M buckets. To do this, convert each oomage's hashcode in the
         * same way as in the visualizer, i.e. (& 0x7FFFFFFF) % M.
         * and ensure that no bucket has fewer than N / 50
         * Oomages and no bucket has more than N / 2.5 Oomages.
         */
        int N = oomages.size();
        int buckets[] = new int[M];
        /* Initialize all bucket length to 0 */
        for (int i = 0; i < M; i++) {
            buckets[i] = 0;
        }

        double upBound = N / 2.5, lowBound = N / 50;
        for (int i = 0; i < N; ++i) {
            int bucketNum = (oomages.get(i).hashCode() & 0x7FFFFFFF) % M;
            buckets[bucketNum]++;
        }

        /* Check no buckets exceed the bound */
        for (int i = 0; i < M; ++i) {
            if (buckets[i] > 0 && (buckets[i] > upBound || buckets[i] < lowBound)) {
                System.out.println("Bad hashcode spreading " + buckets[i]);
                return false;
            }
        }

        return true;
    }
}
