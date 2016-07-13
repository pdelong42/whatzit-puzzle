(ns whatzit-puzzle.core-test
   (:require
      [clojure.math.combinatorics :refer [permutations cartesian-product]]
      [clojure.test        :refer :all]
      [whatzit-puzzle.core :refer :all]  )  )

(def board-1x1 [[1]])

(def board-1x2 [[1 1]])

(def board-2x2
   [  [1 1]
      [1 1]  ]  )

(def pieces-1x1 [[[1]]])

(def pieces-2x2a
   [  [  [1 1]
         [1 0]  ]
      [   [1]   ]  ]  )

(def pieces-2x2b
   [  [  [1 1]  ]
      [  [1 1]  ]  ]  )

(def pieces-2x2c
   [  [  [1 1]  ]
      [   [1]   ]
      [   [1]   ]  ]  )

(def pieces-2x2d
   [  [  [1]  ]
      [  [1]  ]
      [  [1]  ]
      [  [1]  ]  ]  )

(def pieces-1x1-wrapped [[[[1]]]])

(def pieces-2x2a-wrapped
   [  [  [  [1 1]
            [1 0]  ]  ]
      [  [   [1]   ]  ]  ]  )

(def pieces-2x2b-wrapped
   [  [  [  [1 1]  ]  ]
      [  [  [1 1]  ]  ]  ]  )

(def pieces-2x2c-wrapped
   [  [  [  [1 1]  ]  ]
      [  [   [1]   ]  ]
      [  [   [1]   ]  ]  ]  )

(def pieces-2x2d-wrapped
   [  [  [  [1]  ]  ]
      [  [  [1]  ]  ]
      [  [  [1]  ]  ]
      [  [  [1]  ]  ]  ]  )

(def anchors-0x0 #{[0 0]})

(defn coord-range
   [  pos coord-list  ]
   (let
      [  x (map #(nth % pos) coord-list)  ]
      (if
         (empty? x)
         0
         (inc (apply max x))  )  )  )

(defn coord-ranges
   [coords-list]
   [  (coord-range 0 coords-list)
      (coord-range 1 coords-list)  ]  )

(defn add-col
   (  [] (add-col anchors-0x0))
   (  [anchors]
      (let
         [  [h w] (coord-ranges anchors)
             hr (range h) wv (vector w)  ]
         (into anchors (map vec (cartesian-product hr wv)))  )  )  )

(defn add-row
   (  [] (add-row anchors-0x0))
   (  [anchors]
      (let
         [  [h w] (coord-ranges anchors)
             hv (vector h)wr (range w)  ]
         (into anchors (map vec (cartesian-product hv wr)))  )  )  )

(def anchors-1x1 (add-row (add-col anchors-0x0)))

(def anchors-1x2 (add-col anchors-1x1))

(def anchors-2x2 (add-row anchors-1x2))

(def anchors-2x3 (add-col anchors-2x2))

(def anchors-2x4 (add-col anchors-2x3))

(def anchors-3x3 (add-row anchors-2x3))

(def pieces-to-anchors-map
   [  anchors-2x2
      anchors-2x3
      anchors-2x3
      anchors-2x3
      anchors-2x3
      anchors-3x3
      anchors-3x3
      anchors-3x3
      anchors-2x4
      anchors-2x4
      anchors-2x4
      anchors-2x3
      anchors-3x3  ]  )

(def rotation-counts #(vec (map count (generate-rotations-all-pieces %))))

(deftest dimensions-test
   (testing "Piece envelope dimensions"
      (is (= (envelope-dimensions board-1x1) [1 1]))
      (is (= (envelope-dimensions board-2x2) [2 2]))
      (is (= (envelope-dimensions board)     [8 8]))
      (is (= (map envelope-dimensions pieces-1x1)  [[1 1]]))
      (is (= (map envelope-dimensions pieces-2x2a) [[2 2] [1 1]]))
      (is (= (map envelope-dimensions pieces-2x2b) [[1 2] [1 2]]))
      (is (= (map envelope-dimensions pieces-2x2c) [[1 2] [1 1] [1 1]]))
      (is (= (map envelope-dimensions pieces-2x2d) [[1 1] [1 1] [1 1] [1 1]]))
      (comment "placeholder for master list of pieces")  )  )

(deftest anchors-test
   (testing "Anchors of piece envelopes"
      (is (= (anchors-of-envelope board-1x1) anchors-1x1))
      (is (= (anchors-of-envelope board-1x2) anchors-1x2))
      (is (= (anchors-of-envelope board-2x2) anchors-2x2))
      (is (= (vec (map anchors-of-envelope pieces)) pieces-to-anchors-map))
      (comment "placeholder for master list of pieces")  )  )

(deftest vector-wrapper-test
   (testing "Vector Wrapper"
      (is (= pieces-1x1-wrapped  (wrap-each-piece-in-vector pieces-1x1)))
      (is (= pieces-2x2a-wrapped (wrap-each-piece-in-vector pieces-2x2a)))
      (is (= pieces-2x2b-wrapped (wrap-each-piece-in-vector pieces-2x2b)))
      (is (= pieces-2x2c-wrapped (wrap-each-piece-in-vector pieces-2x2c)))
      (is (= pieces-2x2d-wrapped (wrap-each-piece-in-vector pieces-2x2d)))
      (comment "placeholder for master list of pieces")  )  )

(deftest orientations-count-test
   (testing "Orientations Count"
      (is (= (rotation-counts pieces-1x1)  [1]))
      (is (= (rotation-counts pieces-2x2a) [4 1]))
      (is (= (rotation-counts pieces-2x2b) [2 2]))
      (is (= (rotation-counts pieces-2x2c) [2 1 1]))
      (is (= (rotation-counts pieces-2x2d) [1 1 1 1]))
      (is (= (rotation-counts pieces)      [1 4 2 4 4 4 2 4 4 4 4 2 2]))  )  )
