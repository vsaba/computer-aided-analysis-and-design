package hr.fer.zemris.apr.lab5.system;

import hr.fer.zemris.apr.lab5.solution.Matrica;
import hr.fer.zemris.apr.lab5.solution.VectorSolution;

public class Zad4System implements ISystem {

    private static Zad4System instance = new Zad4System();

    private Zad4System() {
    }

    public static Zad4System getInstance() {
        return instance;
    }

    @Override
    public Matrica getA() {
        return new Matrica(new double[][]{
                {1, -5},
                {1, -7}
        }, 2, 2);
    }

    @Override
    public Matrica getB() {
        return new Matrica(new double[][]{
                {5, 0},
                {0, 3}
        }, 2, 2);
    }

    @Override
    public VectorSolution getR(double t) {
        return new VectorSolution(1, 1).multiplyScalar(t);
    }

    @Override
    public VectorSolution applyPoint(VectorSolution x, double t) {
        return getA().mnozenje(x.getAsMatrix()).zbrajanje(getB().mnozenje(getR(t).getAsMatrix())).getVector(0);
    }
}