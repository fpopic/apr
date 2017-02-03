package optimization.function;

import matrix.Matrix;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public abstract class AFunctionWithDerivates extends AFunction {

    public Map<String, Matrix> gradientMap;
    public Map<String, Matrix> laplaceMap;

    public int gradientCounter;
    public int laplaceCounter;

    public IFunction[] J;   // [ d1f/dx1 ... d1f/dxn]
    public IFunction[][] H; // [[d2f/dx1dx1... d2f/dxndxn] ... [d2f/dx1dx1 ... d2f/dxndxn]]

    public AFunctionWithDerivates() {
        super();
        this.gradientMap = new HashMap<>();
        this.laplaceMap = new HashMap<>();
        this.createJacobian();
        this.createHessian();
    }

    /**
     * Funkcija za tocku x izraèuna cijelu Jakobijevu matricu
     * prvih derivacija, tj. smjer gradijenta u tocki x.
     *
     * @param x pocetna tocka
     * @return vektor gradijenta u tocki X
     */
    public Matrix gradientAt(double... x) {
        if (gradientMap.containsKey(Arrays.toString(x))) {
            return gradientMap.get(Arrays.toString(x));
        }
        gradientCounter++;
        Matrix grad = new Matrix(1, J.length);
        for (int j = 0; j < J.length; j++) {
            grad.mat[0][j] = J[j].valueAt(x);
        }
        gradientMap.put(grad.toString(), grad);
        return grad;
    }

    /**
     * Funkcija za tocku x izraèuna cijelu Hesseovu matricu
     * drugih derivacija, tj. smjer laplaciana u tocki x.
     *
     * @param x pocetna tocka
     * @return vektor laplaciana u tocki X
     */
    public Matrix laplaceAt(double... x) {
        if (laplaceMap.containsKey(Arrays.toString(x))) {
            return laplaceMap.get(Arrays.toString(x));
        }
        laplaceCounter++;
        Matrix laplace = new Matrix(H.length, H[0].length);
        for (int i = 0; i < H.length; i++) {
            for (int j = 0; j < H[0].length; j++)
                laplace.mat[i][j] = H[i][j].valueAt(x);
        }
        laplaceMap.put(laplace.toString(), laplace);
        return laplace;
    }

    public abstract void createJacobian();

    public abstract void createHessian();

    public void resetCounters() {
        this.counter = 0;
        this.gradientCounter = 0;
        this.laplaceCounter = 0;
        this.valueMap = new HashMap<>();
        this.gradientMap = new HashMap<>();
        this.laplaceMap = new HashMap<>();
    }

    //region Wrappers

    public Matrix gradientAt(Matrix x) {
        return gradientAt(x.mat[0]);
    }

    public Matrix laplaceAt(Matrix x) {
        return laplaceAt(x.mat[0]);
    }


    //endregion

}
