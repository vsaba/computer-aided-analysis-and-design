package hr.fer.zemris.apr.lab4.limits.explicitLimits;

import hr.fer.zemris.apr.lab4.solution.VectorSolution;

public class Zadatak_1_ogr implements IExpLimit {

    private final double upperLimit = 100;
    private final double lowerLimit = -100;

    @Override
    public double getUpperLimit() {
        return upperLimit;
    }

    @Override
    public double getLowerLimit() {
        return lowerLimit;
    }

    @Override
    public boolean checkLimit(VectorSolution x) {
        for (int i = 0; i < x.getDimension(); i++) {
            double x_i = x.getValue(i);

            if (x_i < -100 || x_i > 100) {
                return false;
            }
        }

        return true;
    }

}