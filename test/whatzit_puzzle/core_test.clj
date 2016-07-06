(ns whatzit-puzzle.core-test
   (:require
      [clojure.test        :refer :all]
      [whatzit-puzzle.core :refer :all]  )  )

(def board-1x1 [[1]])

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

(def rotation-counts #(vec (map count (generate-rotations-all-pieces %))))

(deftest dimensions-of-pieces
   (testing "Piece dimensions"
      (is (= (piece-dimensions board-1x1) [1 1]))
      (is (= (piece-dimensions board-2x2) [2 2]))
      (is (= (piece-dimensions board)     [8 8]))
      (is (= (map piece-dimensions pieces-1x1)  [[1 1]]))
      (is (= (map piece-dimensions pieces-2x2a) [[2 2] [1 1]]))
      (is (= (map piece-dimensions pieces-2x2b) [[1 2] [1 2]]))
      (is (= (map piece-dimensions pieces-2x2c) [[1 2] [1 1] [1 1]]))
      (is (= (map piece-dimensions pieces-2x2d) [[1 1] [1 1] [1 1] [1 1]]))
      (comment "placeholder for master list of pieces")  )  )

(deftest anchors-test
   (testing "Anchors of pieces"
      (is (= (anchors-of-piece board-1x1) #{[0 0] [0 1] [1 0] [1 1]}))  )  )

(deftest vector-wrapper
   (testing "Vector Wrapper"
      (is (= pieces-1x1-wrapped  (wrap-each-piece-in-vector pieces-1x1)))
      (is (= pieces-2x2a-wrapped (wrap-each-piece-in-vector pieces-2x2a)))
      (is (= pieces-2x2b-wrapped (wrap-each-piece-in-vector pieces-2x2b)))
      (is (= pieces-2x2c-wrapped (wrap-each-piece-in-vector pieces-2x2c)))
      (is (= pieces-2x2d-wrapped (wrap-each-piece-in-vector pieces-2x2d)))
      (comment "placeholder for master list of pieces")  )  )

(deftest orientations
   (testing "Orientations"
      (is (= (rotation-counts pieces-1x1)  [1]))
      (is (= (rotation-counts pieces-2x2a) [4 1]))
      (is (= (rotation-counts pieces-2x2b) [2 2]))
      (is (= (rotation-counts pieces-2x2c) [2 1 1]))
      (is (= (rotation-counts pieces-2x2d) [1 1 1 1]))
      (is (= (rotation-counts pieces)      [1 4 2 4 4 4 2 4 4 4 4 2 2]))  )  )
