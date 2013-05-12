(ns mailcheck.core
  (:use [mailcheck.email]
        [clojure.string :only (split)]))

(defonce *threshold* 3)

(defonce *domains*
  ["aol.com" "att.net" "comcast.net" "facebook.com" "gmail.com"
   "gmx.com" "google.com" "googlemail.com" "hotmail.co.uk"
   "hotmail.com" "live.com" "mac.com" "mail.com" "me.com" "msn.com"
   "sbcglobal.net" "verizon.net" "yahoo.co.uk" "yahoo.com"])

(defonce *top-level-domains*
  ["co.uk" "com" "edu" "gov" "info" "mil" "net" "org"])

(defn split-email
  [email]
  (when (valid-email? email)
    (let [email-parts (split email #"@")
          domain (second email-parts)
          domain-parts (split domain #"\.")]
      {:top_level_domain (last domain-parts)
       :domain domain
       :email email})))
