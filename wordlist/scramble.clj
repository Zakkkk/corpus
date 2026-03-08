(ns scramble
  (:require [clojure.string :as str]))

(defn string-size-mb [s]
  (/ (count (.getBytes s "UTF-8")) 1048576.0))

(let [[in-file out-file amount-str] *command-line-args*
      contents (str/replace (slurp in-file) #"\n" " ")
      words (str/split contents #" ")
      word-count (count words)
      amount (Integer/parseInt amount-str)
      result (->> (repeatedly amount #(nth words (rand-int word-count)))
                  (str/join " "))]
  (spit out-file result)
  (println "Output size:" (string-size-mb result) "mb"))