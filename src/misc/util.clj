(ns misc.util
  (:require [net.cgrand.xforms :as xf]))


(set! *warn-on-reflection* true)


(defn parse-char
  [^String s]
  (assert (= (count s) 1))
  (.charAt s 0))


(defn char->long
  [^Character c]
  (Character/getNumericValue c))


(defn first-line
  [lines-in]
  (xf/some (take 1) lines-in))
