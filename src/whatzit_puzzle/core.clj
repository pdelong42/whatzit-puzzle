(ns whatzit-puzzle.core
   (:require
      [clojure.string    :refer [join]]
      [clojure.math.combinatorics :refer [permutations cartesian-product]]
;      [clojure.core.matrix :refer [transpose]] ; for later, during cleanup
      [clojure.core.matrix]  )
   (:gen-class)  )

(def board
   [  [1 1 1 1 1 1 1 1]
      [1 1 1 1 1 1 1 1]
      [1 1 1 1 1 1 1 1]
      [1 1 1 1 1 1 1 1]
      [1 1 1 1 1 1 1 1]
      [1 1 1 1 1 1 1 1]
      [1 1 1 1 1 1 1 1]
      [1 1 1 1 1 1 1 1]  ]  )

(def pieces

   [  [  [1 1]
         [1 1]  ]

      [  [1 1 1]
         [0 1 0]  ]

      [  [0 1 1]
         [1 1 0]  ]

      [  [1 0 0]
         [1 1 1]  ]

      [  [1 0 1]
         [1 1 1]  ]

      [  [1 0 0]
         [1 0 0]
         [1 1 1]  ]

      [  [0 1 1]
         [0 1 0]
         [1 1 0]  ]

      [  [0 1 0]
         [0 1 1]
         [1 1 0]  ]

      [  [0 1 0 0]
         [1 1 1 1]  ]

      [  [1 1 1 0]
         [0 0 1 1]  ]

      [  [1 0 0 0]
         [1 1 1 1]  ]

      [  [1 1 1]
         [1 1 1]  ]

      [  [1 1 1]
         [0 1 0]
         [1 1 1]  ]  ]  )

(def turn-left #(-> % clojure.core.matrix/transpose reverse vec))

(defn generate
   [rotations]
   (let
      [  newest (turn-left (last rotations))  ]
      (if
         (some #(= % newest) rotations)
         rotations
         (generate (conj rotations newest))  )  )  )

(defn -main
   [& args]
   (printf "The board has %d rows.\n" (count  board))
   (printf "There are %d pieces.\n"   (count pieces))
   (println
      "Each piece has "
      (join \space (map #(-> % vector generate count) pieces))
      "distinct orientations, respectively."  )  )
