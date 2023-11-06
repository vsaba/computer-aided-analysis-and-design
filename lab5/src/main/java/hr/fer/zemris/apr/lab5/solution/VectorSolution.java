package hr.fer.zemris.apr.lab5.solution;

import java.util.Arrays;

public class VectorSolution {

    private double[] x;


    public VectorSolution(double... x) {
        this.x = x;
    }

    public double getValue(int index) {
        if (index < 0 || index >= x.length) {
            throw new IllegalArgumentException("Index out of range for array");
        }

        return x[index];
    }

    public void setValue(int index, double value) {
        if (index < 0 || index >= x.length) {
            throw new IllegalArgumentException("Index out of range for array");
        }

        x[index] = value;

        return;
    }

    public VectorSolution add(VectorSolution y) {
        if (x.length != y.getDimension()) {
            throw new IllegalArgumentException("The dimensions of the vector dont match");
        }

        double[] newVector = Arrays.copyOf(x, x.length);
        for (int i = 0; i < x.length; i++) {
            newVector[i] += y.getValue(i);
        }

        return new VectorSolution(newVector);
    }

    public VectorSolution addEquals(VectorSolution y) {
        if (x.length != y.getDimension()) {
            throw new IllegalArgumentException("The dimensions of the vector dont match");
        }

        for (int i = 0; i < x.length; i++) {
            x[i] += y.getValue(i);
        }

        return this;
    }

    public VectorSolution addScalar(double scalar) {
        double[] newVector = Arrays.copyOf(x, x.length);

        for (int i = 0; i < x.length; i++) {
            newVector[i] += scalar;
        }

        return new VectorSolution(newVector);
    }

    public VectorSolution addScalarEquals(double scalar) {

        for (int i = 0; i < x.length; i++) {
            x[i] += scalar;
        }

        return this;
    }

    public VectorSolution multiplyScalar(double scalar) {
        double[] newVector = Arrays.copyOf(x, x.length);

        for (int i = 0; i < x.length; i++) {
            newVector[i] *= scalar;
        }

        return new VectorSolution(newVector);
    }

    public VectorSolution multiplyScalarEquals(double scalar) {
        for (int i = 0; i < x.length; i++) {
            x[i] *= scalar;
        }
        return this;
    }

    public VectorSolution subtract(VectorSolution y) {
        if (x.length != y.getDimension()) {
            throw new IllegalArgumentException("The dimensions of the vector dont match");
        }

        return add(y.multiplyScalar(-1));
    }

    public VectorSolution subtractEquals(VectorSolution y) {
        if (x.length != y.getDimension()) {
            throw new IllegalArgumentException("The dimensions of the vector dont match");
        }

        addEquals(y.multiplyScalar(-1));

        return this;
    }


    public double norm() {
        double sum = 0;
        for (int i = 0; i < x.length; i++) {
            sum += Math.pow(x[i], 2);
        }

        return Math.sqrt(sum);
    }

    public int getDimension() {
        return x.length;
    }

    public VectorSolution getCopy() {
        double[] newX = Arrays.copyOf(x, x.length);


        return new VectorSolution(newX);
    }

    public double[] getAsDoubleArray() {
        return Arrays.copyOf(this.x, this.x.length);
    }

    public Matrica getAsMatrix() {
        return new Matrica(Arrays.copyOf(this.x, x.length), x.length, 1);
    }

    public static VectorSolution getNullVector(int size) {
        return new VectorSolution(new double[size]);
    }

    @Override
    public String toString() {
        return Arrays.toString(x);
    }
}
