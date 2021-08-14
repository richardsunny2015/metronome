(ns metronome.time-test
  (:require [clojure.test :refer :all]
            [metronome.time :as sut]))

(deftest seconds->time-str-test
  (testing "Returns 00:00 when given zero"
    (is (= "00:00" (sut/seconds->time-str 0))))
  (testing "Returns correct time with given seconds"
    (is (= "00:01" (sut/seconds->time-str 1)))
    (is (= "10:00" (sut/seconds->time-str (* 60 10))))
    (is (= "05:50" (sut/seconds->time-str (+ 50
                                             (* 60 5)))))))