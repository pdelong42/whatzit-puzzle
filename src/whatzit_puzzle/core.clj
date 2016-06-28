(ns whatzit-puzzle.core
   (:require
      [clojure.core.matrix        :refer [transpose]]
      [clojure.math.combinatorics :refer [permutations cartesian-product]]
      [clojure.pprint             :refer [pprint]]  )
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

(defn generate-rotations-one-piece
   [rotations]
   (let
      [  newest (-> rotations last transpose reverse vec)  ]
      (if
         (some #(= % newest) rotations)
         rotations
         (generate-rotations-one-piece (conj rotations newest))  )  )  )

(defn generate-rotations-all-pieces [ps]
   (map #(-> % vector generate-rotations-one-piece) ps)  )

(defn step-through-all-combos [rps]
   (apply cartesian-product (generate-rotations-all-pieces rps))  )

(defn permute-each-combo [c]
   (reduce into (map permutations (step-through-all-combos c)))  )

(defn -main
   [& args]
   (-> pieces generate-rotations-all-pieces pprint)  )
