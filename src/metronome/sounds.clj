(ns metronome.sounds
  (:import [java.io FileInputStream BufferedInputStream]
           [javazoom.jl.player Player]))

(def ^:const beep-file "resources/sounds/beep-02.mp3")

(defn play-beep
  []
  (let [fis (FileInputStream. beep-file)
        bis (BufferedInputStream. fis)
        player (Player. bis)]
    (doto player
      (.play)
      (.close))))