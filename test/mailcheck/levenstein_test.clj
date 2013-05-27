(ns mailcheck.levenstein-test
  (:require [clojure.test :refer :all]
            [mailcheck.levenstein :refer :all]))

(deftest test-levenstein-distance
  (are [expected x y] (= expected (levenshtein-distance x y))
       2 "stri" "string"
       5 "ao" "aol.com"
       2 "htmail.cm" "hotmail.com"))
