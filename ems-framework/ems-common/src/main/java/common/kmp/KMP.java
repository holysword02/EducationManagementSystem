package common.kmp;

public class KMP {
    private static int[] kmpNext(String pattern) {
        int len = pattern.length();
        int[] next = new int[len];
        int i = 1, j = 0;
        while (i < len) {
            if (pattern.charAt(i) == pattern.charAt(j)) {
                next[i++] = ++j;
            } else {
                if (j == 0) {
                    next[i++] = 0;
                } else {
                    j = next[j - 1];
                }
            }
        }
        return next;
    }

    public static int kmpSearch(String text, String pattern) {
        int[] next = kmpNext(pattern);
        int ans = 0;
        for (int i = 0, j = 0; i < text.length(); i++) {
            while (j > 0 && text.charAt(i) != pattern.charAt(j)) {
                j = next[j - 1];
            }
            if (text.charAt(i) == pattern.charAt(j)) {
                j++;
            }
            if (j == pattern.length()) {
                ans++;
                j = next[j - 1];
            }
        }
        return ans;
    }
}
