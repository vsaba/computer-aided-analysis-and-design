package hr.fer.zemris.apr.lab3.function.states;

import hr.fer.zemris.apr.lab3.function.IFunction;
import hr.fer.zemris.apr.lab3.solution.VectorSolution;

public class DeltaXState implements IFunctionState {

    private VectorSolution deltaX;
    private VectorSolution x_0;
    private IFunction function;

    public DeltaXState(IFunction function, VectorSolution x_0) {
        this.x_0 = x_0;
        this.function = function;
    }

    @Override
    public void setFunction(IFunction function) {
        this.function = function;
    }

    @Override
    public double applyPointToLambdaFunction(double lambda) {
        VectorSolution x = x_0.add(deltaX.multiplyScalar(lambda));

        return this.function.applyPoint(x);
    }

    @Override
    public void setStartingVector(VectorSolution x_0) {
        this.x_0 = x_0;
    }

    @Override
    public void setDeltaX(VectorSolution deltaX) {
        this.deltaX = deltaX;
    }
}
