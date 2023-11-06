package hr.fer.zemris.apr.lab4.function;

import hr.fer.zemris.apr.lab4.solution.VectorSolution;

public class F2 implements IFunction {

    private final int numOfVariables = 2;
    private int numOfFunCalls = 0;


    @Override
    public int numOfVariables() {
        return numOfVariables;
    }

    @Override
    public int getNumOfFuncCalls() {
        return numOfFunCalls;
    }

    @Override
    public double applyPoint(VectorSolution x) {
        double x1 = x.getValue(0);
        double x2 = x.getValue(1);

        numOfFunCalls++;
        return Math.pow(x1 - 4, 2) + 4 * Math.pow(x2 - 2, 2);
    }
}
