(ns misc.adventofcode.2020.p01
  (:require [clojure.test :refer [with-test is]]
            [clojure.java.io :as io]
            [net.cgrand.xforms.io :as xfio]))


(defn in
  [f]
  (->> f io/resource xfio/lines-in
       (into [] (map parse-long))))


(def +in1 (in "adventofcode/2020/p01_in01.txt"))
(def +in2 (in "adventofcode/2020/p01_in02.txt"))


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
  (is (= (solve2 +in2)  85555470)))


#_(clojure.test/run-tests *ns*)
