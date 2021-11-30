(ns misc.util
  (:require
   [clojure.java.io :as io])
  (:import
   (java.io BufferedReader)
   (clojure.lang IReduceInit)))


(defn reducible-lines
  "Returns a reducible that, when reduced, opens a reader
  and yields file's lines. Reader is closed when reduced?
  / there are no more lines to read / exception is thrown."
  [file]
  (reify IReduceInit
    (reduce [_ f init]
      (with-open [^BufferedReader r
                  (io/reader file)]
        (loop [state init]
          (if (reduced? state)
            state
            (if-let [line (.readLine r)]
              (recur (f state line))
              state)))))))


(defn parse-char
  [s]
  (assert (= (count s) 1))
  (.charAt s 0))
