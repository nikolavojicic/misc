(ns misc.adventofcode.2021.p01
  (:require [clojure.test :refer [with-test is]]
            [clojure.java.io :as io]))


(def +in1 (-> "adventofcode_2021_p01_i01.txt" io/resource io/reader line-seq))
(def +in2 (-> "adventofcode_2021_p01_i02.txt" io/resource io/reader line-seq))


(with-test

  (defn solve1
    [input]
    (->> input
         (map parse-long)
         (partition 2 1)
         (filter #(apply < %))
         count))

  (is (= (solve1 +in1)    7))
  (is (= (solve1 +in2) 1154)))


(with-test

  (defn solve2
    [input]
    (->> input
         (map parse-long)
         (partition 3 1)
         (map #(reduce + %))
         (partition 2 1)
         (filter #(apply < %))
         count))

  (is (= (solve2 +in1)    5))
  (is (= (solve2 +in2) 1127)))


#_(clojure.test/run-tests *ns*)
