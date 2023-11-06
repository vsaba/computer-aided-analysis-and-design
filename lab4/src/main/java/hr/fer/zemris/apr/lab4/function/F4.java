package hr.fer.zemris.apr.lab4.function;

import hr.fer.zemris.apr.lab4.solution.VectorSolution;

public class F4 implements IFunction {

    private final int numOfVariables = 2;
    private int numOfFuncCalls = 0;


    @Override
    public int numOfVariables() {
        return numOfVariables;
    }

    @Override
    public int getNumOfFuncCalls() {
        return numOfFuncCalls;
    }

    @Override
    public double applyPoint(VectorSolution x) {
        double x1 = x.getValue(0);
        double x2 = x.getValue(1);

        numOfFuncCalls++;
        return Math.pow(x1 - 3, 2) + Math.pow(x2, 2);
    }
}
