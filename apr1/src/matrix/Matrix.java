package matrix;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import static matrix.MatrixException.*;


public class Matrix {

    //region Members

    public int m; //retci
    public int n; //stupci
    public double[][] mat;
    public static double EPS = 10e-9;
    public static int[] P;

    //endregion

    //region Constructors

    public Matrix(double[][] mat) {
        this.m = mat.length;
        this.n = mat[0].length;
        this.mat = new double[m][n];
        for (int i = 0; i < mat.length; i++) {
            System.arraycopy(mat[i], 0, this.mat[i], 0, mat[0].length);
        }
    }

    public Matrix(int m, int n) {
        //zero matrix of mxn
        this.m = m;
        this.n = n;
        this.mat = new double[m][n];
    }

    //endregion

    //region IO methods

    public static Matrix load(String path) throws IOException {
        final BufferedReader br = new BufferedReader(new FileReader(path));
        List<String[]> lines = new ArrayList<>();

        String line;
        while ((line = br.readLine()) != null) {
            lines.add(line.split(" "));
        }
        br.close();

        int m_load = lines.size();
        int n_load = lines.get(0).length;
        double[][] mat_load = new double[m_load][n_load];

        String[] row;
        for (int i = 0; i < m_load; i++) {
            row = lines.get(i);
            for (int j = 0; j < n_load; j++) {
                mat_load[i][j] = Double.parseDouble(row[j]);
            }
        }
        return new Matrix(mat_load);
    }

