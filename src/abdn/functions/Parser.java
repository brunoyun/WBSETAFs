package abdn.functions;

import abdn.structure.Argument;
import abdn.structure.Relation;
import abdn.structure.Setaf;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    public Setaf Parse(String filepath){
        File file = new File(filepath);

        BufferedReader br = null;
        Setaf AF = new Setaf();
        try {
            br = new BufferedReader(new FileReader(file));
            String st ="";

            HashMap<String, Double> ArgumentsWeight = new HashMap<>();
            HashMap<String, Double> RelationWeight = new HashMap<>();
            HashMap<String, HashSet<String>> AttacksBuilder = new HashMap<>();
            HashMap<String, HashSet<String>> SupportBuilder = new HashMap<>();
            HashMap<String,String> Relation = new HashMap<>();

            while (true) {
                try {
                    if (!((st = br.readLine()) != null)) break;
                } catch (IOException e) {
                    e.printStackTrace();
                }

                //We find the arguments
                String regexarg = "arg\\(\\s*([a-z,0-9,A-Z]+)\\s*\\)\\.";
                String regexweight = "w\\(\\s*([a-z,0-9,A-Z]+)\\s*,\\s*([0-9]+|[0-9]+\\.[0-9]+)\\s*\\)\\.";
                String regexatt = "att\\(\\s*([a-z,0-9,A-Z]+)\\s*,\\s*([a-z,0-9,A-Z]+)\\s*\\)\\.";
                String regexsupp = "supp\\(\\s*([a-z,0-9,A-Z]+)\\s*,\\s*([a-z,0-9,A-Z]+)\\s*\\)\\.";
                String regexmem = "mem\\(\\s*([a-z,0-9,A-Z]+)\\s*,\\s*([a-z,0-9,A-Z]+)\\s*\\)\\.";

                Pattern patternarg = Pattern.compile(regexarg);
                Pattern patternweight = Pattern.compile(regexweight);
                Pattern patternatt = Pattern.compile(regexatt);
                Pattern patternsupp = Pattern.compile(regexsupp);
                Pattern patternmem = Pattern.compile(regexmem);


                Matcher matcher = patternarg.matcher(st);
                while(matcher.find())
                {
                    if(ArgumentsWeight.get(matcher.group(1)) == null){ //If we did not encounter this argument before
                        ArgumentsWeight.put(matcher.group(1), 0.5); //Default weight is 1
                    }
                }

                matcher = patternweight.matcher(st);
                while(matcher.find())
                {
                    if(ArgumentsWeight.keySet().contains(matcher.group(1))) //If we know this is an argument
                        ArgumentsWeight.put(matcher.group(1), Double.valueOf(matcher.group(2)));

                    if(AttacksBuilder.keySet().contains(matcher.group(1)) || SupportBuilder.keySet().contains(matcher.group(1))) //If we know this is an attack
                        RelationWeight.put(matcher.group(1), Double.valueOf(matcher.group(2)));
                }

                matcher = patternatt.matcher(st);
                while(matcher.find())
                {
                    if(!AttacksBuilder.keySet().contains(matcher.group(1))){ //We did not encounter this attack before
                        AttacksBuilder.put(matcher.group(1), new HashSet<>());
                    }
                    Relation.put(matcher.group(1),matcher.group(2));
                    RelationWeight.put(matcher.group(1),1.0);
                }

                matcher = patternsupp.matcher(st);
                while(matcher.find())
                {
                    if(!SupportBuilder.keySet().contains(matcher.group(1))){ //We did not encounter this attack before
                        SupportBuilder.put(matcher.group(1), new HashSet<>());
                    }
                    Relation.put(matcher.group(1),matcher.group(2));
                    //Default weight is 1
                    RelationWeight.put(matcher.group(1),1.0);
                }

                matcher = patternmem.matcher(st);
                while(matcher.find()){
                    if(AttacksBuilder.keySet().contains(matcher.group(1))){
                        AttacksBuilder.get(matcher.group(1)).add(matcher.group(2));
                    }

                    if(SupportBuilder.keySet().contains(matcher.group(1))){
                        SupportBuilder.get(matcher.group(1)).add(matcher.group(2));
                    }
                }
            }

            for(String s : ArgumentsWeight.keySet()){
                AF.addArgument(new Argument(s,ArgumentsWeight.get(s)));
            }

            for(String s : AttacksBuilder.keySet()){
                HashSet<Argument> source = new HashSet<>();
                for(String x : AttacksBuilder.get(s)){
                    source.add(AF.getArgumentByName(x));
                }

                AF.addRelation(new Relation(source, AF.getArgumentByName(Relation.get(s)), true, RelationWeight.get(s)));


            }

            for(String s : SupportBuilder.keySet()){
                HashSet<Argument> source = new HashSet<>();
                for(String x : SupportBuilder.get(s)){
                    source.add(AF.getArgumentByName(x));
                }
                AF.addRelation(new Relation(source, AF.getArgumentByName(Relation.get(s)), false, RelationWeight.get(s)));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return AF;
    }
}
