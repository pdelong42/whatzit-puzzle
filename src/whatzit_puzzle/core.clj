(ns whatzit-puzzle.core
   (:require
      [clojure.core.matrix        :refer [transpose]]
      [clojure.math.combinatorics :refer [permutations count-permutations cartesian-product]]
      [clojure.pprint             :refer [pprint]]
      [clojure.string             :refer [join]]  )
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

(def turn-left #(-> % transpose reverse vec))

(defn generate-rotations-one-piece
   [rotations]
   (let
      [  newest (turn-left (last rotations))  ]
      (if
         (some #(= % newest) rotations)
         rotations
         (generate-rotations-one-piece (conj rotations newest))  )  )  )

(defn generate-rotations-all-pieces []
   (map #(-> % vector generate-rotations-one-piece) pieces)  )

(defn step-through-all-combos []
   (apply cartesian-product (generate-rotations-all-pieces))  )

(defn -main
   [& args]
   (pprint (generate-rotations-all-pieces))  )
