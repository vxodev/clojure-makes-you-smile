(ns example.core
  (:require [clojure.core.async :as async]
            [quil.core :as q]
            [quil.middlewares.fun-mode :as funmode]))

(defonce state-mailbox (async/chan 3))

(defn setup "Create the initial state" []
  {:background [200 200 200]
   :color [255 128 100]
   :x     150
   :y     150
   :size  10})

(defn calc-next-state
  [{:keys [x y size] :as state}]

  ;; (assoc state :x (inc x))




(defn render
  "Render the state 's'."
  [{:keys [background x y size color]}]

  (apply q/background background)
  (q/ellipse-mode :center)
  (apply q/fill color)
  (q/ellipse x y size size))

(defn next-state
  "Poll the mailbox or use the state from current canvas."
  [s]
  (calc-next-state (or (async/poll! state-mailbox) s)))

(defn restart!
  ([] (restart! (setup)))
  ([s] (async/put! state-mailbox s)))

(defn run-me
  "Display a canvas - hook up functions for drawing."
  []
  (q/sketch
   :features [:keep-on-top]
   :size [300 300]
   :middleware [funmode/fun-mode]
   :update #'next-state
   :draw #'render
   :setup #'setup))
