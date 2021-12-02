(ns misc.adventofcode.2021.p02
  (:require [clojure.test :refer [with-test is]]
            [clojure.string :as str]
            [clojure.java.io :as io]
            [net.cgrand.xforms :as xf]
            [net.cgrand.xforms.io :as xfio]))


(def +in1 (-> "adventofcode_2021_p02_i01.txt" io/resource xfio/lines-in))
(def +in2 (-> "adventofcode_2021_p02_i02.txt" io/resource xfio/lines-in))


(defn parse-direction
  [line]
  (-> (apply hash-map (str/split line #" "))
      (update-vals parse-long)
      (update-keys keyword)))


(with-test

  (defn solve
    [input]
    (let [{:keys [forward down up]}
          (transduce (map parse-direction)
                     (partial merge-with +)
                     input)]
      (* forward (- down up))))

  (is (= (solve +in1)     150))
  (is (= (solve +in2) 2073315)))


#_(clojure.test/run-tests *ns*)
