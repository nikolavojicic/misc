(ns misc.adventofcode.2020.p04
  (:require [clojure.test :refer [with-test is]]
            [clojure.string :as str]
            [clojure.set :as set]
            [clojure.java.io :as io]
            [net.cgrand.xforms :as xf]
            [net.cgrand.xforms.io :as xfio]))


(def +in1 (-> "adventofcode/2020/p04_in01.txt" io/resource xfio/lines-in))
(def +in2 (-> "adventofcode/2020/p04_in02.txt" io/resource xfio/lines-in))
(def +in3 (-> "adventofcode/2020/p04_in03.txt" io/resource xfio/lines-in))


(def +req-fields #{"byr" "iyr" "eyr" "hgt" "hcl" "ecl" "pid"})
(def +req-ecl    #{"amb" "blu" "brn" "gry" "grn" "hzl" "oth"})


(defn between?
  [from x to]
  (if-some [x (parse-long x)]
    (<= from x to)
    false))


(defn has-required-fields?
  [passport]
  (set/subset? +req-fields (set (keys passport))))


(defn valid-passport?
  [{:strs [byr iyr eyr hgt hcl ecl pid]}]
  (and byr iyr eyr hgt hcl ecl pid
       (between? 1920 byr 2002)
       (between? 2010 iyr 2020)
       (between? 2020 eyr 2030)
       (or (between? 150 (str/replace hgt "cm" "") 193)
           (between?  59 (str/replace hgt "in" "")  76))
       (re-matches #"#([0-9]|[a-f]){6}" hcl)
       (+req-ecl ecl)
       (re-matches #"[0-9]{9}" pid)
       true))


(with-test

  (defn solve
    [validator input]
    (xf/count
     (comp (partition-by #{""})
           (remove #{[""]})
           (map (fn [ptn]
                  (mapcat #(str/split % #" ") ptn)))
           (map (fn [ptn]
                  (mapv #(apply hash-map (str/split % #":")) ptn)))
           (map #(apply merge %))
           (filter validator))
     input))

  (is (= (solve has-required-fields? +in1)   2))
  (is (= (solve has-required-fields? +in2) 226))
  (is (= (solve has-required-fields? +in3)   8))
  (is (= (solve valid-passport?      +in1)   2))
  (is (= (solve valid-passport?      +in2) 160))
  (is (= (solve valid-passport?      +in3)   4)))


#_(clojure.test/run-tests *ns*)
