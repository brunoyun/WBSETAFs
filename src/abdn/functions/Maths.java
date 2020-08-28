package abdn.functions;

import abdn.structure.Argument;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.HashSet;

public class Maths {

    public static DecimalFormat format2digits = new DecimalFormat("#.##");

    public static double min(HashSet<Argument> X, HashMap<Argument,Double> scores){

        if(! X.isEmpty()){
            Argument minimumElt = X.iterator().next();
            for(Argument a: X){
                if(scores.get(a)  < scores.get(minimumElt))
                    minimumElt = a;
            }
            return scores.get(minimumElt);
        }
        else
            return 0.0;
    }

    public static double max(HashMap<HashSet<Argument>, Double> scores){


        if(! scores.keySet().isEmpty()){
            double result = scores.get(scores.keySet().iterator().next());
            for(HashSet<Argument> X : scores.keySet()){
                if(scores.get(X) > result)
                    result = scores.get(X);
            }
            return result;
        }
        else
            return 0.0;
    }

}
