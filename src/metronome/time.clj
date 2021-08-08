(ns metronome.time)

(defn calculate-seconds
  "Takes a number of seconds and returns the seconds
   left in a minute."
  [seconds]
  (mod seconds 60))

(defn calculate-minutes
  "Takes a number of seconds and returns the amount of minutes
   you can make with those seconds."
  [seconds]
  (quot seconds 60))