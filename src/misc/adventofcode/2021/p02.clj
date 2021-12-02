(ns misc.adventofcode.2021.p02
  (:require [clojure.test :refer [with-test is]]
            [clojure.string :as str]
            [clojure.java.io :as io]
            [net.cgrand.xforms.io :as xfio]))


(def +in1 (-> "adventofcode_2021_p02_i01.txt" io/resource xfio/lines-in))
(def +in2 (-> "adventofcode_2021_p02_i02.txt" io/resource xfio/lines-in))


(defn parse-direction
  [line]
  (-> (str/split line #" ")
      (update 0 keyword)
      (update 1 parse-long)))


(with-test

  (defn solve1
    [input]
    (let [{:keys [forward down up]}
          (transduce
           (comp (map parse-direction)
                 (map #(apply hash-map %)))
           (partial merge-with +)
           input)]
      (* forward (- down up))))

  (is (= (solve1 +in1)     150))
  (is (= (solve1 +in2) 2073315)))


(with-test

  (defn solve2
    [input]
    (let [{:keys [horiz depth]}
          (transduce
           (map parse-direction)
           (completing
            (fn [ret [direction x]]
              (case direction
                :forward (-> ret
                             (update :horiz + x)
                             (update :depth + (* (:aim ret) x)))
                :down (update ret :aim + x)
                :up   (update ret :aim - x))))
           {:horiz 0 :depth 0 :aim 0}
           input)]
      (* horiz depth)))

  (is (= (solve2 +in1)        900))
  (is (= (solve2 +in2) 1840311528)))


#_(clojure.test/run-tests *ns*)
