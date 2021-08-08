(ns metronome.time
  (:require [clojure.spec.alpha :as s]
            [clojure.spec.test.alpha :as stest]
            [clojure.string :as cstr]))

(defn- parse-int
  [int]
  (Integer/parseInt int))

(defn- time-str?
  [time-str]
  (and (string? time-str)
       (not (nil? (re-matches #"\d\d:\d\d" time-str)))))

;; API

(defn parse-time-str
  "Takes a string formatted as `MM:SS`
   and returns the total seconds."
  [time-str]
  (let [[minutes seconds] (->> (cstr/split time-str #":")
                               (map parse-int))]
    (+ (* 60 minutes)
       seconds)))

(defn calculate-interval
  [seconds frequency]
  (/ seconds frequency))

(s/def ::seconds number?)
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

(stest/instrument `parse-time-str
                  `calculate-interval)
