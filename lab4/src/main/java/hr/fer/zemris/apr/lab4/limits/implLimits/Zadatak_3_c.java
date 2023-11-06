package hr.fer.zemris.apr.lab4.limits.implLimits;

import hr.fer.zemris.apr.lab4.solution.VectorSolution;

public class Zadatak_3_c implements IImplLimit {

    @Override
    public boolean checkLimit(VectorSolution x) {
        if (x.getDimension() != 2) {
            throw new IllegalArgumentException("Dimenzija vektora mora biti 2");
        }

        double x2 = x.getValue(1);

        return x2 - 1 == 0;
    }

    @Override
    public double getLimitValue(VectorSolution x) {
        double x2 = x.getValue(1);

        return x2 - 1;
    }
}
