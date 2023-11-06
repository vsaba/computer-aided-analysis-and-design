package hr.fer.zemris.apr.lab2.function;

import hr.fer.zemris.apr.lab2.solution.VectorSolution;

public class OneDimFunction implements IFunction{

    private IFunction function;
    private int dimensionIndex;
    private VectorSolution solution;

    public OneDimFunction(IFunction function, int dimensionIndex, VectorSolution solution){
        this.function = function;
        this.dimensionIndex = dimensionIndex;
        this.solution = solution;
    }

    @Override
    public double apply(VectorSolution xVector) {

        if(xVector.getDimension() != 1){
            throw new IllegalArgumentException("The required dimension for this function is 1");
        }

        solution.setValue(dimensionIndex, xVector.getValue(0));

        return function.apply(solution);
    }

    @Override
    public int getNumOfCalls() {
        return function.getNumOfCalls();
    }
}
