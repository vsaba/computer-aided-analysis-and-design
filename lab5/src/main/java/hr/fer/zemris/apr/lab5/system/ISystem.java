package hr.fer.zemris.apr.lab5.system;

import hr.fer.zemris.apr.lab5.solution.Matrica;
import hr.fer.zemris.apr.lab5.solution.VectorSolution;

public interface ISystem {

    Matrica getA();

    Matrica getB();

    VectorSolution getR(double t);

    VectorSolution applyPoint(VectorSolution x, double t);

}
