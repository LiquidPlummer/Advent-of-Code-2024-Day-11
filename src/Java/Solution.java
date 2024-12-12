import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class Solution {

/*
We want to cache solutions
Any stone that ends up with length as a power of 2 will then start dividing down into individual digits.
X XX XXXX XXXXXXXX XXXXXXXXXXXXXXXX ...
0s become 1s
1s become 2024
...
2 1 2 4

2's become 4048
4's become 8096

now we have digits:
0 1 2 4 6 8 9
missing: 357

6: 12144 -> 24579456 -> 2 4 5 7 9 4 5 6 adds: 5 7
8: 3 2 7 7 2 6 0 8 adds 3
9: doesn't matter

From one zero with maybe 12 iterations we already have all digits.

So, every time we do a calculation for any input, we cache the input and an output array, map input to output.

 */



    private final BigInteger twentyFortyEight = BigInteger.valueOf(2024);
    private final Map<BigInteger, BigInteger[]> cache;

    public Solution() {
        this.cache = new HashMap<>();
        cache.put(BigInteger.valueOf(1), new BigInteger[]{twentyFortyEight});//look at that, we already have the first one!
    }



    public int solve(int n, Integer[] array) {
        int solution = 0;
        for(int i = 0; i < array.length; ++i) {
            solution += recurse(n, BigInteger.valueOf(array[i]));
        }
        return solution;
    }

    public int recurse(int n, BigInteger stone) {


        //base case, no more recursion
        if(n == 0) {
            return 1;
        }

        //behavior 1
        if(stone.compareTo(BigInteger.ZERO) == 0) {
            return recurse(n-1, BigInteger.ONE);
        }

        //behavior 2:
        String str = stone.toString();
        if(str.length() % 2 == 0) {
            return recurse(n-1, new BigInteger(str.substring(0, str.length()/2)))
                    + recurse(n-1, new BigInteger(str.substring(str.length()/2)));

        }


        //behavior 3:
        return recurse(n-1, stone.multiply(twentyFortyEight));
    }

    public BigInteger[] combineArrays(BigInteger[] left, BigInteger[] right) {
        BigInteger[] combined = new BigInteger[left.length + right.length];
        System.arraycopy(left, 0, combined, 0, left.length);
        System.arraycopy(right, 0, combined, left.length, right.length);

        return combined;
    }



    // public static int solve(int n, long[] array) {
    //     List<Long> solution = new ArrayList<>();
    //     for(int i = 0; i < array.length; i++) {
    //         solution.addAll(recursiveDoThing(n, array[i]));
    //     }
    //     return solution.size();
    // }

    // public static List<Long> recursiveDoThing(int n, Long stone) {
    //     //base case no more recursing
    //     if(n == 0) {
    //         List<Long> newList = new ArrayList<Long>();
    //         newList.add(stone);
    //         return newList;
    //     }
    //
    //
    //     if(stone == 0) {
    //         return recursiveDoThing(n-1, 1L);
    //     }
    //
    //
    //     String intString = String.valueOf(stone);
    //     if(intString.length() % 2 == 0) {
    //         List<Long> left = recursiveDoThing(n-1, Long.parseLong(intString.substring(0, intString.length()/2)));
    //         left.addAll(recursiveDoThing(n-1, Long.parseLong(intString.substring(intString.length()/2))));
    //
    //         return left;
    //
    //     }
    //
    //     return recursiveDoThing(n-1, stone * 2024);
    // }

}
