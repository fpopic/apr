package optimization.misc;

import java.util.Arrays;
import java.util.Locale;

public class ResultHelper {

    public static double median(double[] results) {
        Arrays.sort(results);
        int middle = results.length / 2;
        if (results.length % 2 == 1) {
            return results[middle];
        }
        else {
            return (results[middle - 1] + results[middle]) / 2.0;
        }
    }

    public static int score(double[] results, double eps) {
        int score = 0;
        for (double result : results) {
            if (result <= eps) {
                score++;
            }
        }
        return score;
    }

    public static String scientific(double... results) {
        StringBuilder sb = new StringBuilder();
        if (results.length > 1) {
            sb.append("[");
        }

        for (double result : results) {
            sb.append(String.format(Locale.getDefault(), "%1.0e", result)).append(", ");
        }
        sb.replace(sb.length() - ", ".length(), sb.length(), "");

        if (results.length > 1) {
            sb.append("]");
        }
        return sb.toString();
    }

}
