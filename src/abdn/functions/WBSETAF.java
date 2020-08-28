package abdn.functions;

import abdn.semantics.DFQuad;
import abdn.semantics.Euler;
import abdn.semantics.Sigmoid;
import abdn.structure.Argument;
import abdn.structure.Relation;
import abdn.structure.Setaf;

import java.io.*;
import java.util.Arrays;
import java.util.HashSet;

public class WBSETAF {
    public static void main(String[] args)
    {
//        args = new String[1];
//        args[0] = "/Users/s01by9/Desktop/AF2.apx";

        if(args.length == 1){

            String filepath = args[0];

            Parser p = new Parser();
            Setaf AF = p.Parse(filepath);


            System.out.println(AF);

            Euler eu = new Euler();
            eu.Compute(AF, 100, 0.0001);

            DFQuad df = new DFQuad();
            df.Compute(AF, 100, 0.0001);

            Sigmoid sd = new Sigmoid();
            sd.Compute(AF, 100, 0.001);
        }
        else{
            System.out.println("Please enter the path to the ASPARTIX file.");
        }


    }
}
