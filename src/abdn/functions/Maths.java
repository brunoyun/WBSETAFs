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
                if(a.getInStrength()  < minimumElt.getInStrength())
                    minimumElt = a;
            }
            return minimumElt.getInStrength();
        }
        else
            return 0.0;
    }


}
