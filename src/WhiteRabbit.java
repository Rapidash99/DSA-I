import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;

public class WhiteRabbit {
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        Scanner input = new Scanner(new File("input.txt"));
        PrintWriter output = new PrintWriter("output.txt", "UTF-8");

        ArrayList<Integer> time = new ArrayList<>();
        ArrayList<Integer> price = new ArrayList<>();

        Integer budget = 0;

        while (input.hasNextInt()) {

            int temp = input.nextInt();

            if (input.hasNextInt()) {
                time.add(temp);
                price.add(input.nextInt());
            } else {
                budget = temp;
            }

        }

        BruteForce(time, price, budget, null, 0, 0);
        int Answer = BestCase;
        Integer BestGreedyCase = Greedy(time, price, budget);

        output.print(Answer);
        output.close();

    }

    private static Integer BestCase = 0;

    private static void BruteForce(ArrayList<Integer> time, ArrayList<Integer> price, Integer budget, boolean[] used, int curTime, Integer timeGot) {


        if (used == null) {
            used = new boolean[time.size()];
            BestCase = 0;
        }

        else {
            BestCase = Math.max(BestCase, timeGot);
        }

        for (int i = curTime; i < time.size(); i++) {
            if (price.get(i) > budget) {
                continue;
            }

            if (used[i]) {
                continue;
            }

            used[i] = true;
            BruteForce(time, price, budget - price.get(i), used, i + 1, timeGot + time.get(i));
            used[i] = false;
        }
    }

    private static Integer Greedy(ArrayList<Integer> time, ArrayList<Integer> price, Integer budget) {
        double[] worth = new double[time.size()];

        for (int i = 0; i < time.size(); i++) {
            worth[i] = time.get(i).doubleValue() / price.get(i);
        }

        //sorting by worth
        for (int i = 1; i < worth.length; i++) {
            int k = i;
            while (k > 0 && worth[k] > worth[k - 1]) {
                Tools.SwapIndoubleArray(k, k - 1, worth);
                Tools.SwapInIntegerArrayList(k, k - 1, price);
                Tools.SwapInIntegerArrayList(k, k - 1, time);
                k = k - 1;
            }
        }

        int BestCase = 0;
        int sumPrice = 0;

        

        for (int i = 0; i < time.size(); i++) {

            sumPrice += price.get(i);

            if (sumPrice <= budget) {
                BestCase += time.get(i);
            } else {
                sumPrice -= price.get(i);
            }
        }
        return BestCase;
    }

}
