package hr.fer.zemris.apr.lab2.interval;

import hr.fer.zemris.apr.lab2.function.IFunction;
import hr.fer.zemris.apr.lab2.solution.IntervalSolution;
import hr.fer.zemris.apr.lab2.solution.VectorSolution;

public class UnimodalInterval {

    public static IntervalSolution findInterval(double h, double x_0, IFunction function) {

        double l = x_0 - h;
        double r = x_0 + h;
        double m = x_0;

        int step = 1;

        double fm = function.apply(new VectorSolution(x_0));
        double fl = function.apply(new VectorSolution(l));
        double fr = function.apply(new VectorSolution(r));

        if(fm < fr && fm < fl){
            return new IntervalSolution(l, r);
        }
        else if(fm > fr){
            do{
                l = m;
                m = r;
                fm = fr;
                r = x_0 + h * (step *= 2);
                fr = function.apply(new VectorSolution(r));
            }while(fm > fr);
        }
        else{
            do {
                r = m;
                m = l;
                fm = fl;
                l = x_0 - h * (step *= 2);
                fl = function.apply(new VectorSolution(l));
            }while(fm > fl);
        }

        return new IntervalSolution(l, r);
    }
}