    public static void save(Matrix matrix, String path) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < matrix.mat.length; i++) {
            double[] row = matrix.mat[i];
            for (int j = 0; j < row.length; j++) {
                if ((int) row[j] == row[j]) {
                    sb.append(Integer.valueOf((int) row[j]));
                } else {
                    //String.format(Locale.getDefault(), "%.2f",
                    sb.append(row[j]);
                }
                if (j != row.length - 1) {
                    sb.append(" ");
                }
            }
            if (i != matrix.mat.length - 1) {
                sb.append(System.lineSeparator());
            }
        }

        final BufferedWriter bw = new BufferedWriter(new FileWriter(path));
        bw.write(sb.toString());
        bw.close();
    }

    public static void print(Matrix matrix, String title) {
        if (title != null) {
            System.out.println(title);
        }
        System.out.println(matrix.toString());
        System.out.println();
    }

    //endregion

    //region Java methods

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.m != 1) {
            sb.append("[");
        }
        for (double[] row : mat) {
            sb.append("[");
            for (int j = 0; j < row.length; j++) {
                sb.append(String.format(Locale.getDefault(), "%+.10f", row[j]));
                if (j != row.length - 1) {
                    sb.append(" ");
                } else {
                    sb.append("]");
                }
            }
        }
        if (this.m != 1) {
            sb.append("]");
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Matrix)) return false;

        Matrix matrix = (Matrix) o;

        if (m != matrix.m) return false;
        if (n != matrix.n) return false;
        return Arrays.deepEquals(mat, matrix.mat);

    }

    @Override
    public int hashCode() {
        int result = m;
        result = 31 * result + n;
        result = 31 * result + Arrays.deepHashCode(mat);
        return result;
    }

    //endregion

    //region Matrix methods

    // +
    public Matrix plus(Matrix matrix) {
        matrixDimensionsCheck(this, matrix);
        Matrix newMatrix = new Matrix(this.m, this.n);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                newMatrix.mat[i][j] = this.mat[i][j] + matrix.mat[i][j];
            }
        }
        return newMatrix;
    }

    // -
    public Matrix minus(Matrix matrix) {
        matrixDimensionsCheck(this, matrix);
        Matrix newMatrix = new Matrix(this.m, this.n);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                newMatrix.mat[i][j] = this.mat[i][j] - matrix.mat[i][j];
            }
        }
        return newMatrix;
    }

    // * scalar
    public Matrix times(double scalar) {
        Matrix newMatrix = new Matrix(this.m, this.n);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                newMatrix.mat[i][j] = this.mat[i][j] * scalar;
            }
        }
        return newMatrix;
    }

    // transponse
    public Matrix T() {
        double[][] transponsed = new double[n][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                transponsed[j][i] = this.mat[i][j];
            }
        }
        return new Matrix(transponsed);
    }

    // matrix multiplication
    public Matrix multiply(Matrix matrix) {
        //A(m,n) x B(p,q)   n==p    C(m,q)
        multiplicationCheck(this, matrix);
        Matrix newMatrix = new Matrix(this.m, matrix.n);
        for (int i = 0; i < m; i++) { //po prvoj sve retke
            for (int j = 0; j < matrix.n; j++) { //po drugoj sve stupce
                for (int k = 0; k < matrix.m; k++) { //elementi koje zbrajam
                    newMatrix.mat[i][j] += mat[i][k] * matrix.mat[k][j];
                }
            }
        }
        return newMatrix;
    }

    // distance from intercept
    public static double euclideanNorm(Matrix x) {
        double sum = 0.0;
        for (int i = 0; i < x.n; i++) {
            sum += pow(x.mat[0][i], 2);
        }
        return sqrt(sum);
    }

    //endregion

    //region Matrix Decompositions

    public static Matrix permuteRows(Matrix b) {
        Matrix permutatedB = new Matrix(b.m, b.n);
        for (int i = 0; i < P.length; i++) {
            permutatedB.mat[i] = b.mat[P[i]];
        }
        return permutatedB;
    }

    public Matrix forwardSupstiton(Matrix vector) {
        Matrix b = new Matrix(vector.mat);
        vectorCheck(this, b);
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                b.mat[j][0] -= this.mat[j][i] * b.mat[i][0];
            }
        }
        return b;
    }

    public Matrix backwardSupstition(Matrix vector) {
        Matrix b = new Matrix(vector.mat);
        vectorCheck(this, b);
        for (int i = n - 1; i >= 0; i--) {
            pivotCheck(this.mat[i][i]);
            b.mat[i][0] /= this.mat[i][i];
            for (int j = 0; j < i; j++) {
                b.mat[j][0] -= this.mat[j][i] * b.mat[i][0];
            }
        }
        return b;
    }

    public Matrix lu() {
        Matrix A = new Matrix(this.mat);
        for (int i = 0; i < n - 1; i++) {
            print(A, "Korak " + i + ":");
            for (int j = i + 1; j < n; j++) {
                pivotCheck(A.mat[i][i]);
                A.mat[j][i] /= A.mat[i][i]; //dijelim s pivotom
                for (int k = i + 1; k < n; k++) {
                    A.mat[j][k] -= A.mat[j][i] * A.mat[i][k];
                }
            }
        }
        print(A, "LU:");
        return A;
    }

    public Matrix lup() {
        Matrix A = new Matrix(this.mat);
        int pivot;

        P = new int[A.n];
        for (int i = 0; i < P.length; i++) {
            P[i] = i;
        }

        for (int i = 0; i < n; i++) {
            //print(A, "Korak " + i + ":");
            //todo print(A, "Korak " + i + ":");
            // odabir pivota unutar stupca
            pivot = i;
            for (int j = i + 1; j < n; j++) {
                if (Math.abs(A.mat[j][i]) > Math.abs(A.mat[pivot][i])) {
                    pivot = j;
                }
            }

            //region zamijeni retke
            double[] tmp = A.mat[i];
            A.mat[i] = A.mat[pivot];
            A.mat[pivot] = tmp;

            int t = P[i];
            P[i] = P[pivot];
            P[pivot] = t;
            //endregion

            pivotCheck(A.mat[i][i]);

            for (int j = i + 1; j < n; j++) {
                A.mat[j][i] /= A.mat[i][i];
                for (int k = i + 1; k < n; k++) {
                    A.mat[j][k] -= A.mat[j][i] * A.mat[i][k];
                }
            }
        }
        //print(A, "LUP:");
        //todo print(A, "LUP:");
        return A;
    }

    //endregion

    //region Matrix inverse

    public static Matrix inverse(Matrix A) throws IOException {
        // A = matrica sustava
        // b = I jedinicna matrica
        // x = A^-1 rjesenje sustava
        Matrix x = new Matrix(A.m, A.n);
        Matrix b = createEye(A.m);

        // 1 LUP
        Matrix LUP = A.lup();
        b = Matrix.permuteRows(b);

        // n unaprijed, n unazad
        for (int j = 0; j < A.n; j++) {
            Matrix bColumn = getColumn(j, b);
            Matrix yColumn = LUP.forwardSupstiton(bColumn);
            Matrix xColumn = LUP.backwardSupstition(yColumn);
            x = replaceColumn(j, xColumn, x);
        }
        return x;
    }

    public static Matrix createEye(int n) {
        Matrix eye = new Matrix(n, n);
        for (int i = 0; i < eye.m; i++) {
            for (int j = 0; j < eye.n; j++) {
                if (i == j) {
                    eye.mat[i][j] = 1;
                }
            }
        }
        return eye;
    }

    public static Matrix getColumn(int j, Matrix matrix) {
        Matrix column = new Matrix(matrix.m, 1);
        for (int row = 0; row < matrix.m; row++) {
            for (int col = 0; col < matrix.n; col++) {
                if (col == j) {
                    column.mat[row][0] = matrix.mat[row][col];
                }
            }
        }
        return column;
    }

    public static Matrix replaceColumn(int j, Matrix column, Matrix matrix) {
        Matrix matrixSet = new Matrix(matrix.mat);
        for (int row = 0; row < matrix.m; row++) {
            for (int col = 0; col < matrix.n; col++) {
                if (col == j) {
                    matrixSet.mat[row][col] = column.mat[row][0];
                }
            }
        }
        return matrixSet;
    }

    //endregion

}