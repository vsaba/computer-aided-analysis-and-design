package hr.fer.zemris.apr.lab4.limits.implLimits;

import hr.fer.zemris.apr.lab4.solution.VectorSolution;

public class Zadatak_1_b implements IImplLimit {

    @Override
    public boolean checkLimit(VectorSolution x) {
        if (x.getDimension() != 2) {
            throw new IllegalArgumentException("Dimenzija vektora mora biti 2");
        }

        double x1 = x.getValue(0);

        return 2 - x1 >= 0;
    }

    @Override
    public double getLimitValue(VectorSolution x) {

        double x1 = x.getValue(0);

        return 2 - x1;
    }

}
