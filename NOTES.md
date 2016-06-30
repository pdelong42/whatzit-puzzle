 - Next step: Write logic to take a piece as input, and produce a list
   of coordinates as output.  Those coordinates will be used as input
   to logic for placing the next piece, choosing trial anchors at
   which to place the lower-left corner of the piece.

 - A termination condition is needed.  At the moment, it's not clear
   what my criterion is for aborting a test on a candidate sequence,
   and breaking out early before wasting effort on any more
   processing.

 - The pieces are generally concave, but I plan to work with their
   convex counterparts (where gaps are "filled" to make an "envelope"
   shape).  From that point, I can convolve each piece with the
   remaining space in generating trial solutions.

 - UPDATE: I think I may be approaching this the wrong way.  I should
   think about doing it "additively" rather than "subtractively".
   That is, I should consider incrementally growing a shape until all
   pieces are exhausted, then testing for the desired shape (rather
   than starting with the desired shape, and progressively subtracting
   pieces from it).

 - I've managed to prune the duplicate solutions which would've
   resulted from those pieces which have rotational symmetries.  This
   was a side-effect of generating the distinct orientations of each
   piece.

 - To generate a candidate board shape the pieces will form in
   aggregate, for each trial permutation and rotation configuration of
   the pieces: start by placing the lower-left corner of the first
   piece at the origin, then adding subsequent pieces by aligning the
   lower-left corner of each, iteratively at each coordinate of the
   board shape that's been accumulated thus far.  Any overlap (i.e.,
   any cells adding up to more than "1") would be condition for
   rejecting a candidate.

 - The part I haven't yet figured-out is how to determine whether the
   candidate board shape is the desired end-result.  Clojure will
   recognize equality between complex data structures, which gets us
   most of the way there.  But two boards can still be valid
   solutions, yet not be equal to each-other.  An example of this is
   an 8x8 board padded with row or column of zeros.  So I need to find
   a way to filter-out any zero padding once all the pieces have been
   placed in a candidate solution.

 - It would be nice to have some way to print the solutions in a
   vaguely graphical form, even if it is just ASCII art.  As long as
   it's something I can look at and not get a headache.  I can still
   print out the listing of each piece by number, rotation, and
   coordinates, so it isn't an absolute necessity (but it would be so
   much nicer).

 - The function "step-through-all-combos" is really inadequately
   named.  But to call it something that more accurately reflects its
   purpose would probably have been unwieldy.  So I'm going to attempt
   to explain it here.  There are 'N' pieces, and for each of the 'n',
   there are M(n) distinct rotations.  It's easiest for me to think of
   it as an N-digit number.  But instead of having the same constant
   'M' possible choices for each digit, there are M(n) choices for
   each digit.  That is, 'M' is in-general different for each position
   'n'.

 - [X] Take a more comprehensive look at enumerating all possible
   trial solutions.

 - [X] Build a convenient data structure (or structures) for
   representing the board and puzzle pieces in a manner that makes the
   logic as natural as possible.

 - [X] Drawing ASCII maps of the pieces, and some of the known
   solutions, might be helpful visual aids.

 - [X] I need a procedure for determining whether a shape fits inside
   of a given space. (This became obsolete when I changed tactics.)

 - [X] I'll be manually "pre-compiling" the envelopes around each
   piece - the data structures representing the pieces will already
   have the envelope shapes built-in (hard-coded, as it were).  This
   is non ideal, and I'm not crazy about it; but it's a shortcut I'm
   taking for now, until I get around to writing logic which
   automatically calculates the envelopes, given a piece as input.

 - [X] As the trial solutions are generated, the space to be filled
   will shrink while walking through any one particular trial
   solution.  I should optimize this to account for the smaller space
   to be convolved / iterated over. (obsolete)

 - [X] I don't yet know how to think about the rotations of the
   pieces, in relation to the permutations of the pieces.  Is it just
   a cartesian product?  Still bouncing this around in my head.
