package hr.fer.zemris.apr.lab3.function.states;

import hr.fer.zemris.apr.lab3.function.IFunction;
import hr.fer.zemris.apr.lab3.solution.VectorSolution;

public interface IFunctionState {

    void setFunction(IFunction function);
    double applyPointToLambdaFunction(double lambda);
    void setStartingVector(VectorSolution x_0);
    void setDeltaX(VectorSolution deltaX);
}
