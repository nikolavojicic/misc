(ns misc.adventofcode.2020.p03
  (:require [clojure.test :refer [with-test is]]
            [clojure.java.io :as io]
            [net.cgrand.xforms :as xf]
            [net.cgrand.xforms.io :as xfio]))


(def +in1 (-> "adventofcode_2020_p03_i01.txt" io/resource xfio/lines-in))
(def +in2 (-> "adventofcode_2020_p03_i02.txt" io/resource xfio/lines-in))


(with-test

  (defn solve
    ([directions]
     (comp (xf/transjuxt
            (map #(apply solve %) directions))
           cat))
    ([down right input]
     (xf/some (solve down right) input))
    ([right down]
     (comp (drop 1)
           (partition-all down)
           (map last)
           (map-indexed
            (fn [ix line]
              (nth line (mod (* (inc ix) right)
                             (count line)))))
           (filter #{\#})
           xf/count)))

  (is (= (solve 3 1 +in1)   7))
  (is (= (solve 3 1 +in2) 270))

  (is (= (transduce (solve [[1 1] [3 1] [5 1] [7 1] [1 2]]) * +in1)        336))
  (is (= (transduce (solve [[1 1] [3 1] [5 1] [7 1] [1 2]]) * +in2) 2122848000)))


#_(clojure.test/run-tests *ns*)
