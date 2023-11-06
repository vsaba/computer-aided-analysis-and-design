package hr.fer.zemris.apr.lab3;

import hr.fer.zemris.apr.lab3.algorithm.GaussNewton;
import hr.fer.zemris.apr.lab3.algorithm.GradijentniSpust;
import hr.fer.zemris.apr.lab3.algorithm.IAlgorithm;
import hr.fer.zemris.apr.lab3.algorithm.NewtonRaphson;
import hr.fer.zemris.apr.lab3.function.*;
import hr.fer.zemris.apr.lab3.function.states.DeltaXState;
import hr.fer.zemris.apr.lab3.function.states.GradientState;
import hr.fer.zemris.apr.lab3.function.states.IFunctionState;
import hr.fer.zemris.apr.lab3.solution.VectorSolution;

public class Main {

    public static void main(String[] args) {
//        zadatak1();
//        zadatak2();
//        zadatak3();
//        zadatak4();
//        zadatak5();
//        zadatak6();
        optimizeAllFunctions(true);
    }


    private static void runAlgorithms(IFunction function, IFunctionState state, VectorSolution x_0, boolean optimizeLambda) {
        IAlgorithm gradijentniSpust = new GradijentniSpust(function, state, x_0, optimizeLambda);
        VectorSolution x = gradijentniSpust.run();
        System.out.println("Rjesenje gradijentnog spusta: x = " + x + "; F(x) = " + function.applyPoint(x));


        IAlgorithm newtonRaphson = new NewtonRaphson(function, state, x_0, optimizeLambda);
        x = newtonRaphson.run();
        System.out.println("Rjesenje Newton - Raphson algoritma: x = " + x + "; F(x) = " + function.applyPoint(x));

        IAlgorithm gaussNewton = new GaussNewton(function, state, x_0, optimizeLambda);
        x = gaussNewton.run();
        System.out.println("Rjesenje Gauss - Newton algoritma: x = " + x + "; F(x) = " + function.applyPoint(x));
    }


    private static void zadatak1() {
        IFunction function = new F3();
        VectorSolution x_0 = new VectorSolution(0, 0);
        IFunctionState state = new GradientState(function, x_0);

        IAlgorithm algorithm = new GradijentniSpust(function, state, x_0, false);
        VectorSolution x = algorithm.run();

        System.out.println("\nRjesenje gradijentni spust bez optimizacije lambda:");
        System.out.println("x = " + x + "; F(x) = " + function.applyPoint(x));


        algorithm = new GradijentniSpust(function, state, x_0, true);
        x = algorithm.run();

        System.out.println("\nRjesenje gradijentni spust sa optimizacijom lambda:");
        System.out.println("x = " + x + "; F(x) = " + function.applyPoint(x));

    }


    private static void zadatak2() {
        IFunction function = new RosenbrockFunction();
        VectorSolution x_0 = new VectorSolution(-1.9, 2);
        IFunctionState state = new GradientState(function, x_0);

        IAlgorithm algorithm = new GradijentniSpust(function, state, x_0, true);
        VectorSolution x = algorithm.run();

        System.out.println("\nGradijentni spust za funkciju 1: ");
        System.out.println("x = " + x + "; F(x) = " + function.applyPoint(x));

        function = new RosenbrockFunction();
        x_0 = new VectorSolution(-1.9, 2);
        state = new DeltaXState(function, x_0);

        algorithm = new NewtonRaphson(function, state, x_0, true);
        x = algorithm.run();

        System.out.println("\nNewton - Raphson za funkciju 1: ");
        System.out.println("x = " + x + "; F(x) = " + function.applyPoint(x));


        //////////////////////////////////////////////////////////////////////


        function = new F2();
        x_0 = new VectorSolution(0.1, 0.3);
        state = new GradientState(function, x_0);

        algorithm = new GradijentniSpust(function, state, x_0, true);
        x = algorithm.run();

        System.out.println("\nGradijentni spust za funkciju 2:");
        System.out.println("x = " + x + "; F(x) = " + function.applyPoint(x));

        function = new F2();
        x_0 = new VectorSolution(0.1, 0.3);
        state = new DeltaXState(function, x_0);

        algorithm = new NewtonRaphson(function, state, x_0, true);
        x = algorithm.run();

        System.out.println("\nNewton - Raphson za funkciju 2: ");
        System.out.println("x = " + x + "; F(x) = " + function.applyPoint(x));

    }

