package abdn.semantics;

import abdn.functions.Maths;
import abdn.structure.Argument;
import abdn.structure.Setaf;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.HashSet;

public class Euler {
    private Setaf AF;
    private HashMap<Argument, Double> scores = new HashMap<>();

    public void Compute(Setaf AF, int steps, double precision){
        scores = new HashMap<>();

        System.out.println("\n-- Euler semantics --");

        for(Argument a : AF.getListOfArguments()){
            scores.put(a,a.getInStrength());
        }

        int step = 0;
        boolean convergence = false;
        while((step < steps ) && (! convergence)){

            double stepPrecision=0;
            for(Argument a: AF.getListOfArguments()){
                //We update the score of a
                double E = 0.0;

                for(HashSet<Argument> X : a.getListOfsupporters())
                    E+= Maths.min(X,scores) * AF.getRelation(X,a,false).getInStrength();

                for(HashSet<Argument> X : a.getListOfattackers())
                    E-= Maths.min(X,scores) * AF.getRelation(X,a,true).getInStrength();

                double oldScore = scores.get(a);
                scores.put(a,1 - (1- Math.pow(a.getInStrength(),2.0))/(1 + a.getInStrength()* Math.exp(E)));
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
}
