package hr.fer.zemris.apr.lab2.function;

import hr.fer.zemris.apr.lab2.solution.VectorSolution;

public class Func_3_onedim implements IFunction {

    private int numOfCalls;

    public Func_3_onedim() {
        this.numOfCalls = 0;
    }

    @Override
    public double apply(VectorSolution xVector) {
        if(xVector.getDimension() > 1){
            throw new IllegalArgumentException("This is a 1-D function");
        }

        numOfCalls++;
        return Math.pow(xVector.getValue(0) - 3, 2);
    }

    @Override
    public int getNumOfCalls() {
        return numOfCalls;
    }
}
