(ns misc.util)


(defn parse-char
  [s]
  (assert (= (count s) 1))
  (.charAt s 0))
