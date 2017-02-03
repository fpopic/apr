package optimization.constraint;

import java.util.ArrayList;
import java.util.List;

import static optimization.constraint.ConstraintType.EQUALITY;
import static optimization.constraint.ConstraintType.INEQUALITY;

public class Constraints {

    public static ExplicitConstraints explicits() {
        return new ExplicitConstraints(-100, +100);
    }

    public static ImplicitConstraints implicits12() {
        List<IConstraint> g = new ArrayList<>();
        List<ConstraintType> t = new ArrayList<>();

        g.add(x -> {
                    double x2 = x[1];
                    double x1 = x[0];
                    return x2 - x1;
                }
        );
        t.add(INEQUALITY);

        g.add(x -> {
                    double x1 = x[0];
                    return 2 - x1;
                }
        );
        t.add(INEQUALITY);

        assert t.size() == g.size();
        return new ImplicitConstraints(g, t);
    }

    public static ImplicitConstraints implicits4() {
        List<IConstraint> g = new ArrayList<>();
        List<ConstraintType> t = new ArrayList<>();

        g.add(x -> {
                    double x2 = x[1];
                    double x1 = x[0];
                    return 3 - x1 - x2;
                }
        );
        t.add(INEQUALITY);

        g.add(x -> {
                    double x2 = x[1];
                    double x1 = x[0];
                    return 3 + 1.5 * x1 - x2;
                }
        );
        t.add(INEQUALITY);

        g.add(x -> {
                    double x2 = x[1];
                    return x2 - 1;
                }
        );
        t.add(EQUALITY);

        assert t.size() == g.size();
        return new ImplicitConstraints(g, t);
    }
}