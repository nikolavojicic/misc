(ns misc.adventofcode.2020.p02
  (:require [clojure.test :refer [with-test is]]
            [clojure.java.io :as io]
            [misc.util :as util]))


(def +in1 (-> "adventofcode_2020_p02_i01.txt" io/resource util/reducible-lines))
(def +in2 (-> "adventofcode_2020_p02_i02.txt" io/resource util/reducible-lines))


(defn parse-password
  [line]
  (-> #"(\d+)-(\d+) ([a-zA-Z]): (.+)"
      (re-find line) rest vec
      (update 0 parse-long)
      (update 1 parse-long)
      (update 2 util/parse-char)))


(defn valid-password1?
  [line]
  (let [[lowest highest letter password] (parse-password line)]
    (<= lowest ((frequencies password) letter 0) highest)))


(defn valid-password2?
  [line]
  (let [[lowest highest letter password] (parse-password line)
        lowest?  (= (nth password (dec lowest )) letter)
        highest? (= (nth password (dec highest)) letter)]
    (and (or lowest? highest?) (not (and lowest? highest?)))))


(with-test

  (defn solve
    [pred input]
    (transduce
     (comp (filter pred)
           (map (constantly 1)))
     + input))

  (is (= (solve valid-password1? +in1)   2))
  (is (= (solve valid-password1? +in2) 542))
  (is (= (solve valid-password2? +in1)   1))
  (is (= (solve valid-password2? +in2) 360)))


#_(clojure.test/run-tests *ns*)
