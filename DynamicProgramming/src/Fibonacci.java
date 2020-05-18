public class Fibonacci {

    private int[] memo;

    public static void main(String[] args) {
        Fibonacci fib = new Fibonacci();
        System.out.println(fib.tabularFib(46));
    }

    public int recursionFib(int n) {
        if (n <= 2)
            return 1;

        return recursionFib(n - 1) + recursionFib(n - 2);
    }

    public int memoFib(int n) {
        memo = new int[n + 1];
        return memoHelper(n);
    }

    private int memoHelper(int n) {
        if (memo[n] == 0) {
            if (n == 1 || n == 2)
                memo[n] = 1;
            else
                memo[n] = memoHelper(n - 1) + memoHelper(n - 2);
        }
        return memo[n];
    }

    public int tabularFib(int n) {
        int[] table = new int[n + 1];
        table[1] = table[2] = 1;

        for (int i = 3; i <= n; i++) {
            table[i] = table[i - 1] + table[i - 2];
        }

        return table[n];
    }

}
