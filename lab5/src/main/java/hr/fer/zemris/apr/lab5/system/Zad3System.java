package hr.fer.zemris.apr.lab5.system;

import hr.fer.zemris.apr.lab5.solution.Matrica;
import hr.fer.zemris.apr.lab5.solution.VectorSolution;

public class Zad3System implements ISystem {

    private static Zad3System instance = new Zad3System();

    private Zad3System() {
    }

    public static Zad3System getInstance() {
        return instance;
    }

    @Override
    public Matrica getA() {
        return new Matrica(new double[][]{
                {0, -2},
                {1, -3}
        }, 2, 2);
    }

    @Override
    public Matrica getB() {
        return new Matrica(new double[][]{
                {2, 0},
                {0, 3}
        }, 2, 2);
    }

    @Override
    public VectorSolution getR(double t) {
        return new VectorSolution(1, 1);
    }

    @Override
    public VectorSolution applyPoint(VectorSolution x, double t) {
        return getA().mnozenje(x.getAsMatrix()).zbrajanje(getB().mnozenje(getR(t).getAsMatrix())).getVector(0);
    }
}