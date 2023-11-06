package hr.fer.zemris.apr.lab5.system;

import hr.fer.zemris.apr.lab5.solution.Matrica;
import hr.fer.zemris.apr.lab5.solution.VectorSolution;

public class Zad2System implements ISystem {

    private static Zad2System instance = new Zad2System();

    private Zad2System() {
    }

    public static Zad2System getInstance() {
        return instance;
    }

    @Override
    public Matrica getA() {
        return new Matrica(new double[][]{
                {0, 1},
                {-200, -102}
        }, 2, 2);
    }

    @Override
    public Matrica getB() {
        return Matrica.getNullMatrix(2, 2);
    }

    @Override
    public VectorSolution getR(double t) {
        return VectorSolution.getNullVector(2);
    }

    @Override
    public VectorSolution applyPoint(VectorSolution x, double t) {
        return getA().mnozenje(x.getAsMatrix()).getVector(0);
    }
}