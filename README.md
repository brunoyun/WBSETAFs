# WBSETAF

We work in the particular setting of argumentation graphs with set of attacking and supporting arguments, i.e. arguments can collectively attack or defend other arguments.
In this repository, we generalise and implement existing ranking-based semantics ([Euler][1] and [DF-Quad][2]) for this type of graph.


## Content

This git repository contains the following files:
- A set of handcrafted WBSETAFs instances
- A runnable JAR that computes ranking-based semantics from the literature.
- The corresponding sourcecode (_src folder_)

## Sandbox

The sandbox shows different hotel recommendations with respect to profiles, algorithm presets and the database used for training.

The recommender algorithm used in this sandbox is a simple hybrid recommender system (see https://pypi.org/project/hybrid-recommender/). 

### Data used

### Requirements

This sandbox is installed using the Django Python Web framework (https://www.djangoproject.com).
It is advised to use Python with a version above or equal to 3.6.

TODO: Add all the requirements

[1]: https://www.irit.fr/~Leila.Amgoud/BAFs.pdf
[2]: https://spiral.imperial.ac.uk/bitstream/10044/1/35993/5/Main.pdf