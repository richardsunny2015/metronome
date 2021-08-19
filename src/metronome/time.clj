(ns metronome.time
  (:require [clojure.spec.alpha :as s]
            [clojure.spec.test.alpha :as stest]
            [clojure.string :as cstr]))

;; Will be used for calculating time to display
(defn- calculate-seconds
  "Takes a number of seconds and returns the seconds
   left in a minute."
  [seconds]
  (mod seconds 60))

(defn- calculate-minutes
  "Takes a number of seconds and returns the amount of minutes
   you can make with those seconds."
  [seconds]
  (quot seconds 60))

(defn- parse-int
  [int]
  (Integer/parseInt int))

(defn- time-str?
  [time-str]
  (and (string? time-str)
       (not (nil? (re-matches #"\d+:\d+" time-str)))))

;; API

(defn parse-time-str
  "Takes a string formatted as `MM:SS`
   and returns the total seconds."
  [time-str]
  (let [[minutes seconds] (->> (cstr/split time-str #":")
                               (map parse-int))]
    (+ (* 60 minutes)
       seconds)))

(defn seconds->time-str
  [sec]
  (let [minutes (calculate-minutes sec)
        seconds (calculate-seconds sec)]
    (format "%02d:%02d" minutes seconds)))

(defn calculate-interval
  [seconds frequency]
  (/ seconds frequency))

(s/def ::seconds nat-int?)
(s/def ::frequency pos-int?)
(s/def ::interval pos?)
(s/def ::time-str time-str?)

(s/fdef parse-time-str
  :args (s/cat :time-str ::time-str)
  :ret ::seconds)

(s/fdef calculate-interval
  :args (s/cat :seconds ::seconds
               :frequency ::frequency)
  :ret ::seconds)

(s/fdef seconds->time-str
  :args (s/cat :seconds ::seconds)
  :ret ::time-str)

(stest/instrument [`parse-time-str
                   `calculate-interval
                   `seconds->time-str])
