(ns misc.adventofcode.2020.p01
  (:require [clojure.test :refer [with-test is]]
            [clojure.java.io :as io]
            [misc.util :as util]))


(def +in1 [1721
           979
           366
           299
           675
           1456])


(def +in2 (->> "adventofcode_2020_p01_i01.txt"
               io/resource util/reducible-lines
               (into [] (map #(Long/parseLong %)))))


(with-test

  (defn solve1
    [input]
    (first
     (for [i1 input i2 input
           :when (= 2020 (+ i1 i2))]
       (* i1 i2))))

  (is (= (solve1 +in1) 514579))
  (is (= (solve1 +in2) 902451)))


(with-test

  (defn solve2
    [input]
    (first
     (for [i1 input i2 input i3 input
           :when (= 2020 (+ i1 i2 i3))]
       (* i1 i2 i3))))

  (is (= (solve2 +in1) 241861950))
  (is (= (solve2 +in2) 85555470)))


#_(clojure.test/run-tests *ns*)
