(ns mailcheck.core
  (:use [mailcheck.email]
        [mailcheck.levenshtein]
        [clojure.set :only (map-invert)]
        [clojure.string :only (lower-case split)]))

(defonce -threshold- 3)

(defonce -domains-
  ["aol.com" "att.net" "comcast.net" "facebook.com" "gmail.com"
   "gmx.com" "google.com" "googlemail.com" "hotmail.co.uk"
   "hotmail.com" "live.com" "mac.com" "mail.com" "me.com" "msn.com"
   "sbcglobal.net" "verizon.net" "yahoo.co.uk" "yahoo.com"])

(defonce -top-level-domains-
  ["co.uk" "com" "edu" "gov" "info" "mil" "net" "org"])

(defn split-email
  [email]
  (when (valid-email? email)
    (let [email-parts (split email #"@")
          domain (second email-parts)
          domain-parts (split domain #"\.")]
      {:top_level_domain (last domain-parts)
       :domain domain
       :address (first email-parts)})))

(defn find-closest-domain
  [domain domains]
  (let [min-dist 99
        distances (map #(levenshtein-distance (lower-case domain) %) domains)
        matches (zipmap domains distances)
        top-match (->> matches
                       map-invert
                       (into (sorted-map))
                       first)]
    (when (<= (first top-match) min-dist)
      (second top-match))))

(defn suggest
  [email]
  (let [email-parts (split-email email)]
    (when (seq email-parts)
      (let [closest-domain (find-closest-domain (:domain email-parts) -domains-)]
        (when (seq closest-domain)
          {:address (:address email-parts),
           :domain closest-domain,
           :full (str (:address email-parts) "@" closest-domain)})))))
