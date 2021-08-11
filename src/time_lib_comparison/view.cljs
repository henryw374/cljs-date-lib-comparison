(ns time-lib-comparison.view
  (:require [reagent.core :as r]))

(defn app-view [interval-calc tomorrow-str]
  (r/with-let [days-until-event (r/atom nil)
               event-date (r/atom nil)] ;
    [:div
     [:h1 "Countdown Calculator"]
     [:br]
     [:label "Event date"]
     [:input {:type      "date"
              :min       (tomorrow-str)
              :value     @event-date
              :on-change (fn [e] (reset! event-date e.target.value))}]
     [:button {:disabled (not @event-date)
               :on-click (fn []
                           (reset! days-until-event
                             (interval-calc @event-date)))} 
      "Calculate"]
     [:br]
     (when @days-until-event
       [:div 
        [:h2 "There are "
         [:span {:style {:color "red"}} @days-until-event] 
         " days to go until the event"]])]))