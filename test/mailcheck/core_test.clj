(ns mailcheck.core-test
  (:require [clojure.test :refer :all]
            [mailcheck.core :refer :all]))

(deftest test-split-email
  (testing "returns nil if the email is invalid"
    (is (nil? (split-email "a@b"))))
  (testing "returns a hash of the email's split information"
    (is (= {:top_level_domain "com", :domain "b.com", :address "a"}
           (split-email "a@b.com")))))

(deftest test-find-closest-domain
  (testing "that the expected domain is returned given some erroneous input"
    (let [domains ["yahoo.com" "yahoo.com.tw" "google.com""hotmail.com"
                   "gmail.com" "emaildomain.com" "comcast.net" "facebook.com"
                   "msn.com" "gmx.com"]]
      (are [input expected] (= expected (find-closest-domain input domains))
           "emaildomain.co" "emaildomain.com"
           "gmail.con" "gmail.com"
           "gnail.con" "gmail.com"
           "GNAIL.con" "gmail.com"
           "#gmail.com" "gmail.com"
           "comcast.com" "comcast.net"
           "homail.con" "hotmail.com"
           "hotmail.co" "hotmail.com"
           "fabecook.com" "facebook.com"
           "yajoo.com" "yahoo.com"))))

(deftest test-suggest
  (testing "returns a map of the address, domain, and full email address when there's a suggestion"
    (is (= {:address "user", :domain "hotmail.com", :full "user@hotmail.com"}
           (suggest "user@hotma.com"))))
  (testing "is nil when an imcomplete email is provided"
    (is (nil? (suggest "contact")))
    (is (nil? (suggest "")))
    (is (nil? (suggest "test@")))))
