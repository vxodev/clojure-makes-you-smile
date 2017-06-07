(ns example.core)

(+ 2 2)

(def adder (fn [a b] (+ a b)))

(adder 1 2)
;; => 3
