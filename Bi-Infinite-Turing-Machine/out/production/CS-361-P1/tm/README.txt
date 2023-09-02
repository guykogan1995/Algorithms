# Project 3: Turing Machine Simulator

* Authors: Guy Kogan and Yelena Irwin
* Class: CS361 Section 1
* Semester: Spring 2023 

## Overview

This Java application simulates a bi-infinite Turing machine based on given input files consisting the amount of states, amount of sigma(alphabet of the TM and transitional symbols), and transitions for the TM, as well as an optional initilizer for the bi-infinite tape.

## Reflection

	It was interesting how the previous projects formed the foundation for this project. A lot of the components were very similar but
more complex in the way they were structured including the addition of a TMtape class. My impression is that I better understand the 
modular nature of objects in java. One of the things that was difficult was coordinating the different parts of the work between group partner.
The thing that worked well was communication and pair programming sessions. I feel that I (Yelena) learned a lot from both the pair programming sessions 
and tutoring sessions and my profficiency level has increased.
	
	To make our code easier to debug and modify we used the debugger in Intellij IDE and print statements within the code. One of the bugs discovered was that we added the transitions to the transitions  map in the wrong order and as a result the output of the larger files was partially incorrect.
	
	One thing I would change about the design process would be starting out from a commonground instead of one person starting and the other picking up from there as this resulted in some unnecessary work. I'm still not always clear on when to use public and private keywords. If I could go back in time I would tell myself that completing the TM homework assignment would be a great help with this project and that doing it soner is a good idea.

## Compiling and Using

1. Enter Project folder: P3
2. compile using command: javac tm/*.java 
3. type command to run: java tm.TMSimulator <File name you wish to test>

TESTS MUST BE IN "test" folder as TMSimulator the driver program appends
file names to test/ + <filename> = example = test/file0.txt
	

## Sources used

Java HashMap<K,V> and ArrayList<E> documentation on docs.oracle.com.

