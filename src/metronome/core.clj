(ns metronome.core
  (:require [metronome.sounds :refer [play-beep]]))

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))

(defn -main
  []
  (play-beep))