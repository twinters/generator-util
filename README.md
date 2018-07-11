# generator-util
This repository contains several procedural generation tools in Java that I reuse in a lot of projects.
It is made public mainly due to it being a dependency in some of my other open-sourced software.
I might add some more documentation for this repository when it grows larger.

Currently, it contains several diverse tools, such as:
* `Picker`: To easily pick random numbers, random different indices in a list etc.
* `IGenerator`:
A functional interface to represent a generator that returns different results each time, and can result an Optional.empty()
It has a lot of methods to manipulate the generator, some similar to the native Java Stream API.
* `IReactingGenerator`, `IStreamGenerator`, `IReactingStreamGenerator`: Similar to IGenerator, but receiving a certain input and/or generating a stream instead of an optional.
* `ISelector`: Various tools to select random values from a list, often using `IFitnessFunction`, inspired by genetic algorithms.
There are several well-known variants provided in these utilities such as roulette wheel selection, tournament selection, uniform selection, sorted selection.
