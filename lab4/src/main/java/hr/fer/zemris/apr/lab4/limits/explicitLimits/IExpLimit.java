package hr.fer.zemris.apr.lab4.limits.explicitLimits;

import hr.fer.zemris.apr.lab4.solution.VectorSolution;

public interface IExpLimit {

    double getUpperLimit();

    double getLowerLimit();

    boolean checkLimit(VectorSolution x);

}
