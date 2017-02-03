package optimization.model;

import java.util.Arrays;
import java.util.Locale;

public class SolutionFloat {
    public double[] vector;
    public double f;

    public SolutionFloat(double[] vector) {
        super();
        this.vector = vector;
    }

    @Override
    public String toString() {
        return "{" +
                "f=" + String.format(Locale.getDefault(), "%1.2e", this.f) +
                ", x=" + Arrays.toString(vector) +
                "} ";

    }

}
