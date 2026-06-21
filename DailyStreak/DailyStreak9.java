/*
# Problem Type
Greedy + Counting Sort

# Observation
To maximize number of ice cream bars:
Always buy cheapest bars first.

# Why Counting Sort?
Constraints:
1 <= costs[i] <= 100000

Instead of sorting O(n log n),
use frequency array O(n + maxCost).

# Algorithm

1. Find maximum cost.
2. Create frequency array counts[].
3. Store occurrence of each cost.
4. Traverse prices from 1 to maxCost.
5. For each price:
      if no bar exists -> continue

      if coins < price -> break

      canBuy = min(counts[price], coins/price)

      coins -= canBuy * price
      totalBars += canBuy
6. Return totalBars.

# Greedy Choice
Buy cheapest available bars first.

# Example

costs = [1,3,2,4,1]
coins = 7

Frequency:
1 -> 2
2 -> 1
3 -> 1
4 -> 1

Buy:
price=1 -> buy 2 bars
coins=5

price=2 -> buy 1 bar
coins=3

price=3 -> buy 1 bar
coins=0

Answer = 4

# Time Complexity
O(n + maxCost)

# Space Complexity
O(maxCost)

# Pattern
Frequency Array / Counting Sort + Greedy
 */

import java.util.*;

public class DailyStreak9 {

    public static int maxIceCream(int[] costs, int coins) {

        // Step 1: Find maximum cost
        int maxCost = 0;
        for (int cost : costs) {
            maxCost = Math.max(maxCost, cost);
        }

        // Step 2: Create frequency array
        int[] counts = new int[maxCost + 1];
        for (int cost : costs) {
            counts[cost]++;
        }

        int totalBars = 0;

        // Step 3: Buy cheapest bars first
        for (int price = 1; price <= maxCost; price++) {

            if (counts[price] == 0)
                continue;

            if (coins < price)
                break;

            int canBuy = Math.min(counts[price], coins / price);

            coins -= canBuy * price;
            totalBars += canBuy;
        }

        return totalBars;
    }

    public static void main(String[] args) {

        int[] costs = {1, 3, 2, 4, 1};
        int coins = 7;

        System.out.println(maxIceCream(costs, coins));
    }
}

/*
Use Counting Sort when:

1. Values are small bounded integers.
2. Need sorted order but sorting is expensive.
3. Constraints look like:
      n = 1e5
      value <= 1e5
4. Asked explicitly:
      "Solve using Counting Sort"

Keywords:
- frequency array
- bounded range
- buy cheapest first
- process values in sorted order
*/