package optimization.constraint;

import matrix.Matrix;

public class ExplicitConstraints {
    public double d;
    public double g;

    public ExplicitConstraints(double d, double g) {
        this.d = d;
        this.g = g;
    }

    public boolean test(Matrix x) {
        for (int j = 0; j < x.mat[0].length; j++) {
            if (x.mat[0][j] < d || x.mat[0][j] > g) return false;
        }
        return true;
    }

}

