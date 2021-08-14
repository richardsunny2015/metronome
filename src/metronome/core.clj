(ns metronome.core
  (:require [metronome
             [sounds :refer [play-beep]]
             [time :as time]]))

(defn beep-future
  "Creates a future that beeps every
   x seconds. Returns future object to 
   be cancelled later."
  [x]
  (future
    (while true
      (Thread/sleep (* 1000 x))
      (play-beep))))

(defn print-countdown
  "Prints out countdown timer."
  [seconds]
  (loop [sec seconds]
    (when (nat-int? sec)
      (Thread/sleep 1000)
      (print (str "\r" (time/seconds->time-str sec)))
      (flush)
      (recur (dec sec)))))

(defn -main
  [& args]
  (let [[time-str frequency] args
        total-seconds (time/parse-time-str time-str)
        interval (time/calculate-interval total-seconds
                                          (Integer/parseInt frequency))
        beep-interval (beep-future interval)]
    (print-countdown total-seconds)
    (future-cancel beep-interval)
    (System/exit 0)))