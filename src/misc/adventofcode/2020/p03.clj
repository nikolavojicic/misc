(ns misc.adventofcode.2020.p03
  (:require [clojure.test :refer [with-test is]]
            [clojure.java.io :as io]
            [net.cgrand.xforms :as xf]
            [net.cgrand.xforms.io :as xfio]))


(def +in1 (-> "adventofcode/2020/p03_in01.txt" io/resource xfio/lines-in))
(def +in2 (-> "adventofcode/2020/p03_in02.txt" io/resource xfio/lines-in))
(def +directions [[1 1] [3 1] [5 1] [7 1] [1 2]])


(with-test

  (defn solve
    ([directions]
     (xf/multiplex
      (mapv #(apply solve %) directions)))
    ([right down]
     (comp (drop 1)
           (partition-all down)
           (map last)
           (map-indexed
            (fn [ix line]
              (nth line (mod (* (inc ix) right)
                             (count line)))))
           (filter #{\#})
           xf/count))
    ([down right input]
     (xf/some (solve down right) input)))

  (is (= (solve 3 1 +in1)   7))
  (is (= (solve 3 1 +in2) 270))

  (is (= (transduce (solve +directions) * +in1)        336))
  (is (= (transduce (solve +directions) * +in2) 2122848000)))


#_(clojure.test/run-tests *ns*)
