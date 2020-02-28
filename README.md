# WBSETAF

We work in the particular setting of argumentation graphs with set of attacking and supporting arguments, i.e. arguments can collectively attack or defend other arguments.
In this repository, we generalise and implement existing ranking-based semantics ([Euler][1] and [DF-Quad][2]) for this type of graph.


## Content

This git repository contains the following files:
- A set of handcrafted WBSETAFs instances (_Instances/Handcrafted Examples/_)
- A runnable JAR that computes ranking-based semantics from the literature (_out/artifacts/WeightedBipolarSETAFs_jar/_)
- The corresponding sourcecode (_src/_)

## Input Format

Weighted Bipolar SETAFs (WBSETAFs) are argumentation graphs with sets of attacking/supporting arguments and weights on both attacks/supports and arguments.
Our implementation uses a generalisation of the existing [ASPARTIX format for SETAFs][4]. The syntax is given below.


| Syntax        | Description  |
| ------------- |:------------------:|
| arg(a).      | a is an argument |
| att(X,b).      | X is a set attack on b       |
| supp(X,b). | X is a set support on b       |
| mem(X,b). | b is a member of the set attack/support X       |
| w(x,0.2). | The element x has an intrinsic weight of 0.2      |

If the intrinsic weight of an argument is not specified, then it is fixed at 0.5 by default. Similarly, if the instrinsic weight of an attack is not specifed, it is fixed at 1 by default.

## Usage

To run the software, just use the following command in a terminal:


```sh
java -jar WeightedBipolarSETAFs.jar [ASPARTIX_file]
```

where [ASPARTIX_file] refers to a text file in the aforementioned ASPARTIX format.

### Requirements

It is advised to install the latest [Java Runtime Environment][3].


[1]: https://www.irit.fr/~Leila.Amgoud/BAFs.pdf
[2]: https://spiral.imperial.ac.uk/bitstream/10044/1/35993/5/Main.pdf
[3]: https://java.com/en/download/manual.jsp
[4]: https://www.dbai.tuwien.ac.at/research/argumentation/aspartix/setaf.html