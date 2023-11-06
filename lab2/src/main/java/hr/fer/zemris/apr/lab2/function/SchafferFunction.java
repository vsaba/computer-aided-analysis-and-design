package hr.fer.zemris.apr.lab2.function;

import hr.fer.zemris.apr.lab2.solution.VectorSolution;

public class SchafferFunction implements IFunction {

    private int numOfCalls;

    public SchafferFunction() {
        this.numOfCalls = 0;
    }


    @Override
    public double apply(VectorSolution xVector) {

        double sum = 0;

        for (int i = 0; i < xVector.getDimension(); i++) {
            sum += Math.pow(xVector.getValue(i), 2);
        }

        numOfCalls++;
        return 0.5 + (Math.sin(Math.sqrt(sum)) * Math.sin(Math.sqrt(sum)) - 0.5) / (Math.pow(1 + 0.001 * sum, 2));
    }


    @Override
    public int getNumOfCalls() {
        return numOfCalls;
    }
}
