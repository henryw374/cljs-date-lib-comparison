(ns time-lib-comparison.java-time
  (:require [cljc.java-time.local-date :as date]
            [cljc.java-time.temporal.chrono-unit :as cu]
            [time-lib-comparison.app-main :as app]))

(defn interval-calc [event-date]
  (-> cu/days (cu/between (date/now) (date/parse event-date))))

(defn tomorrow []
  (-> (date/now)
      (date/plus-days 1)))

(app/init interval-calc tomorrow)