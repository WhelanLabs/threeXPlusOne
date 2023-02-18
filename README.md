# Three X Plus One

## Basic Approach - Checking Tail Sequences

This program was designed to work on the Colletz Conjecture by finding the (second) smallest number that contains a closed loop sequence.  For this application, a closed loop sequence starts with a minimum number that through a sequence ends up looping back to the same number without having that sequence hit a lower value.  For example, the number one is a minimum number for the closed loop of {1,4,2,1, ...}.

This application works by testing sets of natural numbers.  I refer to these sets by what I call their Tail Squences, and works in binary.  As an example, consider the numbers with a Tail Sequence of "X0101"; this set of numbers would include all natural numbers where the binary representation ends in "0101", which includes {5, 21, 37, 53, ...}.  This set of numbers can be represented as "16X+5", where X is any natural number.  Performing a 3X+1 operation on this Tail Sequence is shown as follows:

		        X0101
		      +X01011
		 ------------
		 [X][X+1]0000

From this we know that [X][X+1]0000 = 32X + (16X+16) + 0.  Furthermore, through the "divide even results by 2", we know that we can bit shift the answer to [X][X+1] which equals 3X+1.  Since we know that for all natural number values of X that 3X+1<16X+5, we know that numbers of the form X0101 cannot be the smallest values for a closed loop answer of the Colletz Conjecture.  Given this, we can disregard numbers ending in "0101" from further testing.

Other Tail Sequences, such as X0111 produce indetirminate results. Specifically:

		                  X0111
		                +X01111
		             ----------
		             X[X+1]0110 -> 24X+11

		repeating the process with the current result...

		            [X][X+1]011
		      +[X][X+1]   0 111
		    -------------------
		        X[2X+1][X+2]010  -> 36X+17
		
		repeating the process with the current result...

		       [X][2X+1][X+2]01
		+[X][2X+1] [X+2]   0 11
		-----------------------
		 [X][3X+1][3X+3][X+3]00  -> 27X+13

At this point, we have not dropped below the initial value of 16X+7, and we have run out of Tail Sequence bits.  In this case, give up on this Tail Sequence and we add the child Tail Sequences of  X00101 and X10101 for testing.

## The Program

I created a program I call TXPO (Three X Plus One) program to test results.  It starts with 4-bit sequences, and ignoring the even numbers, runs through the process described above.  The fun bits of the application, besides coding the algorithm, were in having it be performant while largely using a fixed amount of memory, and allowing to to scale to take advantage of larger machines.

[TODO: describe the logic to test results.]


## Current Results

At present, TXPO has checked all canidates with up to and including 36 bits.  At the 36-bit mark, 99% of all numbers have been eliminated as smallest-number canidates.  The general pattern that I see between each generation of bits (generation being based on tail size) , is that each subsequent generation is about 10% smaller than double the prior generation.  This translates to having only 1.9 billion remaining canidate tail sequences for numbers with a 37-bit tail sequence.  (specifically 1,934,757,182 of 137,438,953,472 possible numbers.)  This approach should allow benifits over a pure brute force method as numbers start to get large.

When running TXPO, each successive generation takes roughly twice as long to run.  On my meager ThinkPad with an XMX setting of 4GB, the set of 36-bit numbers took a few hours to run.  Unfortunately, while the biggest machine I have access to is a 24-core box, I don't believe that would be enough to push the state-of-the-art for the problem.

## Possible Future Enhancements

The program currently only runs on a single machine.  Augmentation to the Feeder logic should allow the program to scale across multiple machines.

The program currently has a hardcoded batch size. as canidates get big, this may proove to be a problem.  Adding logic to adjust the batch size to stay within memory constraints could be done.  (At present, the program runs fine within a 4GB XMX setting.)