package hr.fer.zemris.apr.lab5.function;

import hr.fer.zemris.apr.lab5.solution.VectorSolution;

public class Zad1TrueFunc implements IFunction {

    private static Zad1TrueFunc instance = new Zad1TrueFunc();

    private Zad1TrueFunc() {

    }

    public static Zad1TrueFunc getInstance() {
        return instance;
    }

    @Override
    public VectorSolution applyT(VectorSolution x0, double t) {

        double x1 = x0.getValue(0) * Math.cos(t) + x0.getValue(1) * Math.sin(t);
        double x2 = x0.getValue(1) * Math.cos(t) - x0.getValue(0) * Math.sin(t);

        return new VectorSolution(x1, x2);
    }
}