    private static void zadatak3() {

        IFunction function = new F4();
        VectorSolution x_0 = new VectorSolution(3, 3);
        IFunctionState state = new DeltaXState(function, x_0);

        IAlgorithm algorithm = new NewtonRaphson(function, state, x_0, false);
        VectorSolution x = algorithm.run();

        System.out.println("\nNewton-Raphson za funkciju 4 bez optimiranja lambde; x0 = (3, 3): ");
        System.out.println("x = " + x + "; F(x) = " + function.applyPoint(x));

        function = new F4();
        x_0 = new VectorSolution(1, 2);
        state = new DeltaXState(function, x_0);

        algorithm = new NewtonRaphson(function, state, x_0, false);
        x = algorithm.run();

        System.out.println("\nNewton-Raphson za funkciju 4 bez optimiranja lambda; x0 = (1, 2) :");
        System.out.println("x = " + x + "; F(x) = " + function.applyPoint(x));

        ////////////////////////////////////////////////////////////////////////

        function = new F4();
        x_0 = new VectorSolution(3, 3);
        state = new DeltaXState(function, x_0);

        algorithm = new NewtonRaphson(function, state, x_0, true);
        x = algorithm.run();

        System.out.println("\nNewton-Raphson za funkciju 4 uz optimiranja lambda; x0 = (1, 2) :");
        System.out.println("x = " + x + "; F(x) = " + function.applyPoint(x));


        function = new F4();
        x_0 = new VectorSolution(1, 2);
        state = new DeltaXState(function, x_0);

        algorithm = new NewtonRaphson(function, state, x_0, true);
        x = algorithm.run();

        System.out.println("\nNewton-Raphson za funkciju 4 uz optimiranja lambda; x0 = (1, 2) :");
        System.out.println("x = " + x + "; F(x) = " + function.applyPoint(x));

    }

    private static void zadatak4() {
        IFunction function = new RosenbrockFunction();
        VectorSolution x_0 = new VectorSolution(-1.9, 2);
        IFunctionState state = new DeltaXState(function, x_0);

        IAlgorithm algorithm = new GaussNewton(function, state, x_0, true);
        VectorSolution x = algorithm.run();

        System.out.println("\nGauss - Newton za funkciju 1 uz optimiranje lambda: ");
        System.out.println("x = " + x + "; F(x) = " + function.applyPoint(x));
    }

    private static void zadatak5() {
        IFunction function = new Zadatak_5_func();
        VectorSolution x_0 = new VectorSolution(-2, 2);
        IFunctionState state = new DeltaXState(function, x_0);

        IAlgorithm algorithm = new GaussNewton(function, state, x_0, true);
        VectorSolution x = algorithm.run();

        System.out.println("\nGauss - Newton za funkciju zadanu u 5. zadataku: ");
        System.out.println("x_0 = " + x_0 + "; x = " + x + "; F(x) = " + function.applyPoint(x));

        function = new Zadatak_5_func();
        x_0 = new VectorSolution(2, 2);
        state = new DeltaXState(function, x_0);

        algorithm = new GaussNewton(function, state, x_0, true);
        x = algorithm.run();

        System.out.println("\nGauss - Newton za funkciju zadanu u 5. zadataku: ");
        System.out.println("x_0 = " + x_0 + "; x = " + x + "; F(x) = " + function.applyPoint(x));

        function = new Zadatak_5_func();
        x_0 = new VectorSolution(2, -2);
        state = new DeltaXState(function, x_0);

        algorithm = new GaussNewton(function, state, x_0, true);
        x = algorithm.run();

        System.out.println("\nGauss - Newton za funkciju zadanu u 5. zadataku: ");
        System.out.println("x_0 = " + x_0 + "; x = " + x + "; F(x) = " + function.applyPoint(x));

    }

    private static void zadatak6() {
        IFunction function = new Zadatak_6_func();
        VectorSolution x_0 = new VectorSolution(1, 1, 1);
        IFunctionState deltaState = new DeltaXState(function, x_0);

        IAlgorithm gaussNewton = new GaussNewton(function, deltaState, x_0, true);
        VectorSolution x = gaussNewton.run();

        System.out.println("\nGauss - Newton za funkciju zadanu u 6. zadataku: ");
        System.out.println("x = " + x + "; F(x) = " + function.applyPoint(x));

    }

