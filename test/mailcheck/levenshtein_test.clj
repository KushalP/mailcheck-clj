(ns mailcheck.levenshtein-test
  (:require [clojure.test :refer :all]
            [mailcheck.levenshtein :refer :all]))

(deftest test-levenshtein-distance
  (are [expected x y] (= expected (levenshtein-distance x y))
       2 "stri" "string"
       5 "ao" "aol.com"
       2 "htmail.cm" "hotmail.com"))
