(ns example.core
  (:require [clojure.core.async :as async]
            [quil.core :as q]
            [quil.middlewares.fun-mode :as funmode]))

(defprotocol Greeter
  (say-hello [this name] "Say hello!"))

(defrecord Swedish []
  Greeter
  (say-hello [_ n] (format "Hej, %s" n)))

(defrecord English []
  Greeter
  (say-hello [_ n] (format "Hello, %s" n)))

(extend-type java.lang.String
  Greeter
  (say-hello [s n] (format "Um, I'm a string (%s).. Hi, %s" s n)))

(say-hello (->Swedish) "VxoDev")
;; => "Hej, VxoDev"
(say-hello (->English) "VxoDev")
;; => "Hello, VxoDev"
(say-hello "a string" "VxoDev")
;; => "Um, I'm a string (a string).. Hi, VxoDev"


(defmulti get-a-beer :brand)

(defmethod get-a-beer "Oppigård"
  [{:keys [qty]}]
  (format "Great choice - here's %d bottles of fine beer!" qty))

(defmethod get-a-beer "Budweiser"
  [{:keys [qty]}]
  (format "%d bottles of water coming up!" qty))

(defmethod get-a-beer :default
  [_]
  "All out of that stuff, sorry.")

(get-a-beer {:brand "Oppigård" :qty 2})
;; => "Great choice - here's 2 bottles of fine beer!"
(get-a-beer {:brand "Budweiser" :qty 3})
;; => "3 bottles of water coming up!"
(get-a-beer {:brand "Heineken" :qty 15})
;; => "All out of that stuff, sorry."

(def record {:id   12
             :name "Bucket"
             :qty  58})

record
;; => {:id 12, :name "Bucket", :qty 58}

(assoc record :qty 99)
;; => {:id 12, :name "Bucket", :qty 99}

record
;; => {:id 12, :name "Bucket", :qty 58}
