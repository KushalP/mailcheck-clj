(ns mailcheck.email
  (:import [org.apache.commons.validator.routines EmailValidator]))

(defn valid-email?
  [email]
  (.isValid (EmailValidator/getInstance) email))
