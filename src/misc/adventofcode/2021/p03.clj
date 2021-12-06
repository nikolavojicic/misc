(ns misc.adventofcode.2021.p03
  (:require [clojure.test :refer [with-test is]]
            [clojure.java.io :as io]
            [net.cgrand.xforms :as xf]
            [net.cgrand.xforms.io :as xfio]
            [misc.util :as util]))


(def +in1 (-> "adventofcode/2021/p03_in01.txt" io/resource xfio/lines-in))
(def +in2 (-> "adventofcode/2021/p03_in02.txt" io/resource xfio/lines-in))


(with-test

  (defn solve1
    [input]
    (transduce
     (comp (xf/transjuxt
            (mapv
             (fn [ix]
               (comp (xf/by-key #(util/char->long (nth % ix)) xf/count)
                     (xf/into [])
                     (map #(mapv first (sort-by second %)))))
             (range (count (util/first-line input)))))
           (mapcat (partial apply map (comp #(Integer/parseInt % 2) str))))
     * input))

  (is (= (solve1 +in1)     198))
  (is (= (solve1 +in2) 4191876)))


#_(clojure.test/run-tests *ns*)
