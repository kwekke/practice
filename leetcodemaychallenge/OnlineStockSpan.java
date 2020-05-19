
/*
https://leetcode.com/explore/featured/card/may-leetcoding-challenge/536/week-3-may-15th-may-21st/3334/
Write a class StockSpanner which collects daily price quotes for some stock,
and returns the span of that stock's price for the current day.

The span of the stock's price today is defined as the maximum number of consecutive days (starting from today and going backwards)
for which the price of the stock was less than or equal to today's price.

For example, if the price of a stock over the next 7 days were [100, 80, 60, 70, 60, 75, 85],
then the stock spans would be [1, 1, 1, 2, 1, 4, 6].

Note:
Calls to StockSpanner.next(int price) will have 1 <= price <= 10^5.
There will be at most 10000 calls to StockSpanner.next per test case.
There will be at most 150000 calls to StockSpanner.next across all test cases.
The total time limit for this problem has been reduced by 75% for C++, and 50% for all other languages.
========================================================================================================================
1) List
maintain a variable storing the index being considered 
since a span is cumulative, the index decrements by its span
Time Complexity: O(N)
Space Complexity: O(1)

2) Array
The implementation essentially detects increasing subsequences. 
If a current number is larger than the previous number, update the top of stack. 
0 0 0 0 0 0 0 
1 0 0 0 0 0 0 
1 1 0 0 0 0 0
1 1 1 0 0 0 0
1 1 2 0 0 0 0
1 1 2 1 0 0 0
1 1 4 1 0 0 0

The constraints allow us to initialise an array of fixed size.
Time Complexity: O(N)
Space Complexity: O(1)

*/
import java.util.*;

public class OnlineStockSpan {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StockSpanner2 sp2 = new StockSpanner2();
        while (sc.hasNext()) {
            int n = sc.nextInt();
            System.out.println(sp2.next(n));
        }
    }
}

class StockSpanner {
    List<Integer> prices;
    List<Integer> spans;

    public StockSpanner() {
        prices = new ArrayList<Integer>();
        spans = new ArrayList<Integer>();
    }

    public int next(int price) {
        int span = 1;
        int index = prices.size() - 1;
        while (index >= 0 && price >= prices.get(index)) {
            span += spans.get(index);
            index -= spans.get(index);
        }
        spans.add(span);
        prices.add(price);
        return span;
    }
}

class StockSpanner2 {
    private int[] prices;
    private int[] spans;
    private int top;

    public StockSpanner2() {
        prices = new int[10000];
        spans = new int[10000];
        top = -1;
    }

    public int next(int price) {
        int span = 1;
        while (0 <= top && prices[top] <= price) {
            span += spans[top--];
        }
        top++;
        spans[top] = span;
        prices[top] = price;
        return span;
    }
}