import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        Integer[] input = new Integer[]{572556, 22, 0, 528, 4679021, 1, 10725, 2790};
        int blinks = 25;

        System.out.println(solution.solve(blinks, input));

    }
}
