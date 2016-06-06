This is going to be my brute-force attempt at finding the solutions to my son's
"What'zit" puzzle.  Look in the docs directory for the photos of the solutions
sheet.  It would be nice to convert this to a more convenient format for the
long-term, like ASCII graphics or SVG (anyone know a good, free OCR tool?).

I'm going to be attempting to find all of the 8x8 solutions.  At a bare
minimum, I would expect to find the four solutions they provide - this would be
a test of whether my code works at all.

I would also expect to get a number of duplicate solutions as well, based on a
number of factors:

 - x4 for the number of rotations of each unique solution
 - x4 for each piece that has four-fold rotational symmetry (there's only one)
 - x2 for each piece with two-fold rotational symmetry (there are four)

This results in a combined factor of 256, or 1024 total solutions when
duplicates are accounted for.  That's assuming there aren't more than four
unique solutions (which may not be the case).  I don't yet know how I'm going
to detect duplicates (through rotations, etc.), so this is a limitation of my
current plan.

Maybe if I'm feeling even more ambitious, I'll try the 3D solutions as well.

I think it's time to start defining goals here, or I'll sit
contemplating this indefinitely and never actually do anything...

ToDo:

 - Take a more comprehensive look at enumerating all possible trial
   solutions.

 - Build a convenient data structure (or structures) for representing
   the board and puzzle pieces in a manner that makes the logic as
   natural as possible.

 - Drawing ASCII maps of the pieces, and some of the known solutions,
   might be helpful visual aids.

 - A termination condition is needed.  At the moment, it's not clear
   what my criterion is for aborting a test on a candidate sequence,
   and breaking out early before wasting effort on any more
   processing.

 - I need a procedure for determining whether a shape fits inside of a
   given space.

Okay, that's a start.  There will of-course be more later.
