package abdn.semantics;

import abdn.functions.Maths;
import abdn.structure.Argument;
import abdn.structure.Setaf;

import java.util.HashMap;
import java.util.HashSet;

public class DFQuad {
    private Setaf AF;
    private HashMap<Argument, Double> scores = new HashMap<>();

    public void Compute(Setaf AF, int steps, double precision){
        scores = new HashMap<>();

        System.out.println("\n-- DF-Quad semantics --");

        for(Argument a : AF.getListOfArguments()){
            scores.put(a,a.getInStrength());
        }

        int step = 0;
        boolean convergence = false;
        while((step < steps ) && (! convergence)){

            double stepPrecision=0;
            for(Argument a: AF.getListOfArguments()){
                //We update the score of a

                //Computation of va
                double E1 = 1;
                for(HashSet<Argument> X : a.getListOfattackers())
                    E1*= (1 - Maths.min(X,scores) * AF.getRelation(X,a,true).getInStrength());
                double va = 1 - E1;

                //Computation of vb
                double E2 = 1;
                for(HashSet<Argument> X : a.getListOfsupporters())
                    E2*= (1 - Maths.min(X,scores) * AF.getRelation(X,a,false).getInStrength());
                double vb = 1 - E2;

                double oldScore = scores.get(a);
                if(va == vb)
                    scores.put(a, a.getInStrength());
                else
                    scores.put(a, a.getInStrength() + (0.5 + (vb - va)/(2* Math.abs(vb-va)) - a.getInStrength())* Math.abs( vb- va) );
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
