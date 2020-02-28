package abdn.structure;

import java.util.HashSet;

public class Setaf {
    HashSet<Argument> listOfArguments;
    HashSet<Relation> listOfRelations;

    public Setaf() {
        this.listOfArguments = new HashSet<>();
        this.listOfRelations = new HashSet<>();
    }

    public void addArgument(Argument a){
        listOfArguments.add(a);
    }

    public Setaf addRelation(Relation r){
        this.listOfRelations.add(r);
        listOfArguments.add(r.getTarget());
        listOfArguments.addAll(r.getOrigin());

        //We now add attackers and supporters in the arguments parameters
        if(r.isRelationType()){
            r.getTarget().getListOfattackers().add(r.getOrigin());
        }
        else
            r.getTarget().getListOfsupporters().add(r.getOrigin());

        return this;
    }

    public String toString(){
        String result = "-- List of arguments --\n";
        for(Argument a : listOfArguments)
            result+=a.toString()+"\n";
        result+="\n-- List of relations --\n";
        for(Relation r : listOfRelations)
            result+=r.toString()+"\n";

        return result;
    }

    public HashSet<Argument> getListOfArguments() {
        return listOfArguments;
    }

    public HashSet<Relation> getListOfRelations() {
        return listOfRelations;
    }

    public Relation getRelation(HashSet<Argument> source, Argument target, boolean attack){
        for(Relation r : listOfRelations)
        {
            if(r.getOrigin().containsAll(source) && source.containsAll(r.getOrigin()) && target.equals(r.getTarget()) && (r.isRelationType()== attack))
                return r;
        }
        return null;
    }

    public Argument getArgumentByName(String name){
        for(Argument a : listOfArguments){
            if(a.getDescription().equals(name))
                return a;
        }
        return null;
    }
}
