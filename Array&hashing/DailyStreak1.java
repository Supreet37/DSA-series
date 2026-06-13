public class DailyStreak1 {

    public static String weightedWordMapping(String[] words, int[] weights) {
        StringBuilder res = new StringBuilder();

        for (String word : words) {
            int sum = 0;

            for (char c : word.toCharArray()) {
                sum += weights[c - 'a'];
            }

            res.append((char) ('z' - (sum % 26)));
        }

        return res.toString();
    }

    public static void main(String[] args) {

        String[] words = {"abcd", "def", "xyz"};

        int[] weights = {
            5, 3, 12, 14, 1, 2, 3, 2, 10, 6, 6, 9, 7,
            8, 7, 10, 8, 9, 6, 9, 9, 8, 3, 7, 7, 2
        };

        String ans = weightedWordMapping(words, weights);

        System.out.println(ans); // Output: rij
    }
}