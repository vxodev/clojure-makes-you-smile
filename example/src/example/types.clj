(ns example.types)

;; "Primitives"

(+ 1/2 1/3)

;; Hash (associative)
(def a-record
  {:id          123
   :name        "Vxodev"
   :in-session? true})

(get a-record :name)
;; => "Vxodev"

(:id a-record)
;; => 123

(assoc a-record :in-session? false)
;; => {:id 123, :name "Vxodev", :in-session? false}

