(ns mailcheck.core-test
  (:require [clojure.test :refer :all]
            [mailcheck.core :refer :all]))

(deftest test-split-email
  (testing "returns nil if the email is invalid"
    (is (nil? (split-email "a@b"))))
  (testing "returns a hash of the email's split information"
    (is (= {:top_level_domain "com", :domain "b.com", :email "a@b.com"}
           (split-email "a@b.com")))))
