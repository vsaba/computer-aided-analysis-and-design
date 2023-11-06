package hr.fer.zemris.apr.lab2.function;

import hr.fer.zemris.apr.lab2.solution.VectorSolution;

public class RosenbrockFunction implements IFunction {

    private int numOfCalls;

    public RosenbrockFunction() {
        this.numOfCalls = 0;
    }

    @Override
    public double apply(VectorSolution xVector) {
        if(xVector.getDimension() != 2){
            throw new IllegalArgumentException("The required dimension is 2");
        }

        double x1 = xVector.getValue(0);
        double x2 = xVector.getValue(1);

        numOfCalls++;
        return 100 * Math.pow((x2 - Math.pow(x1, 2)), 2) + Math.pow(1 - x1, 2);
    }


    @Override
    public int getNumOfCalls() {
        return numOfCalls;
    }
}
