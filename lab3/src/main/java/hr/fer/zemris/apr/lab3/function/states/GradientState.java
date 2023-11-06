package hr.fer.zemris.apr.lab3.function.states;

import hr.fer.zemris.apr.lab3.function.IFunction;
import hr.fer.zemris.apr.lab3.solution.VectorSolution;

public class GradientState implements IFunctionState{

    private IFunction function;
    private VectorSolution x_0;

    public GradientState(IFunction function, VectorSolution x_0){
        this.function = function;
        this.x_0 = x_0;
    }

    @Override
    public void setFunction(IFunction function) {
        this.function = function;
    }

    @Override
    public double applyPointToLambdaFunction(double lambda) {
        VectorSolution gradient = this.function.getDerivative(this.x_0);

        VectorSolution x = x_0.subtract(gradient.multiplyScalar(lambda));

        return this.function.applyPoint(x);
    }

    @Override
    public void setStartingVector(VectorSolution x_0) {
        this.x_0 = x_0;
    }

    @Override
    public void setDeltaX(VectorSolution deltaX) {
        throw new IllegalArgumentException("This method is not implemented for this class");
    }
}
