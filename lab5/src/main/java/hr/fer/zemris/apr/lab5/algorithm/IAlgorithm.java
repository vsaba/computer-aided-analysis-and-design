package hr.fer.zemris.apr.lab5.algorithm;

import hr.fer.zemris.apr.lab5.solution.VectorSolution;

public interface IAlgorithm {

    VectorSolution run(VectorSolution x0);

//    void setMaxIter(int maxIter);

    void setPredictedX(VectorSolution xk);

    void setT0(double t0);
}
