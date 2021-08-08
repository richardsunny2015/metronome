(ns metronome.time
  (:require [clojure.spec.alpha :as s]
            [clojure.spec.test.alpha :as stest]
            [clojure.string :as cstr]))

;; Not sure what these are for yet. I have them jic.
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

(defn parse-time-str
  "Takes a string formatted as `MM:SS`
   and returns the total seconds."
  [time-str]
  (let [[minutes seconds] (->> (cstr/split time-str #":")
                               (map parse-int))]
    (+ (* 60 minutes)
       seconds)))

(defn- time-str?
  [time-str]
  (and (string? time-str)
       (not (nil? (re-matches #"\d\d:\d\d" time-str)))))

(s/def ::seconds number?)
(s/def ::time-str time-str?)

(s/fdef parse-time-str
  :args (s/cat :time-str ::time-str)
  :ret ::seconds)

(stest/instrument `parse-time-str)
