(ns clara.examples.lp_rules
  (:require [clara.rules :refer :all]))

(defrecord SupportRequest [client level])

(defrecord ClientRepresentative [name client])

(defrule is-important
  "Find important support requests."
  [SupportRequest (= :high level)]
  =>
  (println "High priority support requested!"))

(defrule is-low
  "Find low priority support requests."
  [SupportRequest (= :low level)]
  =>
  (println "Low prioity support requested!"))

(defrule is-medium
  "Find medium priority support requests."
  [SupportRequest (= :medium level)]
  =>
  (println "Medium prioity support requested!"))

(defrule notify-client-rep
    "Find the client representative and request support."
    [SupportRequest (= ?client client)]
    [ClientRepresentative (= ?client client) (= ?name name)]
      =>
    (println "Notify" ?name "that"
        ?client "has a new support request!"))

(defn run-examples
  "Function to run the above example."
  []
  (println "LP Rules example 1:")
  ;; prints "checks if 1+1 equals true.  Prints "success!"
(-> (mk-session 'clara.examples.lp_rules)
(comment
    (insert (->ClientRepresentative "Alice" "Acme")
            (->SupportRequest "Acme" :high))
    (insert (->ClientRepresentative "Larry" "Acme1")
            (->SupportRequest "Acme1" :low))
    (insert (->ClientRepresentative "Joel" "Acme2")
            (->SupportRequest "Acme2" :medium))
)
    (fire-rules))

  nil)
