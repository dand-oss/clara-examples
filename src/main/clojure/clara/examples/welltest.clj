(ns clara.examples.welltest
  (:require [clara.rules :refer :all]))

(defrecord well-test [oil-rate water-rate form-gas-rate
      lift-gas-rate prod-press inj-press choke-diam ])

(defrule positive-oil
  "Find if well has positive oil rate"
  [well-test (> oil-rate 0.0 ) (= ?oil-rate oil-rate)]
  =>
  (println "Oil rate is greater than 0 with value " ?oil-rate ".") )

(defrule negative-oil
  "Find if well has negative oil rate"
  [well-test (< oil-rate 0.0 ) (= ?oil-rate oil-rate)]
  =>
  (println "Oil rate is less than 0 with value " ?oil-rate ".") )

(defrule define-watercut
  "Defines the water cut"
  [well-test (= ?oil-rate oil-rate )(= ?water-rate water-rate )]
  =>
  (println "water cut is " (/ ?water-rate (+ ?oil-rate ?water-rate ))))

( defrule notify-welltest-added
  "Notifies that a well test was added"
  [well-test (= ?oil-rate oil-rate)
             (= ?water-rate water-rate)
             (= ?form-gas-rate form-gas-rate)
             (= ?lift-gas-rate lift-gas-rate)
             (= ?prod-press prod-press)
             (= ?inj-press inj-press)
             (= ?choke-diam choke-diam) ]
  =>
  (println "well test values: \n"
          "oil-rate = " ?oil-rate "\n"
          "water-rate = " ?water-rate "\n"
          "form-gas-rate = " ?form-gas-rate "\n"
          "lift-gas-rate = " ?lift-gas-rate "\n"
          "prod-press = " ?prod-press "\n"
          "inj-press = " ?inj-press "\n"
          "choke-diam = " ?choke-diam ) )

(defn run-examples
  "Function to run test on well test rules."
  []
  (println "well test Rules example 1:")
  ;; prints "checks if 1+1 equals true.  Prints "success!"
(-> (mk-session 'clara.examples.welltest)
    (insert (->well-test 120.0, 594.0, 160.0, 420.0, 100.0, 780.0, 64.0) )
    (insert (->well-test -120.0, 594.0, 160.0, 420.0, 100.0, 780.0, 64.0) )
    (fire-rules))

  nil)
