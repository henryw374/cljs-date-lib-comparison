(ns time-lib-comparison.js-date
  (:require [lambdaisland.deja-fu :as deja-fu]
            [time-lib-comparison.app-main :as app]))

(def millis-per-day (* 1000 60 60 24))

(defn interval-calc [event-date]
  (let [now (deja-fu/local-date)
        event-date (deja-fu/parse-local-date event-date)
        interval-millis (- (deja-fu/epoch-ms event-date)
                          (deja-fu/epoch-ms now))]
    (/ interval-millis millis-per-day)))

(defn tomorrow []
  (-> (deja-fu/local-date)
      (update :days inc)))

(app/init interval-calc tomorrow)