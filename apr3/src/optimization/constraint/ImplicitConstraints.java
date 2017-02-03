package optimization.constraint;

import matrix.Matrix;

import java.util.List;

import static optimization.constraint.ConstraintType.INEQUALITY;

public class ImplicitConstraints {

    public List<IConstraint> constraints;
    public List<ConstraintType> types;

    public ImplicitConstraints(List<IConstraint> constraints, List<ConstraintType> types) {
        this.constraints = constraints;
        this.types = types;
    }

    // ako svi iz liste true onda je ukupno true
    public boolean test(Matrix x) {
        for (int i = 0; i < constraints.size(); i++) {
            IConstraint gi = constraints.get(i);
            ConstraintType ti = types.get(i);
            if (ti == INEQUALITY) {
                if (gi.valueAt(x.mat[0]) < 0) return false;
            }
            else {
                if (gi.valueAt(x.mat[0]) != 0) return false;
            }
        }
        return true;
    }

}

