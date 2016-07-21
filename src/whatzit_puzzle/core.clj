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

(defn envelope-dimensions
   [piece]
   [  (count piece)
      (apply max (map count piece))  ]  )

(defn anchors-of-envelope
   [piece]
   (->>
      piece
      envelope-dimensions
      (map #(range (inc %)))
      (apply cartesian-product)
      (map vec)
      set  )  )

(defn generate-rotations-one-piece
   [rotations]
   (let
      [  newest (-> rotations last transpose reverse vec)  ]
      (if
         (some #(= % newest) rotations)
         rotations
         (generate-rotations-one-piece (conj rotations newest))  )  )  )

(def wrap-each-piece-in-vector #(vec (map vector %))) ; footnote 1

(def generate-rotations-all-vectors #(map generate-rotations-one-piece %))

(def step-through-all-products #(apply cartesian-product %))

(def permute-each-product #(reduce into (map permutations %)))

(def generate-rotations-all-pieces
   (comp generate-rotations-all-vectors wrap-each-piece-in-vector)  )

(def generate-all-permutations
   (comp
      permute-each-product
      step-through-all-products
      generate-rotations-all-vectors
      wrap-each-piece-in-vector  )  )

(defn add-points [p q] (vec (map #(+ %1 %2) p q))) ; footnote 2

(defn -main
   [& args]
   (-> pieces generate-rotations-all-pieces pprint)  )

; Footnote 1:
; 
; Make a singleton group out of each piece, before generating its
; rotations.
;
; Footnote 2:
;
; I really ought to generalize this to a) more than two operands; and
; b) more than two coordinates per-point
