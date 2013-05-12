(ns mailcheck.email-test
  (:require [clojure.test :refer :all]
            [mailcheck.email :refer :all]))

(deftest test-valid-email?
  (testing "strings that aren't emails return false"
    (is (not (valid-email? "a")))
    (is (not (valid-email? "a@b"))))
  (testing "string that are emails return true"
    (is (valid-email? "a@b.com"))
    (is (valid-email? "xyz@gmail.com"))))
