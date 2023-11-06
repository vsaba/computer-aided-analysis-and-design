package hr.fer.zemris.apr.lab4.solution;

public class IntervalSolution {

    private double left;
    private double right;


    public IntervalSolution(double left, double right) {
        this.left = left;
        this.right = right;
    }

    public IntervalSolution() {
        this.left = 0;
        this.right = 0;
    }


    public double getRight() {
        return right;
    }

    public double getLeft() {
        return left;
    }

    public void setLeft(double left) {
        this.left = left;
    }

    public void setRight(double right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "[" + left +
                ", " + right +
                "]";
    }
}
