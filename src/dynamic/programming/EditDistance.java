package dynamic.programming;

public class EditDistance {
    private static long startTime;
    private static long endTime;

    public static int editDistance(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();

        if (n == 0) {
            return m;
        }
        if (m == 0) {
            return n;
        }

        if (s1.charAt(0) == s2.charAt(0)) {
            return editDistance(s1.substring(1), s2.substring(1));
        } else {
            int insert = editDistance(s1, s2.substring(1));

            int delete = editDistance(s1.substring(1), s2);

            int sub = editDistance(s1.substring(1), s2.substring(1));

            return 1 + Math.min(insert, Math.min(delete, sub));
        }
    }

    public static int editDistanceM(String s1, String s2) {
        startTime = System.nanoTime();
        int m = s1.length();
        int n = s2.length();

        int[][] storage = new int[s1.length() + 1][s2.length() + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                storage[i][j] = -1;
            }
        }
        endTime   = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println(totalTime);
        return editDistanceM(s1, s2, storage);

    }

    private static int editDistanceM(String s1, String s2, int[][] storage) {
        int m = s1.length();
        int n = s2.length();

        if (n == 0) {
            storage[m][n] = m;
            return storage[m][n];
        }
        if (m == 0) {
            storage[m][n] = n;
            return storage[m][n];
        }

        if (storage[m][n] != -1) {
            return storage[m][n];
        }

        if (s1.charAt(0) == s2.charAt(0)) {
            storage[m][n] = editDistanceM(s1.substring(1), s2.substring(1),storage);
        } else {
            int insert = editDistanceM(s1, s2.substring(1),storage);

            int delete = editDistanceM(s1.substring(1), s2,storage);

            int sub = editDistanceM(s1.substring(1), s2.substring(1), storage);

            storage[m][n] = 1 + Math.min(insert, Math.min(delete, sub));
        }

        return storage[m][n];
    }

    public static int editDistanceDP(String s1, String s2) {
        startTime = System.nanoTime();
        int m = s1.length();
        int n = s2.length();

        int[][] storage = new int[m + 1][n + 1];

        for (int i = 0; i <= n; i++) {
            storage[i][0] = i;
        }
        for (int j = 0; j <= m; j++) {
            storage[0][j] = j;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(m-i) == s2.charAt(n-j)) {
                    storage[i][j] = storage[i-1][j-1];
                } else {
                    int insert = storage[i][j-1];

                    int delete = storage[i-1][j];

                    int sub = storage[i-1][j-1];

                    storage[i][j] = 1 + Math.min(insert, Math.min(delete, sub));
                }
            }
        }
        endTime   = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println(totalTime);
        return storage[n][m];

    }


    public static void main(String[] args) {
        String s1 = "adef";
        String s2 = "bedf";
        System.out.println(editDistanceDP(s1, s2));
        System.out.println(editDistanceM(s1, s2));
        System.out.println(editDistance(s1, s2));

    }
}