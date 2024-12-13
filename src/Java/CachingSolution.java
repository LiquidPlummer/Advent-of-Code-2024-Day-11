import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class CachingSolution {

    private final BigInteger twentyTwentyFour = BigInteger.valueOf(2024);
    private final Map<BigInteger, BigInteger[]> cache;
    private BigInteger cacheHits;
    private BigInteger cacheMisses;

    public CachingSolution() {
        this.cache = new HashMap<>();
        cacheHits = BigInteger.ZERO;
        cacheMisses = BigInteger.ZERO;
        cache.put(BigInteger.ZERO, new BigInteger[]{BigInteger.ONE});
        cache.put(BigInteger.ONE, new BigInteger[]{twentyTwentyFour});
    }

    public long solve(int n, Integer[] problem){
        long solution = 0;
        for(int i = 0; i < problem.length; ++i) {
            solution += recurse(n, BigInteger.valueOf(problem[i]));
            System.out.println("Hits: " + cacheHits + ", misses: " + cacheMisses);
        }
        return solution;
    }

    private int recurse(int n, BigInteger stone) {
        //base case n == 0
        if(n == 0) {
            return 1;
        }

        BigInteger[] nextStep = blink(stone);
        if(nextStep.length == 1) {
            return recurse(n-1, nextStep[0]);
        }
        return recurse(n-1, nextStep[0]) + recurse(n-1, nextStep[1]);
    }



    private BigInteger[] blink(BigInteger stone) {
        if(cache.containsKey(stone)) {
            cacheHits = cacheHits.add(BigInteger.ONE);
            return cache.get(stone);
        }
        cacheMisses = cacheMisses.add(BigInteger.ONE);

        //behavior 1 - already pre-populated in cache.

        //behavior 2:
        String str = stone.toString();
        if(str.length() % 2 == 0) {
            BigInteger[] split = new BigInteger[2];
            split[0] = new BigInteger(str.substring(0, str.length()/2));
            split[1] = new BigInteger(str.substring(str.length()/2));

            cache.put(stone, split);
            return split;
        }

        //behavior 3:
        BigInteger[] product = new BigInteger[]{stone.multiply(twentyTwentyFour)};
        cache.put(stone, product);
        return product;
    }

}
