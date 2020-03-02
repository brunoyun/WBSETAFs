package abdn.structure;

import java.util.ArrayList;
import java.util.HashSet;

public class Argument {

    private String description;
    private double inStrength;
    private HashSet<HashSet<Argument>> listOfattackers;
    private HashSet<HashSet<Argument>> listOfsupporters;
    static int numberOfArgs = 0;

    public Argument(String description) {
        inStrength = 0.5;
        numberOfArgs++;
        this.description = description;
        this.listOfattackers = new HashSet<>();
        this.listOfsupporters = new HashSet<>();
    }

    public Argument(String description, double inStrength) {
        this.inStrength = (inStrength > 1)? 1 : ((inStrength< 0)? 0 : inStrength);
        numberOfArgs++;
        this.description = description;
        this.listOfattackers = new HashSet<>();
        this.listOfsupporters = new HashSet<>();
    }

    @Override
    public int hashCode() { return this.description.hashCode(); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (this.getClass() != o.getClass()) return false;
        Argument a = (Argument) o;
        return this.description.equals(a.description) && (this.getInStrength() == a.getInStrength());
    }

    @Override
    public String toString(){
        return description+"[w="+ inStrength+"]";
    }

    public HashSet<HashSet<Argument>> getListOfattackers() {
        return listOfattackers;
    }

    public HashSet<HashSet<Argument>> getListOfsupporters() {
        return listOfsupporters;
    }

    public String getDescription() {
        return description;
    }

    public double getInStrength() {
        return inStrength;
    }
}
