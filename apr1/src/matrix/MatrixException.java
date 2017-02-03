package matrix;

public class MatrixException extends Exception {

    public MatrixException(String message) {
        super(message);
    }

    public static void vectorCheck(Matrix a, Matrix b) {
        if (b.m != a.m) try {
            throw new MatrixException("Slobodni vektor mora biti jednake duljine dimenziji kvadratne matrice");
        } catch (MatrixException e) {
            System.out.println(e.getMessage());
            System.out.println();
        }
    }

    public static void matrixDimensionsCheck(Matrix a, Matrix b) {
        if (a.m != b.m || a.n != b.n) try {
            throw new MatrixException("Matrice moraju biti istih dimenzija!");
        } catch (MatrixException e) {
            System.out.println(e.getMessage());
            System.out.println();

        }
    }

    public static void pivotCheck(double pivot) {
        if (Math.abs(pivot) <= Matrix.EPS) try {
            throw new MatrixException("Element " + pivot + " je približno jednak nuli!");
        } catch (MatrixException e) {
            System.out.println(e.getMessage());
            System.out.println();
//            System.exit(0);
        }
    }

    public static void multiplicationCheck(Matrix a, Matrix b) {
        if (a.n != b.m) {
            try {
                throw new MatrixException("Matrice moraju biti odgovarajuæih dimenzija (m,n) x (p,q)  n=p");
            } catch (MatrixException e) {
                System.out.println(e.getMessage());
                System.out.println();
//                System.exit(0);
            }
        }
    }

}
