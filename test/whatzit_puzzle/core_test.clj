(ns whatzit-puzzle.core-test
   (:require
      [clojure.test        :refer :all]
      [whatzit-puzzle.core :refer :all]  )  )

(def board-1x1 [[1]])

(def pieces-1x1 [[[1]]])

(deftest board-rows (testing "Board rows" (is (= 8 (count board)))))

(deftest pieces-count (testing "Pieces count" (is (= 13 (count pieces)))))

(def rotation-counts #(vec (map count (generate-rotations-all-pieces %))))

(deftest orientations
   (testing "Orientations"
      (is (= [1] (rotation-counts pieces-1x1)))
      (is (= [1 4 2 4 4 4 2 4 4 4 4 2 2] (rotation-counts pieces)))  )  )
