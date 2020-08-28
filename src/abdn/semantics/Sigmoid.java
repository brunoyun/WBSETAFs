package abdn.semantics;

import abdn.functions.Maths;
import abdn.structure.Argument;
import abdn.structure.Setaf;

import java.util.HashMap;
import java.util.HashSet;

public class Sigmoid {
    private Setaf AF;
    private HashMap<Argument, Double> scores = new HashMap<>();

    public void Compute(Setaf AF, int steps, double precision){
        scores = new HashMap<>();

        System.out.println("\n-- Sigmoid semantics --");

        double gamma = 3;


        for(Argument a : AF.getListOfArguments()){
            scores.put(a,a.getInStrength());
        }

        int step = 0;
        boolean convergence = false;
        while((step < steps ) && (! convergence)){

            double stepPrecision=0;
            for(Argument a: AF.getListOfArguments()){
                //We update the score of a
                double Y = 0.0;

                HashMap<HashSet<Argument>, Double> minInverseScore = new HashMap<>();
                for(HashSet<Argument> X : a.getListOfsupporters()){
                    minInverseScore.put(X,Maths.min(X,convertInverse(scores)) * AF.getRelation(X,a,false).getInStrength());
                }
                Y+= Maths.max(minInverseScore);


                minInverseScore = new HashMap<>();
                for(HashSet<Argument> X : a.getListOfattackers()){
                    minInverseScore.put(X, Maths.min(X,convertInverse(scores)) * AF.getRelation(X,a,true).getInStrength());
                }

                Y-= Maths.max(minInverseScore);

                double oldScore = scores.get(a);
                scores.put(a, f(Y/gamma + f_inverse(a.getInStrength())));


                if(Math.abs(oldScore - scores.get(a)) > stepPrecision)
                    stepPrecision = Math.abs(oldScore - scores.get(a));
            }

            //We update the stop variables
            if(stepPrecision < precision)
                convergence = true;
            step++;
        }

        //We display the final scores
        displayScore();
        System.out.println("Finished after "+step+" steps.");





    }


    public void displayScore(){


        for(Argument a : scores.keySet()){
            System.out.println(a+" [score="+Maths.format2digits.format(scores.get(a))+"]");
        }
    }

    public static double f(double x){
        return (Math.tanh(x)+1)/2;
    }

    public static double f_inverse(double x){
        return atanh((x -0.5)*2);
    }

    private static HashMap<Argument,Double> convertInverse(HashMap<Argument,Double> input){
        HashMap<Argument,Double> result = new HashMap<>();
        for(Argument a : input.keySet())
            result.put(a, f_inverse(input.get(a)));
        return result;
    }


    private static double atanh(double x)
    {
        return 0.5*Math.log( (x + 1.0) / (1.0-x));
    }

}
