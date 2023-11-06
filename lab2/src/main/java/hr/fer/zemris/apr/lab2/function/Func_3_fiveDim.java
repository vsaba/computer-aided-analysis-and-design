package hr.fer.zemris.apr.lab2.function;

import hr.fer.zemris.apr.lab2.solution.VectorSolution;

public class Func_3_fiveDim implements IFunction{

    private int numOfCalls = 0;

    @Override
    public double apply(VectorSolution xVector) {

        if(xVector.getDimension() != 5){
            throw new IllegalArgumentException("Invalid dimension");
        }

        numOfCalls++;
        return Math.pow(xVector.getValue(0) - 1, 2)
                + Math.pow(xVector.getValue(1) - 2, 2)
                + Math.pow(xVector.getValue(2) - 3, 2)
                + Math.pow(xVector.getValue(3) - 4, 2)
                + Math.pow(xVector.getValue(4) - 5, 2);
    }

    @Override
    public int getNumOfCalls() {
        return numOfCalls;
    }
}
