class Solution {
    public int[] pathsWithMaxScore(List<String> board) {
        final int MOD = 1_000_000_007;
        int n = board.size();

        int[] prevScore = new int[n + 1];
        int[] prevWays = new int[n + 1];
        Arrays.fill(prevScore, -1);

        for (int i = n - 1; i >= 0; i--) {
            int[] currScore = new int[n + 1];
            int[] currWays = new int[n + 1];
            Arrays.fill(currScore, -1);

            for (int j = n - 1; j >= 0; j--) {
                char cell = board.get(i).charAt(j);

                if (cell == 'X')
                    continue;

                if (cell == 'S') {
                    currScore[j] = 0;
                    currWays[j] = 1;
                    continue;
                }

                int bestScore = -1;
                long totalWays = 0;

                // Down
                if (prevScore[j] != -1) {
                    bestScore = prevScore[j];
                    totalWays = prevWays[j];
                }

                // Right
                if (currScore[j + 1] > bestScore) {
                    bestScore = currScore[j + 1];
                    totalWays = currWays[j + 1];
                } else if (currScore[j + 1] == bestScore && bestScore != -1) {
                    totalWays += currWays[j + 1];
                }

                // Diagonal
                if (prevScore[j + 1] > bestScore) {
                    bestScore = prevScore[j + 1];
                    totalWays = prevWays[j + 1];
                } else if (prevScore[j + 1] == bestScore && bestScore != -1) {
                    totalWays += prevWays[j + 1];
                }

                if (bestScore == -1)
                    continue;

                int value = (cell == 'E') ? 0 : cell - '0';
                currScore[j] = bestScore + value;
                currWays[j] = (int) (totalWays % MOD);
            }

            prevScore = currScore;
            prevWays = currWays;
        }

        if (prevScore[0] == -1)
            return new int[] {0, 0};

        return new int[] {prevScore[0], prevWays[0]};
    }
}