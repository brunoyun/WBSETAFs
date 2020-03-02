package abdn.structure;

import java.util.HashSet;

public class Relation {

    private HashSet<Argument> Origin;
    private Argument target;
    private boolean relationType; //True is attack and false is defeat
    private double inStrength;


    public Relation(HashSet<Argument> origin, Argument target, boolean relationType, double inStrength) {
        Origin = origin;
        this.target = target;
        this.relationType = relationType;
        this.inStrength = (inStrength > 1)? 1 : ((inStrength< 0)? 0 : inStrength);
    }

    public String toString(){
        String result="[ ";
        for(Argument a : Origin)
            result+=a.getDescription()+" ";
        if(relationType)
            result+="]->"+target.getDescription()+" [w:"+inStrength+"]";
        else
            result+="]=>"+target.getDescription()+" [w:"+inStrength+"]";
        return result;
    }


    public HashSet<Argument> getOrigin() {
        return Origin;
    }

    public Argument getTarget() {
        return target;
    }

    public boolean isRelationType() {
        return relationType;
    }

    public double getInStrength() {
        return inStrength;
    }
}
