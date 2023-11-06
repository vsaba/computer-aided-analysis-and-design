package hr.fer.zemris.apr.lab4.limits.implLimits;

import hr.fer.zemris.apr.lab4.solution.VectorSolution;

public interface IImplLimit {

    boolean checkLimit(VectorSolution x);
    double getLimitValue(VectorSolution x);
}
