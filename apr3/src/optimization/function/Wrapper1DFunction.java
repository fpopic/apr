package optimization.function;

import matrix.Matrix;

public class Wrapper1DFunction extends AFunction {
    public Matrix x;
    public Matrix v;
    public AFunctionWithDerivates f;

    public Wrapper1DFunction(AFunctionWithDerivates f) {
        this.f = f;
    }

    @Override
    protected void initProperties() {
    }

    /**
     * Pri svakom pozivu funkcije vise varijabli sve cu koordinate pomaknuti za lambda*v
     * u svakoj iteraciji prije pozivanja zlatnog reza set-am x, v i onda wrappam valueAt
     *
     * @param lambda varijabla funkcije kao npr f(x) sam je x ovdje lambda
     * @return vrijednost pocetne funkcije za pomaknutu tocku x+lambda*v
     */
    @Override
    public double valueAt(double... lambda) {
        // xk = xk + v*lambda
        return f.valueAt(x.plus(v.times(lambda[0]))); //zapravo lambda nije polje neg sam jedan broj jer je 1d
    }
}