    private static void optimizeAllFunctions(boolean optimizeLambda) {

        System.out.println("Funckija 1: ");

        IFunction function = new RosenbrockFunction();
        VectorSolution x_0 = new VectorSolution(-1.9, 2);
        IFunctionState deltaState = new DeltaXState(function, x_0);
        IFunctionState gradientState = new GradientState(function, x_0);

        IAlgorithm gradientDescent = new GradijentniSpust(function, gradientState, x_0, optimizeLambda);
        VectorSolution x = gradientDescent.run();

        System.out.println("Gradijentni spust za 1. funkciju: x_0 = " + x_0 + "; x = " + x + "; F(x) = " + function.applyPoint(x));

        IAlgorithm newtonRaphson = new NewtonRaphson(function, deltaState, x_0, optimizeLambda);
        x = newtonRaphson.run();
        System.out.println("Newton - Raphson za 1. funkciju: x_0 = " + x_0 + "; x = " + x + "; F(x) = " + function.applyPoint(x));

        IAlgorithm gaussNewton = new GaussNewton(function, deltaState, x_0, optimizeLambda);
        x = gaussNewton.run();
        System.out.println("Gauss - Newton za 1. funkciju: x_0 = " + x_0 + "; x = " + x + "; F(x) = " + function.applyPoint(x));
        System.out.println();

        /////////////////////////////////////////////////////////////////////////////////////////////

        System.out.println("Funckija 2: ");
        function = new F2();
        x_0 = new VectorSolution(0.1, 0.3);
        deltaState = new DeltaXState(function, x_0);
        gradientState = new GradientState(function, x_0);

        gradientDescent = new GradijentniSpust(function, gradientState, x_0, optimizeLambda);
        x = gradientDescent.run();
        System.out.println("Gradijentni spust za 2. funkciju: x_0 = " + x_0 + "; x = " + x + "; F(x) = " + function.applyPoint(x));

        newtonRaphson = new NewtonRaphson(function, deltaState, x_0, optimizeLambda);
        x = newtonRaphson.run();
        System.out.println("Newton - Raphson za 2. funkciju: x_0 = " + x_0 + "; x = " + x + "; F(x) = " + function.applyPoint(x));

        gaussNewton = new GaussNewton(function, deltaState, x_0, optimizeLambda);
        x = gaussNewton.run();
        System.out.println("Gauss - Newton za 2. funkciju: x_0 = " + x_0 + "; x = " + x + "; F(x) = " + function.applyPoint(x));

        System.out.println();

        /////////////////////////////////////////////////////////////////////////////////////////////

        System.out.println("Funkcija 3: ");

        function = new F3();
        x_0 = new VectorSolution(0, 0);
        deltaState = new DeltaXState(function, x_0);
        gradientState = new GradientState(function, x_0);

        gradientDescent = new GradijentniSpust(function, gradientState, x_0, optimizeLambda);
        x = gradientDescent.run();
        System.out.println("Gradijentni spust za 3. funkciju: x_0 = " + x_0 + "; x = " + x + "; F(x) = " + function.applyPoint(x));

        newtonRaphson = new NewtonRaphson(function, deltaState, x_0, optimizeLambda);
        x = newtonRaphson.run();
        System.out.println("Newton - Raphson za 3. funkciju: x_0 = " + x_0 + "; x = " + x + "; F(x) = " + function.applyPoint(x));

        gaussNewton = new GaussNewton(function, deltaState, x_0, optimizeLambda);
        x = gaussNewton.run();
        System.out.println("Gauss - Newton za 3. funkciju: x_0 = " + x_0 + "; x = " + x + "; F(x) = " + function.applyPoint(x));

        System.out.println();

        /////////////////////////////////////////////////////////////////////////////////////////////

        System.out.println("Funkcija 4: ");

        function = new F4();
        x_0 = new VectorSolution(0, 0);
        deltaState = new DeltaXState(function, x_0);
        gradientState = new GradientState(function, x_0);

        gradientDescent = new GradijentniSpust(function, gradientState, x_0, optimizeLambda);
        x = gradientDescent.run();
        System.out.println("Gradijentni spust za 4. funkciju: x_0 = " + x_0 + "; x = " + x + "; F(x) = " + function.applyPoint(x));

        newtonRaphson = new NewtonRaphson(function, deltaState, x_0, optimizeLambda);
        x = newtonRaphson.run();
        System.out.println("Newton - Raphson za 4. funkciju: x_0 = " + x_0 + "; x = " + x + "; F(x) = " + function.applyPoint(x));

//        gaussNewton = new GaussNewton(function, deltaState, x_0, optimizeLambda);
//        x = gaussNewton.run();
//        System.out.println("Gauss - Newton za 4. funkciju: x_0 = " + x_0 +  "; x = " + x + "; F(x) = " + function.applyPoint(x));


    }

}
