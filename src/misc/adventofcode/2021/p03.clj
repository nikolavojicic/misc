(ns misc.adventofcode.2021.p03
  (:require [clojure.test :refer [with-test is]]
            [clojure.java.io :as io]
            [net.cgrand.xforms :as xf]
            [net.cgrand.xforms.io :as xfio]
            [misc.util :as util]))


(def +in1 (-> "adventofcode/2021/p03_in01.txt" io/resource xfio/lines-in))
(def +in2 (-> "adventofcode/2021/p03_in02.txt" io/resource xfio/lines-in))


(defn nth-bit
  [line ix]
  (util/char->long (nth line ix)))


(with-test

  (defn solve1
    [input]
    (transduce
     (comp (xf/transjuxt
            (mapv
             (fn [ix]
               (comp (xf/by-key #(nth-bit % ix) xf/count)
                     (xf/into [])
                     (map #(mapv first (sort-by second %)))))
             (range (count (util/first-line input)))))
           (mapcat (partial apply map (comp #(Integer/parseInt % 2) str))))
     * input))

  (is (= (solve1 +in1)     198))
  (is (= (solve1 +in2) 4191876)))


(with-test

  (defn rating
    [criteria freq]
    (if (apply = (vals freq))
      criteria
      (let [[least-common most-common]
            (keys (sort-by val freq))]
        (if (= criteria 0)
          least-common
          most-common))))

  (is (= (rating 0 {0 2, 1 3}) 0))
  (is (= (rating 1 {0 2, 1 3}) 1))

  (is (= (rating 0 {0 3, 1 2}) 1))
  (is (= (rating 1 {0 3, 1 2}) 0))

  (is (= (rating 0 {0 2, 1 2}) 0))
  (is (= (rating 1 {0 2, 1 2}) 1)))


(with-test

  (defn solve2
    ([input]
     (* (solve2 0 input) (solve2 1 input)))
    ([criteria input]
     (loop [ix 0, input input]
       (let [bit (->> input
                      (xf/into {} (xf/by-key #(nth-bit % ix) xf/count))
                      (rating criteria))
             remain (into [] (filter #(= bit (nth-bit % ix))) input)]
         (if (second remain)
           (recur (inc ix) remain)
           (Integer/parseInt (first remain) 2))))))

  (is (= (solve2 +in1)     230))
  (is (= (solve2 +in2) 3414905)))


#_(clojure.test/run-tests *ns*)
