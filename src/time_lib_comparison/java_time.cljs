(ns time-lib-comparison.java-time
  (:require [cljc.java-time.local-date :as date]
            [cljc.java-time.temporal.chrono-unit :as cu]
            [time-lib-comparison.app-main :as app]))

(defn interval-calc [event-date]
  (cu/between cu/days (date/parse event-date) (date/now)))

(defn tomorrow []
  (-> (date/now)
      (date/plus-days 2)
      str))

(app/init interval-calc tomorrow)