(ns metronome.core
  (:require [metronome
             [sounds :refer [play-beep]]
             [time :as time]]
            [overtone.at-at :refer [every stop mk-pool]]))

(defn beep-periodically
  "Creates a future that beeps every
   x seconds. Returns future object to 
   be cancelled later."
  [x]
  (every (* 1000 x)
         play-beep
         (mk-pool)))

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
  (let [[minutes-str beeps-per-minute] args
        ;; TODO allow time-str as optional argument
        #_#_total-seconds (time/parse-time-str time-str)
        minutes (Integer/parseInt minutes-str)
        total-seconds (* 60 minutes)
        interval (time/calculate-interval total-seconds
                                          (* minutes
                                             (Integer/parseInt beeps-per-minute)))
        beep-interval (beep-periodically interval)]
    (print-countdown total-seconds)
    (stop beep-interval)
    (System/exit 0)))