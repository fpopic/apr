import matrix.Matrix;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        //region moje
        Matrix LU_A = Matrix.load("src\\task\\LU_A.txt");
        Matrix LU = LU_A.lu();

        Matrix LUP_A = Matrix.load("src\\task\\LUP_A.txt");
        Matrix LUP = LUP_A.lup();

        Matrix L = Matrix.load("src\\task\\L.txt");
        Matrix b = Matrix.load("src\\task\\VectorB.txt");

        Matrix y = L.forwardSupstiton(b);
        Matrix.print(y, "y:");

        Matrix u = Matrix.load("src\\task\\U.txt");
        Matrix x = u.backwardSupstition(y);
        Matrix.print(x, "x:");
        //endregion

    }

}
