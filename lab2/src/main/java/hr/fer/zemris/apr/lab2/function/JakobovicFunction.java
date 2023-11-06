package hr.fer.zemris.apr.lab2.function;

import hr.fer.zemris.apr.lab2.solution.VectorSolution;

public class JakobovicFunction implements IFunction {

    private int numOfCalls;

    public JakobovicFunction() {
        this.numOfCalls = 0;
    }

    @Override
    public double apply(VectorSolution xVector) {
        if (xVector.getDimension() != 2) {
            throw new IllegalArgumentException("The required dimension is 2");
        }

        double x1 = xVector.getValue(0);
        double x2 = xVector.getValue(1);


        numOfCalls++;
        return Math.abs((x1 - x2) * (x1 + x2)) + Math.sqrt(Math.pow(x1, 2) + Math.pow(x2, 2));
    }


    @Override
    public int getNumOfCalls() {
        return numOfCalls;
    }
}
