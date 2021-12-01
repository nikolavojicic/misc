(ns misc.adventofcode.2021.p01
  (:require [clojure.test :refer [with-test is]]
            [clojure.java.io :as io]
            [net.cgrand.xforms :as xf]
            [net.cgrand.xforms.io :as xfio]))


(def +in1 (-> "adventofcode_2021_p01_i01.txt" io/resource xfio/lines-in))
(def +in2 (-> "adventofcode_2021_p01_i02.txt" io/resource xfio/lines-in))


(with-test

  (defn solve1
    [input]
    (xf/count
     (comp (map parse-long)
           (xf/partition 2 1)
           (filter #(apply < %)))
     input))

  (is (= (solve1 +in1)    7))
  (is (= (solve1 +in2) 1154)))


(with-test

  (defn solve2
    [input]
    (xf/count
     (comp (map parse-long)
           (xf/partition 3 1)
           (map #(reduce + %))
           (xf/partition 2 1)
           (filter #(apply < %)))
     input))

  (is (= (solve2 +in1)    5))
  (is (= (solve2 +in2) 1127)))


#_(clojure.test/run-tests *ns*)
