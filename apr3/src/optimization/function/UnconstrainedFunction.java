package optimization.function;

import optimization.constraint.ConstraintType;
import optimization.constraint.IConstraint;
import optimization.constraint.ImplicitConstraints;

import static java.lang.Math.log;
import static java.lang.Math.pow;
import static optimization.constraint.ConstraintType.EQUALITY;
import static optimization.constraint.ConstraintType.INEQUALITY;

public class UnconstrainedFunction extends AFunction {

    public double t;
    public AFunctionWithDerivates f;
    public ImplicitConstraints imp;

    public UnconstrainedFunction(double t, AFunctionWithDerivates f, ImplicitConstraints imp) {
        this.t = t;
        this.f = f;
        this.imp = imp;
        this.x0 = f.getX0();
        this.xMin = f.getxMin();
        this.name = "U(x,t) za " + f.toString();
    }

    @Override
    protected void initProperties() {
    }

    @Override
    public double valueAt(double... x) {
        double uValue = f.valueAt(x);

        for (int i = 0; i < imp.constraints.size(); i++) {
            IConstraint gi = imp.constraints.get(i);
            ConstraintType ti = imp.types.get(i);
            double gValue = gi.valueAt(x);

            if (ti == INEQUALITY) {
                if (gValue >= 0) {
                    uValue -= (1 / t) * log(gValue); //ako gi(x) nije zadovoljena treba ograniciti
                }
                if (gValue < 0) {
                    return Double.POSITIVE_INFINITY;
                }
            }
            if (ti == EQUALITY) {
                if (gValue != 0) { //ako hi(x) nije zadovoljena treba ograniciti
                    uValue += (t) * pow(gValue, 2);
                }
            }

        }
        return uValue;
    }
}
